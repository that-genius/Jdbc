package com.company;

import java.sql.*;
import java.util.logging.*;

public class Main {

    public static void main(String[] args) {
        retrieveDataFromDbTable();
    }

    private static void retrieveDataFromDbTable() {
        Connection connection = null;
        try {
            connection = null;
            //URL к базе состоит из протокола:подпротокола://[хоста]:[порта_СУБД]/[БД] и других_сведений
            String url = "jdbc:mysql://127.0.0.1:3306/blogdb";
            //Имя пользователя БД
            String name = "root";
            //Пароль
            String password = "password";
            //Загружаем драйвер
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Драйвер подключен");
            //Создаём соединение
            connection = DriverManager.getConnection(url, name, password);
            System.out.println("Соединение установлено");
            //Для использования SQL запросов существуют 3 типа объектов:
            //1.Statement: используется для простых случаев без параметров
//            Statement statement = null;


            PreparedStatement statement = null;
//
//            statement = connection.createStatement();
            statement = connection.prepareStatement("select * from user where username=?");
            statement.setString(1, "user1");
            ResultSet result1 = statement.executeQuery();
//            //Выполним запрос
//            ResultSet result1 = statement.executeQuery(
//                    "SELECT * FROM user");
            //result это указатель на первую строку с выборки
            //чтобы вывести данные мы будем использовать
            //метод next() , с помощью которого переходим к следующему элементу
            System.out.println("Выводим statement");
            while (result1.next()) {
                System.out.println("Номер в выборке #" + result1.getRow()
                        + "\t Логин #" + result1.getString("username")
                        + "\t Password" + result1.getString("password"));
            }
            // Вставить запись
            /*statement.executeUpdate(
                    "INSERT INTO users(username) values('name')");
            //Обновить запись
            statement.executeUpdate(
                    "UPDATE users SET username = 'admin' where id = 1");



            //2.PreparedStatement: предварительно компилирует запросы,
            //которые могут содержать входные параметры
            PreparedStatement preparedStatement = null;
            // ? - место вставки нашего значеня
            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM users where id > ? and id < ?");
            //Устанавливаем в нужную позицию значения определённого типа
            preparedStatement.setInt(1, 2);
            preparedStatement.setInt(2, 10);
            //выполняем запрос
            ResultSet result2 = preparedStatement.executeQuery();

            System.out.println("Выводим PreparedStatement");
            while (result2.next()) {
                System.out.println("Номер в выборке #" + result2.getRow()
                        + "\t Номер в базе #" + result2.getInt("id")
                        + "\t" + result2.getString("username"));
            }

            preparedStatement = connection.prepareStatement(
                    "INSERT INTO users(username) values(?)");
            preparedStatement.setString(1, "user_name");
            //метод принимает значение без параметров
            //темже способом можно сделать и UPDATE
            preparedStatement.executeUpdate();



            //3.CallableStatement: используется для вызова хранимых функций,
            // которые могут содержать входные и выходные параметры
            CallableStatement callableStatement = null;
            //Вызываем функцию myFunc (хранится в БД)
            callableStatement = connection.prepareCall(
                    " { call myfunc(?,?) } ");
            //Задаём входные параметры
            callableStatement.setString(1, "Dima");
            callableStatement.setString(2, "Alex");
            //Выполняем запрос
            ResultSet result3 = callableStatement.executeQuery();
            //Если CallableStatement возвращает несколько объектов ResultSet,
            //то нужно выводить данные в цикле с помощью метода next
            //у меня функция возвращает один объект
            result3.next();
            System.out.println(result3.getString("MESSAGE"));
            //если функция вставляет или обновляет, то используется метод executeUpdate()*/

        } catch (Exception ex) {
            //выводим наиболее значимые сообщения
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    void executeQueryWithParam(String query, String param) {

    }

    void executeQuery(String query, String param) {

    }
}

