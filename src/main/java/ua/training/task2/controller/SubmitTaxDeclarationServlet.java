package ua.training.task2.controller;

import ua.training.task2.model.pojo.TaxPayer;
import ua.training.task2.model.service.TaxDeclaration;
import ua.training.task2.model.service.TaxDeclarationService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.ResourceBundle;

public class SubmitTaxDeclarationServlet extends HttpServlet {
    private TaxDeclaration declare = new TaxDeclarationService();
    private TaxPayer payer = new TaxPayer();
    private TaxJdbc jdbc = TaxJdbcImpl.getInstance();

    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws IOException {
        String language = httpServletRequest.getParameter("language");
        ResourceBundle resourceBundle;
        if (language.equals("uk_UA")) {
            resourceBundle = ResourceBundle.getBundle("message_uk_UA",
                    new Locale("uk", "UA"));
        } else {
            resourceBundle = ResourceBundle.getBundle("message",
                    new Locale("en", "GB"));
        }

        String firstName = httpServletRequest.getParameter("fn");
        String lastName = httpServletRequest.getParameter("ln");
        int taxId = Integer.parseInt(httpServletRequest.getParameter("ti"));
        String taxCategory = httpServletRequest.getParameter("tc");
        //income
        int regularJobIncomeAmount = Integer.parseInt(httpServletRequest.getParameter("rJob"));
        int extraJobIncomeAmount = Integer.parseInt(httpServletRequest.getParameter("eJob"));
        int annualBonusAmount = Integer.parseInt(httpServletRequest.getParameter("aBonus"));
        int benefits = Integer.parseInt(httpServletRequest.getParameter("benefits"));
        int propertySalesAmount = Integer.parseInt(httpServletRequest.getParameter("propSale"));
        int moneyGotAsGiftAmount = Integer.parseInt(httpServletRequest.getParameter("giftMoney"));
        int propertyGotAsGiftAmount = Integer.parseInt(httpServletRequest.getParameter("giftProp"));
        int foreignMoneyTransactionsAmount = Integer.parseInt(httpServletRequest.getParameter("transactions"));

        int financialAssistanceAmount = Integer.parseInt(httpServletRequest.getParameter("finAssist"));
        int declarationDate = Integer.parseInt(httpServletRequest.getParameter("decDate"));

        jdbc.buildUserInfoIntoDB(taxId, firstName, lastName, taxCategory, regularJobIncomeAmount, extraJobIncomeAmount,
                annualBonusAmount, benefits, financialAssistanceAmount, foreignMoneyTransactionsAmount,
                propertySalesAmount, moneyGotAsGiftAmount, propertyGotAsGiftAmount, declarationDate);

        payer.setFirstName(firstName);
        payer.setLastName(lastName);
        payer.setTaxId(taxId);
        payer.setTaxCategory(taxCategory);
        payer.setPrimeJobIncomeAmount(regularJobIncomeAmount);
        payer.setExtraJobIncomeAmount(extraJobIncomeAmount);
        payer.setAnnualBonusAmount(annualBonusAmount);
        payer.setBenefitsAmount(benefits);
        payer.setPropertySalesAmount(propertySalesAmount);
        payer.setMoneyGotAsGiftAmount(moneyGotAsGiftAmount);
        payer.setPropertyGotAsGiftAmount(propertyGotAsGiftAmount);
        payer.setForeignMoneyTransactionsAmount(foreignMoneyTransactionsAmount);
        payer.setFinancialAssistanceAmount(financialAssistanceAmount);
        payer.setDeclarationDate(declarationDate);

        long income = declare.countEntireIncome(payer);
        double taxes = declare.getTaxes(payer);

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
}
