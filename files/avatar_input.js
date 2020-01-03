var e = document.getElementsByClassName('button-default')[0];
var d = document.createElement('div');
d.innerHTML = e.innerHTML;
e.parentNode.replaceChild(d, e);