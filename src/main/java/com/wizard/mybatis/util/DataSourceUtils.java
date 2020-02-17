package com.wizard.mybatis.util;

import com.wizard.mybatis.cfg.Configuration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSourceUtils {
    public static Connection getConnection(Configuration config) {
        Connection connection = null;
        try {
            Class.forName(config.getDriver());
            connection = DriverManager.getConnection(config.getUrl(), config.getUsername(), config.getPassword());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
