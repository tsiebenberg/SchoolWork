function myQuery(selector, factory) {
  this.elements = null;
  this.selector = selector;
  
  if (typeof factory == "undefined") {
  
    // See if selector starts with a #. If so we're looking for an ID
    if (selector[0] == '#') {
      // Strip off the # sign
      var idName = selector.substring(1, selector.length);
      var element = document.getElementById(idName);
    
      myQobj = new myQuery(selector, true);
      myQobj.elements = [element];
      return myQobj;
    }
	// See if selector starts with a '.' If so we're looking for a class
	else if( selector[0] == '.') {
		// Strip off the . 
		var className = selector.substring(1, selector.length);
		var elements = document.getElementsByClassName(className);

		myQobj = new myQuery(selector, true);
		myQobj.elements = elements;						// retrun just elements (not [elements]) since getElementsByClassName returns a list of classes
		return myQobj;
	}
	else {
		var name = selector;
		var elements = document.getElementsByTagName(name);

		myQobj = new myQuery(selector, true);
		myQobj.elements = elements;						// similar to class section, only retruning elements since getElementsByTagName returns a list of classes
		return myQobj;
	}
    
  } else {
    return this;
  }
}

$ = myQuery;

myQuery.prototype = {	
	// Add class method 
  addClass:			function(newClass) {	
						var el = this.elements;
						var i;
						for(i = 0; i < el.length; i++ ) {  		// for each element in the given list
							el[i].classList.add(newClass);			// add a class to its class list
						}
					},
	
	// remove class method
   removeClass:		function(aClass) {
						var el = this.elements;
						var i;
						for(i = 0; i < el.length; i++ ) {		// for each element in the given list
							el[i].classList.remove(aClass);			// remove a specific class
						}
					},

   ready:           function(myFunciton) {
                      if (window.attachEvent) {
                          window.attachEvent('onload', myFunciton);
                          console.log("IE");
                      } else if (window.addEventListener) {
                          window.addEventListener('load', myFunciton);
                          console.log("Modern");
                      } else {
                          console.log("Legacy");
                          if(window.onload) {
                              var curronload = window.onload;
                              var newonload = function() {
                                  curronload();
                                  myFunciton();
                              };
                              window.onload = newonload;
                          } else {
                              window.onload = myFunciton;
                          }
                      }
                    },
                    
  getElements:      function() {
                      return this.elements;
                    },
                    
  getSelector:      function() {
                      return this.selector;
                    },
}
