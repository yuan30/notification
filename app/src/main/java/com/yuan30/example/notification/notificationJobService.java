package com.yuan30.example.notification;

import android.app.job.JobParameters;
import android.app.job.JobService;

public class notificationJobService extends JobService {
    @Override
    public boolean onStartJob(JobParameters params) {
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }
}
