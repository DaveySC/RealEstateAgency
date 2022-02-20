package leshka.realestateagency.data;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.TextArea;

public class FlatInfo {

    private Canvas canvas;
    private final TextArea flatInfo;
    private final TextArea floatRealtor;
    private final SimpleIntegerProperty price;
    private final SimpleIntegerProperty numberOfRooms;
    private final SimpleIntegerProperty floor;
    private final SimpleIntegerProperty houseID;
    private final SimpleIntegerProperty flatTypeID;
    private final SimpleIntegerProperty area;
    private final SimpleStringProperty image;

    public FlatInfo(Canvas canvas, String flatInfo, String floatRealtor) {
        this.canvas = canvas;
        this.flatInfo = new TextArea(flatInfo);
        this.floatRealtor = new TextArea(floatRealtor);
        this.flatTypeID = null;
        this.price = null;
        this.numberOfRooms = null;
        this.houseID = null;
        this.floor = null;
        this.area = new SimpleIntegerProperty();
        this.image = new SimpleStringProperty();
        this.flatInfo.setStyle("-fx-wrap-text: true");
        this.floatRealtor.setStyle("-fx-wrap-text: true");
    }

    public FlatInfo() {
        this.canvas = new Canvas();
        this.flatInfo = new TextArea();
        this.floatRealtor = new TextArea();
        this.flatTypeID = new SimpleIntegerProperty();
        this.price = new SimpleIntegerProperty();
        this.numberOfRooms = new SimpleIntegerProperty();
        this.houseID = new SimpleIntegerProperty();
        this.floor = new SimpleIntegerProperty();
        this.area = new SimpleIntegerProperty();
        this.image = new SimpleStringProperty();
        this.flatInfo.setStyle("-fx-wrap-text: true");
        this.floatRealtor.setStyle("-fx-wrap-text: true");
    }

    public FlatInfo(int price, int numberOfRooms, int floor, int houseID, int typeID, int area, String imageID) {
        this.canvas = new Canvas();
        this.flatInfo = new TextArea();
        this.floatRealtor = new TextArea();
        this.flatTypeID = new SimpleIntegerProperty(typeID);
        this.price = new SimpleIntegerProperty(price);
        this.numberOfRooms = new SimpleIntegerProperty(numberOfRooms);
        this.houseID = new SimpleIntegerProperty(houseID);
        this.floor = new SimpleIntegerProperty(floor);
        this.area = new SimpleIntegerProperty(area);
        this.image = new SimpleStringProperty(imageID);
        this.flatInfo.setStyle("-fx-wrap-text: true");
        this.floatRealtor.setStyle("-fx-wrap-text: true");
    }

    public void setImage(String image) {
        this.image.set(image);
    }

    public String getImage() {
        return image.get();
    }

    public SimpleStringProperty imageProperty() {
        return image;
    }

    public void setArea(int area) {
        this.area.set(area);
    }

    public int getArea() {
        return area.get();
    }

    public SimpleIntegerProperty areaProperty() {
        return area;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public String getFlatInfo() {
        return flatInfo.getText();
    }

    public String getFloatRealtor() {
        return floatRealtor.getText();
    }



    public void setPrice(int price) {
        this.price.set(price);
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms.set(numberOfRooms);
    }

    public void setFloor(int floor) {
        this.floor.set(floor);
    }

    public void setHouseID(int houseID) {
        this.houseID.set(houseID);
    }

    public void setFlatTypeID(int flatTypeID) {
        this.flatTypeID.set(flatTypeID);
    }

    public int getPrice() {
        return price.get();
    }

    public SimpleIntegerProperty priceProperty() {
        return price;
    }

    public int getNumberOfRooms() {
        return numberOfRooms.get();
    }

    public SimpleIntegerProperty numberOfRoomsProperty() {
        return numberOfRooms;
    }

    public int getFloor() {
        return floor.get();
    }

    public SimpleIntegerProperty floorProperty() {
        return floor;
    }

    public int getHouseID() {
        return houseID.get();
    }

    public SimpleIntegerProperty houseIDProperty() {
        return houseID;
    }

    public int getFlatTypeID() {
        return flatTypeID.get();
    }

    public SimpleIntegerProperty flatTypeIDProperty() {
        return flatTypeID;
    }

    public void setFlatInfo(String flatInfo) {
        this.flatInfo.setText(flatInfo);
    }

    public void setFloatRealtor(String floatRealtor) {
        this.floatRealtor.setText(floatRealtor);
    }

    public void setStyleToTextArea(String s) {
        this.flatInfo.setStyle(s);
        this.floatRealtor.setStyle(s);
    }
}
