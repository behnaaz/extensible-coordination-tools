<%@ taglib prefix="ajax" uri="javawebparts/ajaxparts/taglib" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="org.ect.reo.Connector,org.ect.reo.Component,org.ect.reo.actions.*,org.ect.reo.data.*,org.ect.reo.jobapi.*, java.util.List"%>

<div class="wikipage">

<%
	PageHelper helper = new PageHelper(config);
	Workspace workspace = helper.getWorkspace();	
	
	NetworkIdentifier id = NetworkIdentifier.parse(request.getPathInfo());
	Network network = workspace.getNetwork(id);
%>

<h1><%= workspace.getResolvedName(id) %></h1>

<table>

<tr>

<td valign="top" width="40%">

<h3>Connectors</h3>
<ul>
<%	for (Connector connector : network.getConnectors()) {										
		NetworkIdentifier connectorId = workspace.getIdentifier(connector);  %>
	<li><%= helper.elementLink(connectorId) %></li>
<%	} %>
</ul>
<br />

<h3>Components</h3>
<ul>
<%	for (Component component : network.getComponents()) {										
		NetworkIdentifier componentId = workspace.getIdentifier(component);  %>
	<li><%= helper.elementLink(componentId) %></li>
<%	} %>
</ul>
<br />

<h3>Animations</h3>
<ul>
<%
 for (int i=1; i<network.getAnimations().size(); i++) {
	String anim = '/' + helper.getApplicationName() + '/' + PageHelper.PAGE_NETWORKS + '/' + id + '?' + 
				 AnimationAction.INDEX + '=' + i;	%>
	<li><a href="<%= anim %>">Animation <%= i %></a> (<%= network.getAnimations().get(i).size() %> steps)</li>
<% } %>
</ul>
<br />

<!-- 

<h3>Reconfigurations</h3>

Rules applied so far:
<br />
 -->

<script type="text/javascript">

function refreshPage() {
	newTarget='/<%= helper.getApplicationName() %>/refresh/networks/<%= id %>';
	ajaxPartsTaglib.events['Reo/networkmanual'].targURI=newTarget;
	startMonitor();
}

</script>

<!-- 
<form action="/%= helper.getApplicationName() %/actions/reconfiguration"
		target="null" onSubmit="refreshPage(); return true;">

<input type="hidden" id="%= ReconfigurationAction.NETWORK %" name="%= ReconfigurationAction.NETWORK %>" value="<= id %>">
<input type="hidden" id="%= ReconfigurationAction.ACTION %" name="%= ReconfigurationAction.ACTION %>" value="<= ReconfigurationAction.APPLY %>">

<p>
Apply rule the following to this network: 
<select id="<= ReconfigurationAction.RULE %>" name="<= ReconfigurationAction.RULE %>" size="1">

int ruleID = 0;
for (ReconfigurationRule rule : workspace.getReconfigurationRules()) {

	<option value="%= ruleID >">= rule.getName() ></option>

	ruleID++;
}

%>
</select>
 &nbsp; 
<input type="submit" value="apply">
</p>
</form>
 -->

</td>

<td valign="top" width="60%" align="center">
<jsp:include page="/pages/animation.jsp"/>
</td>

</tr>
</table>


<br />

<h3>Constraint automaton</h3>
<%
	String reo2ea = '/' +helper.getApplicationName() + '/' + Reo2EaAction.PAGE + '/' + id;
	String ea = Reo2EaAction.TYPE + '=' + Reo2EaAction.EA;
	String cat = Reo2EaAction.TYPE + '=' + Reo2EaAction.CAT;
%>

<p>
Using the following links you can convert this this network to a constraint automaton. 
Note that all connectors will be merged together and a single constraint automaton is generated.
</p>

<p>
Choose your preferred file format:
<ul>
	<li><a href="<%= reo2ea + "?" + ea  %>">EA</a> (XML format for the graphical editor) </li>
	<li><a href="<%= reo2ea + "?" + cat %>">CAT</a> (textual format) </li>
</ul>
</p>

</div>

