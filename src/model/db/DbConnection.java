package model.db;

import util.LoadConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    public static Connection getInstance(){

        LoadConfig.loadProperties();

        try{
            return DriverManager.getConnection(
                    LoadConfig.PROPERTIES.getProperty("DB_URL"),
                    LoadConfig.PROPERTIES.getProperty("DB_USERNAME"),
                    LoadConfig.PROPERTIES.getProperty("DB_PASSWORD")
            );
        }catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
}
