package ua.training.task2.model.pojo;

import ua.training.task2.model.pojo.income.Income;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaxPayer {
    private User user;
    private TaxIdentification taxIdentification;
    private Income income;
    private List<Income> incomeList;

    public void setIncome(Income income) {
        this.income = income;
    }

    public List<Income> getIncomeList() {
        return incomeList;
    }

    public void addIncomeToList(Income income) {
        incomeList.add(income);
    }

    public boolean removeIncomeFromList(Income income) {
        return incomeList.remove(income);
    }

    public Income getIncome(int index) {
        return incomeList.get(index);
    }

    public TaxPayer() {
        this.user = new UserImpl();
        this.taxIdentification = new TaxIdentificationImpl();
        incomeList = new ArrayList();
    }

    public TaxPayer(User user, TaxIdentification taxIdentification) {
        this.user = user;
        this.taxIdentification = taxIdentification;
        incomeList = new ArrayList();
    }

    public TaxPayer(User user, TaxIdentification taxIdentification, List<Income> incomeList) {
        this.user = user;
        this.taxIdentification = taxIdentification;
        this.incomeList = incomeList;
    }

    public TaxPayer(User user, TaxIdentification taxIdentification, Income income) {
        this.user = user;
        this.taxIdentification = taxIdentification;
        this.income = income;
        this.incomeList = new ArrayList<>();
        this.incomeList.add(income);
    }

    public int getIncomeValueByType(String type) {
        for (int i = 0; i < incomeList.size(); i++) {
            if (incomeList.get(i).getIncomeType() == type) {
                return incomeList.get(i).getAmount();
            }
        }
        return -1;

      /*  return incomeList.stream().filter(a -> type.equals(income.getIncomeType()))
                .findAny()
                .map(income -> income.getAmount())
                .get();*/
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TaxIdentification getTaxIdentification() {
        return taxIdentification;
    }

    public void setTaxIdentification(TaxIdentification taxIdentification) {
        this.taxIdentification = taxIdentification;
    }


    @Override
    public String toString() {
        incomeList.stream()
                .map(e -> (e.getIncomeType() + ": " + e.getAmount()))
                .collect(Collectors.toList());
        return "Tax ID: " + taxIdentification.getTaxId() +
                "\nTax category: " + taxIdentification.getTaxCategory() +
                "\nName: " + user.getFirstName() +
                "\nLast Name: " + user.getLastName();
    }
}
