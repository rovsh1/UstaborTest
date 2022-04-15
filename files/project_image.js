var fileInput = $('<input/>', {
    type:'file',
    name:'project[image]',
    id:'form_project_image',
    class:' field-image 1',
    multiple:''
});
$('.ui-image-input').replaceWith(fileInput)
