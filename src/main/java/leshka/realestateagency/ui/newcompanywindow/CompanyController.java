package leshka.realestateagency.ui.newcompanywindow;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import leshka.realestateagency.data.DataBaseHandler;
import leshka.realestateagency.helper.AlertGenerator;
import leshka.realestateagency.ui.postwindow.PostController;

public class CompanyController {
    DataBaseHandler dataBaseHandler;
    private PostController postController;

    public void setPostController(PostController postController) {
        this.dataBaseHandler = DataBaseHandler.getInstance();
        this.postController = postController;
    }

    @FXML
    private TextField textField1;

    @FXML
    private TextField textField2;

    @FXML
    void create(ActionEvent event) {
        String name = textField1.getText();
        String rate = textField2.getText();
        if (name.isEmpty() || rate.isEmpty()) {
            AlertGenerator.showAlert("Заполните все поля", Alert.AlertType.ERROR, null);
        }
        this.postController.companyRule.setText(name);
        this.postController.rateCompany = Integer.parseInt(rate);
        String query = "insert into управляющая_компания(Название, Рейтинг) values('"+ name +"',"+ Integer.parseInt(rate) +")";
        this.dataBaseHandler.executeAction(query);
        this.postController.setCompanyRuleItems();
        Stage stage = (Stage) textField1.getScene().getWindow();
        stage.close();
    }



}
