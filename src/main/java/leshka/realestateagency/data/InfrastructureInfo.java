package leshka.realestateagency.data;

import javafx.beans.property.SimpleStringProperty;

public class InfrastructureInfo {
    private final SimpleStringProperty idTypeObject;
    private final SimpleStringProperty description;
    private final SimpleStringProperty title;

    public InfrastructureInfo(String idTypeObject, String description, String title) {
        this.idTypeObject = new SimpleStringProperty(idTypeObject);
        this.description = new SimpleStringProperty(description);
        this.title = new SimpleStringProperty(title);
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
