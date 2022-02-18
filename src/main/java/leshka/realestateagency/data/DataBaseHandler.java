package leshka.realestateagency.data;

import java.sql.*;

public class DataBaseHandler {
    private static DataBaseHandler dataBaseHandler = null;

    private static final String username = "sa";
    private static final String password = "passwordLl";
    private static final String url = "jdbc:sqlserver://DESKTOP-TBLMLR5:1433;DatabaseName=agency;encrypt=true;trustServerCertificate=true";

    private static Connection connection;

    private DataBaseHandler() {
        createConnection();
    }


    private void createConnection() {

        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connection succeed");
        } catch (SQLException  e) {
           e.printStackTrace();
        }

    }

    public static DataBaseHandler getInstance() {
        if (dataBaseHandler == null) {
            dataBaseHandler = new DataBaseHandler();
        }
        return dataBaseHandler;
    }


    public ResultSet executeQuery(String query) {
        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery(query);
        } catch (SQLException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public boolean executeAction(String query) {
        try {
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException exception) {
            System.out.println("Error in executeAction method");
            return false;
        }
        return true;
    }

    public static void main(String[] args) {

        DataBaseHandler dataBaseHandler = DataBaseHandler.getInstance();

    }
}
