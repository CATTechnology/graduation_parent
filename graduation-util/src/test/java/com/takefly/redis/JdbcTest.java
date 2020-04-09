package com.takefly.redis;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author 戴灵飞 dailingfei
 * @version 1.0
 * @date 2020/4/4 22:50
 */
public class JdbcTest {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String jdbcUrl = "jdbc:mysql://218.78.84.3:3306/graduation_user?useUnicode=true&useCharacterEncoding=utf8";
        Class.forName("com.mysql.jdbc.Driver");
        DriverManager.getConnection(jdbcUrl , "root" , "980518");
    }
}
