$(document).ready(function() {
    $.ajax({
        url: "http://localhost:8080/mvc/person"
    }).then(function(data) {
        $('.nameV').append(data.name);
        $('.cityV').append(data.city);
        $('.activeV').append(data.active);
    });
});