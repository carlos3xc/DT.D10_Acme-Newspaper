<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
		
	<b><spring:message code="actor.name" /></b>: <jstl:out value="${actor.name}"></jstl:out><br/>
	<b><spring:message code="actor.surname" /></b>: <jstl:out value="${actor.surname}"></jstl:out><br/>
	<b><spring:message code="actor.email" /></b>: <jstl:out value="${actor.email}"></jstl:out><br/>
	<b><spring:message code="actor.phone" /></b>: <jstl:out value="${actor.phoneNumber}"></jstl:out><br/>
	<b><spring:message code="actor.address" /></b>: <jstl:out value="${actor.postalAddress}"></jstl:out><br/>	
	
	<jstl:if test="${OK == 'OK' }">
	<spring:url var="url" value="profile/edit.do">
		<spring:param name="actorId" value="${actor.id}"/>
	</spring:url>
	<button type="button" onclick="javascript: window.location.replace('${url}')"><spring:message code="actor.edit"/></button>
	</jstl:if>
	
	<security:authorize access="hasRole('USER')">
	<h3>	<spring:message code="user.articles" /></h3><br/>
	<display:table name="articles" id="row" requestURI="actor/articles/list.do" pagesize="5">
	
		<spring:message code="article.title" var="titleHeader"/>
		<display:column property="title" title="${titleHeader}" sortable="false" />
		
		<spring:message code="article.summary" var="summaryHeader"/>
		<display:column property="summary" title="${summaryHeader}" sortable="false" />
		
		<spring:message code="article.publicationDate" var="publicationDateHeader" />
		<spring:message code="article.publicationDate.format" var="formatMoment"/>
		<display:column property="publicationDate" title="${publicationDateHeader }"  sortable="true"  format="{0,date,${formatMoment} }" />

	</display:table>
	</security:authorize>
	
	
		
	
