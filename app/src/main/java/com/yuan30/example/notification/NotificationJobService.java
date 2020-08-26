package com.yuan30.example.notification;


import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;


public class NotificationJobService extends JobService {

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.d(Attribute.TAG, "onStartJob: ");
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.d(Attribute.TAG, "onStopJob: ");
        return true;
    }
}
