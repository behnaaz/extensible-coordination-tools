
Building
========

It is a good idea to use the J2EE version of Eclipse and to have Tomcat installed.

You need JET (Java Emitter Templates) to generate the classes BuildXml and ComponentJava in cwi.reo.templates.

Compilation:

just run ant (assuming the templates have been generated already).

Running
=======

Just copy the generated live.war into your Tomcat application folder.

If you get strange runtime exceptions, try to add
Xalan 2.7.1 or higher both on the client side and the server side.
http://apache.nedmirror.nl/xml/xalan-j/xalan-j_2_7_1-bin-2jars.tar.gz

