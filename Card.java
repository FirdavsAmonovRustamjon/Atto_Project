import java.time.LocalDateTime;
import java.util.List;

public class Card {

    private Integer id;
    private Double balance;


    private Double companyCArd;

    public Double getCompanyCArd() {
        return companyCArd;
    }

    public void setCompanyCArd(Double companyCArd) {
        this.companyCArd = companyCArd;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    // bugungi transaction lar ro'yxati
    public Transaction[] todayTransactionList() {

        return null;
    }


    // xozirgi balansdagi pul
    public double currentAmount() {
        return 0.0;
    }

    // berilgan kun bo'yicha ishlatilgan pul miqdori yyyy.MM.dd
    public double getTransactionAmountByDay(String date) {


        return 0.0;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", balance=" + balance +
                '}';
    }
}
