$(function (){
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
})
