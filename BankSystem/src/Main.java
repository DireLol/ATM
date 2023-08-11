import java.util.Scanner;
public class Main {

    public static void main(String[] args){
                BankAccount bankAccount = new BankAccount();
                Scanner scanner = new Scanner(System.in);

                System.out.println("Добро пожаловать в банкомат!");

                // Меню для работы с банкоматом
                while (true) {
                    System.out.print("Введите номер карты: ");
                    String cardNumber = scanner.nextLine().trim();
                    System.out.print("Введите ПИН-код: ");
                    String pin = scanner.nextLine().trim();

                    Card authenticatedCard = bankAccount.authenticateCard(cardNumber, pin);
                    if (authenticatedCard != null) {
                        System.out.println("Авторизация прошла успешно.");
                        while (true) {
                            System.out.println("1. Проверить баланс");
                            System.out.println("2. Снять средства");
                            System.out.println("3. Пополнить баланс");
                            System.out.println("0. Выйти");
                            System.out.print("Выберите действие: ");
                            int choice = Integer.parseInt(scanner.nextLine());

                            if (choice == 1) {
                                double balance = bankAccount.getBalance(authenticatedCard);
                                System.out.println("Баланс карты: " + balance);
                            } else if (choice == 2) {
                                System.out.print("Введите сумму для снятия: ");
                                double amount = Double.parseDouble(scanner.nextLine());
                                boolean success = bankAccount.withdrawFunds(authenticatedCard, amount);
                                if (success) {
                                    System.out.println("Снятие средств прошло успешно.");
                                } else {
                                    System.out.println("Ошибка при снятии средств.");
                                }
                            } else if (choice == 3) {
                                System.out.print("Введите сумму для пополнения: ");
                                double amount = Double.parseDouble(scanner.nextLine());
                                boolean success = bankAccount.depositFunds(authenticatedCard, amount);
                                if (success) {
                                    System.out.println("Пополнение баланса прошло успешно.");
                                } else {
                                    System.out.println("Ошибка при пополнении баланса.");
                                }
                            } else if (choice == 0) {
                                break;
                            } else {
                                System.out.println("Неверный выбор.");
                            }
                        }
                    } else {
                        System.out.println("Ошибка авторизации. Проверьте номер карты и ПИН-код.");
                    }
                }
            }
        }
