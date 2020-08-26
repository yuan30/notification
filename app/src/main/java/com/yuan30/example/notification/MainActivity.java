package com.yuan30.example.notification;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    private static final int JOB_TEST = 11;
    private static final String TAG = MainActivity.class.getSimpleName();

    private Button btmSetting;
    private boolean enable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enable = false;

        btmSetting = findViewById(R.id.setting);
        btmSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enable = enable == false ? true : false;
                Log.d(Attribute.TAG, "onClick: set enable " + enable);
                schedulJob(enable);
            }
        });
    }

    public void schedulJob(boolean enable) {
        JobScheduler jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);

        if (enable) {
            Log.d(Attribute.TAG, "schedulJob: " + enable);
            JobInfo jobInfo = new JobInfo.Builder(
                    JOB_TEST
                    , new ComponentName(getPackageName()
                    , NotificationJobService.class.getName()))
                    .setPersisted(true)
                    .setPeriodic(AlarmManager.INTERVAL_FIFTEEN_MINUTES)
                    .build();
            jobScheduler.schedule(jobInfo);
        } else {
            Log.d(Attribute.TAG, "schedulJob: " + enable);
            jobScheduler.cancel(JOB_TEST);
        }
    }
}