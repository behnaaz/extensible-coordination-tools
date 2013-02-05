<%@ taglib prefix="ajax" uri="javawebparts/ajaxparts/taglib" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="org.ect.reo.Connector,org.ect.reo.actions.*,org.ect.reo.data.*,org.ect.reo.jobapi.*"%>
<%
	PageHelper helper = new PageHelper(config);
	Workspace workspace = helper.getWorkspace();	
%>

<div class="wikipage">

<h1>Connector List</h1>

<table class="listing reports">

<tr>
	<th>Connector</th>
</tr>

<%																					
	int e = 0;																		
	for (Network network : workspace.getNetworks()) {												
		for (Connector connector : network.getConnectors()) {						
%>
<tr class="<%= (e % 2 == 0) ? "even" : "odd"%>">
	<td> 
	<%= helper.elementLink(workspace.getIdentifier(connector)) %>
	</td>
</tr>
<% 		}															
		e++;														
	}															  %>

</table>
<br/>

<div id="help">
	<strong>Note:</strong>
	See <a href="http://reo.project.cwi.nl/cgi-bin/trac.cgi/reo/wiki/NetworkIdentifiers">NetworkIdentifiers</a> 
	for help on the network IDs.
</div>

</div>
