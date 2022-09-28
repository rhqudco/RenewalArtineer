$(document).ready(function(){
    $('#find-id').on('click', function () {
        event.preventDefault();

        $.ajax({
            type:"post",
            url:"/members/find/account/id",
            data:{
                "memberName":$('#member-name').val(),
                "emailId":$('#email-id').val(),
                "emailDomain":$('#email-domain').val()
            },
            dataType:'text',
            async: false,
            success:function (result) {
                console.log(result)
                if (result === "") {
                    $('#result-info-id').css({'display': 'inline'});
                    $('#result-text-id').text("입력하신 정보를 다시 확인해 주세요.");
                }
                else if(result === "위 정보로 가입된 회원을 찾을 수 없습니다."){
                    $('#result-info-id').css({'display': 'inline'});
                    $('#result-text-id').text(result);
                }
                else {
                    $('#result-info-id').css({'display': 'inline'});
                    $('#result-text-id').text("아이디는 " + result + " 입니다.");
                }
            },
            error:function (data, textStatus) {
                console.log(data);
                console.log(textStatus);
            }
        })
    })

    $('#find-pw').on('click', function () {
        event.preventDefault();

        $.ajax({
            type:"post",
            url:"/members/find/account/pw",
            data:{
                "memberId":$('#member-id').val(),
                "emailId":$('#emailId').val(),
                "emailDomain":$('#emailDomain').val()
            },
            dataType:'text',
            async: true,
            success:function (result) {
                console.log(result)
                if (result === "") {
                    $('#result-info').css({'display': 'inline'});
                    $('#result-text').text("입력하신 정보를 다시 확인해 주세요.");
                }
                else if(result === "위 정보로 가입된 회원을 찾을 수 없습니다."){
                    $('#result-info').css({'display': 'inline'});
                    $('#result-text').text(result);
                }
                else if(result === "위 정보로 가입된 회원을 찾을 수 없습니다."){
                    $('#result-info').css({'display': 'inline'});
                    $('#result-text').text(result);
                }
                else {
                    $('#result-info').css({'display': 'inline'});
                    $('#result-text').text("입력하신  " + result + "으로 임시 비밀번호를 발송하였습니다.");
                }
            },
            error:function (data, textStatus) {
                console.log(data);
                console.log(textStatus);
            }
        })
    })
})