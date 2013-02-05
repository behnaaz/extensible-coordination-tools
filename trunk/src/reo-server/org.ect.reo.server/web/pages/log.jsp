<%@ taglib prefix="ajax" uri="javawebparts/ajaxparts/taglib" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="org.ect.reo.actions.*,org.ect.reo.jobapi.*, java.util.*, java.text.*"%>

<div class="wikipage">

<%
	PageHelper helper = new PageHelper(config);
	
	Calendar cal = Calendar.getInstance();
	SimpleDateFormat sdf = new SimpleDateFormat("MM/dd HH:mm:ss");
	String now = sdf.format(cal.getTime());
%>

<h1><%= helper.getDisplayName() %> Log</h1>

<p>
<div id="log">
<%= helper.getScheduler().printLog() %>
</div>
</p>

<div id="help">
	<i>Last update: <%= now %></i>
</div>

</div>
