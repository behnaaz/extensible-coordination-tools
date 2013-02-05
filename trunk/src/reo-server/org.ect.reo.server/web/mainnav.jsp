<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="org.ect.reo.actions.PageHelper"%>
<%

PageHelper helper = new PageHelper(config);
String app = helper.getApplicationName();
String name = application.getServletContextName();
String trac = "http://reo.project.cwi.nl/cgi-bin/trac.cgi/reo";

%>
<ul>
  <li class="first active"><a href="<%= trac %>/wiki">About</a></li>
  <li><a href="<%= trac %>/wiki/Publications">Publications</a></li>
  <li><a href="<%= trac %>/wiki/Tools">Tools</a></li>
  <li><a href="<%= trac %>/wiki/Development">Development</a></li>
  <li><a href="<%= trac %>/wiki/MailingLists">Mailing Lists</a></li>
  <li class="last"><a href="/<%= app %>"><%= name %></a></li>
</ul>
