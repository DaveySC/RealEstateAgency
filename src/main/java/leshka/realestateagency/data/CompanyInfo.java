package leshka.realestateagency.data;

public class CompanyInfo {
    private String name;
    private String rate;

    public void setName(String name) {
        this.name = name;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public String getRate() {
        return rate;
    }

    public CompanyInfo(String name, String rate) {
        this.name = name;
        this.rate = rate;
    }
}
