package ua.training.task2.model.service;

import ua.training.task2.model.JdbcDao;
import ua.training.task2.model.TaxJdbcDao;
import ua.training.task2.model.pojo.TaxPayer;
import ua.training.task2.model.pojo.income.Income;
import ua.training.task2.model.service.taxcategory.TaxCategoryCommand;
import ua.training.task2.model.service.taxcategory.TaxManager;

import java.io.IOException;
import java.util.List;

public class TaxDeclarationService implements TaxDeclaration {

    public TaxPayer getTaxPayerInfoFromDb(TaxPayer payer, int taxId) {
        JdbcDao jdbc = TaxJdbcDao.getInstance();
        jdbc.getUserDataFromDb(payer, taxId);
        return payer;
    }

    @Override
    public long countEntireIncome(TaxPayer taxPayer) {
        List<Income> incomeList = taxPayer.getIncomeList();
        return incomeList.stream()
                .map(Income::getAmount).mapToInt(Integer::intValue).sum();
    }

    @Override
    public double getTaxes(TaxPayer taxPayer) throws IOException {
        TaxManager taxManager = new TaxManager();
        TaxCategoryCommand taxCategoryCounter = taxManager.getTaxServiceByCategory(taxPayer.getTaxIdentification().getTaxCategory());
        return taxCategoryCounter.getTaxAmount(taxPayer);
    }
}
