
(function(){
  var Services=function($http){
    
    var getIndividuals=function(className){
    	var classNameParts=className.split("#");
    	className=classNameParts[classNameParts.length-1];
        return $http.get('/individuals/class/' + className).then(function(response){
                 var data=response.data;
			
			data.unshift({"uri":"off","localName":"Select an individual"});
			return response.data;
          
        });
    }
	
	var getProperties=function(className){
		 return $http.get('/properties/class/' + className).then(function(response){
			 var data=response.data;
			 data.unshift({'localName':'Select a property','uri':'off'});
            return data;
          
        });
	}
	
	var getOperators=function(propertyUri,isDescriptionOfIndividual){
		 return $http.get('/properties/operators/' + propertyUri+'/'+isDescriptionOfIndividual).then(function(response){						
			var data=response.data;
			data.operators.unshift({displayValue: "Select an operator", realValue: "off"});
			return data;
          
        });
	}
	
	var getIndividualDataForUpdate=function(individualName){
		 return $http.get('/individuals/data/' + individualName).then(function(response){
       
			return response.data;
          
        });
	}
	
	var getClasses=function(className){
		 return $http.get('/class/property/' + className).then(function(response){
          var data= response.data;		  
		  data.unshift({"uri":"off","localName":"Select a class"});		
		  return data;
          
        });
	}
	
	var add=function(data,elementName){
		var url='';
		if(isAddIndividual())
			url='/individuals/save';
		else
			url="/class/save";
		var dataToBeSend={'conditionJson':data,'name':elementName};
			 return $http.post(url, dataToBeSend).then(function(response){
            return response.data;
          
        });
	}
	
	var getAnnotationProperties=function(componentUri){
		return $http.get('/annotationProperties/get/'+encodeURIComponent(componentUri)).then(function(response){
			return response.data;
			
		});
		
	}
	
	var isAddIndividual=function(){
		if(window.location.pathname.startsWith("/addClassExpression"))
			return false;
		return true;
	}
    
 
    
    return {
      getIndividuals:getIndividuals,
	  getProperties:getProperties,
	  getOperators:getOperators,
	  getClasses:getClasses,
	  add:add,
	  getAnnotationProperties:getAnnotationProperties,
	  isAddIndividual:isAddIndividual,
	  getIndividualDataForUpdate:getIndividualDataForUpdate
    };
  }
   var app = angular.module('Ontoplay');
   app.factory("Services",Services);
  
}());