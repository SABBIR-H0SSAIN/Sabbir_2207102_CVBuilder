module com.example.sabbir_2207102_cvbuilder {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires javafx.graphics;

    opens com.example.sabbir_2207102_cvbuilder to javafx.fxml;
    exports com.example.sabbir_2207102_cvbuilder;

    opens com.example.sabbir_2207102_cvbuilder.controllers to javafx.fxml;
    exports com.example.sabbir_2207102_cvbuilder.controllers;
}