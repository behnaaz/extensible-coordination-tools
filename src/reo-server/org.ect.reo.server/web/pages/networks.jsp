<%@ taglib prefix="ajax" uri="javawebparts/ajaxparts/taglib" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="org.ect.reo.Connector,org.ect.reo.Component,org.ect.reo.actions.*,org.ect.reo.data.*,org.ect.reo.jobapi.*"%>
<%
	PageHelper helper = new PageHelper(config);
	Workspace workspace = helper.getWorkspace();	
%>

<div class="wikipage">

<h1>Network List</h1>

<script type="text/javascript">

function disableActions() {
	disable('start');
	disable('stop');
	disable('remove');
	disable('upload');
}

function updateNetworks(action) {
	disableActions();
	document.getElementsByName("<%=NetworkAction.ACTION%>")[0].value = action;
	document.NetworksForm.submit();
	startMonitor();
}

</script>

<form name="NetworksForm" action="/<%=helper.getApplicationName()%>/actions/network" target="null" method="post">

<table class="listing reports">

<tr>
	<th>Network</th>
	<th>Connectors</th>
	<th>Components</th>
	<th>Status</th>
</tr>

<%
	int e = 0;												
	for (Network network : workspace.getNetworks()) {
		NetworkIdentifier identifier = workspace.getIdentifier(network);
%>
<tr class="<%=(e % 2 == 0) ? "even" : "odd"%>">
	<td> 
	<input type="checkbox" name="network[<%=identifier%>]" value="true">
	<%=helper.elementLink(identifier)%>
	</td>
	<td>
	<%
		for (int i=0; i<network.getConnectors().size(); i++) {
			Connector connector = network.getConnectors().get(i);
	%>
		<%=helper.elementLink( workspace.getIdentifier(connector) )%>
		<%
			if (i!=network.getConnectors().size()-1) {
		%>, <%
			}
		%>
	<%
		}
	%>
	</td>
	<td>
	<%
		for (int i=0; i<network.getComponents().size(); i++) {
			Component component = network.getComponents().get(i);
	%>
		<%=helper.elementLink( workspace.getIdentifier(component))%>
		<%
			if (i!=network.getComponents().size()-1) {
		%>, <%
			}
		%>
	<%
		}
	%>
	</td>
	<td class="<%=network.getStatus()%>"><%=network.getStatus()%></td>
</tr>
<%
	e++;													
	}
%>

</table>
<br/>

<input type="hidden" name="<%=NetworkAction.ACTION%>">

<input type="button" id="start" value="start" onclick="updateNetworks('<%=NetworkAction.START%>')">
<input type="button" id="stop" value="stop" onclick="updateNetworks('<%=NetworkAction.STOP%>')">
<input type="button" id="remove" value="remove" onclick="updateNetworks('<%=NetworkAction.REMOVE%>')">

</form>


<form action="/<%= helper.getApplicationName() %>/actions/upload"
		name="UploadForm"
		target="null"
		method="post"
		enctype="multipart/form-data" 
		onSubmit="disableActions(); startMonitor(); return true;">
<p>
Upload *.reo file: <input type="file" id="reofile" name="reofile" size="30" > &nbsp; 
<input type="submit" id="upload" value="submit">
</p>
</form>


<form action="/<%= helper.getApplicationName() %>/actions/changeID"
		target="null"
		onSubmit="disableActions(); startMonitor(); return true;">
<p>
Change network ID from <input type="text" id="from" name="from" size="4"> to 
<input type="text" id="to" name="to" size="4">. &nbsp; 
<input type="submit" id="upload" value="apply">
</p>
</form>



<div id="help">
<strong>Note:</strong>
See <a href="http://reo.project.cwi.nl/cgi-bin/trac.cgi/reo/wiki/NetworkIdentifiers">NetworkIdentifiers</a> 
for help on the network IDs.
</div>

</div>
