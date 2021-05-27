package EU_VAT;


public class euState implements Comparable<euState> {
    private static final String FILE_ITEM_DELIMITER = "\t";

    private String stateAbbreviation;
    private String stateName;
    private double vatTaxRate;
    private double reducedVatTaxRate;
    private boolean hasSpecialTaxRate;

    public euState(String stateAbbreviation, String stateName, int vatTaxRate, double reducedVatTaxRate, boolean hasSpecialTaxRate) {
        this.stateAbbreviation = stateAbbreviation;
        this.stateName = stateName;
        this.vatTaxRate = vatTaxRate;
        this.reducedVatTaxRate = reducedVatTaxRate;
        this.hasSpecialTaxRate = hasSpecialTaxRate;
    }

    public static euState parseData(String record) {
        String[] items = record.split(FILE_ITEM_DELIMITER);

        String stateAbbreviation = items[0];
        String stateName = items[1];
        int vatTaxRate = Integer.parseInt((items[2]));
        double reducedVatTaxRate = Double.parseDouble((items[3]));
        boolean hasSpecialTaxRate = Boolean.parseBoolean(items[4]);

        return new euState(stateAbbreviation, stateName, vatTaxRate, reducedVatTaxRate, hasSpecialTaxRate);
    }

//    public static

    public int vatTaxRateToInteger() {
        int vatTaxRateAsInteger = (int) getVatTaxRate();
        return vatTaxRateAsInteger;
    }

    @Override
    public int compareTo(euState nextItem) {
        Double  vatAsDoubleObject = vatTaxRate;
        Double nextItemVAT = nextItem.vatTaxRate;
        return vatAsDoubleObject.compareTo(nextItemVAT);
    }

    @Override
    public String toString() {
        return getStateDescription();
    }

    public String getStateDescription() {
        String description = getStateName() + " "
                + "(" + getStateAbbreviation() + "): "
                + vatTaxRateToInteger() + " %";
        return  description;
    }


    public double getVatTaxRate() {
        return vatTaxRate;
    }


    public String getStateAbbreviation() {
        return stateAbbreviation;
    }

    public void setStateAbbreviation(String stateAbbreviation) {
        this.stateAbbreviation = stateAbbreviation;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public void setVatTaxRate(int vatTaxRate) {
        this.vatTaxRate = vatTaxRate;
    }

    public double getReducedVatTaxRate() {
        return reducedVatTaxRate;
    }

    public void setReducedVatTaxRate(double reducedVatTaxRate) {
        this.reducedVatTaxRate = reducedVatTaxRate;
    }

    public boolean hasSpecialTaxRate() {
        return hasSpecialTaxRate;
    }

    public void setHasSpecialTaxRate(boolean hasSpecialTaxRate) {
        this.hasSpecialTaxRate = hasSpecialTaxRate;
    }
}



