package com.example.sabbir_2207102_cvbuilder.controllers;

import com.example.sabbir_2207102_cvbuilder.models.CVFormModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class CreateCVFormController {
    @FXML private TextField fullName;
    @FXML private TextField email;
    @FXML private TextField phoneNumber;
    @FXML private TextField address;
    @FXML private TextArea educationalQualifications;
    @FXML private TextArea skills;
    @FXML private TextArea workExperiances;
    @FXML private TextArea projects;

    public  void backToHomePageAction(ActionEvent event) throws IOException{
        Parent createRoot = FXMLLoader.load(getClass().getResource("/com/example/sabbir_2207102_cvbuilder/views/homepage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(createRoot));
        stage.show();
    }

    public void setData(CVFormModel cvFormModel) {
        this.fullName.setText(cvFormModel.getFullName());
        this.email.setText(cvFormModel.getEmail());
        this.phoneNumber.setText(cvFormModel.getPhoneNumber());
        this.address.setText(cvFormModel.getAddress());
        this.educationalQualifications.setText(cvFormModel.getEducationalQualifications());
        this.skills.setText(cvFormModel.getSkills());
        this.workExperiances.setText(cvFormModel.getWorkExperiances());
        this.projects.setText(cvFormModel.getProjects());

    }

    public void buildCVPageAction(ActionEvent event) throws IOException{
        Alert alert= new Alert(Alert.AlertType.ERROR,"Fillup all the required types first");
        if(fullName.getText().isEmpty()||email.getText().isEmpty()||phoneNumber.getText().isEmpty() || address.getText().isEmpty() || educationalQualifications.getText().isEmpty() || skills.getText().isEmpty() || workExperiances.getText().isEmpty()||projects.getText().isEmpty()){
            alert.show();
            return;
        }
        CVFormModel cvFormData = new CVFormModel();
        cvFormData.setFullName(fullName.getText());
        cvFormData.setEmail(email.getText());
        cvFormData.setPhoneNumber(phoneNumber.getText());
        cvFormData.setAddress(address.getText());
        cvFormData.setEducationalQualifications(educationalQualifications.getText());
        cvFormData.setSkills(skills.getText());
        cvFormData.setWorkExperiances(workExperiances.getText());
        cvFormData.setProjects(projects.getText());

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sabbir_2207102_cvbuilder/views/preview_cv_page.fxml"));
        Parent root = loader.load();
        CVPreviewController cvPreviewController = loader.getController();
        cvPreviewController.setData(cvFormData);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        Alert succes_alert= new Alert(Alert.AlertType.INFORMATION,"CV generated successfully");
        stage.show();
        succes_alert.show();
    }
}