package leshka.realestateagency.ui.infowindow;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import leshka.realestateagency.data.DataBaseHandler;
import leshka.realestateagency.data.FlatInfo;
import leshka.realestateagency.data.InfrastructureInfo;
import leshka.realestateagency.ui.postwindow.PostController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InfoController {
    private PostController postController;
    private DataBaseHandler dataBaseHandler;

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

    }
    private ObservableList<InfrastructureInfo> data = FXCollections.observableArrayList();
    @FXML
    void initialize() {
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
                String title = "";
                String secondQuery = "select * from тип_объекта where ID ="+ typeId+";";
                ResultSet resultSecond = this.dataBaseHandler.executeQuery(secondQuery);
                if (resultSecond.next()) {
                    title = resultSecond.getString("Название");
                }
                data.add(new InfrastructureInfo(typeId, description, title));
            }
        }catch (SQLException exception) {
            exception.printStackTrace();
        }
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
}
