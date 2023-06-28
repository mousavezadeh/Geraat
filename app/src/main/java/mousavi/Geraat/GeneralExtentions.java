package mousavi.Geraat;

import android.annotation.SuppressLint;
import android.app.Application;
import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.media.ExifInterface;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.app.NotificationCompat;
import androidx.core.content.FileProvider;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import mousavi.Notification.BaseNotification;
import mousavi.Request.GetOfficerReportResponse;
import mousavi.database.Dto.IDto;
import mousavi.file.operation.file_operation;

public class GeneralExtentions {
    public static class Extention{


        public static boolean FileExist(String filename){
            try {
                    File f = new File(filename);
                    if (f.exists()) return true;
                    return false;
                }catch (Exception Error){
                return false;}
        }

        public static void MakeVibrate(Context context) {
            Vibrator vibrate = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
            long[] Pattern = {0, 100, 100, 300, 100, 500};
            vibrate.vibrate(Pattern, -1);
        }
        /**
         *
         * @return String of datetime now with this format yyyy-MM-dd HH:mm:ss
         */
        public static String GetDateNowWithFormat(){
            Calendar c = Calendar.getInstance(Locale.ENGLISH);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String strDate = sdf.format(c.getTime());
            return strDate;
        }
        /**
         * @return Base64 String of photo
         */
        public static String ImageToBase64(File imagefilepath){
            Bitmap bm = BitmapFactory.decodeFile(imagefilepath.getAbsolutePath());
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] b = baos.toByteArray();
            return Base64.encodeToString(b,Base64.DEFAULT);
        }

        @SuppressLint("MissingPermission")
        public static String GetImei(Context context) {
            String imei = "";
            try {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

                imei = telephonyManager.getDeviceId();

                if (imei==null)
                    return   Settings.Secure.getString(
                            context.getApplicationContext().getContentResolver(),
                            Settings.Secure.ANDROID_ID);
                    return imei;
            } catch (Exception error) {
                return Settings.Secure.getString(context.getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
            }

        }
        public boolean isAbleToConnect(String url, int timeout) {
            try {
                URL myUrl = new URL(url);
                URLConnection connection = myUrl.openConnection();
                connection.setConnectTimeout(timeout);
                connection.connect();
                return true;
            } catch (Exception e) {
                Log.i("exception", "" + e.getMessage());
                return false;
            }
        }
        public static boolean PayUrlConnectionState() {
            try {
                URL myUrl = new URL("https://pay.abfakhz.ir");
                URLConnection connection = myUrl.openConnection();
                connection.setConnectTimeout(1000);
                connection.connect();
                return true;
            } catch (Exception e) {
                Log.i("exception", "" + e.getMessage());
                return false;
            }
        }
        public static   boolean CheckDateTime(String ServerDateTime){
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss a",Locale.ENGLISH);
                Date TempServerdate = dateFormat.parse(ServerDateTime);
                String currentDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss a", Locale.ENGLISH).format(new Date());
                Date date = dateFormat.parse(currentDate);
                long diff = TempServerdate.getTime() - date.getTime();
                long diffSeconds = diff / 1000 % 60;
                long diffMinutes = diff / (60 * 1000) % 60;
                long diffHours = diff / (60 * 60 * 1000) % 24;
                long diffDays = diff / (24 * 60 * 60 * 1000);
                if (diffHours == 0)
                  return true;

                return false;
            }catch (Exception Error){
               return false;
            }

        }
        public static void SendNotification(BaseNotification baseNotification, Context context){
            try
            {
                if (baseNotification.Show().compareTo("Notifications.on") == 0)
                {
                    String CHANNEL_ID = "my_channel_01";
                    CharSequence name = "sdfadsfsa";
                    NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                    int importance = NotificationManager.IMPORTANCE_HIGH;

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, NotificationManager.IMPORTANCE_HIGH);
                        notificationManager.createNotificationChannel(mChannel);
                    }
                    NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context.getApplicationContext(), CHANNEL_ID)
                            .setSmallIcon(R.mipmap.abfa)
                            .setContentTitle(baseNotification.Title())
                            .setContentText(baseNotification.Text())
                            .setVibrate(new long[]{100, 250})
                            .setAutoCancel(false);

                    notificationManager.notify(baseNotification.id(), mBuilder.build());
                }

                return;
            } catch (Exception ex) {
                Log.e("notify", ex.getMessage());
                return;
            }
        }

        private static boolean isNetworkInterfaceAvailable(Context context) {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        }
        public static Boolean checkConnectivity(Context context) {
            if (!isNetworkInterfaceAvailable(context))
                return false;

                return true;
        }
        public static Boolean CheckNewVersion(Context context)
        {
            try
            {
                file_operation file_opp=new file_operation();
                PackageInfo CurrentPackageInfo;
                PackageInfo DownloadPackageInfo;
                CurrentPackageInfo=file_opp.CurrentPackageInfo(context);
                DownloadPackageInfo=file_opp.DownloadPackageInfo(context);
                if (DownloadPackageInfo!=null)
                    if(DownloadPackageInfo.getLongVersionCode()>CurrentPackageInfo.getLongVersionCode())
                    return true;
                return false;
            }catch(Exception error){Log.e("apk",error.getMessage());return false;}
        }
        public static apkinformation CheckNewVersionWithinfo(Context context)
        {
            try
            {
                file_operation file_opp=new file_operation();
                PackageInfo CurrentPackageInfo;
                PackageInfo DownloadPackageInfo;
                CurrentPackageInfo=file_opp.CurrentPackageInfo(context);
                DownloadPackageInfo=file_opp.DownloadPackageInfo(context);

                if (DownloadPackageInfo!=null)
                    if(DownloadPackageInfo.getLongVersionCode()>CurrentPackageInfo.getLongVersionCode())
                        return new apkinformation(DownloadPackageInfo.getLongVersionCode(),GetApkUri(context,DownloadPackageInfo.getLongVersionCode()));
                return null;

            }catch(Exception error){Log.e("apk",error.getMessage());return null;}
        }

        public static boolean isNetworkConnected(Context context) {

            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            if (cm != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    NetworkCapabilities capabilities = cm.getNetworkCapabilities(cm.getActiveNetwork());
                    return capabilities != null && (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR));
                } else {
                    NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                    return activeNetwork != null && activeNetwork.isConnected();
                }
            }

            return false;
        }
        private static Uri GetApkUri(Context context, long versionCode){
        try {


            Uri apkuri = FileProvider.getUriForFile(context,
                    BuildConfig.APPLICATION_ID + ".provider",
                    (new File
                            (Environment.getExternalStorageDirectory() + "/" + "ABFA" + "/Update/AbfaUpdateVersion" + String.valueOf(versionCode) + ".apk"))
            );
            return apkuri;

        }catch (Exception error){
            return null;
        }

    }

        public static LocationInfo GelLocationStatus(Context context){
            LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            Criteria criteria = new Criteria();
            criteria.setAccuracy(Criteria.ACCURACY_COARSE);
            criteria.setCostAllowed(false);
            String provider = locationManager.getBestProvider(criteria, false);
            Location location = locationManager.getLastKnownLocation(provider);
            return new LocationInfo(location!=null?true:false,location.getLatitude(),location.getLongitude());
        }
        static class LocationInfo{
            public double latitude;
            public double longitude;
            protected boolean status;
            public LocationInfo(boolean status,double latitude,double longitude){
                this.status=status;
                this.latitude=latitude;
                this.longitude=longitude;
            }
        }
        public static String ConvertPersianDigitToEnglish(String value){
            char[] number=value.toCharArray();
            for (int i = 0; i < value.length(); i++) {
                int ascii = (int) number[i];
                if ((ascii >= 1776) && (ascii <= 1785)) {
                    switch (ascii) {
                        case 1776:
                            number[i] = '0';
                            break;
                        case 1777:
                            number[i] = '1';
                            break;
                        case 1778:
                            number[i] = '2';
                            break;
                        case 1779:
                            number[i] = '3';
                            break;
                        case 1780:
                            number[i] = '4';
                            break;
                        case 1781:
                            number[i] = '5';
                            break;
                        case 1782:
                            number[i] = '6';
                            break;
                        case 1783:
                            number[i] = '7';
                            break;
                        case 1784:
                            number[i] = '8';
                            break;
                        case 1785:
                            number[i] = '9';
                            break;
                        default:
                            break;
                    }
                }
            }

            return Arrays.toString(number);
        }
        public static String compressImage(Context context,String imageUri) {

            String filePath = getRealPathFromURI(context,imageUri);
            Bitmap scaledBitmap = null;

            BitmapFactory.Options options = new BitmapFactory.Options();

//      by setting this field as true, the actual bitmap pixels are not loaded in the memory. Just the bounds are loaded. If
//      you try the use the bitmap here, you will get null.
            options.inJustDecodeBounds = true;
            Bitmap bmp = BitmapFactory.decodeFile(filePath, options);

            int actualHeight = options.outHeight;
            int actualWidth = options.outWidth;

//      max Height and width values of the compressed image is taken as 816x612

            float maxHeight = 816.0f;
            float maxWidth = 612.0f;
            float imgRatio = actualWidth / actualHeight;
            float maxRatio = maxWidth / maxHeight;

//      width and height values are set maintaining the aspect ratio of the image

            if (actualHeight > maxHeight || actualWidth > maxWidth) {
                if (imgRatio < maxRatio) {
                    imgRatio = maxHeight / actualHeight;
                    actualWidth = (int) (imgRatio * actualWidth);
                    actualHeight = (int) maxHeight;
                } else if (imgRatio > maxRatio) {
                    imgRatio = maxWidth / actualWidth;
                    actualHeight = (int) (imgRatio * actualHeight);
                    actualWidth = (int) maxWidth;
                } else {
                    actualHeight = (int) maxHeight;
                    actualWidth = (int) maxWidth;

                }
            }

//      setting inSampleSize value allows to load a scaled down version of the original image

            options.inSampleSize = calculateInSampleSize(options, actualWidth, actualHeight);

//      inJustDecodeBounds set to false to load the actual bitmap
            options.inJustDecodeBounds = false;

//      this options allow android to claim the bitmap memory if it runs low on memory
            options.inPurgeable = true;
            options.inInputShareable = true;
            options.inTempStorage = new byte[16 * 1024];

            try {
//          load the bitmap from its path
                bmp = BitmapFactory.decodeFile(filePath, options);
            } catch (OutOfMemoryError exception) {
                exception.printStackTrace();

            }
            try {
                scaledBitmap = Bitmap.createBitmap(actualWidth, actualHeight, Bitmap.Config.ARGB_8888);
            } catch (OutOfMemoryError exception) {
                exception.printStackTrace();
            }

            float ratioX = actualWidth / (float) options.outWidth;
            float ratioY = actualHeight / (float) options.outHeight;
            float middleX = actualWidth / 2.0f;
            float middleY = actualHeight / 2.0f;

            Matrix scaleMatrix = new Matrix();
            scaleMatrix.setScale(ratioX, ratioY, middleX, middleY);

            Canvas canvas = new Canvas(scaledBitmap);
            canvas.setMatrix(scaleMatrix);
            canvas.drawBitmap(bmp, middleX - bmp.getWidth() / 2, middleY - bmp.getHeight() / 2, new Paint(Paint.FILTER_BITMAP_FLAG));

//      check the rotation of the image and display it properly
            ExifInterface exif;
            try {
                exif = new ExifInterface(filePath);

                int orientation = exif.getAttributeInt(
                        ExifInterface.TAG_ORIENTATION, 0);
                Log.d("EXIF", "Exif: " + orientation);
                Matrix matrix = new Matrix();
                if (orientation == 6) {
                    matrix.postRotate(90);
                    Log.d("EXIF", "Exif: " + orientation);
                } else if (orientation == 3) {
                    matrix.postRotate(180);
                    Log.d("EXIF", "Exif: " + orientation);
                } else if (orientation == 8) {
                    matrix.postRotate(270);
                    Log.d("EXIF", "Exif: " + orientation);
                }
                scaledBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0,
                        scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix,
                        true);
            } catch (IOException e) {
                e.printStackTrace();
            }

            FileOutputStream out = null;
            String filename = getFilename(filePath);
            try {
                out = new FileOutputStream(filename);

//          write the compressed bitmap at the destination specified by filename.
                scaledBitmap.compress(Bitmap.CompressFormat.PNG, 80, out);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            return filename;

        }
        private static String getFilename(String filepath) {

            File file = new File(filepath);
            if (!file.exists()) {
                file.mkdirs();
            }
            String uriSting = (file.getAbsolutePath());

            return uriSting;

        }
        private static String getRealPathFromURI(Context context,String contentURI) {
            Uri contentUri = Uri.parse(contentURI);
            Cursor cursor = context.getContentResolver().query(contentUri, null, null, null, null);
            if (cursor == null) {
                return contentUri.getPath();
            } else {
                cursor.moveToFirst();
                int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                return cursor.getString(index);
            }
        }
        private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
            final int height = options.outHeight;
            final int width = options.outWidth;
            int inSampleSize = 1;

            if (height > reqHeight || width > reqWidth) {
                final int heightRatio = Math.round((float) height / (float) reqHeight);
                final int widthRatio = Math.round((float) width / (float) reqWidth);
                inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
            }
            final float totalPixels = width * height;
            final float totalReqPixelsCap = reqWidth * reqHeight * 2;
            while (totalPixels / (inSampleSize * inSampleSize) > totalReqPixelsCap) {
                inSampleSize++;
            }

            return inSampleSize;
        }
        static class apkinformation{
           public long versioncode;
           public Uri uri;
           apkinformation(long versioncode,Uri uri) {
               this.uri=uri;
               this.versioncode=versioncode;
           }
        }

        public static Uri GetFileUri(Context context,File file){
            if (file==null)
                return null;

            return FileProvider.getUriForFile(context,
                    context.getPackageName() + ".provider", file);
        }
    }
}
