    $( document ).ready(function() {
        getAge();
        getGender();
        getProductInit();

    });

    function gotoDetail(product_id){
        sessionStorage.setItem("sent_pid", product_id);
        window.open("product_detail.html","_self");

    };

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
                $( "#searchResult" ).text(data.length+" Items Found");
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




    function renderProduct(searchResult,id,product_name,price,availability) {

        if (product_name.length >20)
            product_name = product_name.substring(0, 20)+"...";

        var card  = "<div class='card col-sm-2 zeropadding' style='display:inline-grid;'' >";
        card += "<img class='card-img-top' src='img/"+id+".png' alt='Card image cap'>";
        card += "<div class='card-body'>";
        card += "<h5 class='card-title' >"+product_name+"</h5>";
        card += "<p class='card-text' >"+price+" THB ("+availability+")</p>";
        card += "<div type='button' id=toy_"+id+" class='btn btn-chelsea pid_detail float-right bottom' onclick='gotoDetail("+id+");'>Product Detail</div>";
        card += "</div>";
        card += "</div>";

        $("#searchResultArea").append(card);     // Append new elements

    }








