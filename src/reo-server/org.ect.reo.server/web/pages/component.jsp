<%@ taglib prefix="ajax" uri="javawebparts/ajaxparts/taglib" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="org.ect.reo.*,org.ect.reo.actions.*,org.ect.reo.data.*,org.ect.reo.jobapi.*, java.util.List"%>

<div class="wikipage">

<%
	PageHelper helper = new PageHelper(config);
	Workspace workspace = helper.getWorkspace();	
	
	NetworkIdentifier id = NetworkIdentifier.parse(request.getPathInfo());
	Component component = workspace.getComponent(id);
	ComponentDescriptor descriptor = new ComponentDescriptor(component, helper);
%>

<h1><%= workspace.getResolvedName(id) %></h1>

<h2>Ports</h2>

<ul>
<%
List<PrimitiveEnd> ends = component.getAllEnds();				
for (int i=0; i<ends.size(); i++) {	%>
	<li><%= helper.elementLink( workspace.getIdentifier(ends.get(i)) ) %></li>
<% } %>
</ul>


<h2>Implementation</h2>

<%
String client = "/" + helper.getApplicationName() + "/" + ClientImplAction.PAGE + "/" + id;
%>


<form action="<%= client %>" method="get">
<p>
Here you can download stubs for implementing this component:
<ul>
<li><%= descriptor.componentName %>.zip</li>
</ul>
</p>

<%
if (!ClientImplAction.adminNotified(request)) {
%>
<p>
We kindly ask you to provide us with the following information.
We do not store this data or give to a third party.
</p>
<p>
<b>Name:</b> <input type="text" id="name" name="name" size="14"><br> 
<b>Affiliation:</b> <input type="text" id="affiliation" name="affiliation" size="14"><br> 
</p>
<%
}
%>
<input type="submit" id="submit" value="download">
</form>


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
