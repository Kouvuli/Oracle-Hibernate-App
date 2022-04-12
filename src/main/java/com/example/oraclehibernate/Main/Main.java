package com.example.oraclehibernate.Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    Stage window;
    Scene scene1, scene2;

    @Override
    public void start(Stage stage) throws IOException {
        window =stage;
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/login-view.fxml"));
        scene1 = new Scene(loader.load(), 300, 200);
        stage.setTitle("Oracle Hibernate Management");
        stage.setScene(scene1);
        stage.show();

//        FXMLLoader loader2 = new FXMLLoader(Main.class.getResource("/main-view.fxml"));
//        scene2 = new Scene(loader2.load(), 600, 400);
//        stage.setScene(scene2);

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
