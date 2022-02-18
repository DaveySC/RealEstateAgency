module leshka.realestateagency {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.microsoft.sqlserver.jdbc;
    requires java.sql;

    opens leshka.realestateagency to javafx.fxml;
    exports leshka.realestateagency;

    opens leshka.realestateagency.ui.startwindow to javafx.fxml;
    exports leshka.realestateagency.ui.startwindow;

    opens leshka.realestateagency.ui.loginwindow to javafx.fxml;
    exports leshka.realestateagency.ui.loginwindow;

    opens leshka.realestateagency.ui.registerwindow to javafx.fxml;
    exports leshka.realestateagency.ui.registerwindow;

    opens leshka.realestateagency.data to java.base;
    exports leshka.realestateagency.data;


    opens leshka.realestateagency.ui.postwindow to javafx.fxml;
    exports leshka.realestateagency.ui.postwindow;


    opens leshka.realestateagency.ui.settingwindow to javafx.fxml;
    exports leshka.realestateagency.ui.settingwindow;


    opens leshka.realestateagency.ui.newcompanywindow to javafx.fxml;
    exports leshka.realestateagency.ui.newcompanywindow;


    opens leshka.realestateagency.ui.infowindow to javafx.fxml;
    exports leshka.realestateagency.ui.infowindow;

}