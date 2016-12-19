package by.bstu.solovei.education.organization;

public enum  Organizations {
    EPAM("Epam Systems", 200),
    Itransition("Itransition Software Developer Company", 200),
    IBM("IBM Grpup", 200),
    SAM("SAM Solushions", 200);

    private final String name;
    private final int amountOfMoneyToPay;

    Organizations(String name, int amountOfMoneyToPay) {
        this.name = name;
        this.amountOfMoneyToPay = amountOfMoneyToPay;
    }

    public String getName() {
        return this.name;
    }

    public int getAmountOfMoneyToPay() {
        return  this.amountOfMoneyToPay;
    }
}
