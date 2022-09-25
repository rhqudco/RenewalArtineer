window.onload = function () {
    console.log('123');
    alert('123');
// 도메인 직접 입력 or domain option 선택
    const domainListEl = document.getElementById('emailDomainSelect')
    const domainInputEl = document.getElementById('emailDomain')
// select 옵션 변경 시
    domainListEl.addEventListener('change', function (event) {
        // option에 있는 도메인 선택 시
        if (event.target.value !== "type") {
            // 선택한 도메인을 input에 입력하고 disabled
            domainInputEl.value = event.target.value
            domainInputEl.disabled = true
        } else { // 직접 입력 시
            // input 내용 초기화 & 입력 가능하도록 변경
            domainInputEl.value = ""
            domainInputEl.disabled = false
        }
    });
}

$(function (){
    alert("123");
    $("#emailDomainSelect").change(function (){
        let value = $("#emailDomainSelect").val();
        if(value === "type"){
            $("#emailDomain").val("");
        }
        else {
            $("#emailDomain").val(value);
        }
    })
})