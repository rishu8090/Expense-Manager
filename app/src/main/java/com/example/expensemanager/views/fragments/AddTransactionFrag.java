package com.example.expensemanager.views.fragments;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.expensemanager.R;
import com.example.expensemanager.adapters.AccountsAdapter;
import com.example.expensemanager.adapters.CategoryAdapter;
import com.example.expensemanager.databinding.FragmentAddTransactionBinding;
import com.example.expensemanager.databinding.ListDialogBinding;
import com.example.expensemanager.models.Account;
import com.example.expensemanager.models.Category;
import com.example.expensemanager.utils.Constants;
import com.example.expensemanager.utils.Helper;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.Calendar;

public class AddTransactionFrag extends BottomSheetDialogFragment {



    public AddTransactionFrag() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    FragmentAddTransactionBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAddTransactionBinding.inflate(inflater);

        binding.incomeBtn.setOnClickListener(v -> {
            binding.incomeBtn.setBackground(getContext().getDrawable(R.drawable.income_selector));
            binding.expenseBtn.setBackground(getContext().getDrawable(R.drawable.default_selector));
            binding.expenseBtn.setTextColor(getContext().getColor(R.color.light_black));
            binding.incomeBtn.setTextColor(getContext().getColor(R.color.greenish));
        });

        binding.expenseBtn.setOnClickListener(v -> {
            binding.incomeBtn.setBackground(getContext().getDrawable(R.drawable.default_selector));
            binding.expenseBtn.setBackground(getContext().getDrawable(R.drawable.expense_selector));
            binding.incomeBtn.setTextColor(getContext().getColor(R.color.light_black));
            binding.expenseBtn.setTextColor(getContext().getColor(R.color.red));
        });

        binding.date.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(getContext());
            datePickerDialog.setOnDateSetListener((view, year, month, dayOfMonth) -> {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.DAY_OF_MONTH, view.getDayOfMonth());
                calendar.set(Calendar.MONTH, view.getMonth());
                calendar.set(Calendar.YEAR, view.getYear());

               // SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM, yyyy");
                String dateToshow = Helper.formatDate(calendar.getTime());
                binding.date.setText(dateToshow);
            });
            datePickerDialog.show();

        });

        ///  for setting category editText.
        binding.category.setOnClickListener(v -> {
            ListDialogBinding dialogBinding = ListDialogBinding.inflate(inflater);  // it is used to add list in our dialog.
            AlertDialog categoryDialog = new AlertDialog.Builder(getContext()).create();  // here , we create a alertDialog.
            categoryDialog.setView(dialogBinding.getRoot());  // here we bind list on dialog.

            CategoryAdapter categoryAdapter = new CategoryAdapter(getContext(), Constants.categories, category -> {
                binding.category.setText(category.getCategoryName());
                categoryDialog.dismiss();
            });
            dialogBinding.recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
            dialogBinding.recyclerView.setAdapter(categoryAdapter);
            categoryDialog.show();
        });


        // for setting account editText.
        binding.account.setOnClickListener(v -> {
            ListDialogBinding dialogBinding = ListDialogBinding.inflate(inflater);  // it is used to add list in our dialog.
            AlertDialog accountDialog = new AlertDialog.Builder(getContext()).create();  // here , we create a alertDialog.
            accountDialog.setView(dialogBinding.getRoot());  // here we bind list on dialog.

            ArrayList<Account> accounts = new ArrayList<>();
            accounts.add(new Account(0, "Bank"));
            accounts.add(new Account(0, "Cash"));
            accounts.add(new Account(0, "Gives"));
            accounts.add(new Account(0, "UPI"));
            accounts.add(new Account(0, "Others"));

            AccountsAdapter accountsAdapter = new AccountsAdapter(getContext(), accounts, new AccountsAdapter.AccountsClickListener() {
                @Override
                public void onAccoutnSelected(Account account) {
                   binding.account.setText(account.getAccount_name());
                   accountDialog.dismiss();
                }
            });

            dialogBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            dialogBinding.recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));  // draw a horizontal line b/w items of account.
            dialogBinding.recyclerView.setAdapter(accountsAdapter);
            accountDialog.show();
        });

        return binding.getRoot();
    }
}