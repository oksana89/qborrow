<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="qs2" uri="/quix-strut2-tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
  	<head>
  		<jsp:include page="_head.jsp"></jsp:include>
  	</head>
  	<body ng-app="qborrrow"> 
  		<div class="qcontainer-fluid"> 
  			<jsp:include page="_header.jsp" />
  			<div class="qcontainer">
	  		<div class="qrow">
		  		
  				<div class="qcol-x-12">
  				<div class="frameworkMainCell">
					<div class="qpage-header class="benvenuto" >
					
					<h1>Benvenuto,  <s:property value="userContext.getUserText()" /> ! </h1>
					</div>
					<div class="qrow">
					<div class="qcol-md-12>
						
					</div>
						<form >
  										Ragione Sociale <input type="text" name="ragioneSociale"><br>
  										Nome <input type="text" name="nome"><br>
  										Cognome <input type="text" name="cognome"><br>
  										Email<input type="text" name="email"><br>
  										Data Compleanno<input type="text" name="email"><br>
  										<input type=”submit” value=”Annula”>
  										<input type="submit" value="Salva">
						</form>
					</div>
  				</div>
  				</div>
			</div>
			</div>
  		 	<jsp:include page="_footer.jsp" />
	  	</div>	
	  	
  	</body>
</html>
