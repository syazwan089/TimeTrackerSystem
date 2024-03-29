package com.syazwan.timetrackersystem;
import com.syazwan.timetrackersystem.model.ImageLoader;
import com.syazwan.timetrackersystem.model.JobTrack;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class DashboardController {
    @FXML
    private Label clockTxt;

    @FXML
    private FlowPane jobList;
    @FXML
    private VBox imgBox;

    private ExecutorService executorService;
    ObservableList<JobTrack> jobTrackList =  FXCollections.observableArrayList();
    public void initialize() {
        // Update the time label every second
        Thread clockThread = new Thread(this::runClock);
        clockThread.setDaemon(true);
        clockThread.start();

       // UpdateJobUi();



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
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("newJobForm.fxml"));
            AnchorPane form = loader.load();

            // Create a new stage for the popup form
            Stage formStage = new Stage();
            formStage.initModality(Modality.APPLICATION_MODAL);
            formStage.setTitle("Start Job");
            formStage.setResizable(false);
            Scene scene = new Scene(form);
            formStage.setScene(scene);

            // Set the controller for the popup form
            newJobFormController formController = loader.getController();
            formController.setDashboardController(this); // Pass a reference to the DashboardController

            // Show the form
            formStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  void addNewJob(String jobN,int intJ)
    {
        jobTrackList.add(new JobTrack(jobN,new Date(), intJ));
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
        getImage();
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


    public void getImage()
    {
        ImageLoader imageLoader = new ImageLoader();
        executorService = Executors.newCachedThreadPool();
        System.out.println("img loaded");
        executorService.submit(() -> {
            List<File> imageFiles = imageLoader.getImageFilesFromFolder();

            List<Image> images = new ArrayList<>();
            for (File imageFile : imageFiles) {
                Image image = new Image(imageFile.toURI().toString());
                images.add(image);
                System.out.println(imageFile.getName());
            }

            for (Image image : images) {
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(200);
                imageView.setPreserveRatio(true);
                imgBox.getChildren().add(imageView);

            }
        });
    }



}
