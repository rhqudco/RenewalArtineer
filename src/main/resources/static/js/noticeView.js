$(function (){
    let conSelector = $('#selector').val();
    console.log(conSelector);
    $('#selectorParam').val(conSelector);
    console.log($('#selectorParam').val())

    $('#selector').change(function () {
        let selector = $(this).val();
        console.log(selector);
        $('#selectorParam').val(selector);
        console.log($('#selectorParam').val());
    })
})