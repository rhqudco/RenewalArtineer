$(document).ready(function(){
    $('#find-id').on('click', function () {
        event.preventDefault();

        $.ajax({
            type:"post",
            url:"/members/find/account/id",
            data:{"memberName":$('#memberName').val(),
                "emailId":$('#emailId').val(),
                "emailDomain":$('#emailDomain').val()},
            dataType:'text',
            async: false,
            success:function (result) {
                console.log(result)
                if (result === "") {
                    $('#result-info').css({'display': 'inline'});
                    $('#result-text').text("입력하신 정보를 다시 확인해 주세요.");
                }
                else if(result === "위 정보로 찾을 수 없습니다."){
                    $('#result-info').css({'display': 'inline'});
                    $('#result-text').text("검색 결과가 없습니다.");
                }
                else {
                    $('#result-info').css({'display': 'inline'});
                    $('#result-text').text("아이디는 " + result + " 입니다.");
                }
            },
            error:function (data, textStatus) {
                console.log(data);
                console.log(textStatus);
            }
        })
    })
})