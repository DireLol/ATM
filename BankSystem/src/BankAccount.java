import java.io.*;
import java.util.*;

public class BankAccount {
    private List<Card> cards;
    private static final String FILENAME = "cards.txt";


    public BankAccount() {
        cards = loadCardsFromFile();
    }

    public Card authenticateCard(String cardNumber, String pin) {
        for (Card card : cards) {
            if (card.getCardNumber().equals(cardNumber) && card.getPin().equals(pin)) {
                return card;
            }
        }
        return null;
    }

    public double getBalance(Card card) {
        return card.getBalance();
    }

    public boolean withdrawFunds(Card card, double amount) {
        double currentBalance = card.getBalance();
        if (amount > 0 && amount <= currentBalance) {
            card.setBalance(currentBalance - amount);
            saveCardsToFile();
            return true;
        }
        return false;
    }

    public boolean depositFunds(Card card, double amount) {
        if (amount > 0 && amount <= 1000000) {
            double currentBalance = card.getBalance();
            card.setBalance(currentBalance + amount);
            saveCardsToFile();
            return true;
        }
        return false;
    }

    private List<Card> loadCardsFromFile() {
        List<Card> loadedCards = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(FILENAME))) {
            while (scanner.hasNext()) {
                String[] cardData = scanner.nextLine().split(" ");
                String cardNumber = cardData[0];
                String pin = cardData[1];
                double balance = Double.parseDouble(cardData[2]);
                loadedCards.add(new Card(cardNumber, pin, balance));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл cards.txt не найден.");
        }
        return loadedCards;
    }
//    private List<Card> loadCardsFromFile() {
//        // Загрузить карты из текстового файла (пока заглушка)
//        List<Card> cards = new ArrayList<>();
//        cards.add(new Card("1234-5678-9012-3456", "1234", 1000));
//        cards.add(new Card("9876-5432-1098-7654", "4321", 5000));
//        return cards;
//    }

    private void saveCardsToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILENAME))) {
            for (Card card : cards) {
                writer.println(card.getCardNumber() + " " + card.getPin() + " " + card.getBalance());
            }
        } catch (IOException e) {
            System.out.println("Ошибка сохранения данных.");
        }
    }
}
