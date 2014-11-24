/**
 *
 * 校验必填表单:  调用方式: $("form").validForm()
 * @returns {boolean}
 */
$.fn.validForm = function () {

    var inputs = $(this).find("input");
    for (var i = 0; i < inputs.length; i++) {
        if ($(inputs[i]).attr("required") == "required" && $(inputs[i]).attr("type") != "checkbox") {
            if ($(inputs[i]).val() == null || $(inputs[i]).val() == "") {
                $(inputs[i]).focus();
                $(inputs[i]).attr("placeholder", "必填");
                return false;
            }
        }
    }

    var textareas = $(this).find("textarea");
    for (var i = 0; i < textareas.length; i++) {
        if ($(textareas[i]).attr("required") == "required") {
            if ($(textareas[i]).val() == null || $(textareas[i]).val() == "") {
                $(textareas[i]).focus();
                return false;
            }
        }
    }

    var checkboxs = $(this).find("input:checkbox");
    for (var i = 0; i < checkboxs.length; i++) {
        if ($(checkboxs[i]).attr("required") == "required") {
            var cName = $(checkboxs[i]).attr("name");
            if ($("input:checkbox:checked[name='" + cName + "']").length == 0) {
                alert("还有必选复选框未选择");
                $(checkboxs[i]).focus();
                return false;
            }
        }
    }
    return true;
}

if (undefined != window.mis_data && window.mis_data != null) {
    var MyValidator = function () {
        var handleSubmit = function () {
            $('form').validate({
                errorElement: 'span',
                errorClass: 'help-block',
                focusInvalid: false,
                rules: mis_data.rules,
                messages: mis_data.messages,
                highlight: function (element) {
                    $(element).closest('.form-group').addClass('has-error');
                },

                success: function (label) {
                    label.closest('.form-group').removeClass('has-error');
                    label.remove();
                },

                errorPlacement: function (error, element) {
                    element.parent('div').append(error);
                },

                submitHandler: function () {
                    var flag = true;
                    if (mis_data.postFront != null || mis_data.postFront != undefined) {
                        flag = mis_data.postFront();
                    }
                    if (flag) {
                        var data = $("form").serialize();
                        $.ajax({
                            type: "POST",
                            url: mis_data.url,
                            data: data,
                            dataType: "json",
                            success: function (data) {
                                if (data.code == 1) {
                                    alert(data.result);
                                    document.location.href = mis_data.nextUrl;
                                } else {
                                    alert(data.result);
                                }
                            }

                        });
                    }
                }
            });


            $('form input').keypress(function (e) {
                if (e.which == 13) {
                    if ($('form').validate().form()) {
                        return true;
                    }
                    return false;
                }
            });
        }
        return {
            init: function () {
                handleSubmit();
            }
        };

    }
    ();
    MyValidator.init();

}

