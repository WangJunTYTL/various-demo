$(":submit").bind("click", function () {
    if (this.id == 'test1') {
        $.ajax({
            type: 'GET',
            url: '/userRegist',
            data: $("#regist").serialize(),
            success: function (data) {
                alert(JSON.stringify(data));
            }

        });
    } else if (this.id == 'test2') {
        $.ajax({
            type: 'GET',
            url: '/userRegist2',
            data: $("#regist").serialize(),
            success: function (data) {
                alert(data.province);
            }
        });
    }
});