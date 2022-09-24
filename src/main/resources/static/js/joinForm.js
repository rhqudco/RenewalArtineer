window.onload = function () {
// 도메인 직접 입력 or domain option 선택
    const domainListEl = document.querySelector('#emailDomainSelect')
    const domainInputEl = document.querySelector('#emailDomain')
// select 옵션 변경 시
    domainListEl.addEventListener('change', (event) => {
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
    })
}