

    function getAge(){
                // EXTRACT JSON DATA.
                $.getJSON("rest/age", function (data) {
                    $.each(data, function (index, value) {
                        // APPEND OR INSERT DATA TO SELECT ELEMENT.
                        $('#age').append('<option value="' + value.id + '">' + value.age_name + '</option>');
                    });
                });
    }

    getAge();