package leshka.realestateagency.ui.settingwindow;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import leshka.realestateagency.data.DataBaseHandler;
import leshka.realestateagency.data.UserInfo;
import leshka.realestateagency.helper.AlertGenerator;

public class SettingController {
    UserInfo userInfo;
    DataBaseHandler dataBaseHandler;

    @FXML
    private TextField textField1;

    @FXML
    private TextField textField2;

    @FXML
    private TextField textField3;

    @FXML
    private TextField textField4;

    @FXML
    private TextField textField5;

    @FXML
    private TextField textField6;


    @FXML
    void initialize() {
        this.dataBaseHandler = DataBaseHandler.getInstance();
        this.userInfo = UserInfo.getInstance();
        this.textField1.setText(userInfo.getLogin());
        this.textField2.setText(userInfo.getPassword());
        this.textField3.setText(userInfo.getName());
        this.textField4.setText(userInfo.getSecondName());
        this.textField5.setText(userInfo.getAddress());
        this.textField6.setText(userInfo.getAge());
    }


    @FXML
    void changeUserInfo(ActionEvent event) {
        String query = "";
        if (this.userInfo.getRole() == UserInfo.Role.BUYER) {
          query = "update покупатели set ";
        } else {
            query = "update сотрудники set ";
        }

        query += "Логин ='"    + textField1.getText() + "'," +
                " Пароль ='"   + this.userInfo.getLogin() + "'," +
                " Имя ='"      + textField3.getText() + "'," +
                " Фамилия ='"  + textField4.getText() + "'," +
                " Адрес ='"    + textField5.getText() + "'," +
                " Возраст ='"  + textField6.getText() + "'" +
                " where " +
                " Логин = '"   + this.userInfo.getLogin() + "';";

        this.userInfo.setAll(this.textField1.getText(), this.userInfo.getPassword(), this.textField3.getText(),
                this.textField4.getText(), this.textField5.getText(), this.textField6.getText(),
                this.userInfo.getRole());

        if (this.dataBaseHandler.executeAction(query)) {
            AlertGenerator.showAlert("Данные изменены", Alert.AlertType.INFORMATION, null);
        } else {
            AlertGenerator.showAlert("что-то пошло не так", Alert.AlertType.ERROR, null);
        }

        Stage stage = (Stage) textField1.getScene().getWindow();
        stage.close();
    }
}

