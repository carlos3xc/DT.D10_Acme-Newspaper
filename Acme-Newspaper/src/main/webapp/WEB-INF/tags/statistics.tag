<%--
 * statistics.tag
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>
 
<%@ tag language="java" body-content="empty" %>
 
 <%-- Taglibs --%>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<%-- Attributes --%> 
 
<%@ attribute name="titleCode" required="true" %>
<%@ attribute name="code1" required="true" %>
<%@ attribute name="data1" required="true" %>
<%@ attribute name="code2" required="false" %>
<%@ attribute name="data2" required="false" %>



<%-- Definition --%>  
<h3>
	<spring:message code="${titleCode}"/>	
</h3>

<acme:property code="${code1}" data="${data1}"/>

<jstl:if test="${(code2!=null) && (code2!=null)}">
	<acme:property code="${code2}" data="${data2}"/>
</jstl:if>

