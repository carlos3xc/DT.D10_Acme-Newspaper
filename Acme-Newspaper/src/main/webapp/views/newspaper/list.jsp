<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<display:table name="rangers" id="row"
	requestURI="ranger/administrator/list.do" pagesize="5">

	<spring:message code="ranger.name" var="name" />
	<display:column property="name" title="${name}" sortable="true" />

	<spring:message code="ranger.surname" var="surname" />
	<display:column property="surname" title="${surname}" sortable="true" />

	<spring:message code="ranger.phone" var="phone" />
	<display:column property="phone" title="${phone}" sortable="true" />

	<spring:message code="ranger.email" var="email" />
	<display:column property="email" title="${email}" sortable="true" />

</display:table>

<security:authorize access="hasRole('ADMINISTRATOR')">

	<a href="ranger/administrator/create.do"><spring:message
			code="ranger.create" /></a>

</security:authorize>
