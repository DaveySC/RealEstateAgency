package leshka.realestateagency.data;

import javafx.beans.property.SimpleStringProperty;

public class InfrastructureInfo {
    private final SimpleStringProperty idTypeObject;
    private final SimpleStringProperty description;
    private final SimpleStringProperty title;
    private final SimpleStringProperty idOjb;

    public InfrastructureInfo(String idTypeObject, String description, String title, String idObj) {
        this.idTypeObject = new SimpleStringProperty(idTypeObject);
        this.description = new SimpleStringProperty(description);
        this.title = new SimpleStringProperty(title);
        this.idOjb =  new SimpleStringProperty(idObj);

    }

    public void setIdOjb(String idOjb) {
        this.idOjb.set(idOjb);
    }

    public String getIdOjb() {
        return idOjb.get();
    }

    public SimpleStringProperty idOjbProperty() {
        return idOjb;
    }

    public void setIdTypeObject(String idTypeObject) {
        this.idTypeObject.set(idTypeObject);
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getIdTypeObject() {
        return idTypeObject.get();
    }

    public SimpleStringProperty idTypeObjectProperty() {
        return idTypeObject;
    }

    public String getDescription() {
        return description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public String getTitle() {
        return title.get();
    }

    public SimpleStringProperty titleProperty() {
        return title;
    }
}
