let idToLike;

$(function () {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
});

$(".like-button").on("click", function () {
    idToLike = $(this).parent().children()[0].value;
    // const token = $("meta[name='_csrf']").attr("content");
    // const header = $("meta[name='_csrf_header']").attr("content");
    // xhr.setRequestHeader(header, token);
    $.ajax( "/posts/" + idToLike + "/like", {
        // url: ,
        type: "POST",
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify({
            post: idToLike,
            from: "feed"
        }),
        success: function (data, status, xhr) {
            console.log("post liked!");
        },
        error: function (e) {
            console.log("Id to like: " + idToLike);
            console.log(e);
        }
    });
});