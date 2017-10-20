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
					<div class="qpage-header >
					<h1>Benvenuto,  <s:property value="userContext.getUserText()" /></h1>
					</div>
					<div class="qrow">
						 <div class="row">
    				
						<div class="qcol-md-3">
							
								<div><i class="fa icon fa-plus-square-o " aria-hidden="true"></i></div>
								<div class="tab"><s:a href="%{#oggettiUrl}">Oggetti</s:a></div>
								<s:url id="oggettiUrl" action="oggetti" escapeAmp="false" includeParams="none">
									<s:param name="task">mainPage</s:param>
								</s:url>
								
						</div>
						<div class="qcol-md-3">
							
								<div><i class="fa icon fa-eur " aria-hidden="true"></i></div>
								<div class="tab"><s:a href="%{#prestitiUrl}">Prestiti</s:a>
								<s:url id="prestitiUrl" action="prestiti" escapeAmp="false" includeParams="none">
									<s:param name="task">mainPage</s:param>
								</s:url></div>
								
						</div>
						<div class="qcol-md-3">
								<div><i class="fa icon fa-user" aria-hidden="true"></i></div>
								<div class="tab"><s:a href="%{#soggettiUrl}">Soggetti</s:a>
								<s:url id="prestitiUrl" action="prestiti" escapeAmp="false" includeParams="none">
									<s:param name="task">mainPage</s:param>
								</s:url></div>
								
								
						</div>
						<div class="qcol-md-3">
							
								<div><i class="fa fa-address-book-o" aria-hidden="true"></i></div>
								<div class="tab"><s:a href="%{#frmkUrlUrl}">Amministrazione Framework</s:a>
								<s:url id="prestitiUrl" action="prestiti" escapeAmp="false" includeParams="none">
									<s:param name="task">mainPage</s:param>
								</s:url></div>
								
						</div>
						<div class="qcol-xs-3">
							<ul class="qnav qnav-pills qnav-stacked">
								<s:url id="oggettiUrl" action="oggetti" escapeAmp="false" includeParams="none">
									<s:param name="task">mainPage</s:param>
								</s:url>
								<li><s:a href="%{#oggettiUrl}">Oggetti</s:a></li>
								<s:url id="prestitiUrl" action="prestiti" escapeAmp="false" includeParams="none">
									<s:param name="task">mainPage</s:param>
								</s:url>
								<li><s:a href="%{#prestitiUrl}">Prestiti</s:a></li>
								<s:url id="soggettiUrl" action="soggetti" escapeAmp="false" includeParams="none">
									<s:param name="task">mainPage</s:param>
								</s:url>
								<li><s:a href="%{#soggettiUrl}">Soggetti</s:a></li>						
								<s:url id="frmkUrl" namespace="/framework" action="admin" escapeAmp="false" includeParams="none">
				  					<s:param name="version">2</s:param>
				  				</s:url>
				  				<li><s:a href="%{#frmkUrlUrl}">Amministrazione Framework</s:a></li>
							</ul>
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
