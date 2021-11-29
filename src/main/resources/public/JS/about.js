const email = $("#InputEmail");
const fullName = $("#InputFullName");
let currentUser = {};
const subscriptions = $("#subscriptionsContainer");

getCurrentUser();

/*
 PRINT data of the customer in the web page
 */
function getCurrentUser() {
    $.ajax({
        headers: {'Authorization': localStorage.getItem('token')},
        url: "http://localhost:8080/api/customers/me",
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            currentUser = data;
            email[0].innerText = data.email;
            fullName[0].innerText = data.name + " " + data.secondName;
        },
        error: function () {
            window.location.href = "http://localhost:8080/login-form-17/login_form.html";
        },
    });
}

// display all my subscriptions
function getSubscription() {
    $.ajax({
        headers: {'Authorization': localStorage.getItem('token')},
        url: "http://localhost:8080/api/subscriptions/me",
        type: "GET",
        contentType: "application/json",
        success: function (data) {
            console.log(data);
            for (let d of data) {
                insertSubscription(d);
            }
            ;
        },
    })
}
getSubscription();

function insertSubscription(subscription) {
    let textHtml = `
<div id="subscriptionContainer">

    <div id="dateSusbription>
      ${subscription.sub_date}
    </div>
    <div id = "imageSubscription">
        <img class="productImages" src="data:image/png;base64,${subscription.product.image}" 
      onerror="if (this.src != 'error.jpg') this.src = 'https://i2.wp.com/germandebonis.com/wp-content/uploads/2016/09/cesta-llena-de-verduras_1112-316.jpg?w=895';">    
    </div>
    <div id="nameSubscription">
        ${subscription.product.name}
    </div>
    <div id="quantitySubscription">
    ${subscription.quantity}
    </div>

</div>`;
    console.log(textHtml);
    subscriptions.append(textHtml);
}