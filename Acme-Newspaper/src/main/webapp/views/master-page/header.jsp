<%--
 * header.jsp
 *
 * Copyright (C) 2015 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<div>
	<img src="images/logo.png" alt="Acme-Newspaper Co., Inc." />
</div>

<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		<security:authorize access="hasRole('ADMIN')">
			<li><a class="fNiv"><spring:message	code="master.page.administrator" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="administrator/dashboard.do"><spring:message code="master.page.administrator.dashboard" /></a></li>
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('USER')">
			<li><a class="fNiv"><spring:message code="master.page.newspaper" /></a>	
			<ul>
					<li class="arrow"></li>
					<li><a href="newspaper/list.do"><spring:message code="master.page.user.newspaper.list" /></a></li>
					<li><a href="newspaper/user/create.do"><spring:message code="master.page.user.newspaper.create" /></a></li>
			</ul>
			<li><a class="fNiv"><spring:message code="master.page.article" /></a>	
			<ul>
					<li class="arrow"></li>
					<li><a href="article/user/create.do"><spring:message code="master.page.user.article.create" /></a></li>
			</ul>
		</security:authorize>
		
		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="security/login.do"><spring:message code="master.page.login" /></a></li>
			<li><a class="fNiv" href="register/create.do"><spring:message code="master.page.register" /></a></li>
			<li><a class="fNiv" href="newspaper/list.do"><spring:message code="master.page.newspaper.list" /></a></li>			
			<li><a class="fNiv" href="user/list.do"><spring:message code="master.page.user.list" /></a></li>					
		</security:authorize>
		
		<security:authorize access="permitAll">
			<li><a class="fNiv"><spring:message code="master.page.search" /></a>	
			<ul>
				<li class="arrow"></li>
					<li><a href="article/search.do"><spring:message code="master.page.article" /></a></li>
					<li><a href="newspaper/search.do"><spring:message code="master.page.newspaper" /></a></li>				
			</ul>		
		</security:authorize>
		<security:authorize access="isAuthenticated()">
			<li>
				<a class="fNiv"> 
					<spring:message code="master.page.profile" /> 
			        (<security:authentication property="principal.username" />)
				</a>
				<ul>
					<li class="arrow"></li>
					<li><a href="profile/info.do"><spring:message code="master.page.profile" /></a></li>
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
				</ul>
			</li>
		</security:authorize>
	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

