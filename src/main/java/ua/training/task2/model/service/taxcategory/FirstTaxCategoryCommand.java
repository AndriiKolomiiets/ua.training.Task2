package ua.training.task2.model.service.taxcategory;

import ua.training.task2.model.pojo.TaxPayer;

public class FirstTaxCategoryCommand implements TaxCategoryCommand {

    @Override
    public double getTaxAmount(TaxPayer taxPayer) {
      int income = taxPayer.getPrimaryJobIncomeAmount() + taxPayer.getExtraJobIncomeAmount()
              + taxPayer.getAnnualBonusAmount() + taxPayer.getFinancialAssistanceAmount()
              + taxPayer.getBenefitsAmount() + taxPayer.getPropertyGotAsGiftAmount()
              + taxPayer.getMoneyGotAsGiftAmount()+ taxPayer.getPropertySalesAmount()
              + taxPayer.getForeignMoneyTransactionsAmount();
        return income *0.13;
    }

}
