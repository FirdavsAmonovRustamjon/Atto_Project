import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Managing {
    private Integer generalId = 1;

    private Card CardList[] = new Card[1];
    private int carIndex = 0;

    private Terminal TerminalList[] = new Terminal[1];
    private int terminalIndex = 0;

    private Transaction transactionList[] = new Transaction[1];
    private int transactionIndex = 0;

    private Double faire;

    // yo'l xaqqini o'rnatish (14,000)
    public void setFaire(double faire) {
        this.faire = faire;
    }

    // card yaratadi
    public Card addCard(Integer id, double balance) {

        Card card = new Card();
        if (CardList.length == carIndex) {
            Card tempArr[] = new Card[CardList.length + 1];
            for (int i = 0; i < CardList.length; i++) {
                tempArr[i] = CardList[i];
            }
            CardList = tempArr;
        }

        card.setId(id);
        card.setBalance(balance);
        setFaire(1400d);

        CardList[carIndex++] = card;
        return card;
    }

    // get card by id
    public Card getCard(Integer id) {

        for (Card card : CardList) {
            if (card != null && card.getId().equals(id)) {
                return card;
            }
        }
        return null;
    }

    // cartaga pul tashlash uchun ishlatiladi.
    public Card recharge(Integer id, double balance) {

        for (Card card : CardList) {
            if (card != null && card.getId().equals(id)) {
                card.setBalance(card.getBalance() + balance);
            }
        }
        return null;
    }


    public Card[] getCardList() {
        return CardList;
    }

    //
    public Terminal addTerminal(Integer id, String address) {

        Terminal terminal = new Terminal();

        if (TerminalList.length == terminalIndex) {
            Terminal tempArr[] = new Terminal[TerminalList.length + 1];
            for (int i = 0; i < TerminalList.length; i++) {
                tempArr[i] = TerminalList[i];
            }
            TerminalList = tempArr;
        }
        terminal.setId(id);
        terminal.setAdsreess(address);


        TerminalList[terminalIndex++] = terminal;
        return terminal;
    }

    public Terminal getTerminalById(Integer id) {

        for (Terminal terminal : TerminalList) {
            if (terminal != null && terminal.getId().equals(id)) {
                return terminal;
            }
        }

        return null;
    }

    public Terminal[] terminalList() {
        return TerminalList;
    }


    public Transaction makeTransaction(Integer terminalId, Integer cardId) {
        Card card = getCard(cardId);
        Terminal terminal = getTerminalById(terminalId);

        if (card == null || terminal == null) {
            return null;
        }

        if (card.getBalance() < faire) {
            return null;
        }
        LocalDateTime now = LocalDateTime.now();
        for (Transaction transaction : transactionList) {
            if (transaction != null && transaction.getCardId().equals(cardId)
                    && transaction.getTerminalId().equals(terminalId) &&
                    transaction.getCreateDate().isBefore(now.plusMinutes(1))) {
                return null;
            }
        }

        Transaction transaction = new Transaction();

        transaction.setTerminalId(terminalId);
        transaction.setCardId(cardId);
        transaction.setId(generalId++);
        card.setBalance(card.getBalance() - faire);
        transaction.setCard(card);
        transaction.setTerminal(terminal);
        transaction.setCreateDate(LocalDateTime.now());
        transaction.setAmount(faire);
        card.setCompanyCArd(faire);

        if (transactionList.length == transactionIndex) {
            Transaction tempArr[] = new Transaction[transactionList.length + 1];
            for (int i = 0; i < transactionList.length; i++) {
                tempArr[i] = transactionList[i];
            }
            transactionList = tempArr;
        }

        transactionList[transactionIndex++] = transaction;
        terminal.addTransaction(transaction);
        return transaction;

    }

    public Transaction getById(Integer id) {

        for (Transaction transaction : transactionList) {
            if (transaction != null && transaction.getId().equals(id)) {
                return transaction;
            }
        }

        return null;
    }

    //  terminal id bo'yicha barcha  transaction lar

    public Transaction[] transactionListByTerminal(Integer terminalId) {

        Terminal terminal = getTerminalById(terminalId);
        if (terminal == null) {
            return null;
        }

        return terminal.transactionList();
    }

    //  carta id si  bo'yicha barcha  transaction lar
    public Transaction[] transactionListByCard(Integer cardId) {

        Transaction[] tempArr = new Transaction[transactionList.length];
        int index = 0;
        for (Transaction transaction : transactionList) {
            if (transaction != null && transaction.getCardId().equals(cardId)) {
                tempArr[index++] = transaction;
            }
        }

        return tempArr;
    }

    // kun bo'yicha barcha transaction lar (yyyy.MM.dd    keladigan  kun formati)
    public Transaction[] getTransactionsByDate(String data) {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        LocalDate localDate = LocalDate.parse(data, dateTimeFormatter);

        Transaction[] tempArr = new Transaction[transactionList.length];
        int index = 0;
        for (Transaction transaction : transactionList) {
            if (transaction != null && transaction.getCreateDate().toLocalDate().equals(localDate)) {
                tempArr[index++] = transaction;
            }
        }

        return tempArr;
    }

    //  berilgan kunda  transaction lar soni bo'yicha tartiblangan terminallar yo'yxatini return qiling.
    public Terminal[] terminalList_orderedByTransactionsCount_aDay(String day) {


        Terminal[] arr = new Terminal[TerminalList.length];
        int index = 0;

        for (Transaction transaction : transactionList) {
            if (transaction != null && transaction.getCreateDate().toLocalDate().toString().equals(day)) {
                arr[index++] = transaction.getTerminal();
            }
        }

        return arr;
    }

    // umumiy yo'l kira xaqqini olish
    public double getTotalFaire() {
        Card card = new Card();
        return card.getCompanyCArd();
    }

}
