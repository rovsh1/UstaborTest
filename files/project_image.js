var fileInput = $('<input/>', {
    type:'file',
    name:'project[images][]',
    id:'',
    class:'field-image ui-sortable-handle',
    multiple:'1'
    });
$('.thumbs').append(fileInput);