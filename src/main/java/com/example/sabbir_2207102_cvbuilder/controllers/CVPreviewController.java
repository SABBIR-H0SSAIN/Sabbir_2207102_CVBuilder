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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

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
    @FXML private ImageView profileImageView;
    @FXML private ProgressIndicator progressIndicator;
    @FXML private Button deleteCVButton;

    private CVFormModel cvFormModel;
    private final DBHelper repository = new DBHelper();

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

        if (cvFormModel.getImage() != null) {
            this.profileImageView.setImage(cvFormModel.getImage());
        }

        if (deleteCVButton != null) {
            deleteCVButton.setVisible(cvFormModel.getId() > 0);
        }
    }

    public void onDeleteCVAction(ActionEvent event) {
        if (cvFormModel == null || cvFormModel.getId() <= 0) {
            showAlert("Error", "Something went wrong", Alert.AlertType.ERROR);
            return;
        }

        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Confirm Deletion");
        confirmAlert.setHeaderText("Delete CV");
        confirmAlert.setContentText("Are you sure you want to delete this CV from the database?");

        Optional<ButtonType> result = confirmAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            if (progressIndicator != null) {
                progressIndicator.setVisible(true);
            }
            if (deleteCVButton != null) {
                deleteCVButton.setDisable(true);
            }

            Thread deleteThread = new Thread(() -> {
                try {
                    Thread.sleep(200);
                    boolean deleted = repository.deleteCV(cvFormModel.getId());

                    Platform.runLater(() -> {
                        if (progressIndicator != null) {
                            progressIndicator.setVisible(false);
                        }
                        if (deleteCVButton != null) {
                            deleteCVButton.setDisable(false);
                        }

                        if (deleted) {
                            showAlert("Success", "CV deleted successfully!", Alert.AlertType.INFORMATION);
                            try {
                                Parent createRoot = FXMLLoader.load(getClass().getResource("/com/example/sabbir_2207102_cvbuilder/views/homepage.fxml"));
                                Stage stage = (Stage) deleteCVButton.getScene().getWindow();
                                stage.setScene(new Scene(createRoot));
                                stage.show();
                            } catch (IOException e) {
                                showAlert("Error", "Something went wrong please try again" , Alert.AlertType.ERROR);
                            }
                        } else {
                            showAlert("Error", "Failed to delete CV", Alert.AlertType.ERROR);
                        }
                    });

                } catch (Exception e) {
                    Platform.runLater(() -> {
                        if (progressIndicator != null) {
                            progressIndicator.setVisible(false);
                        }
                        if (deleteCVButton != null) {
                            deleteCVButton.setDisable(false);
                        }
                        showAlert("Error", "Something went wrong" , Alert.AlertType.ERROR);
                    });
                }
            });
            deleteThread.setDaemon(true);
            deleteThread.start();
        }
    }

    public void onEditAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sabbir_2207102_cvbuilder/views/create_cv_form.fxml"));
        Parent root = loader.load();
        CreateCVFormController createCVFormController = loader.getController();
        createCVFormController.setData(this.cvFormModel);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public CVFormModel getCVFormInstance() {
        return cvFormModel;
    }

    public void onbackHome(ActionEvent event) throws IOException {
        Parent createRoot = FXMLLoader.load(getClass().getResource("/com/example/sabbir_2207102_cvbuilder/views/homepage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(createRoot));
        stage.show();
    }

    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }
}