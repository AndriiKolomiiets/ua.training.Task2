package ua.training.task2.model.service;

import org.junit.Before;
import org.junit.Test;
import ua.training.task2.model.pojo.TaxPayer;
import ua.training.task2.model.service.taxcategory.TaxManager;

import java.io.IOException;

import static org.junit.Assert.*;

public class TaxDeclarationServiceTest {
    private TaxPayer taxPayer;
    private TaxDeclaration declare;
    @Before
    public void setUp() throws Exception {
        taxPayer = new TaxPayer();
        taxPayer.setTaxCategory("1 category");
        taxPayer.setTaxId(12435685);
        taxPayer.setPrimeJobIncomeAmount(100000);
        taxPayer.setExtraJobIncomeAmount(1000);
        taxPayer.setAnnualBonusAmount(10);
        taxPayer.setBenefitsAmount(120);
        taxPayer.setFinancialAssistanceAmount(10000);
        taxPayer.setForeignMoneyTransactionsAmount(500);
        taxPayer.setPropertySalesAmount(60000);
        taxPayer.setMoneyGotAsGiftAmount(50);
        taxPayer.setPropertyGotAsGiftAmount(70000);
       declare = new TaxDeclarationService();
    }

    @Test
    public void countEntireIncome() {

        long income = declare.countEntireIncome(taxPayer);
        assertEquals(241680, income);
    }

    @Test
    public void getTaxes() throws IOException {
        TaxDeclarationService service = new TaxDeclarationService();
        double taxes = service.getTaxes(taxPayer);
        assertEquals(31418.4, taxes, 0);
    }
}