package com.barclays.ems.config;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class Database {

    private Connection con = null;
    private static final String url;
    private static final String driver;
    private static final String user;
    private static final String password;

    static {
        driver = "com.mysql.cj.jdbc.Driver";
        url = "jdbc:mysql://localhost:3306/database1";
        user = "root";
        password = "root";

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public  Connection getConnection() {

        try  {
            if(con == null || con.isClosed()) {
                try {
                    con = DriverManager.getConnection(url, user, password);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return con;
    }
}
