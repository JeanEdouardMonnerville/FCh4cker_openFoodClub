
$(document).ready(function () {
    const urlMe = "http://localhost:8080/api/customers/me";
    $("#submit").click(() => {

        var settings = {
            "url": "http://localhost:8080/login",
            "method": "POST",
            "timeout": 0,
            "data": JSON.stringify({
                "username": $("#inputName").val(),
                "password": $("#inputPassword").val()
            }),
            "success": function (data, textStatus, request) {
                console.log('success: ', request.getAllResponseHeaders());
                window.location.href = "http://localhost:8080/";
            },
            "error": function (request, textStatus, errorThrown) {
                console.log('error: ' + textStatus + ' headers: ' + request.getAllResponseHeaders() + ' ErrorThrown: ' + errorThrown);
            }
        };

        $.ajax(settings).done(function (data, textStatus, request) {
            localStorage.setItem('token', request.getResponseHeader('authorization'))
            console.log('Done Response. Data: ', request.getResponseHeader('authorization'));
        });
        return false;  //this is very important because of click. If not here, the page is reloaded and the ajax callback is never called.
    });

    getCurrentUser();


    function getCurrentUser() {
        $.ajax({
            headers: { 'Authorization': localStorage.getItem('token') },
            url: urlMe,
            async: false,
            type: 'GET',
            dataType: 'json',
            success: function (data) {
                currentUser = data;
                console.log(data);
            },
            error: function () {
               // window.location.href = "http://localhost:8080/login-form-17/login_form.html";
            },

        });
    }
});



