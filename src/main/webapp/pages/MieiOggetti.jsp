<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="qs2" uri="/quix-strut2-tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
  	<head>
  		<jsp:include page="_head.jsp"></jsp:include>
 		<script type="text/javascript" src="<%=request.getContextPath() %>/scripts/controller/qxOggettiController.js?_<jsp:include page='_version.jsp' />"></script>
		<qs2:attributeMapAngularFilter type="qbo000_categoria" />
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
  	</head>
  	<body ng-app="qborrrow"> 
  		<div class="container-fluid"> 
  			<jsp:include page="_header.jsp" />
	  		<div>
		  		<!-- <div class="frameworkLeftMenuCell">-->
					
  				<div class="frameworkMainCell" ng-controller="qxOggettiController" ng-include="scopeController.selectedPage">
					
  				</div>
  			
  				<script type="text/ng-template" id="list"><jsp:include page="_mieiOggetti1.jsp" /></script>
  				<script type="text/ng-template" id="edit"><jsp:include page="_oggettiEdit.jsp" /></script>
  				<!--<script type="text/ng-template" id="edit"><jsp:include page="_mioOggettoEdit.jsp" /></script>-->
							
			</div>
  		 	<jsp:include page="_footer.jsp" />
	  	</div>	
  	</body>
</html>
