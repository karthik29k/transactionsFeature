package karthik.com.commBank.ui.accounts;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import karthik.com.commBank.R;

public class AccountDetailsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.account_name_text_view)
    TextView accountNameTextView;

    @BindView(R.id.account_number_text_view)
    TextView accountNumberTextView;

    @BindView(R.id.available_funds_text_view)
    TextView availableFundTextView;

    @BindView(R.id.account_balance_text_view)
    TextView accountBalanceTextView;


    @BindView(R.id.projected_spend_text_view)
    TextView projectedSpendTextView;

    public AccountDetailsViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setAccountNameTextView(@NonNull String accountNameText) {
        this.accountNameTextView.setText(accountNameText);
    }

    public void setAccountNumberTextView(@NonNull String accountNumberText) {
        this.accountNumberTextView.setText(accountNumberText);
    }

    public void setAvailableFundTextView(@NonNull String availableFundText) {
        this.availableFundTextView.setText(availableFundText);
    }

    public void setAccountBalanceTextView(@NonNull String accountBalanceText) {
        this.accountBalanceTextView.setText(accountBalanceText);
    }

    public void setProjectedSpendTextView(@NonNull String projectedSpendText) {
        this.projectedSpendTextView.setText(projectedSpendText);
    }

}
