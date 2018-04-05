<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
	
	<h1><jstl:out value="${article.title}"></jstl:out></h1><br/>
	<b><spring:message code="article.publicationDate" /></b>: <jstl:out value="${article.publicationDate}"/><br/>
	<jstl:out value="${article.summary}"></jstl:out><br/>
	<jstl:out value="${article.text}"></jstl:out><br/>
	
		<jstl:forEach var="x" items="${article.pictures}">
			<img src="${x}" alt="image" width="420" height="420">
		</jstl:forEach><br/>

