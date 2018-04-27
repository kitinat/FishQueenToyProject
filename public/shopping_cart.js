
	var CART = (function($) {
		return {
			getCart : function() {
			            var cartId = sessionStorage.getItem("cartId");
                        $.getJSON("rest/cart/"+cartId, function (data) {
                                                return data;
                                           });
			},
			addToCart : function(){
                                var cartId = sessionStorage.getItem("cartId");
                                if (cartId == null){
                                    sessionStorage.setItem('cartId', 'empty');
                                    cartId = sessionStorage.getItem("cartId");
                                }

			                    var data = {
                                						"product_id" : "2",
                                						"product_name" : "43 Piece dinner Set",
                                						"brand_name" : "CoolKidz",
                                						"age_name" : "3_to_5",
                                						"price" : 0,
                                						"availability" : "",
                                						"qty" : 10

                                					}
			    				return $.ajax({
                					"url" : "/rest/cart/"+cartId,
                					"type" : "POST",
                					"contentType" : "application/json; charset=utf-8",
                					"data" : JSON.stringify(data),
                					"success" : function(response) {
                					        console.log(response);
                                            alert('success');
                					},
                					"error" : function(response) {

                					}
                				});
			},

		}
	}(jQuery));
$( document ).ready(function() {

  CART.addToCart();


  // For ajax get cart id//
    var getProductID = [];
    var getProductName = [];
    var getBrand = [];
    var getGender = [];
    var getAge = [];
    var getMaxStock = [];
    var getPrice = [];
    var cboQtySts = '';

    getProductID[0] = '2';
    getProductName[0] = '43 Piece dinner Set';
    getBrand[0] = 'Coolkidz';
    getGender[0] = 'Female';
    getAge[0]  = '3 to 5';
    getMaxStock[0] = '5';
    getPrice[0] = 12.95;

    getProductID[1] = '1';
    getProductName[1] = 'Balance Training Bike';
    getBrand[1] = 'SportsFun';
    getGender[1] = 'Neutral';
    getAge[1]  = '3 to 5';
    getMaxStock[1] = '0';
    getPrice[1] = 119.95;

    getProductID[2] = '23';
    getProductName[2] = 'Dancing Alligator';
    getBrand[2] = 'Coolkidz';
    getGender[2] = 'Neutral';
    getAge[2]  = 'Baby';
    getMaxStock[2] = '25';
    getPrice[2] = 19.95;

    var stock_status = '';
    var drop_down_qty ='';
    var Subtotal = 0;
    for (var i = 0; i < getProductID.length; i++) {
        Subtotal = Subtotal + getPrice[i];
        if (getMaxStock[i] <1){
            stock_status = "<div class='outstock'>Out of stock";
            cboQtySts = 'disable';
        }else{
            stock_status = "<div class='instock'>In stock";
        };
        var cart_detail = "<tr>";
        cart_detail += "<th scope='row'>";
        cart_detail += "<div class='product_name'><b>"+getProductName[i]+" </b>&nbsp&nbsp&nbsp by "+getBrand[i]+"</div>";
        cart_detail += "<div class='production_criteria'>Gender : "+getGender[i]+"&nbsp&nbsp&nbsp&nbsp&nbsp&nbspAge : "+getAge[1]+" </div>";
        cart_detail += stock_status+"&nbsp&nbsp(available stock : "+getMaxStock[i]+")</div>";
        cart_detail += "<a href=del/'"+getProductID[i]+"' data-toggle='modal' data-target='#exampleModal'><i class='material-icons'>delete</i></a>";
        cart_detail += "</th>";
        cart_detail += "<td>";
        cart_detail += "<select id='"+getProductID[i]+"' class='selectQty' "+cboQtySts+">";
        for(max_stk = 1; max_stk <= getMaxStock[i]; max_stk++ ){
            cart_detail += "<option value='"+max_stk+"'>"+max_stk+"</option>";
        };
        cart_detail += "</select>";
        cart_detail += "</td>";
        cart_detail += "<td> <span product-price='"+getPrice[i]+"'>"+getPrice[i]+"</span></td>";
        cart_detail += "<td> <span class='price"+getProductID[i]+"' product-price='"+getPrice[i]+"'>"+getPrice[i]+"</span></td>";
        cart_detail += "</tr>";
        $( "#cart_detail" ).append(cart_detail);
    }

    $("#subtotal").text(Subtotal);

    $(".selectQty").change(function() {
      var selectQty = this.value;
      var productID = this.id;
      var className = ".price"+productID;
      var productPrice = $(className).attr("product-price");
      var amountPrice = selectQty*productPrice;
      $(className).text(amountPrice.toFixed(2));


    });

});