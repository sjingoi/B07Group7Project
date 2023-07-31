package com.example.b07group7project.database;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ImageDownloader {

    public static void setImageResource(ImageView imageView, String imageURL) {

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Bitmap> future = executorService.submit(new URLDownload(imageURL));
        try {
            Bitmap bitmap = future.get();

            if (bitmap != null) {

                imageView.setImageBitmap(bitmap);
            } else {
                Toast.makeText(imageView.getContext(), "Image null.", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(imageView.getContext(), "Error in setting image.", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        executorService.shutdown();
    }

    private static class URLDownload implements Callable<Bitmap> {

        private final String imageUrl;

        public URLDownload(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        @Override
        public Bitmap call() {
            try {
                URL url = new URL(imageUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                return BitmapFactory.decodeStream(input);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

}
