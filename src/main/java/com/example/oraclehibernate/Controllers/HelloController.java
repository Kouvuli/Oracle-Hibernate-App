package com.example.oraclehibernate.Controllers;

import com.example.oraclehibernate.Models.Student;
import com.example.oraclehibernate.Utils.HibernateUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
//        System.out.println("Hello world");

        Session session = HibernateUtils.getFACTORY().openSession();


        Transaction transaction = session.beginTransaction();
        Student student = new Student();
        student.setEmail("phanthanh@gmail.com");
        student.setName("Thanh");
        session.save(student);
        transaction.commit();
        session.close();
        welcomeText.setText("Welcome to JavaFX Application!");


    }
}