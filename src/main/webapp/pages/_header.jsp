<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="qs2" uri="/quix-strut2-tags"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<s:url id="homeUrl" namespace="/" action="home" escapeAmp="false"
	includeParams="none">
	<s:param name="version">2</s:param>
</s:url>
<s:url id="oggettiUrl" namespace="/" action="oggetti" escapeAmp="false"
	includeParams="none">
	<s:param name="task">mainPage</s:param>
</s:url>
<s:url id="mieiOggettiUrl" namespace="/" action="oggetti"
	escapeAmp="false" includeParams="none">
	<s:param name="task">mieiOggetti</s:param>
</s:url>
<s:url id="prestitiUrl" namespace="/" action="prestiti"
	escapeAmp="false" includeParams="none">
	<s:param name="task">mainPage</s:param>
</s:url>
<s:url id="soggettiUrl" namespace="/" action="soggetti"
	escapeAmp="false" includeParams="none">
	<s:param name="task">mainPage</s:param>
</s:url>
<s:url id="frmkUrl" namespace="/framework" action="admin"
	escapeAmp="false" includeParams="none">
	<s:param name="version">2</s:param>
</s:url>
<s:url id="homeUrl" namespace="/" action="home" escapeAmp="false"
	includeParams="none">
</s:url>
<s:url id="logoutUrl" action="logout" includeParams="none"
	escapeAmp="false">
</s:url>
<s:url id="profiloUrl" namespace="/" action="soggetti"
	escapeAmp="false" includeParams="none">
	<s:param name="task">profilo</s:param>
</s:url>
<div class="qrow">

	<nav class="qnavbar qnavbar-default" role="navigtion">

		<div class="qnavbar-header">
			<button type="button" class="qnavbar-toggle" data-toggle="qcollapse"
				data-target="#bs-example-navbar-collapse-1">
				<span class="qsr-only">Toggle navigation</span> <span
					class="qicon-bar"></span> <span class="qicon-bar"></span> <span
					class="qicon-bar"></span>
			</button>
			<a class="navbar-brand" href="#"> <img
				onclick="document.location.href='<s:property value="#homeUrl"/>';"
				src="<qs2:imagesPath nome="/logo-qfrmk.png" version="2"/>">
			</a>
		</div>
		<div class="qcollapse qnavbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="qnav qnavbar-nav">
				<li><a class="framework-sidebar-menu-element-link"
					href="<s:property value="#homeUrl" escape="false" />"> <s:text
							name="menu.home" />
						<li><a class="framework-sidebar-menu-element-link"
							href="<s:property value="#oggettiUrl" escape="false" />">oggetti
						</a></li>
						<li><a class="framework-sidebar-menu-element-link"
							href="<s:property value="#mieiOggettiUrl" escape="false" />">
								oggetti struts </a></li>
						<li><a class="framework-sidebar-menu-element-link"
							href="<s:property value="#soggettiUrl" escape="false" />">
								soggetti </a></li>
						<li><a class="framework-sidebar-menu-element-link"
							href="<s:property value="#prestitiUrl" escape="false" />">
								prestiti </a></li>
			</ul>
			<ul class="qnav qnavbar-nav qnavbar-right">
				<li>
					<div class="frameworkHeaderRightCellUserDiv qpull-right">
						<a href="#" class="qdropdown-toggle user-menu-link"
							data-toggle="qdropdown">
							<div
								style="display: inline-block; padding-left: 30px; padding-top: 10px;">
								<i class="fa fa-user" aria-hidden="true"></i><span
									class="hidden-xs"><s:property
										value="userContext.getUserText()" /></span>
							</div>
						</a>
						<ul class="qdropdown-menu user-dropdown-menu">
							<li class="user-header"><img
								src="<qs2:imagesPath nome="/user.jpg" version="2"/>"
								class="qimg-circle" alt="<s:text name="header.userimage"/>" />
								<p class="user-header-p">
									<br>
									<s:property value="userContext.getUserText()" />
									-
									<s:if test="userContext.isUserInRole('framework-admin')">
										<s:text name="header.adminRole" />
									</s:if>
									<s:else>
										<s:text name="header.userRole" />
									</s:else>
									<BR> <small><s:text name="header.membersince" />
										<s:property
											value="userContext.getUserCreateDate(getText('format.date'))" /></small>
								</p></li>
							<li class="user-footer">
								<div class="qpull-left">
									<!--<a href=""
										onclick="javascript:window.open('/puma/mydetailedit.do?dn=<s:property value="userContext.realUserDn"/>', 'detailPuma', 'width=600,height=600,statusbar=no,toolbar=no,scrollbars=no,resizable=yes')"
										class="qbtn qbtn-default qbtn-flat"><s:text
											name="header.profile" /></a>-->
									
									<!-- <div class="tab"><s:a href="%{#profilo}">Profilo</s:a></div>-->
									<a class="qbtn qbtn-default qbtn-flat"
									href="<s:property value="#profiloUrl" escape="false" />"/>
								profilo  </a>



								</div>
								<div class="qpull-right">
									<a href="<s:property value="#logoutUrl" escape="false" />"
										  class="qbtn qbtn-default qbtn-flat"><s:text
											name="header.signout" /></a>
								</div>
							</li>
						</ul>
					</div>

				</li>
			</ul>
		</div>
	</nav>

	<!-- <a class="navbar-brand" href="#">Brand</a>-->

	<!--  <div class="frameworkLogoCell">
  		 			<img onclick="document.location.href='<s:property value="#homeUrl"/>';" src="<qs2:imagesPath nome="/logo-qfrmk.png" version="2"/>">
  		 		</div>-->
	<div class="">

		<!--<div class="frameworkHeaderRightCellUserDiv qpull-right" style=" background: url('<qs2:imagesPath nome="/user.jpg" version="2"/>') left center no-repeat; background-size: 30px 30px; min-width: 30px;">
  		 				<a href="#" class="qdropdown-toggle user-menu-link" data-toggle="qdropdown">
	               			<div style="display: inline-block;padding-left: 30px;padding-top: 10px;">
	                  			<span class="hidden-xs"><s:property value="userContext.getUserText()"/></span>
	                  		</div>
	               		</a>
	               		<ul class="qdropdown-menu user-dropdown-menu">
	                   		<li class="user-header">
	                        	<img src="<qs2:imagesPath nome="/user.jpg" version="2"/>" class="qimg-circle" alt="<s:text name="header.userimage"/>" />
	                            <p class="user-header-p">
	                              	<br>
	                              	<s:property value="userContext.getUserText()"/> - 
	                              	<s:if test="userContext.isUserInRole('framework-admin')"><s:text name="header.adminRole"/></s:if>
	                              	<s:else><s:text name="header.userRole"/></s:else><BR>
	                              	<small><s:text name="header.membersince" /> <s:property value="userContext.getUserCreateDate(getText('format.date'))" /></small>
	                            </p>
	                     	</li>
	                     	<li class="user-footer">
	                        	<div class="qpull-left">
	                            	<a href="" onclick="javascript:window.open('/puma/mydetailedit.do?dn=<s:property value="userContext.realUserDn"/>', 'detailPuma', 'width=600,height=600,statusbar=no,toolbar=no,scrollbars=no,resizable=yes')" class="qbtn qbtn-default qbtn-flat"><s:text name="header.profile" /></a>
	                            </div>
	                            <div class="qpull-right">
	                              	<a href="<s:property value="#logoutUrl" escape="false" />" class="qbtn qbtn-default qbtn-flat"><s:text name="header.signout" /></a>
	                            </div>
	                     	</li>
	                  	</ul>
  		 			</div>-->
	</div>
</div>
