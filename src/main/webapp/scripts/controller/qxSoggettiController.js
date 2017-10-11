var qborrrowApp = angular.module('qborrrow');

qborrrowApp.controller('qxSoggettiController', ['$scope', 'qxQborrrowHttpService', '$modal', 'labelService', 'SweetAlert', 'qborrrowConfig', function ($scope, qxQborrrowHttpService, $modal, labelService, SweetAlert, qborrrowConfig){
	
	$scope.forms = {};
	$scope.scopeController = {}; 
	$scope.scopeController.selectedPage = 'list';
	$scope.scopeController.search= {};
	$scope.scopeController.search.page = 1;
	
	$scope.search = function () {
		$scope.scopeController.search.order = 0;
		$scope.scopeController.search.page = 1;
		$scope.list();
	}
	
	$scope.orderBy = function(orderBy) {
		$scope.scopeController.search.order = orderBy;
		$scope.scopeController.search.page = 1;
		$scope.list();
	}
	
	$scope.removeFilter = function(filtername) {
		$scope.scopeController.search[filtername] = null;
		if($scope.scopeController.search[filtername + '_description'] != undefined) {
			$scope.scopeController.search[filtername + '_description'] = null;
		}
		$scope.scopeController.search.page = 1;
		$scope.list();
	}
	
	$scope.list = function () {
		qxQborrrowHttpService.getSoggettiList($scope.scopeController, $scope.forms.soggettiListForm);
	}
	
	$scope.edit = function(row){
		$scope.scopeController.selectedRow = row;
		qxQborrrowHttpService.editSoggetti($scope.scopeController);
	}
	
	$scope.exportXLS = function() {
		document.location.href =  qborrrowConfig.baseUrl + '/soggetti.action?task=exportXls&reset=true&' + quixParamSerializer($scope.scopeController.search, 'soggettiSearch.');
	}
	
	$scope.delete = function(row){
		 SweetAlert.swal({
	        title: labelService.swalWarnDeleteTitle,
	        text: labelService.swalWarnDeleteMessage,
	        type: "warning",
	        showCancelButton: true,
	        confirmButtonColor: "#DD6B55",
	        confirmButtonText: labelService.swalWarnDeleteButton,
	        closeOnConfirm: false,
	        closeOnCancel: true,
	        html: false
	    }, function(isConfirm){
			if(isConfirm){
				$scope.scopeController.selectedRow = row;
				qxQborrrowHttpService.deleteSoggetti($scope.scopeController, labelService);
			}
		});
	}
	
	$scope.create = function(){
		$scope.scopeController.selectedRow = {};
		$scope.scopeController.selectedPage = "edit";
	}
	
	$scope.back = function(){
		$scope.scopeController.selectedRow = {};
		$scope.scopeController.selectedPage = "list";
	}
	
	$scope.save = function(row){
		qxQborrrowHttpService.saveSoggetti($scope.scopeController, $scope.forms.soggettiEditForm);
	}
	
	$scope.resetSearch = function () {
		$scope.scopeController.search= {};
		$scope.scopeController.search.page = 1;
	}
	
	$scope.loadCombo = function(name) {
		qxQborrrowHttpService.getCombo($scope.scopeController, name);
	}
	
	$scope.list();
	
    $scope.initCalendar = function(obj, id) {
    	if ( $('#' + id)[0].type != 'date' )  {
    		obj.oldCalendar = true;
    	}
    }
    
    $scope.openCalendar = function(obj, $event) {
        $event.preventDefault();
        $event.stopPropagation();
    	obj.opened = true;
    	return obj.opened;
    }	
    
    $scope.openPopupField = function(externalModelName, object, baseProp, idProp, descrProp) {
    	$scope.scopeController.modalInstance = $modal.open({
            template: '<div ng-include="\''+externalModelName+'\'"></div>',
            windowTemplateUrl: 'template/modal/window-quix.html',
            controller: 'qx' + externalModelName + 'PopupController',
            size: 'lg'
      	});
		$scope.scopeController.modalInstance.result.then(function (selectedElement) {
			if(selectedElement != undefined) {
	    		object[baseProp] = selectedElement[idProp];
	    		object[baseProp + '_description'] =  selectedElement[descrProp];
	    	}	
   		}, function () {
    	 	//Ok, modal dismissed senza cambiare i filtri
   		});
    }
}]);

qborrrowApp.controller('qxSoggettiPopupController', ['$scope', 'qxQborrrowHttpService', '$modal', '$modalInstance', '$controller', function ($scope, qxQborrrowHttpService, $modal, $modalInstance, $controller) {
	angular.extend(this, $controller('qxSoggettiController', {$scope: $scope}));
	$scope.popup = true;
	
	$scope.select = function(row) {
    	$modalInstance.close(row);
    }
    
    $scope.close = function () {
    	$modalInstance.close();
	}
	
}]);

