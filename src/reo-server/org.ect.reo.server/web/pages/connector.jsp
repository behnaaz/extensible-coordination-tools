<%@ taglib prefix="ajax" uri="javawebparts/ajaxparts/taglib" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="org.ect.reo.*,org.ect.reo.actions.*,org.ect.reo.data.*,org.ect.reo.jobapi.*, java.util.List"%>

<div class="wikipage">

<%
	PageHelper helper = new PageHelper(config);
	Workspace workspace = helper.getWorkspace();	
	
	NetworkIdentifier id = NetworkIdentifier.parse(request.getPathInfo());
	Connector connector = workspace.getConnector(id);
%>

<h1><%= workspace.getResolvedName(id) %></h1>

<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>

</div>