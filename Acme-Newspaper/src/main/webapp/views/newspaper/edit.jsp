<%--
 * edit.jsp
 *
 * Copyright (C) 2015 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

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

<form:form action="ranger/administrator/edit.do" modelAttribute="ranger">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="trips" />

	<form:hidden path="folders" />
	<form:hidden path="socialIdentities" />
	<form:hidden path="isBanned"/>
	<form:hidden path="isSuspicious"/>
	<form:hidden path="userAccount.authorities"/>

	<spring:message code="ranger.name.example" var="name" />
	<spring:message code="ranger.surname.example" var="surname" />
	<spring:message code="ranger.email.example" var="email" />
	<spring:message code="ranger.phone.example" var="phone" />
	<spring:message code="ranger.address.example" var="address" />

	<form:label path="name">
		<spring:message code="ranger.name" />:
	</form:label>
	<form:input path="name" placeholder="${name}" />
	<form:errors cssClass="error" path="name" />
	<br />

	<form:label path="surname">
		<spring:message code="ranger.surname" />:
	</form:label>
	<form:input path="surname" placeholder="${surname}" />
	<form:errors cssClass="error" path="surname" />
	<br />

	<form:label path="email">
		<spring:message code="ranger.email" />:
	</form:label>
	<form:input path="email" placeholder="${email}" />
	<form:errors cssClass="error" path="email" />
	<br />

	<form:label path="phone">
		<spring:message code="ranger.phone" />:
	</form:label>
	<form:input path="phone" placeholder="${phone}" />
	<form:errors cssClass="error" path="phone" />
	<br />

	<form:label path="address">
		<spring:message code="ranger.address" />:
	</form:label>
	<form:textarea path="address" placeholder="${address}" />
	<form:errors cssClass="error" path="address" />
	<br />

	<form:label path="userAccount.username">
		<spring:message code="ranger.userAccount.username" />:
	</form:label>
	<form:input path="userAccount.username" />
	<form:errors cssClass="error" path="userAccount.username" />
	<br />

	<form:label path="userAccount.password">
		<spring:message code="ranger.userAccount.password" />:
	</form:label>
	<form:input type="password" path="userAccount.password" />
	<form:errors cssClass="error" path="userAccount.password" />
	<br />
<!-- 
	<form:select id="authorities.authority" path="userAccount.authorities">
		<form:option label="----" value="0" />
		<form:options items="${authorities}" itemLabel="authority" />
	</form:select>
	<form:errors cssClass="error" path="userAccount.authorities" /> -->

	<input type="submit" name="save"
		value="<spring:message code="ranger.save" />" />&nbsp; 
		
	<jstl:if test="${ranger.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="ranger.delete" />"
			onclick="return confirm('<spring:message code="ranger.confirm.delete" />')" />&nbsp;
	</jstl:if>

	<input type="button" name="cancel"
		value="<spring:message code="ranger.cancel" />"
		onclick="javascript: window.location.replace('ranger/administrator/list.do')" />
	<br />

</form:form>