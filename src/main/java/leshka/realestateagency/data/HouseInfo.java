package leshka.realestateagency.data;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


public class HouseInfo {
    private SimpleIntegerProperty number;
    private SimpleIntegerProperty type;
    private SimpleStringProperty manCom;
    private SimpleStringProperty street;
    private SimpleStringProperty condition;
    private SimpleStringProperty idInfrastructure;

    public HouseInfo(Integer number, Integer type, String manCom, String street, String condition, String idInfrastructure) {
        this.number = new SimpleIntegerProperty(number);
        this.type = new SimpleIntegerProperty(type);
        this.manCom = new SimpleStringProperty(manCom);
        this.street = new SimpleStringProperty(street);
        this.condition = new SimpleStringProperty(condition);
        this.idInfrastructure = new SimpleStringProperty(idInfrastructure);
    }

    public void setNumber(int number) {
        this.number.set(number);
    }

    public void setType(int type) {
        this.type.set(type);
    }

    public void setIdInfrastructure(String idInfrastructure) {
        this.idInfrastructure.set(idInfrastructure);
    }

    public String getIdInfrastructure() {
        return idInfrastructure.get();
    }

    public SimpleStringProperty idInfrastructureProperty() {
        return idInfrastructure;
    }

    public HouseInfo() {
        this.number = new SimpleIntegerProperty();
        this.type = new SimpleIntegerProperty();
        this.manCom = new SimpleStringProperty();
        this.street = new SimpleStringProperty();
        this.condition = new SimpleStringProperty();
        this.idInfrastructure = new SimpleStringProperty();

    }

    public void setNumber(Integer number) {
        this.number.set(number);
    }

    public void setType(Integer type) {
        this.type.set(type);
    }

    public void setManCom(String manCom) {
        this.manCom.set(manCom);
    }

    public void setStreet(String street) {
        this.street.set(street);
    }

    public void setCondition(String condition) {
        this.condition.set(condition);
    }


    public Integer getNumber() {
        return number.get();
    }

    public SimpleIntegerProperty numberProperty() {
        return number;
    }

    public Integer getType() {
        return type.get();
    }

    public SimpleIntegerProperty typeProperty() {
        return type;
    }

    public String getManCom() {
        return manCom.get();
    }

    public SimpleStringProperty manComProperty() {
        return manCom;
    }

    public String getStreet() {
        return street.get();
    }

    public SimpleStringProperty streetProperty() {
        return street;
    }

    public String getCondition() {
        return condition.get();
    }

    public SimpleStringProperty conditionProperty() {
        return condition;
    }
}
