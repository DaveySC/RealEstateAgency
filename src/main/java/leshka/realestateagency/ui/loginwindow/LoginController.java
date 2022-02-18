package leshka.realestateagency.ui.loginwindow;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import leshka.realestateagency.data.DataBaseHandler;

import leshka.realestateagency.data.UserInfo;
import leshka.realestateagency.helper.AlertGenerator;
import leshka.realestateagency.helper.Switcher;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
    UserInfo userInfo;
    DataBaseHandler dataBaseHandler;

    @FXML
    private CheckBox buyerCheckBox;

    @FXML
    private CheckBox realtorCheckBox;

    @FXML
    private Button RegisterButton;

    @FXML
    private Button loginButton;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private AnchorPane rootPane;

    @FXML
    void signIn(ActionEvent event) {
        String login = loginField.getText();
        String password = passwordField.getText();
        if (login.isEmpty() || password.isEmpty()) {
            AlertGenerator.showAlert("Заполните все поля для авторизации", Alert.AlertType.ERROR, null);
            return;
        }

        if (!buyerCheckBox.isSelected() && !realtorCheckBox.isSelected()) {
            AlertGenerator.showAlert("Заполните все поля для регистрации", Alert.AlertType.ERROR, null);
            return;
        }

        if (password.equals("admin") && login.equals("admin")) {
            this.userInfo.setAll(login, password, "admin", "admin", null, null, UserInfo.Role.ADMIN);
            String path = "/leshka/realestateagency/ui/startwindow/start-view.fxml";
            Switcher.switchWindow((Stage)rootPane.getScene().getWindow(),path,this);
            return;
        }

        if (buyerCheckBox.isSelected()) {
            buyerAuthorize(login,  password);
        } else {
            realtorRegister(login, password);
        }

    }

    private void realtorRegister(String login, String password) {
        String query = "select * from сотрудники where сотрудники.Логин = '"+ login +"';";
        ResultSet result = this.dataBaseHandler.executeQuery(query);
        try {
            if (result.next() && result.getString("пароль").equals(password)) {
                String name = result.getString("Имя");
                String secondName = result.getString("Фамилия");
                String address = result.getString("Адрес");
                String age = result.getString("Возраст");

                UserInfo.Role role = UserInfo.Role.REALTOR;
                this.userInfo.setAll(login, password,name, secondName, address, age, role);
                String path = "/leshka/realestateagency/ui/startwindow/start-view.fxml";
                Switcher.switchWindow((Stage)rootPane.getScene().getWindow(),path,this);
            } else {
                AlertGenerator.showAlert("Неверный логин или пароль", Alert.AlertType.ERROR, null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void buyerAuthorize(String login, String password) {
        String query = "select * from покупатели where Логин = '"+ login +"';";
        ResultSet result = this.dataBaseHandler.executeQuery(query);
        try {
            if (result.next()) {
                String passDB =  result.getString("Пароль");
                if (passDB.equals(password)) {
                    String path = "/leshka/realestateagency/ui/startwindow/start-view.fxml";
                    String name = result.getString("Имя");
                    String secondName = result.getString("Фамилия");
                    String address = result.getString("Адрес");
                    String age = result.getString("Возраст");

                    UserInfo.Role role = UserInfo.Role.BUYER;
                    this.userInfo.setAll(login, password,name, secondName, address, age, role);
                    Switcher.switchWindow((Stage)rootPane.getScene().getWindow(),path,this);
                } else {
                    AlertGenerator.showAlert("Неверный логин или пароль", Alert.AlertType.ERROR, null);
                }
            } else {
                AlertGenerator.showAlert("Неверный логин или пароль", Alert.AlertType.ERROR, null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void signUp(ActionEvent event) {
        String path = "/leshka/realestateagency/ui/registerwindow/register-view.fxml";
        Switcher.switchWindow((Stage)rootPane.getScene().getWindow(),path,this);
    }


    @FXML
    void initialize() {
         this.dataBaseHandler = DataBaseHandler.getInstance();
         this.userInfo = UserInfo.getInstance();
    }


    @FXML
    void chose1(ActionEvent event) {
        realtorCheckBox.setSelected(false);
    }

    @FXML
    void chose2(ActionEvent event) {
        buyerCheckBox.setSelected(false);
    }

}
