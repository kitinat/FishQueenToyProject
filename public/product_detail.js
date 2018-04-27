var toy_qty_stock = 0;
var DETAILS = (function($) {
		return {
			addToCart : function(){
                var cartId = sessionStorage.getItem("cart_id");
                if (cartId == null){
                    sessionStorage.setItem('cart_id', 'empty');
                    cartId = sessionStorage.getItem("cart_id");
                }
//                alert(toy_qty_stock);
                var data = {
                                        "product_id" : sessionStorage.getItem("sent_pid"),
                                        "product_name" : $("#toy_name").val(),
                                        "brand_name" : $("#toy_brand").val(),
                                        "age_name" : $("#toy_age").val(),
                                        "price" : $( "#toy_price").val(),
                                        "availability" : $("#toy_status").val(),
                                        "qty" : $("#toy_qty").val(),
                                        "gender_name" : $( "#toy_gender" ).val(),
                                        "stock_qty" : toy_qty_stock,
                                    }
                return $.ajax({
                    "url" : "/rest/cart/"+cartId,
                    "type" : "POST",
                    "contentType" : "application/json; charset=utf-8",
                    "data" : JSON.stringify(data),
                    "success" : function(response) {
                            console.log(response);
                            //alert("Success");
                            sessionStorage.setItem('cart_id', response.id);
                            //alert(response.id);
                            window.open("shopping_cart.html","_self");
                    },
                    "error" : function(response) {

                    }
                });
			},

		}
	}(jQuery));

    $( document ).ready(function() {

            var toy_name = "";
            var toy_qty  = 0;

            var label_unit = "";
            var toy_brand = "";
            var toy_gender = "";
            var toy_age = "";
            var toy_price = 0;
            var toy_shipping ="";
            var toy_status ="";
            var toy_total_amt = 0;
            var a = sessionStorage.getItem("sent_pid");
            var cart_id = "";

            $.getJSON("rest/product/"+a, function (data) {

                    toy_name = data.product_name;
                    toy_qty  = "1";
                    toy_qty_stock = data.qty;
                    label_unit = "item";
                    toy_brand = data.brand_name;
                    toy_gender = data.gender_name;
                    toy_age = data.age_name;
                    toy_price =data.price;
                    toy_shipping ="Cash On Delivery";
                    toy_status =data.availability+" ( "+toy_qty_stock+" )";
                    toy_total_amt = toy_price*toy_qty;

                    $("#toy_name").val(toy_name);
                    $("#toy_qty").attr("max", toy_qty_stock);
                    $( "#toy_brand" ).val(toy_brand);
                    $( "#toy_gender" ).val(toy_gender);
                    $( "#toy_age" ).val(toy_age);
                    $( "#toy_price" ).val(toy_price.toFixed(2));
                    $( "#toy_shipping" ).val(toy_shipping);
                    $( "#toy_status" ).val(toy_status);
                    $( "#toy_qty" ).val(toy_qty);
                    $( "#toy_total" ).val(toy_name +" x " +toy_qty +" "+ label_unit);
                    $( "#toy_total_price" ).val(toy_price +" x " +toy_qty + " = " + toy_total_amt.toFixed(2)+"฿");

                    $("#product_img").attr("src","img/"+a+".png");

                     if(toy_qty_stock <1 ){
                        $("#toy_status" ).val("Out Stock");
                        $("#toy_qty").attr("disabled", true);
                        $("#btn_add_cart").attr("disabled", true);
                        $("#toy_total").hide();
                        $("#toy_total_price").hide();
                        }

                        $( "#toy_qty" ).change(function() {
                        if($( "#toy_qty" ).val()%1 != 0){
                        $( "#toy_qty" ).val(Math.ceil($( "#toy_qty" ).val()));
                        }
                        if($( "#toy_qty" ).val()>=1){
                        $("#btn_add_cart").attr("disabled", false);
                        }
                        if($( "#toy_qty" ).val()<=0){
                        $( "#toy_qty" ).val(1);
                        }

                        if($( "#toy_qty" ).val()>1){
                        label_unit = "items";
                        }else{
                        label_unit = "item";
                        }

                        toy_qty = $( "#toy_qty" ).val();
                        toy_total_amt = toy_price*$( "#toy_qty" ).val();
                        $( "#toy_total" ).val(toy_name +" x " +toy_qty +" "+ label_unit);
                        $( "#toy_total_price" ).val(toy_price +" x " +toy_qty + " = " + toy_total_amt.toFixed(2)+"฿");
                        });//end change qty

                        $("#toy_qty").bind('keyup mouseup', function () {
                        toy_qty = $( "#toy_qty" ).val();
                        toy_total_amt = toy_price*$( "#toy_qty" ).val();
                        $( "#toy_total" ).val(toy_name +" x " +toy_qty +" "+ label_unit);
                        $( "#toy_total_price" ).val(toy_price +" x " +toy_qty + " = " + toy_total_amt.toFixed(2)+"฿");
                        });



                        $("#btn_add_cart" ).click(function() {
                            DETAILS.addToCart();
                        //$( "#toy_in_cart" ).text("1");
                        });//end change qty


                        /*$("#confirm").click(function(){
                            alert ($("#fullname").val()+""+$("#email").val());
                            $.ajax({
                                type: 'POST',
                                url: '("/cart/{id}")',
                                data: $("#email").val(),
                                success: function(data) { alert('data: ' + data); },
                                contentType: "application/json",
                                dataType: 'json'
                            });
                            window.open("thankyou.html","_self");
                        });*/



            });


        });






