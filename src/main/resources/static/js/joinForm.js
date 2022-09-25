$(function (){
    $("#emailDomainSelect").change(function (){ // 이메일 도메인 바꿔주는 기능
        let value = $("#emailDomainSelect").val();
        if(value === "type"){
            $("#emailDomain").val("");
        }
        else {
            $("#emailDomain").val(value);
        }
    })
})