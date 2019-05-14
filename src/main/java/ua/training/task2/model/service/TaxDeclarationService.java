package ua.training.task2.model.service;

import ua.training.task2.controller.TaxJdbc;
import ua.training.task2.controller.TaxJdbcImpl;
import ua.training.task2.model.pojo.TaxPayer;
import ua.training.task2.model.service.taxcategory.TaxCategoryCommand;
import ua.training.task2.model.service.taxcategory.TaxManager;

import java.io.IOException;

public class TaxDeclarationService implements TaxDeclaration {

    public TaxPayer getTaxPayerInfoFromDb(TaxPayer payer, int taxId) {
        TaxJdbc jdbc = TaxJdbcImpl.getInstance();
        jdbc.getUserDataFromDb(payer, taxId);
        return payer;
    }

    @Override
    public long countEntireIncome(TaxPayer taxPayer) {
        return taxPayer.getPrimaryJobIncomeAmount() + taxPayer.getExtraJobIncomeAmount()
                + taxPayer.getAnnualBonusAmount() + taxPayer.getFinancialAssistanceAmount()
                + taxPayer.getBenefitsAmount() + taxPayer.getPropertyGotAsGiftAmount()
                + taxPayer.getMoneyGotAsGiftAmount() + taxPayer.getPropertySalesAmount()
                + taxPayer.getForeignMoneyTransactionsAmount();
    }

    @Override
    public double getTaxes(TaxPayer taxPayer) throws IOException {
        TaxManager taxManager = new TaxManager();
        TaxCategoryCommand taxCategoryCounter = taxManager.getTaxServiceByCategory(taxPayer.getTaxCategory());
        return taxCategoryCounter.getTaxAmount(taxPayer);
    }
}
