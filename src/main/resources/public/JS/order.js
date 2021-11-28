const order_list = $("#oder_list_id");
const validationButton = $("#modifyMyCurrentOrder");
const urlGetOrder = "http://localhost:8080/api/customers/orders/me";
const urlGetOderForCustomer = "http://localhost:8080/api/customers/orders/";//+orderID
const urlDeleteOrder = "http://localhost:8080/api/orders/delete";//+orderID
const urlUpdateOrder = "http://localhost:8080/api/orders/update";//+orderID
const urlAdminUpdateOrder = "http://localhost:8080/api/orders/admin/update/";
const urlCustomers = "http://localhost:8080/api/customers/all";
const urlMe = "http://localhost:8080/api/customers/me";

//Get Current user 
let currentUser = {};
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
            window.location.href = "http://localhost:8080/login-form-17/login_form.html";
        },

    });
}


// Start function
getOrder();

//Event listener 
validationButton.click(updateOrder);

function getOrder() {
    let roles = [];
    for(let autorities of currentUser.roles){
        roles.push(autorities.role);
    }

    if (roles.includes("ROLE_CUSTOMER") && !roles.includes("ROLE_ADMIN")) {
        let allOrderClose = true;
        $.ajax({
            headers: { 'Authorization': localStorage.getItem('token') },
            url: urlGetOrder,
            contentType: "application/json",
            type: "GET",
            success: function (data) {
                console.log(data);
                for (d of data) {
                    if (d.open) {
                        insertOrderOpen(d);
                        allOrderClose = false;
                    }
                    else {
                        insertOrderClosed(d);
                    }

                }
                if (allOrderClose) {
                    hideValidationButton();
                }
                if (data.length == 0) {
                    order_list.append(`<h1 id = "NoOrderAvailable">You have not order for the moment</h1>`);
                }
            },
            error: function () {
                window.location.href = "http://localhost:8080/login-form-17/login_form.html";
            }
        })
    }
    else if (roles.includes("ROLE_ADMIN")) {
        //Get All User 
        $.ajax({
            headers: { 'Authorization': localStorage.getItem('token') },
            url: urlCustomers,
            contentType: "application/json",
            type: "GET",
            async:false,
            success: function (customers) {
                console.log(customers);
                for (let c of customers) {
                    order_list.append(`<div id="${c.name}_oder_list_id" class="order_customer_container"></div>`);  
                    $("#"+c.name+"_oder_list_id").append(`<h2 class="customer_name_title">${c.name} ${c.secondName} </h2>`);
                    $.ajax({
                        headers: { 'Authorization': localStorage.getItem('token') },
                        url: urlGetOderForCustomer + c.id,
                        contentType: "application/json",
                        type: "GET",
                        async:true,
                        success: function (data) {
                            for (let order of data) {
                                insertAdminOrderOpen(order,c);
                            }
                            if (data.length == 0) {
                                $("#"+c.name+"_oder_list_id").append(`<h1 id = "NoOrderAvailable" class="NoOrderAvailableAdmin">This user has not order for the moment</h1>`);
                            }
                        }
                    })
                }
            }

        })


        //For each user, we display the user and his orders 

    }
}

function insertOrderOpen(order) {
    let htmlText =
        `<li >
<div>
    <img class = "order_product_image" src="data:image/png;base64,${order.product.image}" onerror="if (this.src != 'error.jpg') 
    this.src = 'https://i2.wp.com/germandebonis.com/wp-content/uploads/2016/09/cesta-llena-de-verduras_1112-316.jpg?w=895';">
    </div>
    <div>
<h6 id = ${order.product.id} > ${order.product.name} </h6>


<span id="product_details">
Category : ${order.product.category}<br>
Measure : ${order.product.measure_unit}<br>
Suppliers : ${order.product.provider}<br>
VAT : ${order.product.vat_type}<br>
</span>
</div>

<div>
<input type="number" id = ${order.id} class="productquantity" value=${order.quantity} min="0">
<h4>${order.product.price} euros HT</h4> 
<em>Status : Open </em>
</div>
</li>`
    order_list.append(htmlText);
}

function insertAdminOrderOpen(order,customer) {
    let htmlText =
        `<li >
<div>
    <img class = "order_product_image" src="data:image/png;base64,${order.product.image}" onerror="if (this.src != 'error.jpg') 
    this.src = 'https://i2.wp.com/germandebonis.com/wp-content/uploads/2016/09/cesta-llena-de-verduras_1112-316.jpg?w=895';">
    </div>
    <div>
<h6 id = ${order.product.id}> ${order.product.name} </h6>


<span id="product_details">
Category : ${order.product.category}<br>
Measure : ${order.product.measure_unit}<br>
Suppliers : ${order.product.provider}<br>
VAT : ${order.product.vat_type}<br>
</span>
</div>

<div>
<input type="number" id = ${order.id} class="productquantity" value=${order.quantity} min="0">
<h4>${order.product.price} euros HT</h4> 
<em>Status : Open </em>
</div>
</li>`
    $("#"+customer.name+"_oder_list_id").append(htmlText);
}



function insertOrderClosed(order) {
    let htmlText =
        `<li width=100%>
        <div>
    <img class = "order_product_image" src="data:image/png;base64,${order.product.image}" onerror="if (this.src != 'error.jpg') 
    this.src = 'https://i2.wp.com/germandebonis.com/wp-content/uploads/2016/09/cesta-llena-de-verduras_1112-316.jpg?w=895';">
    </div>
    <div>
    <h6 id = ${order.product.id}> ${order.product.name} </h6>
    
    <span id="product_details">
    Category : ${order.product.category}<br>
    Measure : ${order.product.measure_unit}<br>
    Suppliers : ${order.product.provider}<br>
    VAT : ${order.product.vat_type}<br>
    </span>
    </div>
    <div>
    <em>Quantity : ${order.quantity}</em>
    <h4>${order.product.price} euros HT</h4> 
    <em>Status : Close </em>
    </div>
    </li>`

    order_list.append(htmlText);
}

function hideValidationButton() {
    $("#modifyMyCurrentOrder").css("display", "none");
}

function updateOrder() {
    let products = document.getElementsByClassName("productquantity");

    for (let p of products) {
        let newValue = p.valueAsNumber;
        if (p.valueAsNumber > 0) {
            if (!currentUser.roles.includes("ROLE_ADMIN")) {
                $.ajax({
                    headers: { 'Authorization': localStorage.getItem('token') },
                    type: "POST",
                    url: urlUpdateOrder + `/${p.id}?quantity=${newValue}`,
                    contentType: "application/json"
                });
            }
            else if (currentUser.roles.includes("ROLE_ADMIN")) {
                $.ajax({
                    headers: { 'Authorization': localStorage.getItem('token') },
                    type: "POST",
                    url: urlAdminUpdateOrder + `${p.id}?quantity=${newValue}`,
                    contentType: "application/json"
                });
            }
        }
        if (p.valueAsNumber == 0) {
            $.ajax({
                headers: { 'Authorization': localStorage.getItem('token') },
                type: "GET",
                url: urlDeleteOrder + `/${p.id}`,
                contentType: "application/json",
                success: function () {

                }
            });


        }
    }
    order_list.empty();
    getOrder();
}
