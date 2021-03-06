let idToLike;

$(function () {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
});

$(".follow-button").on("click", function () {
    idToFollow = $(this).parent().children()[0].value;
    from = $(this).parent().children()[1].value;
    $.ajax( "/follow/" + idToFollow, {
        type: "POST",
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify({
            user: idToFollow,
            from: from
        }),
        success: function (data, status, xhr) {
            console.log("User Followed!");
        },
        error: function (e) {
            console.log(e);
        }
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
    const postId = $(this).parent().children()[3].value;
    const from = $(this).parent().children()[1].value;
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


let user;

$(".comment-button").on("click", function () {
    const postId = $(this).parent().children()[2].value;
    let body = $(this).parent().children()[1].value;
    const addTo = $(this).parent().parent().parent().children()[4];
    const username = $(this).parent().children()[3].value;
    $.ajax( "/posts/" + postId + "/comment", {
        type: "POST",
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify({
            body: body,
        }),
        success: function (data, status, xhr) {
            console.log("comment posted!");
            const html = `<div class="comment outline"><div class="comment-head head">
                <h5 class="username"><a href="/profile">${username}</a></h5>
                </div>
                <div class="comment-body body">
                <p>${body}</p>
                </div>
                <div class="info">
                <form class="like like-post" method="post">
                <button class="like-comment" type="button"><img src="" alt="like-icon"></button>
                <input type="hidden" name="postId" value="${postId}">
                </form>
                <p class="rating">0</p>
                </div></div>`;
            addTo.innerHTML += html;
            body = "";
        },
        error: function (e) {
            console.log("error");
            console.log(e);
            const html = `<div class="comment outline"><div class="comment-head head">
                <h5 class="username"><a href="/profile">${username}</a></h5>
                </div>
                <div class="comment-body body">
                <p>${body}</p>
                </div>
                <div class="info">
                <form class="like like-post" method="post">
                <button class="like-comment" type="button"><img src="" alt="like-icon"></button>
                <input type="hidden" name="postId" value="${postId}">
                </form>
                <p class="rating">0</p>
                </div></div>`;
            addTo.innerHTML += html;
            body = "";
        }
    });
});