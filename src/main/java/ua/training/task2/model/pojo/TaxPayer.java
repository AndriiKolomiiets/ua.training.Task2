package ua.training.task2.model.pojo;

public class TaxPayer {

    private String firstName;
    private String lastName;
    private int taxId;
    private String taxCategory;
    //income
    private int primaryJobIncomeAmount;
    private int extraJobIncomeAmount;
    private int annualBonusAmount;
    private int propertySellingAmount;
    private int moneyGotAsGiftAmount;
    private int propertyGotAsGiftAmount;
    private int foreignMoneyTransactionsAmount;
    private int benefitsAmount;
    private int financialAssistanceAmount;

    private int declarationDate;

    public void setPropertySalesAmount(int propertySellingAmount) {
        this.propertySellingAmount = propertySellingAmount;
    }

    public int getDeclarationDate() {
        return declarationDate;
    }

    public void setDeclarationDate(int declarationDate) {
        this.declarationDate = declarationDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getTaxId() {
        return taxId;
    }

    public void setTaxId(int taxId) {
        this.taxId = taxId;
    }

    public String getTaxCategory() {
        return taxCategory;
    }

    public void setTaxCategory(String taxCategory) {
        this.taxCategory = taxCategory;
    }

    public int getPrimaryJobIncomeAmount() {
        return primaryJobIncomeAmount;
    }

    public void setPrimeJobIncomeAmount(int primaryJobIncomeAmount) {
        this.primaryJobIncomeAmount = primaryJobIncomeAmount;
    }

    public int getExtraJobIncomeAmount() {
        return extraJobIncomeAmount;
    }

    public void setExtraJobIncomeAmount(int extraJobIncomeAmount) {
        this.extraJobIncomeAmount = extraJobIncomeAmount;
    }

    public int getAnnualBonusAmount() {
        return annualBonusAmount;
    }

    public void setAnnualBonusAmount(int annualBonusAmount) {
        this.annualBonusAmount = annualBonusAmount;
    }

    public int getPropertySalesAmount() {
        return propertySellingAmount;
    }

    public void setPropertySellsAmount(int propertySellingAmount) {
        this.propertySellingAmount = propertySellingAmount;
    }

    public int getMoneyGotAsGiftAmount() {
        return moneyGotAsGiftAmount;
    }

    public void setMoneyGotAsGiftAmount(int moneyGotAsGiftAmount) {
        this.moneyGotAsGiftAmount = moneyGotAsGiftAmount;
    }

    public int getPropertyGotAsGiftAmount() {
        return propertyGotAsGiftAmount;
    }

    public void setPropertyGotAsGiftAmount(int propertyGotAsGiftAmount) {
        this.propertyGotAsGiftAmount = propertyGotAsGiftAmount;
    }

    public int getForeignMoneyTransactionsAmount() {
        return foreignMoneyTransactionsAmount;
    }

    public void setForeignMoneyTransactionsAmount(int foreignMoneyTransactionsAmount) {
        this.foreignMoneyTransactionsAmount = foreignMoneyTransactionsAmount;
    }

    public int getBenefitsAmount() {
        return benefitsAmount;
    }

    public void setBenefitsAmount(int benefitsAmount) {
        this.benefitsAmount = benefitsAmount;
    }

    public int getFinancialAssistanceAmount() {
        return financialAssistanceAmount;
    }

    public void setFinancialAssistanceAmount(int financialAssistanceAmount) {
        this.financialAssistanceAmount = financialAssistanceAmount;
    }

    @Override
    public String toString(){
        return "Tax ID: " + getTaxId()+
                "\nTax taxcategory: " + getTaxCategory() +
                "\nName: " + getFirstName() +
                "\nLast Name: " + getLastName() +
                "\nPrimary job income: " + getPrimaryJobIncomeAmount() +
                "\nExtra job income: " + getExtraJobIncomeAmount() +
                "\nAnnual bonus: " + getAnnualBonusAmount() +
                "\nForeign transactions: " + getForeignMoneyTransactionsAmount() +
                "\nMoney got as a gift: " + getMoneyGotAsGiftAmount() +
                "\nProperty got as a gift: " +getPropertyGotAsGiftAmount() +
                "\nSold property: " + getPropertySalesAmount() +
                "\nBenefits: " + getBenefitsAmount() +
                "\nFinancial assistance: " + getFinancialAssistanceAmount() +
                "\nYear of declaration: " + getDeclarationDate();


    }
}
