$(function (){
    $('#write-comment').on('click', function () {
        event.preventDefault();

        $.ajax({
            type:"post",
            url:"/writeComment",
            data:{
                "comment":$('#comment').val(),
                "notice-no":$('#notice-no').val()
            },
            dataType:'text',
            async: true,
            success:function (result) {
                location.reload();
            },
            error:function (data, textStatus) {
                console.log(data);
                console.log(textStatus);
            }
        })
    })

    $('#write-sub-comment').on('click', function () {
        $('#sub-comment-area').append('<input type="text" id="detail" name="detail" placeholder="내용을 입력하세요.">');
        $('#sub-comment-area').append('<button type="submit">답글 달기</button>');
    })


})