$( document ).ready(function() {
  // For ajax get cart id//
    var getProductID = [];
    var getProductName = [];
    var getBrand = [];
    var getGender = [];
    var getAge = [];
    var getMaxStock = [];
    var getPrice = [];

    getProductID[0] = '2';
    getProductName[0] = '43 Piece dinner Set';
    getBrand[0] = 'Coolkidz';
    getGender[0] = 'Female';
    getAge[0]  = '3 to 5';
    getMaxStock[0] = '5';
    getPrice[0] = '12.95';

    getProductID[1] = '1';
    getProductName[1] = 'Balance Training Bike';
    getBrand[1] = 'SportsFun';
    getGender[1] = 'Neutral';
    getAge[1]  = '3 to 5';
    getMaxStock[1] = '1';
    getPrice[1] = '119.95';

    var stock_status = 'In stock';
    var drop_down_qty ='';
    for (i = 0; i < getProductID.length; i++) {
        if (getMaxStock[i] <1){
            stock_status ='Out of stock';
        };
        var cart_detail = "<tr>";
        cart_detail += "<th scope='row'>";
        cart_detail += "<div class='product_name'><b>"+getProductName[i]+" </b>&nbsp&nbsp&nbsp by "+getBrand[i]+"</div>";
        cart_detail += "<div class='production_criteria'>Gender : "+getGender[i]+"&nbsp&nbsp&nbsp&nbsp&nbsp&nbspAge : "+getAge[1]+" </div>";
        cart_detail += "<div class='instock'>"+stock_status+"&nbsp&nbsp(available stock : "+getMaxStock[i]+")</div>";
        cart_detail += "<a href=del/'"+getProductID[i]+"'>Delete</a>";
        cart_detail += "</th>";
        cart_detail += "<td>";
        cart_detail += "<select>";
        cart_detail += "<option value='1'>1</option>";
        cart_detail += "<option value='2'>2</option>";
        cart_detail += "<option value='3'>3</option>";
        cart_detail += "<option value='4'>4</option>";
        cart_detail += "</select>";
        cart_detail += "</td>";
        cart_detail += "<td> <span id='price_1'>2,500.10</span></td>";
        cart_detail += "</tr>";
        $( "#cart_detail" ).append(cart_detail);
    }

});