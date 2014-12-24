// Hold a global reference to the div#main element.  Initially assign it ... somewhere useful :)
var main,				// the invisible center div
	circles = [];		// holds all the circle elements
	mainX = 0,			// x position of main div
	mainY = 0;			// y position of main div
 
// Initially position a circle based on a given angle.
var positionCircle = function(c, angle) {
	var r = 150;												// initial radius
	var x = mainX + (r * Math.sin(angle * (Math.PI / 180)));		
	var y = mainY + (r * Math.cos(angle * (Math.PI / 180)));
	// x and y position of each circle
	c.style.left = x.toString() + "px";			// set circles x position
	c.style.top = y.toString() + "px";			// set circles y position
}

// Move a circle based on the distance of the approaching mouse
var moveCircle = function(circle, dx, dy) {
	circle.style.left = dx.toString() + "px";	// move circle to new x position
	circle.style.top = dy.toString() + "px";	// move circle to new y position

}

// Look at all the circle elements, and figure out if any of them have to move.
var checkMove = function() {
	var mouseX = event.clientX;							// mouse x position
	var mouseY = event.clientY;							// mouse y position

	var i;
	for( i = 0; i < 15; i++ ) {
		var circleX = Number(circles[i].offsetLeft);												// circle x position
		var circleY = Number(circles[i].offsetTop);													// circle y position
		var d = Math.sqrt((Math.pow(mouseX - circleX, 2)) + (Math.pow((mouseY - circleY) , 2)));	// distance between the mouse and the circle
	
		if( d < 100 ) {												// if circle and mouse are within 100 px
			var x = Math.abs(mouseX - circleX);							//	x used to calculate alpha
			var alpha = Math.acos(x/d);									// andgle of the mouse vector to the circle
			var newD = 101 - d;											// new distance for circle to be placed

			if ( mouseX <= circleX ) {									// if mouse is coming from the left of the circle
				var newX = circleX + newD * Math.cos(alpha);				// add new x distance to the current circle x position
			}
			else {														// else mouse is coming from the right
				var newX = circleX - newD * Math.cos(alpha);				// take the difference of current circle x and new x
			}

			if( mouseY <= circleY ) {									// similar logic for y as for x above
				var newY = circleY + newD * Math.sin(alpha);
			}
			else {
				var newY = circleY - newD * Math.sin(alpha);
			}
			
			moveCircle(circles[i], newX, newY);							// once newX and newY are calculated, move the circle
		}
	}
}

// Set up the initial circle of circles.
var setup = function() {
	main = document.getElementById('main');								// set global variables
	mainX = main.offsetLeft;
	mainY = main.offsetTop;

    var numCircles = 15;
	var i;

	for( i = 0; i < numCircles; i++ ) {									// for each circle
		var c = document.createElement('div');								// create a div for it
		document.body.appendChild(c);										// add it to the body
		c.setAttribute("class", "circle");									// add class for styling 
		circles[i] = c;														// fill the circles array with new cricle
		positionCircle(circles[i], i*24);									// draw the cricle on the screen
	}
  
}

window.addEventListener('load', function() {
	setup();
  	// Add an event listener to pick up the mouse movements and call checkMove
	window.addEventListener('mousemove', function() {
		checkMove();
	});
	window.addEventListener('resize', function() {				//whenever the window is resized
		var i;
		for(i = 0; i < 15; i++) {									// remove all the circle divs and call setup again
			document.body.removeChild(circles[i]);
		}
		setup();
	});
});
