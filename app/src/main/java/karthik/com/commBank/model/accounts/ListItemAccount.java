package karthik.com.commBank.model.accounts;

public class ListItemAccount extends Account {

    private float projectedSpend = 0;

    public static ListItemAccount build(Account acc) {
        ListItemAccount listItemAccount = new ListItemAccount();
        listItemAccount.setAccountName(acc.getAccountName());
        listItemAccount.setAccountNumber(acc.getAccountNumber());
        listItemAccount.setAvailable(acc.getAvailable());
        listItemAccount.setBalance(acc.getBalance());
        return listItemAccount;
    }

    public float getProjectedSpend() {
        return projectedSpend;
    }

    private void setProjectedSpend(float projectedSpend) {
        this.projectedSpend = projectedSpend;
    }

    public ListItemAccount projectedSpend(float projectedSpend) {
        this.setProjectedSpend(projectedSpend);
        return this;
    }
}
