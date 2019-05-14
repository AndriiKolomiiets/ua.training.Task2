package ua.training.task2.model.service.taxcategory;

import ua.training.task2.model.pojo.TaxPayer;

public interface TaxCategoryCommand {
    double getTaxAmount(TaxPayer taxPayer);
}
