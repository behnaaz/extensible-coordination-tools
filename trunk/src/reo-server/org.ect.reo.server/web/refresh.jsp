<%@ taglib prefix="ajax" uri="javawebparts/ajaxparts/taglib" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="org.ect.reo.actions.*,org.ect.reo.data.*,org.ect.reo.jobapi.*"%>


<%-- Page refresh actions. --%>
<ajax:manual ajaxRef="Reo/networks" function="refreshNetworks" />
<ajax:manual ajaxRef="Reo/network" function="refreshNetwork" />
<ajax:manual ajaxRef="Reo/connectors" function="refreshConnectors" />
<ajax:manual ajaxRef="Reo/components" function="refreshComponents" />
<ajax:manual ajaxRef="Reo/rules" function="refreshRules" />
<ajax:manual ajaxRef="Reo/log" function="refreshLog" />

<%-- Monitoring action. --%>
<ajax:timer ajaxRef="Reo/monitor" startOnLoad="false" frequency="700" />

<ajax:enable debug="error"/>

<script type="text/javascript">
/* Do an intial update. */
startMonitor();
</script>
