<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="message"/>

<link rel="stylesheet" type="text/css" href="styles.css">

<!DOCTYPE html>
<html lang="${language}">
<head>

    <title><fmt:message key="index.title"/></title>

</head>

<body>
<br>
<br>
<form>
    <label for="language"></label>
    <h3 align="right" style="color:#FF4500">
        <fmt:message key="language"/><select id="language" name="language"
                                             onchange="submit()">
        <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
        <option value="uk_UA" ${language == 'uk_UA' ? 'selected' : ''}>Ukrainian</option>
    </select></h3>
</form>
<br>
<h1 align="center" style="color:#0000cb"><fmt:message key="admin.header"/></h1>
<br>
<br>
<div align="center">
    <form id="declaration_fill" action="http://localhost:8080/admin_stat_servlet/">
        <fmt:message key="declaration.taxId"/>
        <input type="text" name="ti" pattern="[0-9]{8}"/><br>
        <br>
        <div align="center">
            <input type="submit" class="sub_button" value=<fmt:message key="check.submitButton"/>>
            <input type="reset" class="sub_button" value=<fmt:message key="check.refreshButton"/>>
        </div>
    </form>
</div>
    <div align="center">
    <form id="all_payers_stat" action="http://localhost:8080/all_payers_servlet/">
        <div align="center">
            <fmt:message key="admin.getallpayers"/>
            <input type="submit" class="sub_button" value=<fmt:message key="admin.button.getallpayers"/>>

        </div>
    </form>
</div>
</body>
</html>