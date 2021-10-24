const listProducts = $("row grid");
const validOrderButton =$("validOrder");
const urlProducts= $("http://localhost:8080/api/Products");
const urlOrder=$("http://localhost:8080/api/Order/");

validOrderButton.click(function(event){
    let date = new Date();

    let currentDate = ""+date.getDate+date.getMonth()+date.getFullYear();

    $.post(urlOrder, {user:'', order_date:currentDate, orderDetail:[]});
})

getProducts();

function getProducts(){
    $.get(urlProducts,function(data){
        for(let d of data ){
            insertProduct(d);
        }
    })
}

function insertProduct(Product){
    let newHtmlText=`
    <div class="col-lg-4 col-md-4 all des">
    <div class="product-item">
      <a href="#"><img src=${Product.image} alt="No Image Available"></a>
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
        <input type="number" id=${Product.id} class="productquantity" value=0>
      </div>
    </div>
  </div>`;
  listProducts.prepend(newHtmlText);
}

