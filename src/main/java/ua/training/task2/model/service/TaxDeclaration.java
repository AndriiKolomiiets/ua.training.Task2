package ua.training.task2.model.service;

import ua.training.task2.model.pojo.TaxPayer;

import java.io.IOException;

public interface TaxDeclaration {
    double getTaxes(TaxPayer taxPayer) throws IOException;
    long countEntireIncome(TaxPayer taxPayer);
}
