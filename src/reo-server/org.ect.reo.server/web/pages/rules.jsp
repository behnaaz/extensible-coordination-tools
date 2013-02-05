<%@ taglib prefix="ajax" uri="javawebparts/ajaxparts/taglib" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="org.ect.reo.actions.*,org.ect.reo.data.*,org.ect.reo.jobapi.*"%>
<%
	PageHelper helper = new PageHelper(config);
	Workspace workspace = helper.getWorkspace();	
%>

<div class="wikipage">

<h1>Reconfiguration Rules (currently not supported)</h1>

<script type="text/javascript">

function disableActions() {
	disable('remove');
	disable('upload');
}

function updateRules(action) {
	disableActions();
	document.getElementsByName("<%=RuleAction.ACTION%>")[0].value = action;
	document.RulesForm.submit();
	startMonitor();
}

</script>

<form name="RulesForm" action="/<%=helper.getApplicationName()%>/actions/rule" target="null" method="post">

<table class="listing reports">

<tr>
	<th>Reconfiguration Rule</th>
</tr>

<!-- 
% 
	int e = 0;												
	for (ReconfigurationRule rule : workspace.getReconfigurationRules()) {
%
<tr class="%=(e % 2 == 0) ? "even" : "odd"%>">
	<td> 
	<input type="checkbox" name="rule[%=e%>]" value="true">
	%= rule.getName() %>
	</td>
</tr>
%
	e++;													
	}
%
 -->
</table>
<br/>

<input type="hidden" name="<%=RuleAction.ACTION%>">

<input type="button" id="remove" value="remove" onclick="updateRules('<%=RuleAction.REMOVE%>')">

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

</div>
