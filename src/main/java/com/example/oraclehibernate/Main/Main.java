package com.example.oraclehibernate.Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 300, 200);
        stage.setTitle("Oracle Hibernate");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
//    public static void main(String[] args) {
//
//        Session session = HibernateUtils.getFACTORY().openSession();
//
//
//        Transaction transaction = session.beginTransaction();
//        Student student = new Student();
//        student.setEmail("phanthanh@gmail.com");
//        student.setName("Thanh");
//        session.save(student);
//        transaction.commit();
//        session.close();
//
//    }
}
