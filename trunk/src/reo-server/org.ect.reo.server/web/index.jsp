<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="org.ect.reo.actions.*,org.ect.reo.data.*"%>
<html>

<% 
PageHelper helper = new PageHelper(config);
String content = "/pages/" + helper.getPageName() + ".jsp";
String name = helper.getDisplayName();

//There must be another way to find out the application name.
String app = helper.getApplicationName();
String trac = helper.getTracHome();

%>

<head>
	<title><%= helper.getTitle() + " - " + name %></title>
	<script src="/<%= app %>/script.js" type="text/javascript"></script>
	<link rel="stylesheet" href="/<%= app %>/style.css" type="text/css" />
	<link rel="stylesheet" href="<%= trac %>/chrome/common/css/trac.css" type="text/css" />
	<link rel="stylesheet" href="<%= trac %>/chrome/common/css/wiki.css" type="text/css" />
	<link rel="stylesheet" href="<%= trac %>/chrome/site/reo.css" type="text/css" />
    <link rel="shortcut icon" href="<%= trac %>/chrome/site/images/reo-icon.gif" type="image/gif" />
    <link rel="icon" href="<%= trac %>/chrome/site/images/reo-icon.gif" type="image/gif" />	
    
    <script type="text/javascript">
    /* The name of the current page. */
	var page = "<%= helper.getPageName() %>";
	
	/* Aliases for starting and stopping the progress monitor. */
	function startMonitor() { startReo_monitor(); }
	function stopMonitor() { setTimeout("stopReo_monitor()", 500); }
	</script>
</head>

<body>

<div id="banner">
	<div id="header">
	<a id="logo" href="/<%= app %>"><img src="/<%= app %>/images/reo-live-logo.png" alt="reo.project.cwi.nl/<%= app %>" height="85" width="420" /></a>
	</div>
	<div id="metanav" class="nav">
	<ul>
		<li class="first">
		<a href="/<%= app %>/log">View log</a></li>
	</ul>
	</div>
	<div id="monitor">
	</div>
</div>

<div id="mainnav" class="nav">
<jsp:include page="/mainnav.jsp" />
</div>


<div id="main">

<div id="ctxtnav" class="nav">
<hr />
<jsp:include page="/ctxtnav.jsp" />
</div>

<div id="content" class="wiki">
<jsp:include page="<%= content %>" />
</div>

</div>

<div id="footer">
<hr />
<jsp:include page="/footer.jsp" />
</div>

<%-- This frame is used as the target form submits (e.g. the file upload). --%>
<iframe id='null' name='null' src='' style="display:none"></iframe>

<%-- AJAX configuration. --%>
<jsp:include page="/refresh.jsp" />

</body>
</html>
 