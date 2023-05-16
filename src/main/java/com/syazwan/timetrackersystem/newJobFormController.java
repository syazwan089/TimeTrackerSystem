package com.syazwan.timetrackersystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import javafx.stage.Stage;

public class newJobFormController {
    @FXML
    private TextField jobNameField;
    @FXML
    private TextField intervalJob;
    private DashboardController dashboardController;

    public void setDashboardController(DashboardController controller) {
        dashboardController = controller;
    }

    @FXML
    public void onStartJob(ActionEvent event) {
        String jobName = jobNameField.getText();
        dashboardController.addNewJob(jobName,Integer.parseInt(intervalJob.getText()));
        closeForm();
    }

    @FXML
    public void onCancel(ActionEvent event) {
        closeForm();
    }

    private void closeForm() {
        Stage stage = (Stage) jobNameField.getScene().getWindow();
        stage.close();
    }
}
