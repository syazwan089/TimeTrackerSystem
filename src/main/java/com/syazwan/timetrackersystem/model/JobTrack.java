package com.syazwan.timetrackersystem.model;

import java.security.PublicKey;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.Temporal;
import java.util.Date;

public class JobTrack {
    private String jobName;
    private Date startDate;
    private Date endDate;
    private String totalHour;

    private boolean isStop;

    public JobTrack(String _jobName,Date _startDate){
        this.jobName = _jobName;
        this.startDate = _startDate;
        isStop = false;
    }


    public String getJobName()
    {
        return  jobName;
    }

    public String gettotalHour()
    {
        if(isStop)
        {
            LocalDateTime startDateTime = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            LocalDateTime endDateTime = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

            Duration duration = Duration.between( startDateTime,  endDateTime);
            long totalHours = duration.toHours();
            this.totalHour = Long.toString(totalHours)+" hour ";

            if(totalHours < 1)
            {
                if(duration.toMinutes() < 1)
                {
                    this.totalHour = Long.toString(duration.toSeconds())+" seconds ";
                    return  this.totalHour;
                }
                this.totalHour = Long.toString(duration.toMinutes())+" minutes ";
                return  this.totalHour;
            }

            return totalHour;
        }
        return "0";
    }

    public  String getstartDate()
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String currentTime = dateFormat.format(startDate);
        return currentTime;
    }

    public  Date getendDate()
    {
        return endDate;
    }

    public void setEndTime(Date _date)
    {
        this.endDate = _date;
        isStop = true;
    }
}
