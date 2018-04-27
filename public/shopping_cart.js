
	var CART = (function($) {
		return {
			getCart : function() {
			            var cartId = sessionStorage.getItem("cart_id");

			            return $.ajax({
                                        "url" : "rest/cart/"+cartId,
                                        "type" : "GET",
                                        "contentType" : "application/json; charset=utf-8",
                                        "success" : function(response) {
                                                 //alert("success");
                                                 //console.log("success");
                                                 //console.log(response);
                                                 //$.each(response.items, function (index, value) {
                                                 //    console.log(value);
                                                 //});
                                                  //alert(response.items.length);
                                                  CART.render(response);
                                        },
                                        "error" : function(response) {}
                                      });
			},
			addToCart : function(){
                                var cartId = sessionStorage.getItem("cart_id");
                                if (cartId == null){
                                    sessionStorage.setItem('cart_id', 'empty');
                                    cartId = sessionStorage.getItem("cart_id");
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
                					        //console.log(response);
                                            //alert('success');
                                            sessionStorage.setItem('cartId', response.id);
                					},
                					"error" : function(response) {

                					}
                				});
			},
			render : function(items){
                   //alert('render');
                   $( "#cart_detail" ).empty();
                    //$.each(items, function (key, item) {
                    //    console.log(item);
                    //});
                    var stock_status = '';
                    var drop_down_qty ='';
                    var Subtotal = 0;
                    for (var i = 0; i < items.length; i++) {
                        //console.log("item[i]");
                        //console.log(items[i]);
                        Subtotal = Subtotal + items[i].price;
                        if (items[i].stock_qty <1){
                            stock_status = "<div class='outstock'>Out of stock";
                            cboQtySts = 'disable';
                        }else{
                            stock_status = "<div class='instock'>In stock";
                        };
                        var cart_detail = "<tr>";
                        cart_detail += "<th scope='row'>";
                        cart_detail += "<div class='product_name'><b>"+items[i].product_name+" </b>&nbsp&nbsp&nbsp by "+items[i].brand_name+"</div>";
                        cart_detail += "<div class='production_criteria'>Gender : "+items[i].gender_name+"&nbsp&nbsp&nbsp&nbsp&nbsp&nbspAge : "+items[i].age_name+" </div>";
                        cart_detail += stock_status+"&nbsp&nbsp(available stock : "+items[i].stock_qty+")</div>";
                        cart_detail += "<a href=del/'"+items[i].product_id+"' data-toggle='modal' data-target='#exampleModal'><i class='material-icons'>delete</i></a>";
                        cart_detail += "</th>";
                        cart_detail += "<td>";
                        cart_detail += "<select id='"+items[i].product_id+"' class='selectQty' "+items[i].qty+">";
                        for(max_stk = 1; max_stk <= items[i].stock_qty; max_stk++ ){
                            if(max_stk == items[i].qty){
                                cart_detail += "<option value='"+max_stk+"' selected>"+max_stk+"</option>";
                            }else{
                                cart_detail += "<option value='"+max_stk+"'>"+max_stk+"</option>";
                            }
                        };
                        cart_detail += "</select>";
                        cart_detail += "</td>";
                        cart_detail += "<td> <span product-price='"+items[i].price+"'>"+items[i].price+"</span></td>";
                        cart_detail += "<td> <span class='price' product-price='"+items[i].price*items[i].qty+"'>"+items[i].price*items[i].qty+"</span></td>";
                        cart_detail += "</tr>";
                        $( "#cart_detail" ).append(cart_detail);
                    }

                    Subtotal = 0;
                    $( "#cart_table .price" ).each(function( index ) {
                      console.log( index + ": " + $( this ).text() );
                      Subtotal += parseFloat($( this ).text());
                    });
                    $("#subtotal").text(Subtotal);
                    $("#itemtotal").text("Subtotal (" + ($('#cart_table tr').length-1) + " items) :");
			}

		}
	}(jQuery));
$( document ).ready(function() {

  CART.getCart();

  $('#confirm_button').on('click',function(event) {
        window.open("shipping.html","_self");
  });




  // For ajax get cart id//
/*
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
*/
    $(".selectQty").change(function() {
      var selectQty = this.value;
      var productID = this.id;
      var className = ".price"+productID;
      var productPrice = $(className).attr("product-price");
      var amountPrice = selectQty*productPrice;
      $(className).text(amountPrice.toFixed(2));


    });

});