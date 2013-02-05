<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="org.ect.reo.actions.PageHelper"%>
<%

PageHelper helper = new PageHelper(config);
String app = helper.getApplicationName();

%>
<h2>Context Navigation</h2>
<ul>
  <li class="first "><a href="/<%= app %>/networks">Networks</a></li>
  <li><a href="/<%= app %>/connectors">Connectors</a></li>
  <li><a href="/<%= app %>/components">Components</a></li>
<!-- <li class="last"><a href="/<%= app %>/rules">Rules</a></li>  -->
</ul>
