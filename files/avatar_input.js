var e = document.querySelector('div.form-field > div.button');
var d = document.createElement('div');
d.innerHTML = e.innerHTML;
e.parentNode.replaceChild(d, e);