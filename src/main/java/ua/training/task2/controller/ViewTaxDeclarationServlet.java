package ua.training.task2.controller;

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
        TaxDeclaration declare = new TaxDeclarationService();
        TaxPayer payer = new TaxPayer();
        TaxJdbc jdbc = new TaxJdbcImpl();
        ResourceBundle resourceBundle;

        int taxId = Integer.parseInt(httpServletRequest.getParameter("ti"));
        jdbc.getUserDataFromDb(payer, taxId);
        long income = declare.countEntireIncome(payer);
        double taxes = declare.getTaxes(payer);

        Locale locale = httpServletRequest.getLocale();
        String language = locale.getLanguage();

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
                resourceBundle.getString("view.name") + payer.getFirstName() +
                "<br>" + resourceBundle.getString("declaration.lastName") + payer.getLastName() +
                "<br>" + resourceBundle.getString("declaration.taxCategory") + payer.getTaxCategory() +
                "<br>" + resourceBundle.getString("declaration.taxId") + payer.getTaxId() +
                "<br>" + resourceBundle.getString("declaration.regJobIncome") + payer.getPrimaryJobIncomeAmount() +
                "<br>" + resourceBundle.getString("declaration.extraJobIncome") + payer.getExtraJobIncomeAmount() +
                "<br>" + resourceBundle.getString("declaration.annualBonus") + payer.getAnnualBonusAmount() +
                "<br>" + resourceBundle.getString("declaration.foreignTransactions") + payer.getForeignMoneyTransactionsAmount() +
                "<br>" + resourceBundle.getString("declaration.giftedMoney") + payer.getMoneyGotAsGiftAmount() +
                "<br>" + resourceBundle.getString("declaration.giftedProperty") + payer.getPropertyGotAsGiftAmount() +
                "<br>" + resourceBundle.getString("declaration.propertySales") + payer.getPropertySalesAmount() +
                "<br>" + resourceBundle.getString("declaration.benefits") + payer.getBenefitsAmount() +
                "<br>" + resourceBundle.getString("declaration.financialAssistance") + payer.getFinancialAssistanceAmount() +
                "<br>" + resourceBundle.getString("declaration.declarationYear") + payer.getDeclarationDate() +
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
