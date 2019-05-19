<%@ page import="java.util.List" %>
<%@ page import="ua.training.task2.model.pojo.TaxPayer" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="message"/>
<% List allPayersList = (List)session.getAttribute("allPayersList");%>

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
<fieldset>
    <h1 align="center" style="color:#0000cb"><fmt:message key="alltaxpayers.header"/></h1>
    <br>
    <div align="center">

        <table border ="1" width="500" align="center">
            <tr bgcolor="00FF7F">
                <th><b>First Name</b></th>
                <th><b>Last Name</b></th>
                <th><b>Tax ID</b></th>
                <th><b>Tax Category</b></th>
                <th><b>Regular job income</b></th>
            </tr>
            <%List<TaxPayer> payers =
                    (ArrayList<TaxPayer>)request.getAttribute("payersByConditionsList");
                for(TaxPayer payer:payers){
                    int i=0;%>
            <%-- Arranging data in tabular form
            --%>
            <tr>
                <td><%=payer.getUser().getFirstName()%></td>
                <td><%=payer.getUser().getLastName()%></td>
                <td><%=payer.getTaxIdentification().getTaxId()%></td>
                <td><%=payer.getTaxIdentification().getTaxCategory()%></td>
                 <td><%=payer.getIncomeList().get(i).getAmount()%></td>
            </tr>
            <%i++;}%>
        </table>
        <hr/>
        <%--<c:forEach items="${allPayersList}" var="payers">
                <tr>
                    <td>${payers.User}</td>
                    <td>${payers.IncomeList}</td>  </tr>
            </c:forEach>--%>

        <%--<button class="buttons" onclick=""/>--%>

    </div>
</fieldset>
</body>

</html>