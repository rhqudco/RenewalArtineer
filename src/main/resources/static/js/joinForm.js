$(function (){

    function btnActive() {
        const target = $("#join-btn");
        target.attr("disabled", false);
    }
    function btnDisabled() {
        const target = $("#join-btn");
        target.attr("disabled", true);
    }

    $("#emailDomainSelect").change(function (){ // 이메일 도메인 바꿔주는 기능
        let value = $("#emailDomainSelect").val();
        if(value === "type"){
            $("#emailDomain").val("").attr("readonly", false);
        }
        else {
            $("#emailDomain").val(value).attr("readonly", true);
        }
    })

    $("#email-domain-select").change(function (){ // 이메일 도메인 바꿔주는 기능
        let value = $("#email-domain-select").val();
        if(value === "type"){
            $("#email-domain").val("").attr("readonly", false);
        }
        else {
            $("#email-domain").val(value).attr("readonly", true);
        }
    })

    $("#validate-id-btn").click(function (){
        $.ajax({
            type:"post",
            url:"/validateDuplicateId",
            data:{
                "memberId":$('#member-id').val(),
            },
            dataType:'json',
            async: false,
            success:function (result) {
                console.log(Object.keys(result)[0]);
                if (Object.keys(result)[0] === "BAD_REQUEST") {
                    alert(Object.values(result));
                    btnDisabled();
                }
                if (Object.keys(result)[0] === "OK") {
                    alert(Object.values(result));
                    btnActive();
                }
            },
            error:function (data) {
                // console.log(data);
                // console.log(Object.values(data)[17].message);
                // alert(Object.values(data)[17].message)
                alert("네트워크 상태가 좋지 않습니다. 잠시후 다시 시도해 주세요.")
                btnDisabled();
            }
        })
    })
})