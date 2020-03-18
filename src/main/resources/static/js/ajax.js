$(".like-button").addEventListener().click(function () {
    $.ajax( "/posts/" + this.getPropertyValue("id").substr(5) + "/like", {
        type: "POST",
        data: {
            post: this.getPropertyValue("id").substr(5),
            from: this.getPropertyValue("from")
        },
        success: function (data, status, xhr) {

        },
        error: function () {

        }
    });
});