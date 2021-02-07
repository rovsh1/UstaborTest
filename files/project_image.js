var fileInput = $('<input/>', {
    type:'file',
    name:'project[image]',
    id:'form_project_image',
    class:' field-image 1',
    multiple:''
    });
$('#form_project_image_images').append(fileInput);