package ua.training.task2.model.service;

import ua.training.task2.model.dao.JdbcDao;
import ua.training.task2.model.dao.TaxJdbcDao;
import ua.training.task2.model.pojo.TaxPayer;

import java.util.List;

public class TaxPayersByConditionService {
    public List<TaxPayer> getTaxPayersByCondition (int min, int max) {
        JdbcDao jdbcDao = TaxJdbcDao.getInstance();
        return jdbcDao.getTaxPayersByCondition(min,max);
    }
}
