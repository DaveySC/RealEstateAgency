package leshka.realestateagency.data;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FlatInfo {

    private Canvas canvas;
    private final SimpleStringProperty flatInfo;
    private final SimpleStringProperty floatRealtor;
    private final SimpleIntegerProperty price;
    private final SimpleIntegerProperty numberOfRooms;
    private final SimpleIntegerProperty floor;
    private final SimpleIntegerProperty houseID;
    private final SimpleIntegerProperty flatTypeID;

    public FlatInfo(Canvas canvas, SimpleStringProperty flatInfo, SimpleStringProperty floatRealtor, SimpleIntegerProperty price, SimpleIntegerProperty numberOfRooms, SimpleIntegerProperty floor, SimpleIntegerProperty houseID, SimpleIntegerProperty flatTypeID) {
        this.canvas = canvas;
        this.flatInfo = flatInfo;
        this.floatRealtor = floatRealtor;
        this.price = price;
        this.numberOfRooms = numberOfRooms;
        this.floor = floor;
        this.houseID = houseID;
        this.flatTypeID = flatTypeID;
    }

    public FlatInfo(Canvas canvas, String flatInfo, String floatRealtor) {
        this.canvas = canvas;
        this.flatInfo = new SimpleStringProperty(flatInfo);
        this.floatRealtor = new SimpleStringProperty(floatRealtor);
        this.flatTypeID = null;
        this.price = null;
        this.numberOfRooms = null;
        this.houseID = null;
        this.floor = null;
    }

    public FlatInfo() {
        this.canvas = new Canvas();
        this.flatInfo = new SimpleStringProperty();
        this.floatRealtor = new SimpleStringProperty();
        this.flatTypeID = new SimpleIntegerProperty();
        this.price = new SimpleIntegerProperty();
        this.numberOfRooms = new SimpleIntegerProperty();
        this.houseID = new SimpleIntegerProperty();
        this.floor = new SimpleIntegerProperty();
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public String getFlatInfo() {
        return flatInfo.get();
    }

    public SimpleStringProperty flatInfoProperty() {
        return flatInfo;
    }

    public String getFloatRealtor() {
        return floatRealtor.get();
    }

    public SimpleStringProperty floatRealtorProperty() {
        return floatRealtor;
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
        this.flatInfo.set(flatInfo);
    }

    public void setFloatRealtor(String floatRealtor) {
        this.floatRealtor.set(floatRealtor);
    }
}
