$(document).ready(function(){

    if (cartId == null){
        alert("Session Time Out !!!");
        window.open("index.html","_self");
    }

});

$("#confirm").click(function(){
    alert('dddddd');
    var fullname = $("#fullname").val();
    var address1 = $("#address1").val();
    var address2 = $("#address2").val();
    var city = $("#city").val();
    var province = $("#province").val();
    var postcode = $("#post_code").val();
    var email = $("#post_code").val();
    var cartId = sessionStorage.getItem("cart_id");

    var data = {
                            "fullname" : fullname,
                            "address1" : address1,
                            "address2" : address2,
                            "city" : city,
                            "province" : province,
                            "postcode" : postcode,
                            "email" : email,
                        }
    return $.ajax({
        "url" : "/rest/orderH/",
        "type" : "POST",
        "contentType" : "application/json; charset=utf-8",
        "data" : JSON.stringify(data),
        "success" : function(response) {
                alert(response.id)
        },
        "error" : function(response) {

        }
    });

});
