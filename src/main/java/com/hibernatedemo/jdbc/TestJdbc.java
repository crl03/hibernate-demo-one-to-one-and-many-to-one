package com.hibernatedemo.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {
    public static void main(String[] args) {
//        String jdbcUrl = "jdbc:postgresql://localhost:5432/movie_data_clone?useSSL=false";
        String jdbcUrl = "jdbc:postgresql://ec2-107-23-76-12.compute-1.amazonaws.com:5432/db1hb51t59c1fl?useSSL=false";
//        String userID = "postgres";
        String userID = "mqyyyxuwpevpdj";
//        String pass = "#Xg8ubbeLPN3";
        String pass = "dbfab1d187105ce760c44e6dabc579a37677a73230ec394e7840dfbcb8ac195a";

        try {
            System.out.println("Connecting to db: " + jdbcUrl);
            Connection myConn = DriverManager.getConnection(jdbcUrl, userID, pass);

            System.out.println("Connection Successful.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
