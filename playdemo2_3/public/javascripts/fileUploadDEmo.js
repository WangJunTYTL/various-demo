$("#ajaxUpload").bind("click", function () {
        $.ajax({
            type: 'POST',
            url: '/ajaxUpload',
            data: $("#ajaxUploadForm").serialize(),
            success: function (data) {
                alert(JSON.stringify(data));
            }

        });

});