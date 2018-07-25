const tagAddButton = document.querySelector('.add-tag button');
const tagAddInput = document.querySelector('.add-tag input');
const tagsList = document.querySelector('.tags-list ul');
const tagRemoveButton = document.querySelector('.remove-tag button');
const tagRemoveInput = document.querySelector('.remove-tag input');
//const commentRemoveButton = document.querySelector('.remove-comment button');
//const commentRemoveInput = document.querySelector('.remove-comment input');

const xhr = new XMLHttpRequest()
xhr.onreadystatechange = function(){
	if(xhr.readyState===4 && xhr.status ===200){
		const res = xhr.responseText
		tagsList.innerHTML = res
	}
}

tagAddButton.addEventListener('click', function() {
	postTags(tagAddInput.value)//parameter is whatever the value is of the text field (whatever typed into text box)
	tagAddInput.value = ""//clears text box after click
})

tagRemoveButton.addEventListener('click', function() {
	var ask = confirm('Are you sure?');
	if(ask == true){
		removeTags(tagRemoveInput.value)
		tagRemoveInput.value = ""
	}
})


//commentRemoveButton.addEventListener('click', function() {
//	var ask = confirm('Are you sure?');
//	if(ask == true){
//		removeComments(commentRemoveInput.value)
//		commentRemoveInput.value = ""
//	}
//})


function postTags(tagName) {
	xhr.open('POST', '/tags/' + tagName, true)
	xhr.send()
}

function removeTags(tagName){
	xhr.open('POST', '/tags/remove/' + tagName, true)
	xhr.send()
}

//function removeComment(authorName) {
//	xhr.open('POST', '/remove-comment' + authorName, true)
//	xhr.send()
//}

