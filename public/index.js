

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
                var age = "all";
                var gender = "all";
                $.getJSON("rest/product/"+age+"/"+gender, function (data) {
                $( "#searchResult" ).text(data.length+" Toy Found");
                });

    }
    function getProduct(age,gender){

                $.getJSON("rest/product/"+age+"/"+gender, function (data) {
                $( "#searchResult" ).text(data.length+" Toy Found");
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

