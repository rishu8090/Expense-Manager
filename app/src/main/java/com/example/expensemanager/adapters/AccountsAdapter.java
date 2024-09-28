package com.example.expensemanager.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expensemanager.R;
import com.example.expensemanager.databinding.RowAccountsBinding;
import com.example.expensemanager.models.Account;

import java.util.ArrayList;

public class AccountsAdapter extends  RecyclerView.Adapter<AccountsAdapter.AccountViewHolder> {
    Context context;
    ArrayList<Account> accountArrayList;

    AccountsClickListener accountsClickListener;

    public interface AccountsClickListener{
         void onAccoutnSelected(Account account);
    }

    public AccountsAdapter(Context context, ArrayList<Account> accountArrayList, AccountsClickListener accountsClickListener){
        this.context = context;
        this.accountArrayList = accountArrayList;
        this.accountsClickListener = accountsClickListener;
    }

    @NonNull
    @Override
    public AccountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AccountViewHolder(LayoutInflater.from(context).inflate(R.layout.row_accounts,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull AccountViewHolder holder, int position) {
            Account account = accountArrayList.get(position);
            holder.binding.accountName.setText(account.getAccount_name());

            holder.itemView.setOnClickListener(v -> {
                accountsClickListener.onAccoutnSelected(account);
            });
    }

    @Override
    public int getItemCount() {
        return accountArrayList.size() ;
    }

    public class AccountViewHolder extends RecyclerView.ViewHolder{

        RowAccountsBinding binding;
         public AccountViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = RowAccountsBinding.bind(itemView);
        }
    }
}
