package com.example.tiktalk.Utils;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Base64;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tiktalk.TikTalk;
import com.example.tiktalk.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AppUtils {

    private static String TAG = AppUtils.class.getSimpleName();
    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    //    public static Picasso ImageLoader(){
//        return Picasso.with(JobsOnTheMapEmployee.getContext());
//    }
    public static void Toast(String Message) {
        Toast toast = Toast.makeText(TikTalk.getContext(), "  "+Message+"  ", Toast.LENGTH_SHORT);
        TextView textView = (((TextView)((LinearLayout)toast.getView()).getChildAt(0)));
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        textView.setTextColor(Color.WHITE);
        toast.getView().setBackgroundColor(TikTalk.getContext().getResources().getColor(R.color.colorPrimary));
        toast.show();
    }

    public static void Toast(String Message, boolean Long) {
        Toast toast = Toast.makeText(TikTalk.getContext(), "  "+Message+"  ", Toast.LENGTH_LONG);
        TextView textView = (((TextView)((LinearLayout)toast.getView()).getChildAt(0)));
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        textView.setTextColor(Color.WHITE);
        toast.getView().setBackgroundColor(TikTalk.getContext().getResources().getColor(R.color.colorPrimary));
        toast.show();
    }

    public static void Toast(int Message) {
        Toast.makeText(TikTalk.getContext(), "  "+ String.valueOf(Message)+"  ", Toast.LENGTH_SHORT).show();
    }

    public static boolean isValidEmail(CharSequence target) {
        if (TextUtils.isEmpty(target) || target.length() < 10) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    public static File getFileFromUri(Context context, Uri uri) throws URISyntaxException {
        if (getPathFromUri(context,uri)!=null)
            return new File(getPathFromUri(context,uri));
        else return null;
    }

    public static String getPathFromUri(Context context, Uri uri) throws URISyntaxException {
        String selection = null;
        String[] selectionArgs = null;
        // Uri is different in versions after KITKAT (Android 4.4), we need to
        if (Build.VERSION.SDK_INT >= 19 && DocumentsContract.isDocumentUri(context.getApplicationContext(), uri)) {
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                return Environment.getExternalStorageDirectory() + "/" + split[1];
            } else if (isDownloadsDocument(uri)) {
                final String id = DocumentsContract.getDocumentId(uri);
                uri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
            } else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];
                if ("image".equals(type)) {
                    uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                selection = "_id=?";
                selectionArgs = new String[]{
                        split[1]
                };
            }
        }
        if ("content".equalsIgnoreCase(uri.getScheme())) {
            String[] projection = {
                    MediaStore.Images.Media.DATA
            };
            Cursor cursor = null;
            try {
                cursor = context.getContentResolver()
                        .query(uri, projection, selection, selectionArgs, null);
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                if (cursor.moveToFirst()) {
                    return cursor.getString(column_index);
                }
            } catch (Exception e) {
            }
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static Date parseDate(String date, String time) {
//        27-4-2018 12 : 00
        final String inputFormat = "dd-MM-yyyy HH:mm";
        SimpleDateFormat inputParser = new SimpleDateFormat(inputFormat, Locale.US);
        try {
            return inputParser.parse(date+" "+time);
        } catch (ParseException e) {
            return new Date(0);
        }
    }

//    public static String getPhoneNumber() {
//        TelephonyManager tMgr = (TelephonyManager) JobsOnTheMapEmployee.getContext().getSystemService(Context.TELEPHONY_SERVICE);
//        String mPhoneNumber = tMgr.getLine1Number();
//        if (mPhoneNumber != null) {
//            Log.i("DeviceInfo ", "Number: " + mPhoneNumber);
//            return mPhoneNumber;
//        } else return "";
//    }

    public static String formatDate(String format, Date date){
//        String dayOfTheWeek = (String) DateFormat.format("EEEE", date); // Thursday
//        String day          = (String) DateFormat.format("dd",   date); // 20
//        String monthString  = (String) DateFormat.format("MMM",  date); // Jun
//        String monthString  = (String) DateFormat.format("MMMM",  date); // August
//        String monthNumber  = (String) DateFormat.format("MM",   date); // 06
//        String year         = (String) DateFormat.format("yyyy", date); // 2013
        return (String) DateFormat.format(format, date);
    }

    public static String getTime() {
        Calendar now = Calendar.getInstance();
//        int date = now.get(Calendar.DATE);
//        int month = now.get(Calendar.MONTH);
        int hour = now.get(Calendar.HOUR_OF_DAY);
        int minute = now.get(Calendar.MINUTE);
        int second = now.get(Calendar.SECOND);
        return hour + ":" + minute + ":" + second;
    }

    public static String getDateString() {
        Calendar now = Calendar.getInstance();
        int Date = now.get(Calendar.DATE);
        int Month = now.get(Calendar.MONTH);
        int Year = now.get(Calendar.YEAR);
        return Year + "-" + Month + "-" + Date;
    }

    public static String printDifference(Date startDate, Date endDate) {
        //milliseconds
        long different = endDate.getTime() - startDate.getTime();

        System.out.println("startDate : " + startDate);
        System.out.println("endDate : " + endDate);
        System.out.println("different : " + different);

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        long elapsedSeconds = different / secondsInMilli;

        System.out.printf(
                "%d days, %d hours, %d minutes, %d seconds%n",
                elapsedDays, elapsedHours, elapsedMinutes, elapsedSeconds);
        return /*elapsedDays + "d" + */elapsedHours + "h" + elapsedMinutes + "m";
    }

    /**
     * Returns the consumer friendly device name
     */
    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        }
        return capitalize(manufacturer) + " " + model;
    }

    private static String capitalize(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        char[] arr = str.toCharArray();
        boolean capitalizeNext = true;

        StringBuilder phrase = new StringBuilder();
        for (char c : arr) {
            if (capitalizeNext && Character.isLetter(c)) {
                phrase.append(Character.toUpperCase(c));
                capitalizeNext = false;
                continue;
            } else if (Character.isWhitespace(c)) {
                capitalizeNext = true;
            }
            phrase.append(c);
        }

        return phrase.toString();
    }

    /**
     * Get IP address from first non-localhost interface
     * @param /ipv4  true=return ipv4, false=return ipv6
     * @return  address or empty string
     */
    public static String getIPAddress(boolean useIPv4) {
        try {
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface intf : interfaces) {
                List<InetAddress> addrs = Collections.list(intf.getInetAddresses());
                for (InetAddress addr : addrs) {
                    if (!addr.isLoopbackAddress()) {
                        String sAddr = addr.getHostAddress();
                        //boolean isIPv4 = InetAddressUtils.isIPv4Address(sAddr);
                        boolean isIPv4 = sAddr.indexOf(':')<0;

                        if (useIPv4) {
                            if (isIPv4)
                                return sAddr;
                        } else {
                            if (!isIPv4) {
                                int delim = sAddr.indexOf('%'); // drop ip6 zone suffix
                                return delim<0 ? sAddr.toUpperCase() : sAddr.substring(0, delim).toUpperCase();
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
        } // for now eat exceptions
        return "";
    }

    public static String replaceWord(String inputString, String searchFor, String replaceWith){
        if (inputString.indexOf(searchFor) > 0) {
            return inputString.replace(searchFor, replaceWith);
        }else return inputString;
    }

    public static String userNameFromEmail(String email){
        int indexOfAtSign = email.indexOf("@");
        return email.substring(0,indexOfAtSign);
    }

    public static String toAmPm(String time /*13:00*/){
        int initIndex = time.indexOf(":");
        int hour = Integer.valueOf(time.substring(0,initIndex));
        int endIndex = initIndex+1;
        String end = time.substring(endIndex,time.length());
        String t;
        if (hour>12) {
            int hr = hour - 12;
            t = hr + ":" + end + " PM";
        }else t = hour+ ":" + end + " AM";
        return t;
    }

    public static double distanceInKM(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1))
                * Math.sin(deg2rad(lat2))
                + Math.cos(deg2rad(lat1))
                * Math.cos(deg2rad(lat2))
                * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        return (dist);
    }

    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private static double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }
}

