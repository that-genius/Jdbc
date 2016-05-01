package com.company;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JdbcSimplestExample {

    public static void main(String[] args) {
        retrieveDataFromDbTable();
    }

    private static void retrieveDataFromDbTable() {
        Connection connection = null;
        try {
            //URL к базе состоит из протокола:подпротокола://[хоста]:[порта_СУБД]/[БД] и других_сведений
            String url = "jdbc:mysql://127.0.0.1:3306/blogdb";
            String name = "root";
            String password = "password";
            //Загружаем драйвер
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Драйвер подключен");
            //Создаём соединение
            connection = DriverManager.getConnection(url, name, password);
            System.out.println("Соединение установлено");
            Statement statement = connection.createStatement();
            ResultSet result1 = statement.executeQuery("select * from user");
            System.out.println("Выводим statement");
            while (result1.next()) {
                System.out.println("Номер в выборке #" + result1.getRow()
                        + "\t Логин: " + result1.getString("username")
                        + "\t Password: " + result1.getString("password"));
            }
        } catch (Exception ex) {
            //выводим наиболее значимые сообщения
            Logger.getLogger(JdbcSimplestExample.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JdbcSimplestExample.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}

