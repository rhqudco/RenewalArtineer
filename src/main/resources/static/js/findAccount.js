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
            dataType:'json',
            async: false,
            success:function (result) {
                console.log(result)
                if (Object.keys(result)[0] === "BAD_REQUEST") {
                    $('#result-info-id').css({'display': 'inline'});
                    $('#result-text-id').text(Object.values(result)[0]);
                }
                else {
                    $('#result-info-id').css({'display': 'inline'});
                    $('#result-text-id').text("아이디는 " + Object.values(result)[0] + " 입니다.");
                }
            },
            error:function (data, textStatus) {
                // console.log(Object.values(data)[17].message);
                // $('#result-info-id').css({'display': 'inline'});
                // $('#result-text-id').text(Object.values(data)[17].message);
                // alert(Object.values(data)[17].message)
                console.log(data);
                console.log(textStatus);
                alert("네트워크 삳태가 좋지 않습니다. 잠시후 다시 시도해 주세요.")
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
                else if(result === "잠시 후 다시 시도해 주세요."){
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
                alert("네트워크 삳태가 좋지 않습니다. 잠시후 다시 시도해 주세요.")
            }
        })
    })
})