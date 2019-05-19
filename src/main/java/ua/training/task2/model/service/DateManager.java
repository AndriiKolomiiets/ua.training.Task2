package ua.training.task2.model.service;

public interface DateManager {
    boolean setDeclarationDateToDB(Integer declarationDate);
    Integer getDeclarationDateFromDB();
}
