package leshka.realestateagency.ui.postwindow;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import leshka.realestateagency.data.DataBaseHandler;
import leshka.realestateagency.data.FlatInfo;
import leshka.realestateagency.data.HouseInfo;
import leshka.realestateagency.ui.infowindow.InfoController;
import leshka.realestateagency.ui.newcompanywindow.CompanyController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;


public class PostController {
    HouseInfo houseInfo;
    FlatInfo flatInfo;
    DataBaseHandler dataBaseHandler;
    CompanyController companyController;
    InfoController infoController;
    public int rateCompany = 0;

    public void setCompanyController(CompanyController companyController) {
        this.companyController = companyController;
    }

    @FXML
    private MenuButton streetBox;

    @FXML
    public MenuButton companyRule;

    @FXML
    private MenuButton flatType;

    @FXML
    private MenuButton condtionBox;

    @FXML
    private HBox rootPane;

    @FXML
    private Canvas canvas;

    private File correctFile = null;

    @FXML
    void chooseDir(ActionEvent event) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(stage);
        if(file != null && correctFile(file)) {
            GraphicsContext gc = canvas.getGraphicsContext2D();
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            try {
                FileInputStream fis = new FileInputStream(file);
                Image image = new Image(fis);
                double canvasWidth = canvas.getWidth() / 2.0;
                double canvasHeight = canvas.getHeight() / 2.0;
                gc.drawImage(image, canvasWidth - 450 / 2.0, canvasHeight - 350 / 2.0, canvas.getWidth(), canvas.getHeight());
                gc.setLineWidth(3.0);
                gc.setStroke(Color.WHITE);
                gc.strokeRect(canvasWidth - 450 / 2.0, canvasHeight - 350 / 2.0, canvas.getWidth(), canvas.getHeight());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

    private boolean correctFile(File file) {
        String fileName = file.getName();
        return fileName.endsWith(".png") || fileName.endsWith(".jpg");
    }

    @FXML
    public void initialize() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0,0, canvas.getWidth(), canvas.getHeight());
        this.companyRule.getItems().clear();
        this.dataBaseHandler = DataBaseHandler.getInstance();
        setCompanyRuleItems();
        setStreetItems();
        setCondtionItems();
        this.flatInfo = new FlatInfo();
        this.houseInfo = new HouseInfo();


    }
    public void setCondtionItems() {
        String query = "select * from [состояние_дома]";
        ResultSet result = this.dataBaseHandler.executeQuery(query);
        String contidion = "";
        ObservableList<MenuItem> data = FXCollections.observableArrayList();
        try {
            while (result.next()){
                contidion = result.getString("Состояние");
                MenuItem item = new MenuItem(contidion);
                item.setOnAction(actionEvent -> {
                    this.condtionBox.setText(item.getText());
                    this.houseInfo.setCondition(item.getText());
                });
                data.add(item);
            }
            this.condtionBox.getItems().setAll(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setStreetItems() {
        String query = "select * from улица";
        ResultSet result = this.dataBaseHandler.executeQuery(query);
        String streetName = "";
        ObservableList<MenuItem> data = FXCollections.observableArrayList();
        try {
            while (result.next()){
                streetName = result.getString("Название");
                MenuItem item = new MenuItem(streetName);
                item.setOnAction(actionEvent -> {
                    this.streetBox.setText(item.getText());
                    this.houseInfo.setStreet(item.getText());
                });
                data.add(item);
            }
            this.streetBox.getItems().setAll(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void setCompanyRuleItems() {
        String query = "select * from управляющая_компания";
        ResultSet result = this.dataBaseHandler.executeQuery(query);
        String companyName = "";
        ObservableList<MenuItem> data = FXCollections.observableArrayList();
        try {
            while (result.next()){
                companyName = result.getString("Название");
                MenuItem item = new MenuItem(companyName);
                item.setOnAction(actionEvent -> {
                    this.companyRule.setText(item.getText());
                    this.houseInfo.setManCom(item.getText());
                });
                data.add(item);
            }
            this.companyRule.getItems().setAll(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private TextField priceTextField;
    @FXML
    private TextField roomsTextField;
    @FXML
    private TextField floorTextField;
    @FXML
    private TextField numberTextField;
    @FXML
    void post(ActionEvent event) {
        this.flatInfo.setPrice(Integer.parseInt(priceTextField.getText()));
        this.flatInfo.setNumberOfRooms(Integer.parseInt(roomsTextField.getText()));
        this.flatInfo.setFloor(Integer.parseInt(floorTextField.getText()));
        this.houseInfo.setNumber(Integer.parseInt(numberTextField.getText()));
    }

    @FXML
    void setFlatTypeComm(ActionEvent event) {
        this.flatType.setText("Коммунальная");
        this.flatInfo.setFlatTypeID(1);

    }

    @FXML
    void setFlatTypeStandart(ActionEvent event) {
        this.flatType.setText("Обычная");
        this.flatInfo.setFlatTypeID(3);
    }

    @FXML
    void setFlatTypeStud(ActionEvent event) {
        this.flatType.setText("Студия");
        this.flatInfo.setFlatTypeID(2);
    }



    @FXML
    void addNewCompany(ActionEvent event) {
        String path = "/leshka/realestateagency/ui/newcompanywindow/company-view.fxml";
        //Switcher.openNewWindow((Stage)this.canvas.getScene().getWindow(),path,this );
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource(path));
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

            this.companyController = loader.getController();
            loader.setController(this.companyController);
            this.companyController.setPostController(this);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void addInfo(ActionEvent event) {
        String path = "/leshka/realestateagency/ui/infowindow/infro-view.fxml";
        //Switcher.openNewWindow((Stage)this.canvas.getScene().getWindow(),path,this );
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource(path));
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            this.infoController = loader.getController();
            loader.setController(this.infoController);
            this.infoController.setPostController(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private MenuButton houseType;

    @FXML
    void setHouseTypeBetton(ActionEvent event) {
        houseType.setText("Бетонный");
        this.houseInfo.setType(4);
    }

    @FXML
    void setHouseTypeBrick(ActionEvent event) {
        houseType.setText("Кирпичный");
        this.houseInfo.setType(1);
    }

    @FXML
    void setHouseTypeMono(ActionEvent event) {
        houseType.setText("Монолитный");
        this.houseInfo.setType(3);
    }

    @FXML
    void setHouseTypePane(ActionEvent event) {
        houseType.setText("Панельный");
        this.houseInfo.setType(2);

    }

}
