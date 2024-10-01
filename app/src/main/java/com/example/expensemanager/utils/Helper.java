package com.example.expensemanager.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Helper {
    public  static String formatDate(Date date){    /// always use static keyword if you use this file method in another file .
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM, YYYY");
        return dateFormat.format(date);
    }

}
