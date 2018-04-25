
    var toy_name = "43 Peices Dinner Set";
    var toy_qty  = "1";
    var toy_qty_stock = "10";
    var label_unit = "item";
    var toy_brand = "CoolKidz";
    var toy_gender = "Female";
    var toy_age = "3-5";
    var toy_price =12.95;
    var toy_shipping ="Cash On Delivery";
    var toy_status ="In Stock"+" ( "+toy_qty_stock+" )";
    var toy_total_amt = toy_price*toy_qty;

    $("#toy_qty").attr("max", toy_qty_stock);
    $( "#toy_name" ).val(toy_name);
    $( "#toy_brand" ).val(toy_brand);
    $( "#toy_gender" ).val(toy_gender);
    $( "#toy_age" ).val(toy_age);
    $( "#toy_price" ).val(toy_price.toFixed(2));
    $( "#toy_shipping" ).val(toy_shipping);
    $( "#toy_status" ).val(toy_status);
    $( "#toy_qty" ).val(toy_qty);
    $( "#toy_total" ).val(toy_name +" x " +toy_qty +" "+ label_unit);
    $( "#toy_total_price" ).val(toy_price +" x " +toy_qty + " = " + toy_total_amt.toFixed(2)+"฿");


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



    $( "#btn_add_cart" ).click(function() {
    $( "#toy_in_cart" ).text("1");
    });//end change qty