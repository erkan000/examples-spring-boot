<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Validation</title>
</head>
<body>
    <h1>Validation</h1>
    <form:form modelAttribute="cr">
    
    <form:errors path="*" element="div" />
    
    <br/>    
        <table>
            <tr>
                <td>
                    Name:
                </td>
                <td>
                    <form:input path="brand" />
                </td>
                <td>
                    <form:input path="power" />
                </td>
            </tr>
            <tr>
                <td>
                </td>
                <td>
                    <form:errors path="brand" element="div" />
                </td>
                <td>
                    <form:errors path="power" element="div" />
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="POST!">
                </td>
            </tr>
        </table>

    </form:form>
</body>
</html>
