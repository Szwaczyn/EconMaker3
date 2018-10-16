package hoodStuff;

import javafx.fxml.FXML;

import java.sql.*;

/**
 * Created $(DATE)
 */

public class sqlConnection
{
    private String account = "root";
    private String password = "";
    private String addressSQL = "jdbc:mysql://localhost:3306/econmaker";
    Connection connectionToSQL = null;


    public boolean checkConnection()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            connectionToSQL = DriverManager.getConnection(addressSQL, account, password);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
