package com.example.sabbir_2207102_cvbuilder.controllers;

import com.example.sabbir_2207102_cvbuilder.models.CVFormModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class CVPreviewController {
    @FXML private Text fullName;
    @FXML private Text email;
    @FXML private Text phoneNumber;
    @FXML private Text address;
    @FXML private Text educationalQualifications;
    @FXML private Text skills;
    @FXML private Text workExperiances;
    @FXML private Text projects;
    @FXML private VBox content;


    CVFormModel cvFormModel;
    public void setData(CVFormModel cvFormModel) {
        this.cvFormModel = cvFormModel;
        this.fullName.setText(cvFormModel.getFullName());
        this.email.setText(cvFormModel.getEmail());
        this.phoneNumber.setText(cvFormModel.getPhoneNumber());
        this.address.setText(cvFormModel.getAddress());
        this.educationalQualifications.setText(cvFormModel.getEducationalQualifications());
        this.skills.setText(cvFormModel.getSkills());
        this.workExperiances.setText(cvFormModel.getWorkExperiances());
        this.projects.setText(cvFormModel.getProjects());

    }

    public void onEditAction(ActionEvent event) throws IOException{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sabbir_2207102_cvbuilder/views/create_cv_form.fxml"));
        Parent root = loader.load();
        CreateCVFormController createCVFormController = loader.getController();
        createCVFormController.setData(this.getCVFormInstance());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
    public CVFormModel getCVFormInstance(){
        return cvFormModel;
    }
    public  void onbackHome(ActionEvent event) throws IOException{
        Parent createRoot = FXMLLoader.load(getClass().getResource("/com/example/sabbir_2207102_cvbuilder/views/homepage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(createRoot));
        stage.show();
    }
}