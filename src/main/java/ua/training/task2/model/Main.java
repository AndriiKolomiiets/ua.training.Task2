package ua.training.task2.model;

import ua.training.task2.model.pojo.TaxPayer;
import ua.training.task2.model.pojo.income.IncomeImpl;
import ua.training.task2.model.service.TaxDeclaration;
import ua.training.task2.model.service.TaxDeclarationService;

public class Main {

    public static void main(String[] args) {
       TaxDeclaration declare = new TaxDeclarationService();
        TaxPayer payer = new TaxPayer();
         JdbcDao jdbc = TaxJdbcDao.getInstance();

        String firstName =("fn");
        String lastName = ("ln");
        int taxId = 24152160;
        String taxCategory = "1 category";
        //income
        int regularJobIncomeAmount = 1;
        int extraJobIncomeAmount = 1;
        int annualBonusAmount = 1;
        int benefits = 1;
        int propertySalesAmount = 1;
        int moneyGotAsGiftAmount = 1;
        int propertyGotAsGiftAmount = 1;
        int foreignMoneyTransactionsAmount = 1;
        int financialAssistanceAmount =1;
        int declarationDate = 1;

        jdbc.buildUserInfoIntoDB(taxId, firstName, lastName, taxCategory, regularJobIncomeAmount, extraJobIncomeAmount,
                annualBonusAmount, benefits, financialAssistanceAmount, foreignMoneyTransactionsAmount,
                propertySalesAmount, moneyGotAsGiftAmount, propertyGotAsGiftAmount, declarationDate);
//todo:null
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
        System.out.println(payer);
        System.out.println(payer.getIncomeValueByType("Regular job income"));

       /* System.out.println(payer.getTaxIdentification().getTaxCategory());
        long income = declare.countEntireIncome(payer);
        double taxes=0.0;
        try {
           taxes = declare.getTaxes(payer);

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(income + " "+ taxes);*/
    }
}
