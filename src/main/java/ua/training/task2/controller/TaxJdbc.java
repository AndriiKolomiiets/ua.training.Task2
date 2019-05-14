package ua.training.task2.controller;

import ua.training.task2.model.pojo.TaxPayer;

import java.sql.Connection;

public interface TaxJdbc {
    Connection connectToDb();
    TaxPayer getUserDataFromDb(TaxPayer taxPayer, int taxId);
    void buildUserInfoIntoDB(int taxId, String firstName, String lastName, String taxCategory,
                             int regularJob, int extraJob, int annualBonus, int benefits,
                             int financialAssistance, int foreignTransaction,
                             int propertySells, int giftedProperty, int giftedMoney, int declarationDate);
}
