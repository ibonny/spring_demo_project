$(document).ready(function() {
    $("#testParam").html("Loading...");

    var value = $("#nameval").html();

    console.log(`Name value is: ${value}`);

    $.get("/greeting", function(response) {
        console.log(response);

        $("#testParam").html(`${response.id}: ${response.content}`);
    });
});
