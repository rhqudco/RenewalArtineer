$(function (){
    $('#write-comment').on('click', function () {
        event.preventDefault();

        $.ajax({
            type:"post",
            url:"/writeComment",
            data:{
                "comment":$('#comment-area').val(),
                "notice-no":$('#notice-no').val()
            },
            dataType:'text',
            async: true,
            success:function (result) {
                location.replace("/notice/noticeView/" + result);
            },
            error:function (data, textStatus) {
                console.log(data);
                console.log(textStatus);
            }
        })
    })

    $('.comment-area').on('text-change', function () {
        $('.comment').val($('.comment-area').val());
        console.log("change");
    })

    $('.write-sub-comment').on('click', function () {
        console.log($(this).siblings().length);
        $(this).parent().parent().next().css("display", "block");
    })
})