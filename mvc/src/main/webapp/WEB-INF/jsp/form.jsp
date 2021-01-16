<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Post Mapping</title>
</head>
<body>
    <h1>Post Mapping</h1>
    <form:form action="addme" modelAttribute="per">
        <table>
            <tr>
                <td>
                    <form:label path="name">Name</form:label>
                </td>
                <td>
                    <form:input path="name" />
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
