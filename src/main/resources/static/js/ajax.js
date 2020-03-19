$(".like-button").on("click", function () {
    // console.dir($(this).parent().children()[0]);
    const id = $(this).parent().children()[0].value;
    const from = $(this).parent().children()[1].value;

    console.log("post #" + id); //returns correct output
    console.log("from page " + from); //returns correct output

    $.ajax( "/posts/" + $(this).parent().children()[0].value + "/like", {
        type: "POST",
        data: {
            post: id,
            from: from
        },
        success: function (data, status, xhr) {
            console.log("post liked!");
        },
        // error: function (e) {
        //     console.log(e)
        // }

    });

    // console.log("post #" + id);
    // console.log("from page " + from);


});
// $(".like-button").addEventListener().click(function () {
//     $.ajax( "/posts/" + this.getPropertyValue("id").substr(5) + "/like", {
//         type: "POST",
//         data: {
//             post: this.getPropertyValue("id").substr(5),
//             from: this.getPropertyValue("from")
//         },
//         success: function (data, status, xhr) {
//
//         },
//         error: function () {
//
//         }
//     });
// });