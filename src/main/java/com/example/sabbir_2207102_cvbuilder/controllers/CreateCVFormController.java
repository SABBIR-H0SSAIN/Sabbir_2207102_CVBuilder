package com.example.sabbir_2207102_cvbuilder.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CreateCVFormController {
    public  void backToHomePageAction(ActionEvent event) throws IOException{
        Parent createRoot = FXMLLoader.load(getClass().getResource("/com/example/sabbir_2207102_cvbuilder/views/homepage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(createRoot));
        stage.show();
    }
}