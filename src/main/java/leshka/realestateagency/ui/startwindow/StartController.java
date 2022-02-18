package leshka.realestateagency.ui.startwindow;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelFormat;
import javafx.stage.Stage;
import leshka.realestateagency.data.DataBaseHandler;
import leshka.realestateagency.data.FlatInfo;
import leshka.realestateagency.data.UserInfo;
import leshka.realestateagency.helper.Switcher;
import org.w3c.dom.Text;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class StartController {
    UserInfo userInfo;
    DataBaseHandler dataBaseHandler;

    @FXML
    private TextField floorFlat1;

    @FXML
    private TextField floorFlat2;

    @FXML
    private TextField floorInHouseTextField1;

    @FXML
    private TextField floorInHouseTextField2;

    @FXML
    private TextField areaFromTextField;

    @FXML
    private TextField areaToTextField;

    @FXML
    private CheckBox oneRoom;

    @FXML
    private CheckBox fourRoom;

    @FXML
    private CheckBox threeRoom;

    @FXML
    private CheckBox twoRoom;

    @FXML
    private TextField priceTextField1;

    @FXML
    private TextField priceTextField2;

    @FXML
    private Canvas canvasLogo;

    @FXML
    private MenuButton userNameField;

    @FXML
    private TableColumn<FlatInfo, String> imageColumn;

    @FXML
    private TableColumn<FlatInfo, String> infoColumn;

    @FXML
    private TableColumn<FlatInfo, String> realtorColumn;

    @FXML
    private TableView<FlatInfo> tableView;

    private ObservableList<FlatInfo> data = FXCollections.observableArrayList();


    @FXML
    void initialize() {
        //init database
        this.dataBaseHandler = DataBaseHandler.getInstance();
        //init user
        this.userInfo = UserInfo.getInstance();
        this.userNameField.setText(userInfo.getName());
        System.out.println(this.userInfo.toString());
        //print logo
        GraphicsContext gcHelper = canvasLogo.getGraphicsContext2D();
        Image imageHelper = new Image("file:/leshka/realestateagency/ui/icons/disain_0.jpg");
        gcHelper.drawImage(imageHelper, 0, 0, 400, 400);
        //init columns
        imageColumn.setCellValueFactory(new PropertyValueFactory<>("canvas"));
        infoColumn.setCellValueFactory(new PropertyValueFactory<>("flatInfo"));
        realtorColumn.setCellValueFactory(new PropertyValueFactory<>("floatRealtor"));
        //creating images
        String path = "/leshka/realestateagency/ui/icons/disain_0.jpg";
        Image image = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(path)));
        Canvas canvas = new Canvas(308, 200);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.drawImage(image,0,0, canvas.getWidth(),canvas.getHeight());
        FlatInfo flat = new FlatInfo(canvas, "Жопа", "вторая жопа");
        data.add(flat);
        tableView.getItems().setAll(data);
        //test

        //adding admin panel
        if (this.userInfo.getRole() == UserInfo.Role.ADMIN ) {
            MenuItem item = new MenuItem("Панель админа");
            item.setOnAction(this::openAdminPane);
            userNameField.getItems().add(item);
        }
    }

    private void openAdminPane(ActionEvent actionEvent) {
        String path = "/leshka/realestateagency/ui/adminwindow/admin-view.fxml";
        Switcher.openNewWindow((Stage) tableView.getScene().getWindow(), path, this);
    }


    @FXML
    void postFlat(ActionEvent event) {
        String path = "/leshka/realestateagency/ui/postwindow/post-view.fxml";
        Switcher.openNewWindow((Stage) tableView.getScene().getWindow(), path, this);
    }


    @FXML
    void openSettingWindow(ActionEvent event) {
        String path = "/leshka/realestateagency/ui/settingwindow/setting-view.fxml";
        Switcher.openNewWindow((Stage) tableView.getScene().getWindow(), path, this);
    }

    @FXML
    void logOut(ActionEvent event) {
        this.userInfo.clearAll();
        String path = "/leshka/realestateagency/ui/loginwindow/login-view.fxml";
        Stage stage = (Stage) tableView.getScene().getWindow();
        stage.close();
        Switcher.openNewWindow(stage, path, this);
    }



    @FXML
    void showFlats(ActionEvent event) {

    }


    private String createQuery() {
        String query = "Select * from квартиры where";
        if (!priceTextField1.getText().isEmpty()) {

        }
        if (!priceTextField2.getText().isEmpty()) {

        }

        return query;
    }

}
