package ua.training.task2.controller;

import ua.training.task2.model.JdbcDao;
import ua.training.task2.model.TaxJdbcDao;
import ua.training.task2.model.pojo.TaxPayer;
import ua.training.task2.model.pojo.income.IncomeImpl;
import ua.training.task2.model.service.TaxDeclaration;
import ua.training.task2.model.service.TaxDeclarationService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class SubmitTaxDeclarationServlet extends HttpServlet {
    private TaxDeclaration declare = new TaxDeclarationService();
    private TaxPayer payer = new TaxPayer();
    private JdbcDao jdbc = TaxJdbcDao.getInstance();

    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws IOException {
        String language = httpServletRequest.getParameter("language");
        ResourceBundle resourceBundle;
        Optional<String> optionalLanguage = Optional.ofNullable(language);

        if (optionalLanguage.equals("uk_UA")) {
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

        payer.getUser().setFirstName(firstName);
        payer.getUser().setLastName(lastName);
        payer.getTaxIdentification().setTaxId(taxId);
        payer.getTaxIdentification().setTaxCategory(taxCategory);
        payer.addIncomeToList(new IncomeImpl(regularJobIncomeAmount,"Regular job income" ));
        payer.addIncomeToList(new IncomeImpl(extraJobIncomeAmount, "Extra job income"));
        payer.addIncomeToList(new IncomeImpl(annualBonusAmount, "Annual bonus"));
        payer.addIncomeToList(new IncomeImpl(benefits, "Benefits"));
        payer.addIncomeToList(new IncomeImpl(propertySalesAmount, "Property sales"));
        payer.addIncomeToList(new IncomeImpl(moneyGotAsGiftAmount, "Money got as a gift"));
        payer.addIncomeToList(new IncomeImpl(propertyGotAsGiftAmount, "Property got as a gift"));
        payer.addIncomeToList(new IncomeImpl(foreignMoneyTransactionsAmount, "Foreign transaction"));
        payer.addIncomeToList(new IncomeImpl(financialAssistanceAmount, "Financial assistance"));


        long income = declare.countEntireIncome(payer);
        double taxes=0.0;
        try {
            taxes = declare.getTaxes(payer);

        } catch (IOException e) {
            e.printStackTrace();
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
}
