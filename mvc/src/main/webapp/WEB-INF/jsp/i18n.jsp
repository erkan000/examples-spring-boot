<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>i18n</title>
</head>
<body>
    <h1>i18n</h1>
    This text changes based on "lang" parameter value : 
    
    <spring:message code="label.name" />
    
</body>
</html>
