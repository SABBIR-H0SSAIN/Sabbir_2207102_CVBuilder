package com.example.sabbir_2207102_cvbuilder.controllers;

import com.example.sabbir_2207102_cvbuilder.db.DBHelper;
import com.example.sabbir_2207102_cvbuilder.models.CVFormModel;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class HomePageController {
    @FXML private Button goToCvFormPageButton;
    @FXML private Button refreshCVsButton;
    @FXML private Button editCVButton;
    @FXML private Button deleteCVButton;
    @FXML private Button previewCVButton;
    @FXML private ListView<CVFormModel> cvListView;
    @FXML private ProgressIndicator progressIndicator;

    private final DBHelper repository = new DBHelper();

    @FXML
    public void initialize() {
        if (cvListView != null) {
            cvListView.setCellFactory(param -> new ListCell<CVFormModel>() {
                @Override
                protected void updateItem(CVFormModel cv, boolean empty) {
                    super.updateItem(cv, empty);
                    if (empty || cv == null) {
                        setText(null);
                    } else {
                        setText(String.format("ID: %d | %s | %s",
                                cv.getId(), cv.getFullName(), cv.getEmail()));
                    }
                }
            });
        }

        loadCVsInBackground();
    }

    public void goToCVFormPageAction(ActionEvent event) throws IOException {
        Parent createRoot = FXMLLoader.load(getClass().getResource("/com/example/sabbir_2207102_cvbuilder/views/create_cv_form.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(createRoot));
        stage.show();
    }

    public void onRefreshCVsAction(ActionEvent event) {
        loadCVsInBackground();
    }

    private void loadCVsInBackground() {
        if (progressIndicator != null) {
            progressIndicator.setVisible(true);
        }
        if (refreshCVsButton != null) {
            refreshCVsButton.setDisable(true);
        }

        Thread loadThread = new Thread(() -> {
            try {
                Thread.sleep(200);

                ObservableList<CVFormModel> cvList = repository.findAllCVs();

                Platform.runLater(() -> {
                    if (cvListView != null) {
                        cvListView.setItems(cvList);
                    }

                    if (progressIndicator != null) {
                        progressIndicator.setVisible(false);
                    }
                    if (refreshCVsButton != null) {
                        refreshCVsButton.setDisable(false);
                    }

                    if (cvList.isEmpty()) {
                        showAlert("No CVs Found", "No saved CVs found in database.", Alert.AlertType.INFORMATION);
                    }
                });

            } catch (Exception e) {
                Platform.runLater(() -> {
                    if (progressIndicator != null) {
                        progressIndicator.setVisible(false);
                    }
                    if (refreshCVsButton != null) {
                        refreshCVsButton.setDisable(false);
                    }
                    showAlert("Error", "Failed to load CVs: " + e.getMessage(), Alert.AlertType.ERROR);
                });
            }
        });
        loadThread.setDaemon(true);
        loadThread.start();
    }

    public void onEditCVAction(ActionEvent event) throws IOException {
        CVFormModel selectedCV = cvListView.getSelectionModel().getSelectedItem();

        if (selectedCV == null) {
            showAlert("No Selection", "Please select a CV from the list to edit.", Alert.AlertType.WARNING);
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sabbir_2207102_cvbuilder/views/create_cv_form.fxml"));
        Parent root = loader.load();
        CreateCVFormController controller = loader.getController();
        controller.setData(selectedCV);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void onDeleteCVAction(ActionEvent event) {
        CVFormModel selectedCV = cvListView.getSelectionModel().getSelectedItem();

        if (selectedCV == null) {
            showAlert("No Selection", "Please select a CV from the list to delete.", Alert.AlertType.WARNING);
            return;
        }

        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Confirm Deletion");
        confirmAlert.setHeaderText("Delete CV");
        confirmAlert.setContentText("Are you sure you want to delete this CV: " + selectedCV.getFullName() + "?");

        Optional<ButtonType> result = confirmAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            deleteCVInBackground(selectedCV.getId());
        }
    }

    private void deleteCVInBackground(int cvId) {
        if (progressIndicator != null) {
            progressIndicator.setVisible(true);
        }
        if (deleteCVButton != null) {
            deleteCVButton.setDisable(true);
        }

        Thread deleteThread = new Thread(() -> {
            try {
                Thread.sleep(200);
                boolean success = repository.deleteCV(cvId);

                Platform.runLater(() -> {
                    if (progressIndicator != null) {
                        progressIndicator.setVisible(false);
                    }
                    if (deleteCVButton != null) {
                        deleteCVButton.setDisable(false);
                    }

                    if (success) {
                        showAlert("Success", "CV deleted successfully!", Alert.AlertType.INFORMATION);
                        loadCVsInBackground();
                    } else {
                        showAlert("Error", "Failed to delete CV from database", Alert.AlertType.ERROR);
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
                    showAlert("Error", "Error deleting CV: " + e.getMessage(), Alert.AlertType.ERROR);
                });
            }
        });
        deleteThread.setDaemon(true);
        deleteThread.start();
    }

    public void onPreviewCVAction(ActionEvent event) throws IOException {
        CVFormModel selectedCV = cvListView.getSelectionModel().getSelectedItem();

        if (selectedCV == null) {
            showAlert("No Selection", "Please select a CV from the list to preview.", Alert.AlertType.WARNING);
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sabbir_2207102_cvbuilder/views/preview_cv_page.fxml"));
        Parent root = loader.load();
        CVPreviewController controller = loader.getController();
        controller.setData(selectedCV);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
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
