package ua.training.task2.model;

import ua.training.task2.model.pojo.TaxPayer;

import java.sql.Connection;
import java.util.List;

public interface JdbcDao {
    Connection connectToDb();
    TaxPayer getUserDataFromDb(TaxPayer taxPayer, int taxId);
    void buildUserInfoIntoDB(int taxId, String firstName, String lastName, String taxCategory,
                             int regularJob, int extraJob, int annualBonus, int benefits,
                             int financialAssistance, int foreignTransaction,
                             int propertySells, int giftedProperty, int giftedMoney, int declarationDate);
    TaxPayer getPayerWithBiggestRegularIncome();
    List<TaxPayer> getTaxPayersByCondition(String field, int min, int max);
}
