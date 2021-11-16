const order_list = $("#oder_list_id");
const validationButton = $("#modifyMyCurrentOrder");
const urlGetOrder = "http://localhost:8080/api/customers/orders";//+customerID
const urlDeleteOrder = "http://localhost:8080/api/orders/delete";//+orderID
const urlUpdateOrder = "http://localhost:8080/api/orders/update";//+orderID



const currentUser = {
    id: "1",
    name: "Pepe",
    secondname: "Gimenez",
    role: "USER"
};

// Start function
getOrder();

//Event listener 
validationButton.click(updateOrder);

function getOrder() {
    $.get(urlGetOrder + "/" + currentUser.id, function (data) {
        for (d of data) {
            if (d.open) {
                insertOrderOpen(d);
            }
            else {
                insertOrderClosed(d);
                hideValidationButton();
            }

        }
        if (data.length == 0) {
            order_list.append(`<h1 id = "NoOrderAvailable">You have not order for the moment</h1>`);
        }
    })
}

function insertOrderOpen(order) {
    let htmlText =
        `<li >
    <div>
        <img class = "order_product_image" src="data:image/png;base64,${order.product.image} onerror="if (this.src != 'error.jpg') 
        this.src = 'https://i2.wp.com/germandebonis.com/wp-content/uploads/2016/09/cesta-llena-de-verduras_1112-316.jpg?w=895';">
    
    <h6 id = ${order.product.id}> ${order.product.name} </h6>
    <h4>${order.product.price} euros HT</h4> 
    </div>
    <div>
    <span id="product_details">
    Category : ${order.product.category}<br>
    Measure : ${order.product.measure_unit}<br>
    Suppliers : ${order.product.provider}<br>
    VAT : ${order.product.vat_type}<br>
    </span>
    </div>
    <div>
    <input type="number" id = ${order.id} class="productquantity" value=${order.quantity} >
    </div>
    </li>`
    order_list.append(htmlText);
}

function insertOrderClosed(order) {
    let htmlText =
        `<li >

    <img class = "order_product_image" src="data:image/png;base64,${order.product.image} onerror="if (this.src != 'error.jpg') 
    this.src = 'https://i2.wp.com/germandebonis.com/wp-content/uploads/2016/09/cesta-llena-de-verduras_1112-316.jpg?w=895';">

    <h6 id = ${order.product.id}> ${order.product.name} </h6>
    <h4>${order.product.price}</h4> 
    <span id="product_details">
    Category : ${order.product.category}<br>
    Measure : ${order.product.measure_unit}<br>
    Suppliers : ${order.product.provider}<br>
    VAT : ${order.product.vat_type}<br>
    </span>

    <em>${order.quantity}</em>
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
            let user = currentUser.id;

            $.ajax({
                type: "POST",
                url: urlUpdateOrder + `/${p.id}?quantity=${newValue}`,
                contentType: "application/json"
            });
        }
        if (p.valueAsNumber == 0) {
            $.ajax({
                type: "GET",
                url: urlDeleteOrder + `/${p.id}`,
                contentType: "application/json"
            });

            order_list.empty();
            getOrder();
        }
    }
}
