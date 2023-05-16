package com.syazwan.timetrackersystem.model;

import java.security.PublicKey;
import java.util.Date;

public class JobTrack {
    private String jobName;
    private Date startDate;
    private Date endDate;
    private double totalHour;

    public JobTrack(String _jobName,Date _startDate){
        this.jobName = _jobName;
        this.startDate = _startDate;
    }


    public String getJobName()
    {
        return  jobName;
    }

    public  double gettotalHour()
    {
        return totalHour;
    }

    public  Date getstartDate()
    {
        return startDate;
    }

    public  Date getendDate()
    {
        return endDate;
    }
}
