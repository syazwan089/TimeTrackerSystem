package com.syazwan.timetrackersystem;
import com.syazwan.timetrackersystem.model.JobTrack;
import com.syazwan.timetrackersystem.model.ParameterizedButton;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;

import java.io.Console;
import java.text.SimpleDateFormat;
import java.util.Date;



public class DashboardController {
    @FXML
    private Label clockTxt;

    @FXML
    private FlowPane jobList;

    ObservableList<JobTrack> jobTrackList = FXCollections.observableArrayList(new JobTrack("Designing",new Date()));
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
        jobTrackList.add(new JobTrack("Coding",new Date()));
        UpdateJobUi();
    }

    public void UpdateJobUi()
    {
        jobList.getChildren().clear();
        for (int i = 0; i < jobTrackList.size(); i++)
        {
            if(jobTrackList.get(i).gettotalHour() != "0")
            {
                Label label = new Label(jobTrackList.get(i).getJobName() + " : " +jobTrackList.get(i).getstartDate() + " - Duration : " + jobTrackList.get(i).gettotalHour());
                FlowPane stackPane = new FlowPane();
                stackPane.getChildren().add(label);
                jobList.getChildren().add(stackPane);
            }
            else {
                Label label = new Label(jobTrackList.get(i).getJobName() + " : " +jobTrackList.get(i).getstartDate());
                FlowPane stackPane = new FlowPane();
                stackPane.getChildren().add(label);

                Button stopBtn = new Button("Stop");
                int finalI = i;
                stopBtn.setOnAction(actionEvent -> stopJob(finalI));
                stackPane.getChildren().add(stopBtn);
                jobList.getChildren().add(stackPane);
            }

        }
    }


    // Stop Job
    public void stopJob(int jobInd)
    {
        JobTrack jobTrack = jobTrackList.get(jobInd);
        jobTrack.setEndTime(new Date());
        System.out.println(jobTrack.getJobName());
        jobList.getChildren().clear();
        for (int i = 0; i < jobTrackList.size(); i++)
        {
            if(jobTrackList.get(i).gettotalHour() != "0")
            {
                Label label = new Label(jobTrackList.get(i).getJobName() + " : " +jobTrackList.get(i).getstartDate() + " - Duration : " + jobTrackList.get(i).gettotalHour());
                FlowPane stackPane = new FlowPane();
                stackPane.getChildren().add(label);
                jobList.getChildren().add(stackPane);
            }
            else {
                Label label = new Label(jobTrackList.get(i).getJobName() + " : " +jobTrackList.get(i).getstartDate());
                FlowPane stackPane = new FlowPane();
                stackPane.getChildren().add(label);

                Button stopBtn = new Button("Stop");
                int finalI = i;
                stopBtn.setOnAction(actionEvent -> stopJob(finalI));
                stackPane.getChildren().add(stopBtn);
                jobList.getChildren().add(stackPane);
            }

        }
    }

}
