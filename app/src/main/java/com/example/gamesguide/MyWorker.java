package com.example.gamesguide;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class MyWorker extends Worker {
    public MyWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        for(int i = 1;i <=10;i++){
            try {
                Thread.sleep(1000);
                displayNotification("GamesGuide", i + " Champion Selected!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return Result.success();
    }

    public void displayNotification(String task, String desc) {
        NotificationManager manager = (NotificationManager)
                getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationChannel channel = new NotificationChannel("pesan", "test123",
                NotificationManager.IMPORTANCE_HIGH);
        manager.createNotificationChannel(channel);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(),
                "pesan")
                .setContentTitle(task)
                .setContentText(desc)
                .setSmallIcon(R.drawable.ic_baseline_person_24)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE);
        manager.notify(1, builder.build());
    }
}
