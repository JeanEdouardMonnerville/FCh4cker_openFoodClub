const listProducts = $("#rowgrid");
const validOrderButton = $("#validOrder");
const urlProducts = "http://localhost:8080/api/products";
const urlOrder = "http://localhost:8080/api/order/";

const currentUser = {
  name: "Pepe",
  secondname: "Gimenez",
  email: "pgimenez@tecnocampus.cat",
  password: "12345678",
  role: "USER"
};


validOrderButton.click(function (event) {
  let date = new Date();

  let month = date.getMonth() + 1;
  let currentDate = "" + date.getDate() + "/" + month + "/" + date.getFullYear();

  let products = document.getElementsByClassName("productquantity");
  let newOrderDetails = [];
  for (let p of products) {
    if (p.value > 0) {
      newOrderDetails.push({
        quantity: p.value,
        order: "1",
        product: p.id
      })
    }
  }
  

  let newOrder = {
    order_date: currentDate,
    orderDetail: newOrderDetails,
    user: currentUser
  };
  console.log(newOrder);
  $.post(urlOrder, newOrder);
})

getProducts();

function getProducts() {
  $.get(urlProducts, function (data) {
    console.log(data);
    for (let d of data) {
      insertProduct(d);
    }
  })
}

function insertProduct(Product) {
  let newHtmlText = `
    <div class="col-lg-4 col-md-4 all des">
    <div class="product-item">
      <a href="#"><img src=${Product.image} alt="No Image Available" class="productImages"></a>
      <div class="down-content">
        <a href="#">
          <h4>${Product.name}</h4>
        </a>
        <h6>${Product.price} euros HT</h6>
        <p>
        Category : ${Product.category}
        Measure : ${Product.measure}
        Suppliers : ${Product.suppliers}
        VAT : ${Product.client_tax}
        </p>
        <input type="number" id=${Product.id_product} class="productquantity" value=0>
      </div>
    </div>
  </div>`;
  listProducts.prepend(newHtmlText);
}

