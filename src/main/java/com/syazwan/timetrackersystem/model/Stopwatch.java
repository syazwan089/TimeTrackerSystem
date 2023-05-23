package com.syazwan.timetrackersystem.model;

public class Stopwatch {
    private boolean running;
    private long startTime;
    private String jobName;

    public Stopwatch(String _jobName) {
        running = false;
        startTime = 0;
        this.jobName = _jobName;
    }

    public void start() {
        if (!running) {
            running = true;
            startTime = System.currentTimeMillis();
        }
    }

    public void stop() {
        if (running) {
            running = false;
        }
    }

    public String getElapsedTime() {
        if (running) {
            long currentTime = System.currentTimeMillis();
            long elapsedTime = currentTime - startTime;
            return formatElapsedTime(elapsedTime);
        } else {
            return "00:00:00";
        }
    }

    private String formatElapsedTime(long elapsedTime) {
        long seconds = elapsedTime / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;

        return String.format("%02d:%02d:%02d", hours, minutes % 60, seconds % 60);
    }
}
