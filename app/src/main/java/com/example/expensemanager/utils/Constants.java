package com.example.expensemanager.utils;

import com.example.expensemanager.R;
import com.example.expensemanager.models.Category;

import java.util.ArrayList;

public class Constants {
    public static String INCOME = "INCOME";
    public static String EXPENSE = "EXPENSE";
    public static ArrayList<Category> categories;

    public static void setCategories(){
        categories = new ArrayList<>();
        categories.add(new Category("Salary", R.drawable.ic_salary,R.color.darkerthanlightyellow));
        categories.add(new Category("Food", R.drawable.ic_business,R.color.dodger_blue));
        categories.add(new Category("Investment", R.drawable.ic_investment,R.color._light_green));
        categories.add(new Category("Daily Needs", R.drawable.ic_loan,R.color.dark_gray));
        categories.add(new Category("Travel", R.drawable.ic_rent,R.color.hot_pink));
        categories.add(new Category("Others", R.drawable.ic_other,R.color.indigo));
    }

    public static Category getCategoryDetails(String categoryName){
        for(Category cat : categories){
            if(cat.getCategoryName().equals(categoryName)){
                return cat;
            }
        }
        return null;
    }

    public static int getAccountsColor(String accountName){
        int color = 0;
        switch (accountName){
            case "Bank" :
                return R.color.olive;
            case "Cash" :
                return R.color.colorAccent;
            case "UPI" :
                return  R.color.maroon;
            case "Gives" :
                return R.color.goldenrod;
            default:
             return  R.color.blue_violet;
        }
    }
}
