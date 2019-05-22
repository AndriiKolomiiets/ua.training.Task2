package ua.training.task2.controller;

import ua.training.task2.model.dao.JdbcDao;
import ua.training.task2.model.dao.TaxJdbcDao;
import ua.training.task2.model.pojo.TaxPayer;
import ua.training.task2.model.service.TaxDeclaration;
import ua.training.task2.model.service.TaxDeclarationService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.ResourceBundle;

public class ViewTaxDeclarationServlet extends HttpServlet {

    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        long income;
        double taxes;
        Locale locale;
        String language;
        int taxId;
        TaxDeclaration declare = new TaxDeclarationService();
        TaxPayer payer = new TaxPayer();
        JdbcDao jdbc = TaxJdbcDao.getInstance();
        ResourceBundle resourceBundle;

        taxId = Integer.parseInt(httpServletRequest.getParameter("ti"));
        jdbc.getUserDataFromDb(payer, taxId);
        income = declare.countEntireIncome(payer);
        taxes = declare.getTaxes(payer);
        locale = httpServletRequest.getLocale();
        language = locale.getLanguage();

//todo: change language choosing
        if (language.equals("uk")) {
            resourceBundle = ResourceBundle.getBundle("message_uk_UA",
                    new Locale("uk", "UA"));
        } else {
            resourceBundle = ResourceBundle.getBundle("message",
                    new Locale("en", "GB"));
        }

        httpServletResponse.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = httpServletResponse.getWriter();
        writer.println("<html>" +
                "<head><title>" + resourceBundle.getString("check.header") + "</title></head>\n" +
                "<br>" +
                "<br>" +
                "<body> <fieldset><h2  align=\"center\">" + resourceBundle.getString("view.body") + " </h2>" +
                "<div align=\"center\">" +
                resourceBundle.getString("view.name") + payer.getUser().getFirstName() +
                "<br>" + resourceBundle.getString("declaration.lastName") + payer.getUser().getLastName() +
                "<br>" + resourceBundle.getString("declaration.taxCategory") + payer.getTaxIdentification().getTaxCategory() +
                "<br>" + resourceBundle.getString("declaration.taxId") + payer.getTaxIdentification().getTaxId() +
                "<br>" + resourceBundle.getString("declaration.regJobIncome") + payer.getIncomeValueByType("Regular job income") +
                "<br>" + resourceBundle.getString("declaration.extraJobIncome") + payer.getIncomeValueByType("Extra job income") +
                "<br>" + resourceBundle.getString("declaration.annualBonus") + payer.getIncomeValueByType("Annual bonus") +
                "<br>" + resourceBundle.getString("declaration.foreignTransactions") + payer.getIncomeValueByType("Foreign transaction") +
                "<br>" + resourceBundle.getString("declaration.giftedMoney") + payer.getIncomeValueByType("Money got as a gift") +
                "<br>" + resourceBundle.getString("declaration.giftedProperty") + payer.getIncomeValueByType("Property got as a gift") +
                "<br>" + resourceBundle.getString("declaration.propertySales") + payer.getIncomeValueByType("Property sales") +
                "<br>" + resourceBundle.getString("declaration.benefits") + payer.getIncomeValueByType("Benefits") +
                "<br>" + resourceBundle.getString("declaration.financialAssistance") + payer.getIncomeValueByType("Financial assistance") +
                "<br>" + /*resourceBundle.getString("declaration.declarationYear") + payer.getDeclarationDate() +*/
                "<br>" +
                "<br>" +
                "<br>" +
                "<h2>" + resourceBundle.getString("view.income") + income + "</h2>" +
                "<br>" +
                "<h2>" + resourceBundle.getString("view.taxes") + taxes + "</h2>"
                + "</fieldset></body>" +
                "</html>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
