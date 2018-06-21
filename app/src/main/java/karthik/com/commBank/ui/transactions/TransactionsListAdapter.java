package karthik.com.commBank.ui.transactions;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Date;
import java.util.List;

import karthik.com.commBank.R;
import karthik.com.commBank.model.accounts.Account;
import karthik.com.commBank.model.accounts.ListItemAccount;
import karthik.com.commBank.model.transactions.Transaction;
import karthik.com.commBank.ui.accounts.AccountDetailsViewHolder;
import karthik.com.commBank.utils.StringUtils;
import karthik.com.commBank.utils.ViewUtils;

public class TransactionsListAdapter extends RecyclerView.Adapter {

    private final static int TYPE_ACCOUNT_DETAILS = 0;
    private final static int TYPE_TRANSACTION_DATE = 1;
    private final static int TYPE_TRANSACTION = 2;

    private List<Object> listItems;
    private ItemClickListener<Transaction, View> itemClickListener;

    public TransactionsListAdapter(List<Object> listItems, ItemClickListener<Transaction, View> itemClickListener) {
        this.listItems = listItems;
        this.itemClickListener = itemClickListener;
    }

    @Override
    public int getItemViewType(int position) {
        Object listItem = listItems.get(position);
        return listItem instanceof Account ? TYPE_ACCOUNT_DETAILS :
                listItem instanceof Date ? TYPE_TRANSACTION_DATE :
                        listItem instanceof Transaction ? TYPE_TRANSACTION :
                                -1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_ACCOUNT_DETAILS:
                View accountDetailsListItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_account_details, parent, false);
                return new AccountDetailsViewHolder(accountDetailsListItemView);

            case TYPE_TRANSACTION_DATE:
                View transactionGroupListItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_transaction_date, parent, false);
                return new TransactionDateViewHolder(transactionGroupListItemView);

            case TYPE_TRANSACTION:
                View transactionListItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_transaction, parent, false);
                return new TransactionViewHolder(transactionListItemView);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        switch (holder.getItemViewType()) {
            case TYPE_ACCOUNT_DETAILS:
                ListItemAccount listItemAccount = (ListItemAccount) listItems.get(position);
                AccountDetailsViewHolder accountDetailsViewHolder = (AccountDetailsViewHolder) holder;
                accountDetailsViewHolder.setAccountNameTextView(listItemAccount.getAccountName());
                accountDetailsViewHolder.setAccountNumberTextView(listItemAccount.getAccountNumber());
                accountDetailsViewHolder.setAccountBalanceTextView(ViewUtils.dollarise(listItemAccount.getBalance()));
                accountDetailsViewHolder.setAvailableFundTextView(ViewUtils.dollarise(listItemAccount.getAvailable()));
                accountDetailsViewHolder.setProjectedSpendTextView(ViewUtils.dollarise(listItemAccount.getProjectedSpend()));
                break;

            case TYPE_TRANSACTION_DATE:
                Date effectiveDate = (Date) listItems.get(position);
                TransactionDateViewHolder transactionDateViewHolder = (TransactionDateViewHolder) holder;
                transactionDateViewHolder.dateTextView.setText(ViewUtils.formatTransactionGroupDate(effectiveDate));
                transactionDateViewHolder.daysAgoTextView.setText(ViewUtils.getTransactionDaysAgo(effectiveDate, new Date()));
                break;

            case TYPE_TRANSACTION:
                Transaction transaction = (Transaction) listItems.get(position);
                TransactionViewHolder transactionViewHolder = (TransactionViewHolder) holder;
                transactionViewHolder.transactionDescriptionTextView.setText(
                        transaction.isPending() ?
                                ViewUtils.generatePendingText(transaction.getDescription()) :
                                Html.fromHtml(transaction.getDescription())
                );
                transactionViewHolder.transactionAmountTextView.setText(ViewUtils.dollarise(transaction.getAmount()));
                transactionViewHolder.locationIcon.setVisibility(StringUtils.isEmpty(transaction.getAtmId()) ? View.INVISIBLE : View.VISIBLE);
                if (StringUtils.isNotEmpty(transaction.getAtmId())) {
                    transactionViewHolder.itemView.setOnClickListener(view -> itemClickListener.onItemClicked(transaction, transactionViewHolder.itemView));
                }
                break;
        }
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }
}
