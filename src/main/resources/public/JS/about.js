const email = $("#InputEmail");
const fullName = $("#InputFullName");

getCurrentUser();

function getCurrentUser() {
    $.ajax({
      headers: { 'Authorization': localStorage.getItem('token') },
      url: "http://localhost:8080/api/customers/me",
      type: 'GET',
      dataType: 'json',
      success: function (data) {
        email[0].innerText = data.email;
        fullName[0].innerText = data.name+" "+data.secondName;
      },
      error: function () {
        window.location.href = "http://localhost:8080/login-form-17/login_form.html";
      },
  
    });
  }