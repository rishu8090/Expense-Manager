package com.example.expensemanager.views.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.expensemanager.adapters.TransactionAdapter;
import com.example.expensemanager.models.Transaction;
import com.example.expensemanager.utils.Constants;
import com.example.expensemanager.views.fragments.AddTransactionFrag;
import com.example.expensemanager.R;
import com.example.expensemanager.databinding.ActivityMainBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

     ActivityMainBinding binding;  // View binding allows a developer to incorporate significant interaction in an  application.
                                 // This concept seeks to eliminate the findViewById keyword to become more productive.  for it, you have to add viewBinding = true in build file.

    Calendar calendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
         setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.materialToolbar);   // used to set toolbar
        getSupportActionBar().setTitle("Transactions"); // here we set title of toolbar.

        Constants.setCategories();
        calendar = Calendar.getInstance();  // Calendar.getInstance() method returns a Calendar Object whose calendar fields have been initialized with the current time and date.
        updateDate();

        binding.nextDateBtn.setOnClickListener(v -> {
            calendar.add(Calendar.DATE,1);  // it is used to increase current date with one day.
            updateDate();
        });

        binding.previousDateBtn.setOnClickListener(v -> {
            calendar.add(Calendar.DATE,-1);   // it is used to decrease current date with one day.
            updateDate();
        });

        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AddTransactionFrag().show(getSupportFragmentManager(),null);
            }
        });

        ArrayList<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(Constants.INCOME, "Food", "Cash", "Some Note here!", new Date(), 500, 2));
        transactions.add(new Transaction(Constants.EXPENSE, "Investment", "Bank", "Some Note here!", new Date(), -900, 3));
        transactions.add(new Transaction(Constants.INCOME, "Travel", "Gives", "Some Note here!", new Date(), 350, 4));
        transactions.add(new Transaction(Constants.INCOME, "Salary", "UPI", "Some Note here!", new Date(), 200, 5));

        TransactionAdapter transactionAdapter =  new TransactionAdapter(this, transactions);
        binding.transactionsList.setLayoutManager(new LinearLayoutManager(this));
        binding.transactionsList.setAdapter(transactionAdapter);
    }

    void updateDate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM, YYYY");
        binding.currentDate.setText(dateFormat.format(calendar.getTime()));

        // binding.currentDate.setText(Helper.formatDate(Calendar.getTime()));   you can use this line in place of upper this two lines.
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

}