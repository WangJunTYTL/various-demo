$("button[fun='fetch']").click(function(){
    var type = $(this).attr("cascade");
    var id=$("#userId").attr
    var url = "/findUser"
    $.get(url,data,function(data){
        alert(data);
    })
});

$("#cascadeSave").click(function(){
    var data = $("#saveUser").serialize()+"&cascadeType=1";
    var url = $("#saveUser").attr("action");
    $.get(url,data,function(data){
        alert(data);
    })
});