package com.example.sabbir_2207102_cvbuilder.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class HomePageController{
    private Button goToCvFormPageButton;
    public void goToCVFormPageAction(ActionEvent event) throws IOException {
        try {
            Parent createRoot = FXMLLoader.load(getClass().getResource("/com/example/sabbir_2207102_cvbuilder/views/create_cv_form.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(createRoot));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("FXML not found! Check the path carefully.");
            e.printStackTrace();
        }
    }
}
