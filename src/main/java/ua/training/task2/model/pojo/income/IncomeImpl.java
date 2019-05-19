package ua.training.task2.model.pojo.income;

public class IncomeImpl implements Income {
    int incomeValue;
    String incomeType;


    public IncomeImpl(int incomeValue, String incomeType) {
        this.incomeValue = incomeValue;
        this.incomeType = incomeType;
    }

    @Override
    public void setAmount(int amountOfIncome) {
        incomeValue = amountOfIncome;
    }

    @Override
    public int getAmount() {
        return incomeValue;
    }

    @Override
    public void setIncomeType(String incomeType) {
        this.incomeType = incomeType;
    }

    @Override
    public String getIncomeType() {
        return incomeType;
    }
}
