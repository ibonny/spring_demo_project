$(document).ready(function() {
    $("#testParam").html("Loading...");

    $.get("/greeting", function(response) {
        console.log(response);

        $("#testParam").html(`${response.id}: ${response.content}`)
    });
});
