var courseVideoCOR=(function(config,functions){
    return{
        deleteTags:[],
        submitForm:function(form){
            var me=this;
            functions.showLoading();
            $(form).ajaxSubmit({
                dataType:"json",
                success:function(response){
                    if(response.success){
                        $().toastmessage("showSuccessToast",config.messages.optSuccess);
                        setTimeout(function(){
                            window.location.href="course/mgr";
                        },3000);
                    }else{
                        functions.ajaxReturnErrorHandler(response.error_code);
                    }
                },
                error:function(){
                    functions.ajaxErrorHandler();
                }
            });
        }
    }
})(config,functions);

$(document).ready(function(){
    tinymce.init({
        selector: "#abstract",
        height:100,
        toolbar1: 'insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image',
        toolbar2: 'print preview media | forecolor backcolor emoticons',
        //image_advtab: true,
        plugins : 'link image preview fullscreen table textcolor colorpicker code',
        setup: function (ed) {
            ed.on('blur', function (e) {
                $("#abstract").val(ed.getContent());
            });
        }
    });
    tinymce.init({
        selector: "#content",
        height:300,
        toolbar1: 'insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image',
        toolbar2: 'print preview media | forecolor backcolor emoticons',
        //image_advtab: true,
        plugins : 'link image preview fullscreen table textcolor colorpicker code',
        setup: function (ed) {
            ed.on('blur', function (e) {
                $("#content").val(ed.getContent());
            });
        }
    });
    functions.createQiNiuUploader({
        maxSize:config.uploader.sizes.img,
        filter:config.uploader.filters.img,
        uploadBtn:"uploadBtn",
        multiSelection:false,
        multipartParams:null,
        uploadContainer:"uploadContainer",
        fileAddCb:null,
        progressCb:null,
        uploadedCb:function(info,file,up){
            //后台的up-token里面要注明返回图片信息
            if(info.w==500&&info.h==500){
                $("#imageUrl").val(info.url);

                $("#image").attr("src",info.url);

                $(".error[for='imageUrl']").remove();
            }else{
                $().toastmessage("showErrorToast",config.messages.imageSizeError);
            }
        }
    });

    $("#tags").on("click",".tags",function(){
        var tag=$(this).text();
        //去重
        if($.inArray(tag,courseVideoCOR.deleteTags)==-1){
            courseVideoCOR.deleteTags.push(tag);
        }

        $(this).remove();
    });
    $("#tagInput").keydown(function(event){
        if(event.keyCode==13){
            var value=$(this).val(),
                hasFlag=false;
            $(".tags").each(function(index,el){
                if($(this).text()==value){
                    hasFlag=true;
                    return false;
                }
            });
            if(hasFlag){
                $().toastmessage("showErrorToast","该标签已经添加过！");
            }else{
                $('<span class="tags">'+value+'</span>').appendTo($("#tags"));
            }

            $(this).val("");
        }
    });


    $("#myForm").validate({
        ignore:[],
        rules:{
            title:{
                required:true,
                maxlength:32
            },
            abstract:{
                required:true
            },
            duration:{
                required:true
            },
            thumbnail:{
                required:true
            },
            content:{
                required:true
            }
        },
        messages:{
            title:{
                required:config.validErrors.required,
                maxlength:config.validErrors.maxLength.replace("${max}",32)
            },
            abstract:{
                required:config.validErrors.required
            },
            duration:{
                required:config.validErrors.required
            },
            thumbnail:{
                required:config.validErrors.required
            },
            content:{
                required:config.validErrors.required
            }
        },
        submitHandler:function(form) {
            courseVideoCOR.submitForm(form);
        }
    });
});
