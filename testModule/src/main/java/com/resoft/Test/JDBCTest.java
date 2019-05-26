package com.resoft.Test;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCTest {
    @Test
    public void test() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url ="jdbc:mysql://localhost/temp-test?"+
        "user=root&password=root&" +
                "autoReconnect=true&useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8";
        //myDB为数据库名
        Connection conn= DriverManager.getConnection(url);
        conn.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from user");
        System.out.println(resultSet);

    }
}
