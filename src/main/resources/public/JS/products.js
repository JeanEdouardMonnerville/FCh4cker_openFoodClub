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

  let products = document.getElementsByClassName("productquantity");

  for (let p of products) {
    let newValue = p.valueAsNumber;
    if (p.valueAsNumber > 0) {
      let newId = p.id;
      let user = currentUser.id;
      //$.post(urlSubsription, {customerId:user, productId:newId, quantity:newValue},function (result){},"json");

      $.ajax({
        type: "POST",
        url: urlSubsription + `?customerId=${user}&productId=${newId}&quantity=${newValue}`,
        contentType: "application/json"
      });
    }
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
      <a href="#"><img src=base64,${Product.image} alt="No Image Available" class="productImages"></a>
      <div class="down-content">
        <a href="#">
          <h4>${Product.name}</h4>
        </a>
        <h6>${Product.price} euros HT</h6>
        <p>
        Category : ${Product.category}
        Measure : ${Product.measure_unit}
        Suppliers : ${Product.provider}
        VAT : ${Product.vat_type}
        </p>
        <input type="number" id=${Product.id} class="productquantity" value=0>
      </div>
    </div>
  </div>`;
  listProducts.prepend(newHtmlText);
}

