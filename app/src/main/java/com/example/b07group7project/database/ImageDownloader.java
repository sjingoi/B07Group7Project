package com.example.b07group7project.database;

import android.widget.ImageView;
import com.squareup.picasso.Picasso;

public class ImageDownloader {

//    private static HashMap<String, Bitmap> imageCache;

    public static void setImageResource(ImageView imageView, String imageURL) {
        setImageResource(imageView, imageURL, 256, 256);

//        if (imageCache != null && imageCache.containsKey(imageURL)) {
//            imageView.setImageBitmap(imageCache.get(imageURL));
//        }
//
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        Future<Bitmap> future = executorService.submit(new URLDownload(imageURL));
//        try {
//            Bitmap bitmap = future.get();
//
//            if (bitmap != null) {
//                imageView.setImageBitmap(bitmap);
//                if (imageCache == null) {
//                    imageCache = new HashMap<>();
//                }
//                imageCache.put(imageURL, bitmap);
//            } else {
//                Toast.makeText(imageView.getContext(), "Image null.", Toast.LENGTH_SHORT).show();
//            }
//        } catch (Exception e) {
//            Toast.makeText(imageView.getContext(), "Error in setting image.", Toast.LENGTH_SHORT).show();
//            e.printStackTrace();
//        }
//
//        executorService.shutdown();
    }

    public static void setImageResource(ImageView imageView, String imageURL, int resWidth, int resHeight) {
        Picasso.get().load(imageURL).resize(resWidth, resHeight).centerCrop().into(imageView);
    }

//    private static class URLDownload implements Callable<Bitmap> {
//
//        private final String imageUrl;
//
//        public URLDownload(String imageUrl) {
//            this.imageUrl = imageUrl;
//        }
//
//        @Override
//        public Bitmap call() {
//            try {
//                URL url = new URL(imageUrl);
//                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//                connection.setDoInput(true);
//                connection.connect();
//                InputStream input = connection.getInputStream();
//                return BitmapFactory.decodeStream(input);
//            } catch (Exception e) {
//                e.printStackTrace();
//                return null;
//            }
//        }
//    }

}
