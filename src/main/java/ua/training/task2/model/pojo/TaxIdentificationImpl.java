package ua.training.task2.model.pojo;

public class TaxIdentificationImpl implements TaxIdentification {
    private int taxId;
    private String taxCategory;

    public TaxIdentificationImpl() {
    }

    public TaxIdentificationImpl(int taxId, String taxCategory) {
        this.taxId = taxId;
        this.taxCategory = taxCategory;
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

}
