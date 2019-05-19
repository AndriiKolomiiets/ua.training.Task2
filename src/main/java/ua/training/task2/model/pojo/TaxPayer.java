package ua.training.task2.model.pojo;

import ua.training.task2.model.pojo.income.Income;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaxPayer {
    //todo: User, Work, Property, Identification
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


    public int getIncomeValueByType(String type) {
        for (int i=0; i<incomeList.size();i++){
            if (incomeList.get(i).getIncomeType()==type){
               return incomeList.get(i).getAmount();
            }
        }
        return 0;

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
                "\nTax taxcategory: " + taxIdentification.getTaxCategory() +
                "\nName: " + user.getFirstName() +
                "\nLast Name: " + user.getLastName() +
                "\n" + getIncomeValueByType("Regular job income");
//                        incomeList.forEach(income ->System.out.println(income));


//                "\nPrimary job income: " + incomeList.getPrimaryJobIncomeAmount() +
//                "\nExtra job income: " + getExtraJobIncomeAmount() +
//                "\nAnnual bonus: " + getAnnualBonusAmount() +
//                "\nForeign transactions: " + getForeignMoneyTransactionsAmount() +
//                "\nMoney got as a gift: " + getMoneyGotAsGiftAmount() +
//                "\nProperty got as a gift: " + getPropertyGotAsGiftAmount() +
//                "\nSold property: " + getPropertySalesAmount() +
//                "\nBenefits: " + getBenefitsAmount() +
//                "\nFinancial assistance: " + getFinancialAssistanceAmount() +
//                "\nYear of declaration: " + getDeclarationDate();


    }
}
