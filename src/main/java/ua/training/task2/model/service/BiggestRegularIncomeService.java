package ua.training.task2.model.service;

import ua.training.task2.model.JdbcDao;
import ua.training.task2.model.TaxJdbcDao;
import ua.training.task2.model.pojo.TaxPayer;

public class BiggestRegularIncomeService {
    public TaxPayer getPayer() {
        JdbcDao jdbcDao = TaxJdbcDao.getInstance();
        return jdbcDao.getPayerWithBiggestRegularIncome();
    }
}
