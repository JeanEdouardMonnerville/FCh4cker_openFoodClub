const listProducts = $("#rowgrid");
const urlProducts = "http://localhost:8080/api/products";
const urlSubsription = "http://localhost:8080/api/subscription";
const urlLogin = "http://localhost:8080/login-form-17/login_form.html";
const urlCustomerDetails = "http://localhost/about.html";
const urlMeSubscription = "http://localhost:8080/api/subscriptions/me";

let currentUser = {};

/*  Get the current User*/
getCurrentUser();

function getCurrentUser() {
  $.ajax({
    headers: { 'Authorization': localStorage.getItem('token') },
    url: "http://localhost:8080/api/customers/me",
    type: 'GET',
    dataType: 'json',
    success: function (data) {
      currentUser = data;
      console.log(data);
    },
    error: function () {
      window.location.href = urlLogin;
    },

  });
}


function validSubscriptionButtonFunction(event) {

  let products = document.getElementsByClassName("productquantity");

  for (let p of products) {
    let newValue = p.valueAsNumber;
    if (p.valueAsNumber > 0) {
      let newId = p.id;
      let user = currentUser.id;
      //$.post(urlSubsription, {customerId:user, productId:newId, quantity:newValue},function (result){},"json");

      $.ajax({
        headers: { 'Authorization': localStorage.getItem('token') },
        type: "POST",
        url: urlSubsription + `?customerId=${user}&productId=${newId}&quantity=${newValue}`,
        contentType: "application/json",
        success : function(){
          window.location.href = urlCustomerDetails;
        }
      });
    }
  }
}



getProducts();

function getProducts() {
  $.ajax({
    headers: { 'Authorization': localStorage.getItem('token') },
    url: urlProducts,
    type: "GET",
    contentType: "application/json",
    success: function (data) {
      for (let d of data) {
        insertProduct(d);
      }
      insertButton();
      //  insertCopyright();
    },
    error: function () {
      window.location.href = urlLogin;
    }
  })
}


function insertProduct(Product) {
  let newHtmlText = `
    <div class="col-lg-4 col-md-4 all des">
    <div class="product-item">
      <a href="#"><img class="productImages" src="data:image/png;base64,${Product.image}" 
      onerror="if (this.src != 'error.jpg') this.src = 'https://i2.wp.com/germandebonis.com/wp-content/uploads/2016/09/cesta-llena-de-verduras_1112-316.jpg?w=895';">
      </a>
      <div class="down-content">
        <br>
        <h6>${Product.price} euros HT</h6>
        <a href="#">
        <h4 class="ProductName" >${Product.name}</h4>
        </a>
        <p>
        Category : ${Product.category}<br>
        Measure : ${Product.measure_unit}<br>
        Suppliers : ${Product.provider}<br>
        VAT : ${Product.vat_type}<br>
        </p>
        <input type="number" id=${Product.id} class="productquantity" value=0 min=0>
      </div>
    </div>
  </div>`;
  listProducts.prepend(newHtmlText);
}

function insertButton() {
  let textHtml = `        <div class="col-md-12">
  <input type="button" value="Register my Subscription" id="validOrder" class="validationButtons">
</div>`;
  $("#rowgrid").prepend(textHtml);

  $("#validOrder").click(validSubscriptionButtonFunction);
}

function insertCopyright() {
  let textHtml = `<footer id="copyrightFooter">
  <div class="container">
    <div class="row">
      <div class="col-md-12">
        <div class="inner-content">
          <p>Copyright &copy; 2021 H4CKERS' Co.

            - Design: <a rel="nofollow noopener" href="" target="_blank">TemplateMo</a></p>
        </div>
      </div>
    </div>
  </div>
</footer>`;
  $("#bodyProductPage").append(textHtml);
  //$("#productFiltercontent").append(textHtml);
}
//TEST
function getSubscription(){
  $.ajax({
    headers: { 'Authorization': localStorage.getItem('token') },
    url: urlMeSubscription,
    type: "GET",
    contentType: "application/json",
    success: function (data) {
      console.log(data);
    },

  })
}