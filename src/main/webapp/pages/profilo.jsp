<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="qs2" uri="/quix-strut2-tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
  	
  		<head>
  		<jsp:include page="_head.jsp"></jsp:include>
 		<script type="text/javascript" src="<%=request.getContextPath() %>/scripts/controller/qxOggettiController.js?_<jsp:include page='_version.jsp' />"></script>
 		<script type="text/javascript" src="<%=request.getContextPath() %>/scripts/controller/qxPrestitiController.js?_<jsp:include page='_version.jsp' />"></script>
 		<script type="text/javascript" src="<%=request.getContextPath() %>/scripts/controller/qxSoggettiController.js?_<jsp:include page='_version.jsp' />"></script>
  		<script type="text/javascript">
	var module = angular.module('qborrrow');
	module.constant('labelService', {
	  	'swalWarnDeleteTitle':'<s:property value="getText('label.swalWarnDeleteTitle')" escapeJavaScript="true" escapeHtml="false"/>',
		'swalWarnDeleteMessage':'<s:property value="getText('label.swalWarnDeleteMessage')" escapeJavaScript="true" escapeHtml="false"/>',
		'swalWarnDeleteButton':'<s:property value="getText('label.swalWarnDeleteButton')" escapeJavaScript="true" escapeHtml="false"/>',
		'swalSuccDeleteTitle':'<s:property value="getText('label.swalSuccDeleteTitle')" escapeJavaScript="true" escapeHtml="false"/>',
		'swalSuccDeleteMessage':'<s:property value="getText('label.swalSuccDeleteMessage')" escapeJavaScript="true" escapeHtml="false"/>'
	});
		</script>
	<link  rel="stylesheet" href="<%=request.getContextPath() %>/themes/style.css?_<jsp:include page='_version.jsp' />">
  	</head>
  
  	<body ng-app="qborrrow"> 
  		<div class="qcontainer-fluid"> 
  			<jsp:include page="_header.jsp" />
  			<div class="qcontainer">
	  		<div class="qrow">
		  		
  				<div class="qcol-x-12">
  				<div class="frameworkMainCell">
				
					<div class="qrow">
					<div class="qcol-md-12" ng-controller="qxSoggettiController">
						
					
						<form ng-init="editProfilo(row)" name="forms.soggettiEditForm"  novalidate>
						
										
										<table width= "80%" align ="center">
										
										 <tr>
										    <td width= "30%" >Ragione Sociale</td>
										    <td width= "30%" margin-left="25px" ><input class="qform-control" type="text" name="" ng-model="scopeController.selectedRow.ragioneSociale"></td>
										    <td width= "30%" margin-left="40px"   rowspan="4" align="right"><img class ="userImmage" name="immagine" id="immagine" ng-src ="{{scopeController.selectedRow.immagine}}" height="200px" width="200px" /></td>
										    
										  </tr>
										  <tr>
										    <td width= "30%"  >Nome</td>
										    <td width= "30%"  margin-left="25px"><input class="qform-control" type="text" name="nome" ng-model="scopeController.selectedRow.nome"></td>
										    <td width= "30%"></td>
										  </tr>
										  <tr>
										    <td width= "30%" >Cognome </td>
										    <td width= "30%" margin-left="25px"><input class="qform-control" type="text" name="cognome" ng-model="scopeController.selectedRow.cognome"></td>
										  </tr>
										  <tr>
										    <td width= "30%" >Email</td>
										    <td width= "30%" margin-left="25px" ng-class="{'qhas-error': forms.soggettiEditForm.email.$invalid}" class="qhas-error"><input class="qform-control ng-valid-parse ng-touched ng-not-empty ng-pristine ng-invalid ng-invalid-not-valid" id="email" type="text" name="email" ng-model="scopeController.selectedRow.email"
										    	ng-class="{'qhas-error': forms.soggettiEditForm.email.$invalid}"
										    	id="email" 
												name="email"
												class="qform-control" 
											    pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$"
												required
												/>
												
												<div  class="qhas-error" ng-messages="forms.soggettiEditForm.email.$error" role="alert">
														<div ng-message="required"><s:text name="error.notNull"/></div>
													  	<div ng-message="notNull"><s:text name="error.notNull"/></div>
													  	<div ng-message="invalidAK"><s:text name="error.invalidAK"/></div>
													  	<div ng-message="notValid"><s:text name="error.notValid"/></div>
													  	<div ng-message="lenght"><s:text name="error.lenght"/></div>
													  	<div ng-message="dateToBeforeDateFrom"><s:text name="error.dateToBeforeDateFrom"/></div>
													  	<div ng-message="fieldToBeforeFieldFrom"><s:text name="error.fieldToBeforeFieldFrom"/></div>
													  	<div ng-message="notUnique"><s:text name="error.notUnique"/></div>
													  	<div ng-message="min"><s:text name="error.min"/></div>
													  	<div ng-message="max"><s:text name="error.max"/></div>
													  	<div ng-message="ognl"><s:text name="error.ognl"/></div>
													  	<div ng-message="pattern"><s:text name="error.pattern"/></div>
													  	<div ng-message="notBlank"><s:text name="error.notBlank"/></div>
													  	<div ng-message="qvpattern.message"><s:text name="error.qvpattern.message"/></div>
													  	<div ng-message="string.length"><s:text name="error.string.length"/></div>
													</div>
													
												</td>
													
										  </tr>
										  <tr>
										    <td width= "30%" >Data di Nascita</td>
										    <td width= "30%" margin-left="25px"><div>
										    		<div class="qdropdown">
							<a class="qdropdown-toggle" id="dropdown2" role="qbutton" data-toggle="qdropdown" data-target="#" href="#">
								<div class="qinput-group">
						    		<input type="text" name="dataCompleanno" id="dataCompleanno" class="qform-control box-input-calendar" data-ng-model="scopeController.selectedRow.dataCompleanno" presetDate="true" dateformat="DD/MM/YYYY">
						    		<span class="qinput-group-addon"><i class="fa fa-calendar"></i></span>
						    	</div>
						  	</a>
						  	<ul class="qdropdown-menu" role="qmenu" aria-labelledby="dLabel">
						    	<datetimepicker data-ng-model="scopeController.selectedRow.dataCompleanno" data-datetimepicker-config="{ minView:'day', modelType: 'Date' }"/>
						  	</ul>
						</div></div></td>
										  </tr>
										  <tr>
										    <td></td></br></br></br></br>
										    <td align="right">
													<button ng-click="saveProfile()"
                                           class="qbtn btn-framework-color" data-datetimepicker-config="{ minView:'day', modelType: 'Date' }"><i class="fa fa-floppy-o"></i>&nbsp;<s:text name="button.save"/></button>
										   </td>
										    <td width= "30%" margin-left="25px" align="left"></td>
										    
										    
										  </tr>
										  
										</table>
										
  										
  										
						</form>
					</div>
					</div>
  				</div>
  				</div>
			</div>
			</div>
  		 	<jsp:include page="_footer.jsp" />
	  	</div>	
	  	
  	</body>
</html>
