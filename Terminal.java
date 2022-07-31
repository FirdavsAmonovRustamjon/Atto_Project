import java.util.List;

public class Terminal {
    private Transaction[] transactionList = new Transaction[10];
    private int index = 0;
    private Integer id;
    private String adsreess;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdsreess() {
        return adsreess;
    }

    public void setAdsreess(String adsreess) {
        this.adsreess = adsreess;
    }

    // yyyy.MM.dd
    public double getAmountByDay(String date) {
        return 0.0;
    }

    public Transaction[] transactionList() {
        return transactionList;
    }

    public void addTransaction(Transaction transaction) {
        transactionList[index++] = transaction;
    }

    @Override
    public String toString() {
        return "Terminal{" +
                "id=" + id +
                ", adsreess='" + adsreess + '\'' +
                '}';
    }
}
