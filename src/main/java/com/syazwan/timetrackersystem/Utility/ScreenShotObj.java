package com.syazwan.timetrackersystem.Utility;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;
import java.util.Timer;
import java.util.TimerTask;

public class ScreenShotObj {
    private Timer timer;
    private int countdown;
    private String jobName;
    public ScreenShotObj(int intervalInSeconds, String _jobName) {
        this.countdown = intervalInSeconds * 60 * 1000; // Convert to milliseconds
        this.jobName = _jobName;
        System.out.println("hit contructor");
    }

    public void start() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new ScreenshotTask(), 0, countdown);
        System.out.println("timer Start");
    }

    public void stop() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    private class ScreenshotTask extends TimerTask {
        @Override
        public void run() {
            Thread captureThread = new Thread(new CaptureRunnable());
            captureThread.start();
        }
    }

    private class CaptureRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("run");
            try {
                // Get the default toolkit
                Toolkit toolkit = Toolkit.getDefaultToolkit();
                // Get the screen size
                Rectangle screenRect = new Rectangle(toolkit.getScreenSize());
                // Create a robot to capture the screen
                Robot robot = new Robot();
                // Capture the screen
                BufferedImage screenshot = robot.createScreenCapture(screenRect);

                // Get the current timestamp
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
                String timestamp = dateFormat.format(new Date());

                // Specify the folder to store the screenshots
                String folderPath = System.getProperty("user.home") + "/Documents/";
                // Create the folder if it doesn't exist
                File folder = new File(folderPath);
                if (!folder.exists()) {
                    folder.mkdirs();
                }

                // Generate the screenshot file name with the timestamp
                String fileName = "screenshot_" + timestamp + ".png";
                // Create the file object
                File file = new File(folderPath, fileName);

                // Add watermark to the screenshot
                Graphics2D graphics = screenshot.createGraphics();
                graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

                String watermarkText = jobName;
                Font watermarkFont = new Font("Arial", Font.BOLD, 30);
                FontMetrics fontMetrics = graphics.getFontMetrics(watermarkFont);
                int watermarkWidth = fontMetrics.stringWidth(watermarkText);
                int watermarkHeight = fontMetrics.getHeight();
                int watermarkX = screenshot.getWidth() - watermarkWidth - 10; // 10 pixels from right edge
                int watermarkY = screenshot.getHeight() - watermarkHeight - 10; // 10 pixels from bottom edge

                graphics.setColor(Color.WHITE);
                graphics.setFont(watermarkFont);
                graphics.drawString(watermarkText, watermarkX, watermarkY);
                graphics.dispose();

                // Save the screenshot to the file
                ImageIO.write(screenshot, "png", file);


                System.out.println("Screenshot saved: " + file.getAbsolutePath());
            } catch (AWTException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
