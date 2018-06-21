package karthik.com.commBank.model.transactions;

public class PendingTransaction extends Transaction {

    @Override
    public boolean isPending() {
        return true;
    }
}
