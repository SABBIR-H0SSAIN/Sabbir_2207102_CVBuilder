package com.example.sabbir_2207102_cvbuilder.controllers;

import com.example.sabbir_2207102_cvbuilder.db.DBHelper;
import com.example.sabbir_2207102_cvbuilder.models.CVFormModel;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
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
    @FXML private ImageView profileImageView;
    private String imagePath = null;
    private CVFormModel currentCVModel = null;

    public void backToHomePageAction(ActionEvent event) throws IOException {
        Parent createRoot = FXMLLoader.load(getClass().getResource("/com/example/sabbir_2207102_cvbuilder/views/homepage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(createRoot));
        stage.show();
    }

    public void setData(CVFormModel cvFormModel) {
        this.currentCVModel = cvFormModel;
        this.fullName.setText(cvFormModel.getFullName());
        this.email.setText(cvFormModel.getEmail());
        this.phoneNumber.setText(cvFormModel.getPhoneNumber());
        this.address.setText(cvFormModel.getAddress());
        this.educationalQualifications.setText(cvFormModel.getEducationalQualifications());
        this.skills.setText(cvFormModel.getSkills());
        this.workExperiances.setText(cvFormModel.getWorkExperiances());
        this.projects.setText(cvFormModel.getProjects());
        this.imagePath = cvFormModel.getImagePath();
        this.profileImageView.setImage(cvFormModel.getImage());
    }

    public void onImageUpload(ActionEvent event) throws IOException {
        FileChooser imageChooser = new FileChooser();
        imageChooser.setTitle("Select a image for your cv");
        imageChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Images", "*.jpg", "*.png"));
        File image = imageChooser.showOpenDialog(null);
        if (image != null) {
            profileImageView.setImage(new Image(image.toURI().toString()));
            this.imagePath = image.toURI().toString();
        }
    }

    public void buildCVPageAction(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Fillup all the required fields first");
        if (fullName.getText().isEmpty() || email.getText().isEmpty() || phoneNumber.getText().isEmpty() ||
                address.getText().isEmpty() || educationalQualifications.getText().isEmpty() ||
                skills.getText().isEmpty() || workExperiances.getText().isEmpty() || projects.getText().isEmpty()) {
            alert.show();
            return;
        }

        if (imagePath == null) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Upload a photo to continue");
            a.show();
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
        cvFormData.setImagePath(imagePath);

        if (currentCVModel != null && currentCVModel.getId() > 0) {
            cvFormData.setId(currentCVModel.getId());
        }

        saveCVToDatabase(cvFormData, event);
    }

    private void saveCVToDatabase(CVFormModel cv, ActionEvent event) {
        boolean isUpdate = cv.getId() > 0;

        Thread saveThread = new Thread(() -> {
            try {
                DBHelper repository = new DBHelper();

                Thread.sleep(200);

                int result;
                if (isUpdate) {
                    boolean success = repository.updateCV(cv);
                    result = success ? cv.getId() : -1;
                } else {
                    result = repository.insertCV(cv);
                    if (result > 0) {
                        cv.setId(result);
                    }
                }

                int finalResult = result;
                Platform.runLater(() -> {
                    try {
                        if (finalResult > 0) {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sabbir_2207102_cvbuilder/views/preview_cv_page.fxml"));
                            Parent root = loader.load();
                            CVPreviewController cvPreviewController = loader.getController();
                            cvPreviewController.setData(cv);
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(new Scene(root));

                            Alert successAlert = new Alert(Alert.AlertType.INFORMATION,
                                    isUpdate ? "CV updated successfully!" : "CV saved successfully with ID: " + finalResult);
                            stage.show();
                            successAlert.show();
                        } else {
                            Alert errorAlert = new Alert(Alert.AlertType.ERROR, "Failed to save CV to database");
                            errorAlert.show();
                        }
                    } catch (IOException e) {
                        Alert errorAlert = new Alert(Alert.AlertType.ERROR, "Error loading preview: " + e.getMessage());
                        errorAlert.show();
                    }
                });

            } catch (Exception e) {
                Platform.runLater(() -> {
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR, "Error saving CV: " + e.getMessage());
                    errorAlert.show();
                });
            }
        });
        saveThread.setDaemon(true);
        saveThread.start();
    }
}