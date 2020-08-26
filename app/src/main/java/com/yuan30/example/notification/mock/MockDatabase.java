/*
 * Copyright (C) 2017 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.yuan30.example.notification.mock;

import android.app.NotificationManager;
import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationCompat.MessagingStyle;
import androidx.core.app.Person;
import androidx.core.graphics.drawable.IconCompat;

import com.yuan30.example.notification.R;

import java.util.ArrayList;

/** Mock data for each of the Notification Style Demos. */
public final class MockDatabase {

    public static BigTextStyleReminderAppData getBigTextStyleData() {
        return BigTextStyleReminderAppData.getInstance();
    }

    /** Represents data needed for BigTextStyle Notification. */
    public static class BigTextStyleReminderAppData extends MockNotificationData {

        private static BigTextStyleReminderAppData sInstance = null;

        // Unique data for this Notification.Style:
        private String mBigContentTitle;
        private String mBigText;
        private String mSummaryText;

        public static BigTextStyleReminderAppData getInstance() {
            if (sInstance == null) {
                sInstance = getSync();
            }

            return sInstance;
        }

        private static synchronized BigTextStyleReminderAppData getSync() {
            if (sInstance == null) {
                sInstance = new BigTextStyleReminderAppData();
            }

            return sInstance;
        }

        private BigTextStyleReminderAppData() {

            // Standard Notification values:
            // Title for API <16 (4.0 and below) devices.
            mContentTitle = "Don't forget to...";
            // Content for API <24 (4.0 and below) devices.
            mContentText = "Feed Dogs and check garage!";
            mPriority = NotificationCompat.PRIORITY_DEFAULT;

            // BigText Style Notification values:
            mBigContentTitle = "Don't forget to...";
            mBigText =
                    "... feed the dogs before you leave for work, and check the garage to "
                            + "make sure the door is closed.";
            mSummaryText = "Dogs and Garage";

            // Notification channel values (for devices targeting 26 and above):
            mChannelId = "channel_reminder_1";
            // The user-visible name of the channel.
            mChannelName = "Sample Reminder";
            // The user-visible description of the channel.
            mChannelDescription = "Sample Reminder Notifications";
            mChannelImportance = NotificationManager.IMPORTANCE_HIGH;
            mChannelEnableVibrate = false;
            mChannelLockscreenVisibility = NotificationCompat.VISIBILITY_PUBLIC;
        }

        public String getBigContentTitle() {
            return mBigContentTitle;
        }

        public String getBigText() {
            return mBigText;
        }

        public String getSummaryText() {
            return mSummaryText;
        }

        @Override
        public String toString() {
            return getBigContentTitle() + getBigText();
        }
    }

    /** Represents standard data needed for a Notification. */
    public abstract static class MockNotificationData {

        // Standard notification values:
        protected String mContentTitle;
        protected String mContentText;
        protected int mPriority;

        // Notification channel values (O and above):
        protected String mChannelId;
        protected CharSequence mChannelName;
        protected String mChannelDescription;
        protected int mChannelImportance;
        protected boolean mChannelEnableVibrate;
        protected int mChannelLockscreenVisibility;

        // Notification Standard notification get methods:
        public String getContentTitle() {
            return mContentTitle;
        }

        public String getContentText() {
            return mContentText;
        }

        public int getPriority() {
            return mPriority;
        }

        // Channel values (O and above) get methods:
        public String getChannelId() {
            return mChannelId;
        }

        public CharSequence getChannelName() {
            return mChannelName;
        }

        public String getChannelDescription() {
            return mChannelDescription;
        }

        public int getChannelImportance() {
            return mChannelImportance;
        }

        public boolean isChannelEnableVibrate() {
            return mChannelEnableVibrate;
        }

        public int getChannelLockscreenVisibility() {
            return mChannelLockscreenVisibility;
        }
    }

    public static Uri resourceToUri(Context context, int resId) {
        return Uri.parse(
                ContentResolver.SCHEME_ANDROID_RESOURCE
                        + "://"
                        + context.getResources().getResourcePackageName(resId)
                        + "/"
                        + context.getResources().getResourceTypeName(resId)
                        + "/"
                        + context.getResources().getResourceEntryName(resId));
    }
}
