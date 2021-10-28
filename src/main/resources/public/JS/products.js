const listProducts = $("#rowgrid");
const validOrderButton = $("#validOrder");
const urlProducts = "http://localhost:8080/api/products";
const urlSubsription = "http://localhost:8080/api/subscription";

const currentUser = {
  id: "1",
  name: "Pepe",
  secondname: "Gimenez",
  role: "USER"
};


validOrderButton.click(function (event) {
  let date = new Date();

  let month = date.getMonth() + 1;
  let currentDate = "" + date.getDate() + "/" + month + "/" + date.getFullYear();

  let products = document.getElementsByClassName("productquantity");
for(let p of products){
  let newId =  p.id;
  let newValue = p.valueAsNumber;
  let user = currentUser.id;
  //$.post(urlSubsription, {customerId:user, productId:newId, quantity:newValue},function (result){},"json");
$.ajax({
  type:"POST",
  url:urlSubsription,
  data:{customerId:user, productId:newId, quantity:newValue},
  dataType:"json",
  contentType:"application/json"
});
}
})


getProducts();

function getProducts() {
  $.get(urlProducts, function (data) {
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

