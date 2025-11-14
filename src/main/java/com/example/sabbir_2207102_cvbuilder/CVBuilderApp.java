package com.example.sabbir_2207102_cvbuilder;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CVBuilderApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CVBuilderApp.class.getResource("/com/example/sabbir_2207102_cvbuilder/views/homepage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("CV Builder app");
        stage.setScene(scene);
        stage.show();
    }
}
