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
    from = $(this).parent().children()[2].value;
    $(this).parent().parent().children()[1].innerText = (parseInt($(this).parent().parent().children()[1].innerText) + 1).toString();
    $.ajax( "/posts/" + idToLike + "/like/" + from, {
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

$(".like-comment").on("click", function () {
    postId = $(this).parent().children()[3].value;
    from = $(this).parent().children()[1].value;
    idToLike = $(this).parent().children()[0].value;
    parseInt($(this).parent().parent().children()[1].innerText = (parseInt($(this).parent().parent().children()[1].innerText) + 1).toString());
    $.ajax( "/posts/" + postId + "/comment/" + idToLike + "/like/" + from, {
        type: "POST",
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify({
            post: idToLike,
            from: "feed"
        }),
        success: function (data, status, xhr) {
            console.log("comment liked!");
        },
        error: function (e) {
            console.log("Id to like: " + idToLike);
            console.log(e);
        }
    });
});