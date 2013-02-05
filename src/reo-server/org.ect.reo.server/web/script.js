
function hideDiv(id) {
    var style = document.getElementById(id).style;
    style.display = "none";
}

function showDiv(id) {
    var style = document.getElementById(id).style;
    style.display = "block";
}

function showTab(id) {
	var elem = document.getElementById(id);
    elem.className += " activeTab";
    elem.blur();
}

function hideTab(id) {
	var elem = document.getElementById(id);
    removeName(elem, "activeTab");
}


// Remove the given class name from the element's className property.
function removeName(elem, name) {
	var i, curList, newList;
	newList = new Array();
	curList = elem.className.split(" ");
	for (i=0; i<curList.length; i++) {
    	if (curList[i] != name) newList.push(curList[i]);
    }
  	elem.className = newList.join(" ");
}


function enable(id) {
	document.getElementById(id).disabled = false;
}

function disable(id) {
	document.getElementById(id).disabled = true;
}

