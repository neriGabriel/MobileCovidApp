package com.example.mobilecovidinfo.util;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static String dateFormat(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.ms'Z'");
        SimpleDateFormat formatedLayout = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String formatedData = null;
        try {
            Date data = format.parse(date);
            formatedData = formatedLayout.format(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatedData;
    }
}
