package ua.training.task2.model.service;

import org.junit.Before;
import org.junit.Test;
import ua.training.task2.model.pojo.TaxPayer;
import ua.training.task2.model.pojo.income.IncomeImpl;

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
        taxPayer.addIncomeToList(new IncomeImpl(100000, "Regular job income"));
        taxPayer.addIncomeToList(new IncomeImpl(1000, "Extra job income"));
        taxPayer.addIncomeToList(new IncomeImpl(10, "Annual bonus"));
        taxPayer.addIncomeToList(new IncomeImpl(120,"Benefits") );
        taxPayer.addIncomeToList(new IncomeImpl(10000, "Property sales"));
        taxPayer.addIncomeToList(new IncomeImpl(500, "Money got as a gift"));
        taxPayer.addIncomeToList(new IncomeImpl(60000, "Property got as a gift"));
        taxPayer.addIncomeToList(new IncomeImpl(50, "Foreign transaction"));
        taxPayer.addIncomeToList(new IncomeImpl(70000, "Financial assistance"));
        declare = new TaxDeclarationService();
    }

    @Test
    public void countEntireIncome() {
        long income = declare.countEntireIncome(taxPayer);
        assertEquals(241680, income);
    }

    @Test
    public void getTaxes() {
        TaxDeclarationService service = new TaxDeclarationService();
        double taxes = 0;
        try {
            taxes = service.getTaxes(taxPayer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(31418.4, taxes, 0);
    }
}