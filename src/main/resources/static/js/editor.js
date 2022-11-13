$(function (){

    $("#file-name").on('change',function(){
        var fileName = $("#file-name").val();
        $(".upload-name").val(fileName);
    });

    function quilljsediterInit(){
        var option = {
            modules: {
                toolbar: [
                    [{header: [1,2,false] }],
                    ['bold', 'italic', 'underline'],
                    ['image', 'code-block'],
                    [{ list: 'ordered' }, { list: 'bullet' }]
                ]
            },
            placeholder: '내용을 입력해 주세요.',
            theme: 'snow'
        };

        quill = new Quill('#editor', option);
        quill.on('text-change', function() {
            document.getElementById("detail").value = quill.root.innerHTML;
            console.log("change")
        });

        quill.getModule('toolbar').addHandler('image', function () {
            selectLocalImage();
        });
    }

    /* 이미지 콜백 함수 */

    function selectLocalImage() {
        const fileInput = document.createElement('input');
        fileInput.setAttribute('type', 'file');
        console.log("input.type " + fileInput.type);

        fileInput.click();

        fileInput.addEventListener("change", function () {  // change 이벤트로 input 값이 바뀌면 실행
            const formData = new FormData();
            const file = fileInput.files[0];
            formData.append('uploadFile', file);

            $.ajax({
                type: 'post',
                enctype: 'multipart/form-data',
                url: '/post/imageUpload',
                data: formData,
                processData: false,
                contentType: false,
                dataType: 'json',
                success: function (data) {
                    console.log(data);
                    console.log("Success");
                    const range = quill.getSelection(); // 사용자가 선택한 에디터 범위
                    // data.uploadPath = data.uploadPath.replace(/\\/g, '/');
                    quill.insertEmbed(range.index, 'image', "/display?fileName=" + data);
                },
                error: function (err) {
                    console.log(err);
                    console.log("Error");
                }
            });

        });
    }

    quilljsediterInit();
})