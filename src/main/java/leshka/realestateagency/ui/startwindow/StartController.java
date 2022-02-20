package leshka.realestateagency.ui.startwindow;


import com.microsoft.sqlserver.jdbc.StringUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import leshka.realestateagency.data.DataBaseHandler;
import leshka.realestateagency.data.FlatInfo;
import leshka.realestateagency.data.InfrastructureInfo;
import leshka.realestateagency.data.UserInfo;
import leshka.realestateagency.helper.Switcher;
import leshka.realestateagency.ui.postwindow.PostController;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

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
    private Button myAdButton;

    @FXML
    private Button postButton;

    @FXML
    void initialize() {
        this.dataBaseHandler = DataBaseHandler.getInstance();
        //init user
        this.userInfo = UserInfo.getInstance();
        if (this.userInfo.getRole() == UserInfo.Role.BUYER) {
            postButton.setDisable(true);
            postButton.setVisible(false);
            myAdButton.setText("Мои покупки");
        }
        doAllCoolThings(null);
    }

    public void doAllCoolThings(String queryFromMethod) {
        //init database

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
        realtorColumn.setStyle("-fx-wrap-text: true");
        infoColumn.setStyle("-fx-wrap-text: true");
        //creating images
        String path = "/leshka/realestateagency/ui/icons/disain_0.jpg";
        Image image = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(path)));
        Canvas canvas = new Canvas(308, 200);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        //adding all flats
        String query = "select * from квартиры";
        if (queryFromMethod != null) {
             query = queryFromMethod;
        }
        this.tableView.getItems().clear();
        this.data.clear();
        ResultSet result = this.dataBaseHandler.executeQuery(query);
        initDataArray(result);
        for (FlatInfo elem : data) {
            String fileName = elem.getImage();
            if (fileName.isEmpty()) {
                fileName = "disain_0.jpg";
            }
            path = "/leshka/realestateagency/ui/icons/" + fileName;
            image = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(path)));
            canvas = new Canvas(308, 200);
            gc = canvas.getGraphicsContext2D();
            gc.drawImage(image,0,0, canvas.getWidth(),canvas.getHeight());
            elem.setCanvas(canvas);

            String helpQuery = "select * from тип_квартиры where ID = " + elem.getFlatTypeID() +";";
            ResultSet helpResult = this.dataBaseHandler.executeQuery(helpQuery);
            String flotType = "";
            try {
                if (helpResult.next()) {
                    flotType = helpResult.getString("Тип");
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
            String secondColumnInfoFlat = "Цена: "+ elem.getPrice() + "\n" +
                    "Количество комнат: " + elem.getNumberOfRooms() + "\n" +
                    "Этаж: " + elem.getFloor() + "\n" +
                    "Тип Квартиры: " + flotType + "\n" +
                    "Площадь: "+ elem.getArea() + "\n" ;

            ArrayList<InfrastructureInfo> infoAboutObjNearFlat = new ArrayList<>();
            initObjNearFlat(elem.getHouseID(), infoAboutObjNearFlat);
            secondColumnInfoFlat += "Объекты рядом: ";

            for (InfrastructureInfo helpElem : infoAboutObjNearFlat) {
                secondColumnInfoFlat += helpElem.getDescription() + ", ";
            }
            secondColumnInfoFlat = secondColumnInfoFlat.substring(0, secondColumnInfoFlat.length() - 2);
            secondColumnInfoFlat += ".";
            elem.setStyleToTextArea("-fx-wrap-text: true");
            elem.setFlatInfo(secondColumnInfoFlat);

            String thirdColumnInfo = "";


        }
        tableView.getItems().setAll(data);
        //adding admin panel
        if (this.userInfo.getRole() == UserInfo.Role.ADMIN ) {
            MenuItem item = new MenuItem("Панель админа");
            item.setOnAction(this::openAdminPane);
            userNameField.getItems().add(item);
        }





    }

    private void initObjNearFlat(int houseID, ArrayList<InfrastructureInfo> infoAboutObjNearFlat) {
        String query = "select * from дом where ID = " + houseID +";";
        ResultSet result = this.dataBaseHandler.executeQuery(query);
        int numberOfList = -1;
        int idInfrascructure = -1;
        try {
            if (result.next()) {
                idInfrascructure = Integer.parseInt(result.getString("ID_инфраструктуры"));
                query = "select * from инфраструктура where ID = " + idInfrascructure + ";";
                ResultSet superDuperResult = this.dataBaseHandler.executeQuery(query);

                if (superDuperResult.next()) {
                    numberOfList = Integer.parseInt(superDuperResult.getString("номер_списка"));
                }


                query = "select * from список_объектов_в_инфраструктуре where номер_списка = "+ numberOfList +";";
                ResultSet helpResult = this.dataBaseHandler.executeQuery(query);

                while(helpResult.next()) {
                    String idOjb = helpResult.getString("ID_объекта");

                    query = "select * from объекты where ID = " + idOjb + ";";
                    ResultSet imConfusedResult = this.dataBaseHandler.executeQuery(query);

                    if (imConfusedResult.next()) {
                        String idTypeObject = imConfusedResult.getString("ID_типа");
                        String description = imConfusedResult.getString("Описание");

                        query = "select * from тип_объекта where ID = "+ idTypeObject + ";";
                        ResultSet helpHelpResult = this.dataBaseHandler.executeQuery(query);
                        String title = "";
                        if (helpHelpResult.next()) {
                            title = helpHelpResult.getString("Название");
                        }
                        infoAboutObjNearFlat.add(new InfrastructureInfo(idTypeObject, description, title, idOjb));
                    }
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    private void initDataArray(ResultSet result) {
        try {
            if (result == null) {
                return;
            }
            while(result.next()) {
                int price = Integer.parseInt(result.getString("Цена"));
                int numberOfRooms = Integer.parseInt(result.getString("Количество_комнат"));
                int floor =  Integer.parseInt(result.getString("Этаж"));
                int houseID =  Integer.parseInt(result.getString("ID_дома"));
                int typeID =  Integer.parseInt(result.getString("ID_тип_квартиры"));
                int area =  Integer.parseInt(result.getString("Площадь"));
                String image =  result.getString("Изображение");
                data.add(new FlatInfo(price, numberOfRooms, floor, houseID, typeID, area, image));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void openAdminPane(ActionEvent actionEvent) {
        String path = "/leshka/realestateagency/ui/adminwindow/admin-view.fxml";
        Switcher.openNewWindow((Stage) tableView.getScene().getWindow(), path, this);
    }

    private PostController postController;
    @FXML
    void postFlat(ActionEvent event) {
        String path = "/leshka/realestateagency/ui/postwindow/post-view.fxml";
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource(path));
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

            this.postController = loader.getController();
            loader.setController(this.postController);
            this.postController.setPostController(this);

        } catch (IOException e) {
            e.printStackTrace();
        }

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
        doAllCoolThings(createQuery());
    }


    private String createQuery() {

        String query = "Select * from квартиры where";
        if (!priceTextField1.getText().isEmpty()) {
            query += " цена >= " + priceTextField1.getText();
        } else {
            query += " цена >= " + String.valueOf(0);
        }
        if (!priceTextField2.getText().isEmpty()) {
            query += " and цена <= " + priceTextField2.getText();
        } else {
            query += " and цена <= " + String.valueOf(Integer.MAX_VALUE);
        }

        if (oneRoom.isSelected() || twoRoom.isSelected() || threeRoom.isSelected() || fourRoom.isSelected()) {
            boolean helperPelper = false;
            if (oneRoom.isSelected()) {
                query += " and Количество_комнат = 1";
                helperPelper = true;
            }
            if (twoRoom.isSelected()) {
                if (helperPelper) {
                    query += " or Количество_комнат = 2";
                } else {
                    query += " and Количество_комнат = 2";
                    helperPelper = true;
                }
            }

            if (threeRoom.isSelected()) {
                if (helperPelper) {
                    query += " or Количество_комнат = 3";
                } else {
                    query += " and Количество_комнат = 3";
                    helperPelper = true;
                }
            }

            if (fourRoom.isSelected()) {
                if (helperPelper) {
                    query += " or Количество_комнат >= 4";
                } else {
                    query += " and Количество_комнат >= 4";
                    helperPelper = true;
                }
            }
        } else {
            query += " and Количество_комнат < 1000";
        }

        if (!areaFromTextField.getText().isEmpty()) {
            query += " and Площадь >= " + areaFromTextField.getText();
        } else {
            query += " and Площадь >= 0";
        }


        if (!areaToTextField.getText().isEmpty()) {
            query += " and Площадь <= " + areaFromTextField.getText();
        } else {
            query += " and Площадь <= " + String.valueOf(Integer.MAX_VALUE);
        }


        if (!floorFlat1.getText().isEmpty()) {
            query += " and Этаж >=" + floorFlat1.getText();
        } else {
            query += " and Этаж >= 0";
        }

        if (!floorFlat2.getText().isEmpty()) {
            query += " and Этаж <=" + floorFlat2.getText();
        } else {
            query += " and Этаж <= " + String.valueOf(Integer.MAX_VALUE);
        }

        String helpQuery = "select * from дом where ";

        return query;
    }



    @FXML
    void showMyAdvertisement(ActionEvent event) {

    }


}
