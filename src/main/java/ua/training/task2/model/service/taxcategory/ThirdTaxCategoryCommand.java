package ua.training.task2.model.service.taxcategory;

import ua.training.task2.model.pojo.TaxPayer;

public class ThirdTaxCategoryCommand implements TaxCategoryCommand {

    @Override
    public double getTaxAmount(TaxPayer taxPayer) {
    int jobIncome = taxPayer.getPrimaryJobIncomeAmount() + taxPayer.getExtraJobIncomeAmount()
            + taxPayer.getAnnualBonusAmount();
    int benefits = taxPayer.getFinancialAssistanceAmount() + taxPayer.getBenefitsAmount();
    int gifts = taxPayer.getPropertyGotAsGiftAmount() + taxPayer.getMoneyGotAsGiftAmount();
    int transactions = taxPayer.getPropertySalesAmount() + taxPayer.getForeignMoneyTransactionsAmount();
        return jobIncome*0.06 + benefits * 0.01 + gifts * 0.02 + transactions *0.03;
    }
}
