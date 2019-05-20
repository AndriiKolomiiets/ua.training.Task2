<%@ page import="java.util.List" %>
<%@ page import="ua.training.task2.model.pojo.TaxPayer" %>
<%@ page import="java.util.ArrayList" %>
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
    <h1 align="center" style="color:#0000cb"><fmt:message key="biggestincome.header"/></h1>
    <br>
    <div align="center">

        <table border="1" width="500" align="center">
            <tr bgcolor="00FF7F">
                <th><b><fmt:message key="table.name"/></b></th>
                <th><b><fmt:message key="table.lname"/></b></th>
                <th><b><fmt:message key="table.tid"/></b></th>
                <th><b><fmt:message key="table.tcategory"/></b></th>
                <th><b><fmt:message key="table.income"/></b></th>
            </tr>
            <%TaxPayer payer =
                    (TaxPayer) request.getAttribute("payer");
            %>
            <tr>
                <td><%=payer.getUser().getFirstName()%>
                </td>
                <td><%=payer.getUser().getLastName()%>
                </td>
                <td><%=payer.getTaxIdentification().getTaxId()%>
                </td>
                <td><%=payer.getTaxIdentification().getTaxCategory()%>
                </td>
                <td><%=payer.getIncomeList().get(0).getAmount()%></td>
            </tr>

        </table>
    </div>
</body>

</html>
