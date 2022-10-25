$(function (){
    $('#search-btn').on('click', function () {
        event.preventDefault();

        $.ajax({
            type:"post",
            url:"/writeComment",
            data:{
                "select":$('#title-writer-select').val(),
                "searchParameter":$('#search-parameter').val()
            },
            dataType:'json',
            async: true,
            success:function (result) {

            },
            error:function (data, textStatus) {
                console.log(data);
                console.log(textStatus);
            }
        })
    })
})