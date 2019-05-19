package ua.training.task2.model.service.taxcategory;

import ua.training.task2.model.pojo.income.Income;
import ua.training.task2.model.pojo.TaxPayer;

import java.util.List;

public class SecondTaxCategoryCommand implements TaxCategoryCommand {

    @Override
    public double getTaxAmount(TaxPayer taxPayer) {
        List<Income> incomeList = taxPayer.getIncomeList();
        Integer generalIncome = incomeList.stream()
                .map(Income::getAmount).mapToInt(Integer::intValue).sum();
        return generalIncome * 0.15;
    }

}
