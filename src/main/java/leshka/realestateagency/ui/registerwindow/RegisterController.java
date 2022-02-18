package leshka.realestateagency.ui.registerwindow;

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

public class RegisterController {
    UserInfo userInfo;
    DataBaseHandler dataBaseHandler;

    @FXML
    private TextField addressField;

    @FXML
    private TextField ageField;

    @FXML
    private CheckBox buyerCheckBox;

    @FXML
    private TextField loginField;

    @FXML
    private TextField nameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private CheckBox realtorCheckBox;

    @FXML
    private Button registerButton;

    @FXML
    private TextField secondNameField;

    @FXML
    private AnchorPane rootPane;

    @FXML
    void register(ActionEvent event) {
        String address = addressField.getText();
        address = address.replaceAll(" ", "");

        String name = nameField.getText();
        name = name.replaceAll(" ", "");

        String secondName = secondNameField.getText();
        secondName = secondName.replaceAll(" ", "");

        String age = ageField.getText();
        age = age.replaceAll(" ", "");

        String login = loginField.getText();
        login = login.replaceAll(" ", "");

        String password = passwordField.getText();
        password = password.replaceAll(" ", "");


        if (address.isEmpty() || name.isEmpty() || secondName.isEmpty() ||
                age.isEmpty() || login.isEmpty() || password.isEmpty()) {
            AlertGenerator.showAlert("Заполните все поля для регистрации", Alert.AlertType.ERROR, null);
            return;
        }

        if (!buyerCheckBox.isSelected() && !realtorCheckBox.isSelected()) {
            AlertGenerator.showAlert("Заполните все поля для регистрации", Alert.AlertType.ERROR, null);
            return;
        }

        if (buyerCheckBox.isSelected()) {
            this.userInfo.setAll(login,password,name,secondName,address,age, UserInfo.Role.BUYER);
            buyerRegister(name, secondName, age, login, password, address);
        } else {
            this.userInfo.setAll(login,password,name,secondName,address,age, UserInfo.Role.REALTOR);
            realtorRegister(name, secondName, age, login, password, address);
        }



    }

    private void realtorRegister(String name, String secondName, String age, String login, String password, String address) {
        String query = "select * from сотрудники where сотрудники.Логин = '"+  login +"';";
        ResultSet result = this.dataBaseHandler.executeQuery(query);

        try {
            if (result.next()) {
                AlertGenerator.showAlert("Пользователь с таким логином уже существует", Alert.AlertType.ERROR, null);
                return;
            }

            query = "insert into сотрудники(Имя, Фамилия, Пароль, Логин, Адрес, Возраст) values(" +
                    "'" + name +"'," +
                    "'" + secondName + "'," +
                    "'" + password + "'," +
                    "'" + login + "'," +
                    "'" + address + "'," +
                    "'" + age + "'" +
                    ");";

            if (this.dataBaseHandler.executeAction(query)) {
                String path = "/leshka/realestateagency/ui/startwindow/start-view.fxml";
                Switcher.switchWindow((Stage) rootPane.getScene().getWindow(),path,this);
            } else {
                AlertGenerator.showAlert("Что-то пошло не так", Alert.AlertType.ERROR, null);
            }

        }  catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    private void buyerRegister(String name, String secondName, String age, String login, String password, String address) {
        String query = "select * from покупатели where покупатели.Логин = '"+  login + "';";
        ResultSet result = this.dataBaseHandler.executeQuery(query);

        try {
            if (result.next()) {
                AlertGenerator.showAlert("Пользователь с таким логином уже существует", Alert.AlertType.ERROR, null);
                return;
            }

            query = "insert into покупатели(Имя, Фамилия, Пароль, Логин, Адрес, Возраст) values(" +
                    "'" + name +"'," +
                    "'" + secondName + "'," +
                    "'" + password + "'," +
                    "'" + login + "'," +
                    "'" + address + "'," +
                    "'" + age + "'" +
                    ");";

            if (this.dataBaseHandler.executeAction(query)) {
                String path = "/leshka/realestateagency/ui/startwindow/start-view.fxml";
                Switcher.switchWindow((Stage) rootPane.getScene().getWindow(),path,this);
            } else {
                AlertGenerator.showAlert("Что-то пошло не так", Alert.AlertType.ERROR, null);
            }

        }  catch (SQLException exception) {
            exception.printStackTrace();
        }
    }


    @FXML
    void back(ActionEvent event) {
        String path = "/leshka/realestateagency/ui/loginwindow/login-view.fxml";
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
