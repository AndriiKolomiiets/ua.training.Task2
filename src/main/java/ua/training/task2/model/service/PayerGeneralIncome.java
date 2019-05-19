package ua.training.task2.model.service;

import ua.training.task2.model.pojo.TaxPayer;
import ua.training.task2.model.pojo.income.Income;

import java.util.List;

public class PayerGeneralIncome {
    public long count(TaxPayer taxPayer) {
        List<Income> incomeList = taxPayer.getIncomeList();
        return incomeList.stream()
                .map(Income::getAmount).mapToInt(Integer::intValue).sum();
    }
}