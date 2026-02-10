package com.crud.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoH2 {
    
    private static final String url = "jdbc:h2:mem:dbcrud;DB_CLOSE_DELAY=-1";
    private static final String user = "sa";
    private static final String pwd = "";

    public static Connection getConnection() throws SQLException {

        return DriverManager.getConnection(url, user, pwd);
    }

}
