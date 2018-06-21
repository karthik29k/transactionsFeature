package karthik.com.commBank.ui.transactions;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import karthik.com.commBank.R;

class TransactionViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.transition_description_text_view)
    TextView transactionDescriptionTextView;

    @BindView(R.id.transaction_amount_text_view)
    TextView transactionAmountTextView;

    @BindView(R.id.transition_description_location)
    View locationIcon;

    public TransactionViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
