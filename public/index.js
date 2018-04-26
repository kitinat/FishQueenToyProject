

    function getAge(){
                // EXTRACT JSON DATA.
                $.getJSON("rest/age", function (data) {
                    $.each(data, function (index, value) {
                        // APPEND OR INSERT DATA TO SELECT ELEMENT.
                        $('#age').append('<option value="' + value.id + '">' + value.age_name + '</option>');
                    });
                });
    }

    function getGender(){
                // EXTRACT JSON DATA.
                $.getJSON("rest/gender", function (data) {
                    $.each(data, function (index, value) {
                        // APPEND OR INSERT DATA TO SELECT ELEMENT.
                        $('#gender').append('<option value="' + value.id + '">' + value.gender_name + '</option>');
                    });
                });
    }
    function getProductInit(){
                //renderProduct(10,1,1,1,1);
                var age = "all";
                var gender = "all";
                $.getJSON("rest/product/"+age+"/"+gender, function (data) {
                $( "#searchResult" ).text(data.length+" Toy Found");
                $.each(data, function (index, value) {
                renderProduct(data.length,value.id,value.product_name,value.price,value.availability);
                });


                });


    }
    function getProduct(age,gender){

                $.getJSON("rest/product/"+age+"/"+gender, function (data) {
                $( "#searchResult" ).text(data.length+" Items Found");
                $("#searchResultArea").empty();
                $.each(data, function (index, value) {
                renderProduct(data.length,value.id,value.product_name,value.price,value.availability);
                });

                });

    }


    $( "#btnSearch" ).click(function() {
                var age = $( "#age" ).val();
                var gender =$( "#gender" ).val();
                getProduct(age,gender);
    });//end change qty

    getAge();
    getGender();
    getProductInit();


    function renderProduct(searchResult,id,product_name,price,availability) {

        var card  = "<div class='card col-sm-2 zeropadding' style='display:inline-grid;'' >";
            card += "<img class='card-img-top' src='img/"+id+".png' alt='Card image cap'>";
            card += "<div class='card-body'>";
            card += "<h5 class='card-title' id='productName'>"+product_name+"</h5>";
            card += "<p class='card-text' id='productDetail'>"+price+" ฿ ("+availability+")</p>";
            card += "<a href='product_detail/"+price+"' class='btn btn-chelsea float-right bottom'>Product Detail</a>";
            card += "</div>";
            card += "</div>";

        $("#searchResultArea").append(card);     // Append new elements

    }




