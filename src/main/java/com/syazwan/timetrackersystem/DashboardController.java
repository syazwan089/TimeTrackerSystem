package com.syazwan.timetrackersystem;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.text.SimpleDateFormat;
import java.util.Date;


public class DashboardController {
    @FXML
    private Label clockTxt;
    public void initialize() {
        // Update the time label every second
        Thread clockThread = new Thread(this::runClock);
        clockThread.setDaemon(true);
        clockThread.start();
    }

    private void runClock() {
        while (true) {
            try {
                Thread.sleep(1000);
                updateClock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateClock() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String currentTime = dateFormat.format(new Date());
        Platform.runLater(() -> clockTxt.setText(currentTime));
    }
    @FXML
    public void onStartJobBtn(ActionEvent actionEvent) {
    }
}
