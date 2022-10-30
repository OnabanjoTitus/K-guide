package BankingApplication;


import BankingApplicationExecptions.BankingExceptions;
import BankingApplicationExecptions.TransactionException;

import java.math.BigDecimal;
import java.util.*;

public class BankMainMethod {
    public static void main(String[] args) {
        ArrayList<BankAccount> bankAccount = new ArrayList<>();
        Customer[] customers = new Customer[10];
        Bank bank = new Bank();
        int counter = 0;
        Scanner scanner = new Scanner(System.in);
        int answer = 0;
        String notice = "Welcome To GOLDMAN SACHS\n" +
                "Enter 1 to create open an account\n" +
                "Enter 2 to deposit into an account:an already existing account\n" +
                "Enter 3 Withdraw from an account  disclaimer: this is for users with an existing account\n" +
                "Enter 4 To Transfer from one account to the another disclaimer: this is for users with an existing account\n" +
                "Enter 5 To Exit bank";
        try {
            System.out.println(notice);
            answer = scanner.nextInt();
        }catch (InputMismatchException inputMismatchException){
            System.out.println("Invalid input");
            scanner.nextLine();
        }
        while (answer != 5) {
            switch (answer) {
                case 1 -> {
                    try {
                        System.out.println("Enter firstname");
                        String firstname = scanner.next();
                        System.out.println("Enter lastname");
                        String lastname = scanner.next();
                        System.out.println("Enter otherName");
                        String otherName = scanner.next();
                        System.out.println("Enter address");
                        String address = scanner.next();
                        System.out.println("Enter email");
                        String email = scanner.next();
                        System.out.println("Enter phoneNumber");
                        String phoneNumber = scanner.next();
                        customers[counter] = BankingHall.customerCreation(firstname, lastname, otherName, address, email, phoneNumber);
                        System.out.println("Enter AccountType");
                        String accountType = scanner.next();
                        System.out.println("Enter initialDeposit");
                        int deposit = scanner.nextInt();

                        bankAccount.add(BankingHall.bankAccount(accountType,new BigDecimal( deposit)));
                        boolean message = bank.registerCustomers(customers[counter], bankAccount.get(counter));

                        if (message && bankAccount.get(counter).getAccountId().toString() != null) {
                            System.out.println("Setup your AccountPin: 4 digits only");
                            String pin1 = scanner.next();
                            bank.setCustomerPassword(bankAccount.get(counter).getAccountId().toString(), pin1);
                            while (pin1.length()!=4){
                                System.out.println("Setup your AccountPin: 4 digits only");
                                 pin1 = scanner.next();
                                 bank.setCustomerPassword(bankAccount.get(counter).getAccountId().toString(), pin1);
                            }
                            System.out.println(bank.getCustomerAccountAndDetails(accountType, bankAccount.get(counter).getAccountId().toString()));
                            counter++;
                        }
                        if (!message) {
                            System.out.println("Customer already exists");
                        }


                    }catch (InputMismatchException inputMismatchException){
                        System.err.println("Invalid entry try again");
                        scanner.nextLine();
                    }
                    catch (NullPointerException nullPointerException){
                        System.err.println("Sorry customer we don't operate  that type of account try savings or current");
                    } catch (BankingExceptions bankingExceptions) {
                        bankingExceptions.printStackTrace();
                    }

                    System.out.println(notice);
                    answer = scanner.nextInt();
                }


                case 2 -> {

                   try{ System.out.println("Enter the accountId");
                    String accountId = scanner.next();
                    System.out.println("Enter the amount to deposit");
                    int amount = scanner.nextInt();
                    bank.depositMoney(accountId, new BigDecimal(amount));
                    System.out.println("Enter account type to get transaction confirmation");
                    String accountType = scanner.next();
                    bank.getCustomerAccountAndDetails(accountType, accountId);
                    }
                   catch (InputMismatchException inputMismatchException){
                       System.err.println("Invalid input");
                       scanner.nextLine();
                   }catch (TransactionException transactionException){
                       System.err.println(transactionException.getMessage());
                   }catch (BankingExceptions bankingExceptions){
                       System.err.println(bankingExceptions.getMessage());
                   }
                    System.out.println(notice);
                    answer = scanner.nextInt();
                }



                case 3 -> {
                       try{ System.out.println("Enter your account Id");
                        String accountId = scanner.next();
                        System.out.println("Enter the amount to withdraw");
                        int amount = scanner.nextInt();
                        System.out.println("Enter your accountPin");
                        String password = scanner.next();
                        bank.withdrawal(accountId, new BigDecimal(amount), password);
                        System.out.println("account type");
                        String accountType = scanner.next();
                        System.out.println(bank.getCustomerAccountAndDetails(accountType, accountId));}
                        catch (InputMismatchException inputMismatchException){
                               System.err.println("Invalid input");
                               scanner.nextLine();
                           }catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException){
                           System.err.println("That account has not been created yet");
                       }catch (TransactionException transactionException){
                           System.err.println(transactionException.getMessage());
                       }catch (BankingExceptions bankingExceptions){
                           System.err.println(bankingExceptions.getMessage());
                       }
                    System.out.println(notice);
                           answer = scanner.nextInt();

                    }
                case 4->{
                    try{System.out.println("Enter your bank accountId");
                    String id1=scanner.next();
                    System.out.println("Enter the receiver accountId");
                    String id2=scanner.next();
                    System.out.println("enter amount you want to transfer");
                    int amount=scanner.nextInt();
                    System.out.println("Enter your account password");
                    String password=scanner.next();
                    bank.transfer(id1,id2,new BigDecimal(amount),password);
                    System.out.println("enter account type");
                    String accountType = scanner.next();
                    System.out.println(bank.getCustomerAccountAndDetails(accountType, id1));
                    System.out.println("enter the second account type");
                    accountType = scanner.next();
                    System.out.println(bank.getCustomerAccountAndDetails(accountType, id2));}
                    catch (InputMismatchException inputMismatchException){
                        System.err.println("Invalid input");
                        scanner.nextLine();
                    }catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException){
                        System.err.println("That account has not been created yet");
                    }catch (TransactionException transactionException){
                        System.err.println(transactionException.getMessage());
                    }catch (BankingExceptions bankingExceptions){
                        System.err.println(bankingExceptions.getMessage());
                    }
                    System.out.println(notice);
                    answer = scanner.nextInt();

                }
                default -> {
                    try {
                        System.out.println(notice);
                        answer = scanner.nextInt();
                    }catch (InputMismatchException inputMismatchException){
                        System.out.println("Invalid input");
                        scanner.nextLine();
                    }
                }

                }
            }
        System.out.println("Exiting!!!");
        }
    }




