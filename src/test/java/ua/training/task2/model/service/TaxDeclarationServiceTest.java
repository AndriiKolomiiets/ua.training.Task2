package ua.training.task2.model.service;

import org.junit.Before;
import org.junit.Test;
import ua.training.task2.model.pojo.TaxPayer;
import ua.training.task2.model.pojo.income.IncomeImpl;
import ua.training.task2.model.service.taxcategory.TaxManager;

import java.io.IOException;

import static org.junit.Assert.*;

public class TaxDeclarationServiceTest {
    private TaxPayer taxPayer;
    private TaxDeclaration declare;
    @Before
    public void setUp() throws Exception {
        taxPayer = new TaxPayer();
        taxPayer.getTaxIdentification().setTaxCategory("1 category");
        taxPayer.getTaxIdentification().setTaxId(12435685);
        taxPayer.setIncome(new IncomeImpl(100000, "Regular job income"));
        taxPayer.setIncome(new IncomeImpl(1000, "Extra job income"));
        taxPayer.setIncome(new IncomeImpl(10, "Annual bonus"));
        taxPayer.setIncome(new IncomeImpl(120,"Benefits") );
        taxPayer.setIncome(new IncomeImpl(10000, "Property sales"));
        taxPayer.setIncome(new IncomeImpl(500, "Money got as a gift"));
        taxPayer.setIncome(new IncomeImpl(60000, "Property got as a gift"));
        taxPayer.setIncome(new IncomeImpl(50, "Foreign transaction"));
        taxPayer.setIncome(new IncomeImpl(70000, "Financial assistance"));
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