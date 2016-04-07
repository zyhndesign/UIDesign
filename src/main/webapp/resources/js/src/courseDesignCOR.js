var courseCOR=(function(config,functions){
    return{
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
            courseCOR.submitForm(form);
        }
    });
});
