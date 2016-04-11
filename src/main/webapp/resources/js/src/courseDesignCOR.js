var courseDesignCOR=(function(config,functions){
    return{
        addTags:[],
        deleteTags:[],
        submitForm:function(form){
            var me=this;
            functions.showLoading();
            $(form).ajaxSubmit({
                dataType:"json",
                data:{
                    insertTag:me.addTags.join(","),
                    deleteTag:me.deleteTags.join(",")
                },
                success:function(response){
                    if(response.resultCode==200){
                        $().toastmessage("showSuccessToast",config.messages.optSuccess);
                        setTimeout(function(){
                            window.location.href="coursedesign/courseDesignMgr";
                        },3000);
                    }else{
                        functions.ajaxReturnErrorHandler(response.message);
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
        height:300,
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

    $("#tags").on("click",".tag",function(){
        var tag=$(this).val(),
            tag_id=$(this).data("tag-id");

        //存在tag-id,代表是修改的时候原始数据存在的tag，需要加入到deleteTags
        if(tag_id&&$.inArray(tag_id,courseVideoCOR.deleteTags)==-1){
            courseDesignCOR.deleteTags.push(tag);
        }else{

            //如果不是原始存在的tag,删除addTags中的数据
            var index= $.inArray(tag,courseDesignCOR.addTags);
            courseDesignCOR.addTags.splice(index,1);
        }

        $(this).remove();
    });
    $("#tagInput").keydown(function(event){
        if(event.keyCode==13){
            var tag=$(this).val(),
                hasFlag=false;
            $(".tags").each(function(index,el){
                if($(this).text()==tag){
                    hasFlag=true;

                    //中断循环
                    return false;
                }
            });

            if(hasFlag){
                $().toastmessage("showErrorToast",config.messages.tagExist);
            }else{
                $('<span class="tag">'+tag+'</span>').appendTo($("#tags"));
            }

            courseDesignCOR.addTags.push(tag);

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
            teacher:{
                required:true,
                maxlength:32
            },
            create_time:{
                required:true
            },
            course_detail_id:{
                required:true
            },
            abstract:{
                required:true
            }
        },
        messages:{
            title:{
                required:config.validErrors.required,
                maxlength:config.validErrors.maxLength.replace("${max}",32)
            },
            teacher:{
                required:config.validErrors.required,
                maxlength:config.validErrors.maxLength.replace("${max}",32)
            },
            create_time:{
                required:config.validErrors.required
            },
            course_detail_id:{
                required:config.validErrors.required
            },
            abstract:{
                required:config.validErrors.required
            }
        },
        submitHandler:function(form) {
            courseDesignCOR.submitForm(form);
        }
    });
});
