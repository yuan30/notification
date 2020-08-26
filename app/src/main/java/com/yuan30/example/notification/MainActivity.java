package com.yuan30.example.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlarmManager;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    public static final int NOTIFICATION_ID = 888;

    private static final int JOB_TEST = 11;
    private static final String TAG = MainActivity.class.getSimpleName();

    // ConstraintLayout required for SnackBars to alert users when Notifications are disabled for app.
    private ConstraintLayout mMainConstraintLayout;
    private NotificationManagerCompat mNotificationManagerCompat;
    private Button btmSetting;
    private boolean enable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNotificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
        mMainConstraintLayout = findViewById(R.id.mainConstraintLayout);

        enable = false;

        btmSetting = findViewById(R.id.setting);
        btmSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enable = enable == false ? true : false;
                Log.d(Attribute.TAG, "onClick: set enable " + enable);
                boolean areNotificationsEnabled = mNotificationManagerCompat.areNotificationsEnabled();

                if (!areNotificationsEnabled) {
                    // Because the user took an action to create a notification, we create a prompt to let
                    // the user re-enable notifications for this application again.
                    Snackbar snackbar = Snackbar
                            .make(
                                    mMainConstraintLayout,
                                    "You need to enable notifications for this app",
                                    Snackbar.LENGTH_LONG)
                            .setAction("ENABLE", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    // Links to this app's notification settings
                                    openNotificationSettingsForApp();
                                }
                            });
                    snackbar.show();
                    return;
                }

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

    /**
     * Helper method for the SnackBar action, i.e., if the user has this application's notifications
     * disabled, this opens up the dialog to turn them back on after the user requests a
     * Notification launch.
     *
     * IMPORTANT NOTE: You should not do this action unless the user takes an action to see your
     * Notifications like this sample demonstrates. Spamming users to re-enable your notifications
     * is a bad idea.
     */
    private void openNotificationSettingsForApp() {
        // Links to this app's notification settings.
        Intent intent = new Intent();
        intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
        intent.putExtra("app_package", getPackageName());
        intent.putExtra("app_uid", getApplicationInfo().uid);
        startActivity(intent);
    }
}