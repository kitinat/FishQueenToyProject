

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
    function getProductLen(){
                $.getJSON("rest/product/1/all", function (data) {
                $( "#searchResult" ).text(data.length+" Toy Found");
                });

    }
    getAge();
    getGender();
    getProductLen();