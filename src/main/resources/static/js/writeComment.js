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

    $('#sub-comment-btn').on('click', function () {
        $('#sub-comment-value').prop("type", "text")
        $('#write-sub-comment').prop("type", "button")
    })

    function clickFunc() {
        let id = document.getElementById(this.id);
        id.onclick
    }

})