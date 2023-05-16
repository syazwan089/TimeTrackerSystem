package com.syazwan.timetrackersystem;
import com.syazwan.timetrackersystem.model.JobTrack;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import java.text.SimpleDateFormat;
import java.util.Date;



public class DashboardController {
    @FXML
    private Label clockTxt;

    @FXML
    private FlowPane jobList;
    Date date = new Date();
    ObservableList<JobTrack> jobTrackList = FXCollections.observableArrayList(new JobTrack("Designing",date));
    public void initialize() {
        // Update the time label every second
        Thread clockThread = new Thread(this::runClock);
        clockThread.setDaemon(true);
        clockThread.start();

        UpdateJobUi();



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
        jobTrackList.add(new JobTrack("Coding",date));
        UpdateJobUi();
    }

    public void UpdateJobUi()
    {
        jobList.getChildren().clear();
        for (int i = 0; i < jobTrackList.size(); i++)
        {
            Label label = new Label(jobTrackList.get(i).getJobName() + " : " +jobTrackList.get(i).getstartDate());
            jobList.getChildren().add(label);
        }
    }


    // Set job cell

}
