var qborrrowApp = angular.module('qborrrow');

var qxQborrrowHttpService = function($http, qborrrowConfig, $timeout) {
	this.getOggettiList = function(scopeController, form) {
		_getOggettiList(scopeController, form);
	}
	
	function _getOggettiList(scopeController, form) {
    	var success = function (data) {
        	if((typeof data) == 'string') {
        		// Not Managed Server error
        		_manageError(data, 0);
        		return;
        	}
        	if(data.error == true) {
        		_manageError(data, 0);
        		return;
        	}
        	if(data.errors != undefined) {
        		qxValidationError(data, form, $timeout, scopeController);
        		scopeController.result = {};
        	} else {
        		scopeController.result = data;
        	}
        };
    	
        scopeController.promise = $http({ 
        		method: 'POST', 
        		url: qborrrowConfig.baseUrl + '/oggetti.action?task=list&reset=true', 
        		data: quixParamSerializer(scopeController.search, 'oggettiSearch.'), 
        		headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        });
        scopeController.promise.success(success).error(_manageError);
    }
    
	this.getMieiOggettiList = function(scopeController, form) {
		_getMieiOggettiList(scopeController, form);
	}
	
	function _getMieiOggettiList(scopeController, form) {
    	var success = function (data) {
        	if((typeof data) == 'string') {
        		// Not Managed Server error
        		_manageError(data, 0);
        		return;
        	}
        	if(data.error == true) {
        		_manageError(data, 0);
        		return;
        	}
        	if(data.errors != undefined) {
        		qxValidationError(data, form, $timeout, scopeController);
        		scopeController.result = {};
        	} else {
        		scopeController.result = data;
        	}
        };
    	
        scopeController.promise = $http({ 
        		method: 'POST', 
        		url: qborrrowConfig.baseUrl + '/oggetti.action?task=listMieiOggetti', 
        		data: quixParamSerializer(scopeController.search, 'oggettiSearch.'), 
        		headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        });
        scopeController.promise.success(success).error(_manageError);
    }
    this.editOggetti = function(scopeController){
    	var success = function (data) {
        	if((typeof data) == 'string') {
        		// Not Managed Server error
        		_manageError(data, 0);
        		return;
        	}
        	if(data.error == true) {
        		_manageError(data, 0);
        		return;
        	}
        	scopeController.selectedRow = data;
        	scopeController.selectedPage = "edit";
        };
    	
        scopeController.promise = $http({ 
        		method: 'POST', 
        		url: qborrrowConfig.baseUrl + '/oggetti.action?task=edit', 
        		data: quixParamSerializer(scopeController.selectedRow, 'oggetti.'), 
        		headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        });
        scopeController.promise.success(success).error(_manageError);
    }
    
     this.deleteOggetti = function(scopeController, labelService){
    	var success = function (data) {
        	if((typeof data) == 'string') {
        		// Not Managed Server error
        		_manageError(data, 0);
        		return;
        	}
        	if(data.error == true) {
        		_manageError(data, 0);
        		return;
        	}
        	sweetAlert(labelService.swalSuccDeleteTitle, labelService.swalSuccDeleteMessage, "success");
        	_getOggettiList(scopeController, null);
        };
    	
        scopeController.promise = $http({ 
        		method: 'POST', 
        		url: qborrrowConfig.baseUrl + '/oggetti.action?task=delete', 
        		data: quixParamSerializer(scopeController.selectedRow, 'oggetti.'), 
        		headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        });
        scopeController.promise.success(success).error(_manageError);
    }
    
    this.saveOggetti = function(scopeController, form) {
    	var success = function (data) {
        	if((typeof data) == 'string') {
        		// Not Managed Server error
        		_manageError(data, 0);
        		return;
        	}
        	if(data.error == true) {
        		_manageError(data, 0);
        		return;
        	}
        	if(data.errors != undefined) {
        		qxValidationError(data, form, $timeout, scopeController);
        	} else {
        		_getOggettiList(scopeController, null);
        		scopeController.selectedPage = 'list';
        	}
        };
        scopeController.promise = $http({ 
        		method: 'POST', 
        		url: qborrrowConfig.baseUrl + '/oggetti.action?task=save&reset=true', 
        		data: quixParamSerializer(scopeController.selectedRow, 'oggetti.'), 
        		headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        });
        scopeController.promise.success(success).error(_manageError);
    }
    							
	this.getPrestitiList = function(scopeController, form) {
		_getPrestitiList(scopeController, form);
	}
	
	function _getPrestitiList(scopeController, form) {
    	var success = function (data) {
        	if((typeof data) == 'string') {
        		// Not Managed Server error
        		_manageError(data, 0);
        		return;
        	}
        	if(data.error == true) {
        		_manageError(data, 0);
        		return;
        	}
        	if(data.errors != undefined) {
        		qxValidationError(data, form, $timeout, scopeController);
        		scopeController.result = {};
        	} else {
        		scopeController.result = data;
        	}
        };
    	
        scopeController.promise = $http({ 
        		method: 'POST', 
        		url: qborrrowConfig.baseUrl + '/prestiti.action?task=list&reset=true', 
        		data: quixParamSerializer(scopeController.search, 'prestitiSearch.'), 
        		headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        });
        scopeController.promise.success(success).error(_manageError);
    }
    
    this.editPrestiti = function(scopeController){
    	var success = function (data) {
        	if((typeof data) == 'string') {
        		// Not Managed Server error
        		_manageError(data, 0);
        		return;
        	}
        	if(data.error == true) {
        		_manageError(data, 0);
        		return;
        	}
        	scopeController.selectedRow = data;
        	scopeController.selectedPage = "edit";
        };
    	
        scopeController.promise = $http({ 
        		method: 'POST', 
        		url: qborrrowConfig.baseUrl + '/prestiti.action?task=edit', 
        		data: quixParamSerializer(scopeController.selectedRow, 'prestiti.'), 
        		headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        });
        scopeController.promise.success(success).error(_manageError);
    }
    
     this.deletePrestiti = function(scopeController, labelService){
    	var success = function (data) {
        	if((typeof data) == 'string') {
        		// Not Managed Server error
        		_manageError(data, 0);
        		return;
        	}
        	if(data.error == true) {
        		_manageError(data, 0);
        		return;
        	}
        	sweetAlert(labelService.swalSuccDeleteTitle, labelService.swalSuccDeleteMessage, "success");
        	_getPrestitiList(scopeController, null);
        };
    	
        scopeController.promise = $http({ 
        		method: 'POST', 
        		url: qborrrowConfig.baseUrl + '/prestiti.action?task=delete', 
        		data: quixParamSerializer(scopeController.selectedRow, 'prestiti.'), 
        		headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        });
        scopeController.promise.success(success).error(_manageError);
    }
    
    this.savePrestiti = function(scopeController, form) {
    	var success = function (data) {
        	if((typeof data) == 'string') {
        		// Not Managed Server error
        		_manageError(data, 0);
        		return;
        	}
        	if(data.error == true) {
        		_manageError(data, 0);
        		return;
        	}
        	if(data.errors != undefined) {
        		qxValidationError(data, form, $timeout, scopeController);
        	} else {
        		_getPrestitiList(scopeController, null);
        		scopeController.selectedPage = 'list';
        	}
        };
        scopeController.promise = $http({ 
        		method: 'POST', 
        		url: qborrrowConfig.baseUrl + '/prestiti.action?task=save&reset=true', 
        		data: quixParamSerializer(scopeController.selectedRow, 'prestiti.'), 
        		headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        });
        scopeController.promise.success(success).error(_manageError);
    }
    							
	this.getSoggettiList = function(scopeController, form) {
		_getSoggettiList(scopeController, form);
	}
	
	function _getSoggettiList(scopeController, form) {
    	var success = function (data) {
        	if((typeof data) == 'string') {
        		// Not Managed Server error
        		_manageError(data, 0);
        		return;
        	}
        	if(data.error == true) {
        		_manageError(data, 0);
        		return;
        	}
        	if(data.errors != undefined) {
        		qxValidationError(data, form, $timeout, scopeController);
        		scopeController.result = {};
        	} else {
        		scopeController.result = data;
        	}
        };
    	
        scopeController.promise = $http({ 
        		method: 'POST', 
        		url: qborrrowConfig.baseUrl + '/soggetti.action?task=list&reset=true', 
        		data: quixParamSerializer(scopeController.search, 'soggettiSearch.'), 
        		headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        });
        scopeController.promise.success(success).error(_manageError);
    }
    
    this.editSoggetti = function(scopeController){
    	var success = function (data) {
        	if((typeof data) == 'string') {
        		// Not Managed Server error
        		_manageError(data, 0);
        		return;
        	}
        	if(data.error == true) {
        		_manageError(data, 0);
        		return;
        	}
        	scopeController.selectedRow = data;
        	scopeController.selectedPage = "edit";
        };
    	
        scopeController.promise = $http({ 
        		method: 'POST', 
        		url: qborrrowConfig.baseUrl + '/soggetti.action?task=edit', 
        		data: quixParamSerializer(scopeController.selectedRow, 'soggetti.'), 
        		headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        });
        scopeController.promise.success(success).error(_manageError);
    }
    
     this.deleteSoggetti = function(scopeController, labelService){
    	var success = function (data) {
        	if((typeof data) == 'string') {
        		// Not Managed Server error
        		_manageError(data, 0);
        		return;
        	}
        	if(data.error == true) {
        		_manageError(data, 0);
        		return;
        	}
        	sweetAlert(labelService.swalSuccDeleteTitle, labelService.swalSuccDeleteMessage, "success");
        	_getSoggettiList(scopeController, null);
        };
    	
        scopeController.promise = $http({ 
        		method: 'POST', 
        		url: qborrrowConfig.baseUrl + '/soggetti.action?task=delete', 
        		data: quixParamSerializer(scopeController.selectedRow, 'soggetti.'), 
        		headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        });
        scopeController.promise.success(success).error(_manageError);
    }
    
    this.saveSoggetti = function(scopeController, form) {
    	var success = function (data) {
        	if((typeof data) == 'string') {
        		// Not Managed Server error
        		_manageError(data, 0);
        		return;
        	}
        	if(data.error == true) {
        		_manageError(data, 0);
        		return;
        	}
        	if(data.errors != undefined) {
        		qxValidationError(data, form, $timeout, scopeController);
        	} else {
        		_getSoggettiList(scopeController, null);
        		scopeController.selectedPage = 'list';
        	}
        };
        scopeController.promise = $http({ 
        		method: 'POST', 
        		url: qborrrowConfig.baseUrl + '/soggetti.action?task=save&reset=true', 
        		data: quixParamSerializer(scopeController.selectedRow, 'soggetti.'), 
        		headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        });
        scopeController.promise.success(success).error(_manageError);
    }
    								

	this.getCombo = function(scopeController, name){
    	var success = function (data) {
        	if((typeof data) == 'string') {
        		// Not Managed Server error
        		_manageError(data, 0);
        		return;
        	}
        	if(data.error == true) {
        		_manageError(data, 0);
        		return;
        	}
        	scopeController[name + 'List'] = data.list;
        };
    
    	 scopeController.promise = $http({ 
        		method: 'POST', 
        		url: qborrrowConfig.baseUrl + '/'+name+'.action?task=list&reset=true&'+name+'Search.page=-1', 
        		headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        });
        scopeController.promise.success(success).error(_manageError);
    }					
};

qborrrowApp.service('qxQborrrowHttpService', ['$http', 'qborrrowConfig', '$timeout', qxQborrrowHttpService]);								
