package leshka.realestateagency.ui.infowindow;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import leshka.realestateagency.data.DataBaseHandler;
import leshka.realestateagency.data.FlatInfo;
import leshka.realestateagency.data.InfrastructureInfo;
import leshka.realestateagency.helper.Switcher;
import leshka.realestateagency.ui.addnewojbwindow.AddNewObjController;
import leshka.realestateagency.ui.postwindow.PostController;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InfoController {
    private PostController postController;
    private DataBaseHandler dataBaseHandler;
    private AddNewObjController addNewObjController;

    public void setAddNewObjController(AddNewObjController addNewObjController) {
        this.addNewObjController = addNewObjController;
    }
    public void setPostController(PostController postController) {
        this.postController = postController;
    }


    @FXML
    private TableColumn<InfrastructureInfo, String> descriptColumnLeft;

    @FXML
    private TableColumn<InfrastructureInfo, String> descriptColumnRight;

    @FXML
    private TableView<InfrastructureInfo> leftTable;

    @FXML
    private TableColumn<InfrastructureInfo, String> nameColumnLeft;

    @FXML
    private TableColumn<InfrastructureInfo, String> nameColumnRight;

    @FXML
    private TableView<InfrastructureInfo> rightTable;

    @FXML
    void done(ActionEvent event) {
        ArrayList<InfrastructureInfo> infrastructureInfoArrayList = new ArrayList<>(this.rightTable.getItems());
        this.postController.initInfrastructureInfoArrayList(infrastructureInfoArrayList);
        Stage stage = (Stage) this.leftTable.getScene().getWindow();
        stage.close();
    }
    private ObservableList<InfrastructureInfo> data = FXCollections.observableArrayList();
    @FXML
    void initialize() {
        doAllCoolThings();
    }


    public void doAllCoolThings() {
        data.clear();
        this.dataBaseHandler = DataBaseHandler.getInstance();
        nameColumnLeft.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptColumnLeft.setCellValueFactory(new PropertyValueFactory<>("description"));

        nameColumnRight.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptColumnRight.setCellValueFactory(new PropertyValueFactory<>("description"));
        String query = "Select * from объекты";
        try {
            ResultSet result =  this.dataBaseHandler.executeQuery(query);
            while (result.next()) {
                String description = result.getString("Описание");
                String typeId = result.getString("ID_типа");
                String maneId = result.getString("ID");
                String title = "";
                String secondQuery = "select * from тип_объекта where ID ="+ typeId+";";
                ResultSet resultSecond = this.dataBaseHandler.executeQuery(secondQuery);
                if (resultSecond.next()) {
                    title = resultSecond.getString("Название");
                }
                data.add(new InfrastructureInfo(typeId, description, title, maneId));
            }
        }catch (SQLException exception) {
            exception.printStackTrace();
        }
        this.leftTable.getItems().clear();
        this.rightTable.getItems().clear();
        this.leftTable.getItems().setAll(data);

        this.leftTable.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.isPrimaryButtonDown() && mouseEvent.getClickCount() >= 2) {
                    if (leftTable.getSelectionModel().getSelectedItem() == null){
                        return;
                    }
                    rightTable.getItems().add(leftTable.getSelectionModel().getSelectedItem());
                    leftTable.getItems().remove(leftTable.getSelectionModel().getSelectedItem());
                }
            }
        });

        this.rightTable.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.isPrimaryButtonDown() && mouseEvent.getClickCount() >= 2) {
                    if (rightTable.getSelectionModel().getSelectedItem() == null){
                        return;
                    }
                    leftTable.getItems().add(rightTable.getSelectionModel().getSelectedItem());
                    rightTable.getItems().remove(rightTable.getSelectionModel().getSelectedItem());
                }
            }
        });
    }
    @FXML
    void addNewObj(ActionEvent event) {
        String path = "/leshka/realestateagency/ui/addnewojbwindow/addojb-view.fxml";
        //Switcher.openNewWindow((Stage) rightTable.getScene().getWindow(), path, this);
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource(path));
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            this.addNewObjController = loader.getController();
            loader.setController(this.addNewObjController);
            this.addNewObjController.setInfoController(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
