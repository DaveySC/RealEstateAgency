package leshka.realestateagency.ui.addnewojbwindow;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import leshka.realestateagency.data.DataBaseHandler;
import leshka.realestateagency.data.InfrastructureInfo;
import leshka.realestateagency.helper.AlertGenerator;
import leshka.realestateagency.ui.infowindow.InfoController;

public class AddNewObjController {
    DataBaseHandler dataBaseHandler;
    InfrastructureInfo infrastructureInfo;
    InfoController infoController;

    public void setInfoController(InfoController infoController) {
        this.infoController = infoController;
    }

    @FXML
    private MenuButton typeField;

    @FXML
    private TextArea textField2;

    @FXML
    void add(ActionEvent event) {
        this.infoController.setAddNewObjController(this);
        String description = textField2.getText();
        int integerTypeID = defineIDType(typeField.getText());
        if (description.isEmpty() || integerTypeID == -1) {
            AlertGenerator.showAlert("Заполните все поля", Alert.AlertType.ERROR, null);
            return;
        }
        infrastructureInfo.setDescription(description);
        infrastructureInfo.setIdTypeObject(String.valueOf(integerTypeID));
        String query = "insert into объекты(ID_типа, Описание) values('"+integerTypeID+"','"+description+"');";
        this.dataBaseHandler.executeAction(query);
        Stage stage = (Stage) typeField.getScene().getWindow();
        this.infoController.doAllCoolThings();
        stage.close();
    }

    private int defineIDType(String text) {
        if (text.equals("Тип")) {
            return -1;
        }
        if (text.equals("Больница")) {
            return 1;
        }
        if (text.equals("Школа")) {
            return 2;
        }
        if (text.equals("Университет")) {
            return 3;
        }
        if (text.equals("Парк")) {
            return 4;
        }
        if (text.equals("Сквер")) {
            return 5;
        }
        return -1;
    }

    @FXML
    void initialize() {
        this.dataBaseHandler = DataBaseHandler.getInstance();
        this.infrastructureInfo = new InfrastructureInfo(null,null,null, null);
    }


    @FXML
    void setHospitl(ActionEvent event) {
        typeField.setText("Больница");
    }

    @FXML
    void setPark(ActionEvent event) {
        typeField.setText("Парк");
    }

    @FXML
    void setSchool(ActionEvent event) {
        typeField.setText("Школа");
    }

    @FXML
    void setSkver(ActionEvent event) {
        typeField.setText("Сквер");
    }

    @FXML
    void setUniver(ActionEvent event) {
        typeField.setText("Университет");
    }
}
