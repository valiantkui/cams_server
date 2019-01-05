//alert("checkClassSession");
$.ajaxSetup({
    xhrFields: {
        //允许带上凭据
        withCredentials: true
    },
    crossDomain: true,
});
var classInfo = null;

    $.ajax({
        url: "/login/checkClassSession",
        dataType:"text",
        async: false,
        success:function(result){
            if(result == "") {
                window.location="SignInUp.html";
            }
            else {

                classInfo = $.parseJSON(result);
                $("#classInfo").text(classInfo.name+"已登陆");
            }
        },
        error:function (e) {
            window.location="SignInUp.html";
        }

    });
