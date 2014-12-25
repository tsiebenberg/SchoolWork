

window.addEventListener('load', function() {
	var elements = document.getElementsByClassName('tabs');

	setLeftBarHeight();	
	
	var i;
	for(i = 0; i < elements.length; i++) {
		elements[i].addEventListener('click', function() {
			switchTabs( elements );
		});
	}	
});

var switchTabs = function(elements) {
	var trigger = event.target;
	var tabs = [document.getElementById('Home'), document.getElementById('Snowboards'), document.getElementById('Boots'), document.getElementById('Bindings')]	

	console.log(trigger);

	if(!trigger.classList.contains('selected')) {
		var i;
		for(i = 0; i < elements.length; i++) {
			elements[i].classList.remove('selected');
		}
		trigger.classList.add('selected');

		for( i = 0; i < elements.length; i++ ) {
			tabs[i].classList.add('hidden');
			if(elements[i].classList.contains('selected')) {
				tabs[i].classList.remove('hidden');
				tabs[i].classList.add('selected');
			}
		}
	}
	setLeftBarHeight();
}

var setLeftBarHeight = function() {
	var element = document.getElementsByClassName('leftBar');	
	var newH = document.getElementsByClassName('mainContent');
	element[0].style.height = newH[0].offsetHeight.toString() + "px";
}
