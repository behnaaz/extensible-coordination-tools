<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="org.ect.reo.Connector,org.ect.reo.Component,org.ect.reo.PrimitiveEnd,org.ect.reo.actions.*,org.ect.reo.data.*,org.ect.reo.jobapi.*, java.util.List"%>
<%

	PageHelper helper = new PageHelper(config);
	Workspace workspace = helper.getWorkspace();	
	
	NetworkIdentifier id = NetworkIdentifier.parse(request.getPathInfo());
	Network network = workspace.getNetwork(id);
	
	String swf = '/' +helper.getApplicationName() + '/' + AnimationAction.PAGE + '/' + 
				 id + '?' + AnimationAction.INDEX + '=' + AnimationAction.getAnimationIndex(request);
	int width = network.getSWFSize().getWidth();
	int height = network.getSWFSize().getHeight();
	
%>
<object class="animation" width="<%= width %>" height="<%= height %>" 
		classid="CLSID:D27CDB6E-AE6D-11cf-96B8-444553540000" 
		codebase="http://active.macromedia.com/flash2/cabs/swflash.cab#version=4,0,0,0">
	<param name="movie" value="<%= swf %>">
	<param name="quality" value="high">
	<param name="scale" value="exactfit">
	<param name="menu" value="true">
	<embed src="<%= swf %>" width="<%= width %>" height="<%= height %>" 
		type="application/x-shockwave-flash"
		pluginspage="http://www.macromedia.com/shockwave/download/download.cgi?P1_Prod_Version=ShockwaveFlash"
		quality="high" scale="exactfit" menu="true">
	</embed>
</object>
