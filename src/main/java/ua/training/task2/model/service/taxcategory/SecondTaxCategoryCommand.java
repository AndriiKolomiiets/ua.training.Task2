package ua.training.task2.model.service.taxcategory;

import ua.training.task2.model.pojo.TaxPayer;

public class SecondTaxCategoryCommand implements TaxCategoryCommand {

    @Override
    public double getTaxAmount(TaxPayer taxPayer) {
        int jobIncome = taxPayer.getPrimaryJobIncomeAmount() + taxPayer.getExtraJobIncomeAmount()
                + taxPayer.getAnnualBonusAmount();
        int benefits = taxPayer.getFinancialAssistanceAmount() + taxPayer.getBenefitsAmount();
        int gifts = taxPayer.getPropertyGotAsGiftAmount() + taxPayer.getMoneyGotAsGiftAmount();
        int transactions = taxPayer.getPropertySalesAmount() + taxPayer.getForeignMoneyTransactionsAmount();
        return jobIncome*0.10 + benefits * 0.03 + gifts * 0.22 + transactions *0.15;
    }

}
