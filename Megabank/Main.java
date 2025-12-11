import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void clearScreenPlatformSpecific() {
    try {
        String os = System.getProperty("os.name");

        if (os.contains("Windows")) {
            // For Windows: executes the "cls" command
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } else {
            // For Unix-based systems (Linux, macOS): executes the "clear" command
            new ProcessBuilder("clear").inheritIO().start().waitFor();
        }
    } catch (final Exception e) {
        // Handle exceptions (e.g., if the command fails to run)
        System.out.println("Could not clear screen: " + e.getMessage());
    }
}
    public static void printIco() {System.out.println("                                                                \n" + //
                            "                                      ████                      \n" + //
                            "                                  ████▒▒▒▒████                  \n" + //
                            "                              ████▒▒▒▒▒▒▒▒▒▒▒▒████              \n" + //
                            "                          ████▒▒▒▒░░░░░░░░░░░░▒▒▒▒████          \n" + //
                            "                      ████▒▒▒▒░░░░░░░░░░▒▒▒▒░░░░░░▒▒▒▒████      \n" + //
                            "                  ████░░░░░░▒▒▒▒▒▒▒▒▒▒░░░░░░░░░░░░▒▒▒▒▒▒▒▒████  \n" + //
                            "              ████▒▒▒▒▒▒▒▒░░░░░░▒▒▒▒▒▒▒▒░░░░░░░░░░▒▒▒▒████░░██  \n" + //
                            "          ████▒▒▒▒░░░░░░▒▒▒▒▒▒░░░░░░▒▒▒▒░░░░░░▒▒▒▒████▒▒▒▒████  \n" + //
                            "      ████▒▒▒▒░░░░░░░░░░▒▒▒▒▒▒▒▒▒▒░░░░░░▒▒▒▒▒▒████▒▒▒▒████▒▒██  \n" + //
                            "  ████▒▒▒▒▒▒▒▒░░░░░░░░░░░░▒▒▒▒▓▓▒▒▒▒▒▒░░░░░░▒▒▒▒▒▒████▒▒▒▒████  \n" + //
                            "  ██░░████▒▒▒▒░░░░░░▒▒▒▒░░░░░░░░░░▒▒▓▓▒▒░░░░▒▒████▒▒▒▒████▒▒██  \n" + //
                            "  ████░░░░████▒▒▒▒░░░░░░░░░░░░▒▒▒▒████▒▒░░░░▒▒▒▒▒▒████▒▒▒▒████  \n" + //
                            "  ██░░████░░░░████▒▒▒▒▒▒▒▒▒▒▒▒████▒▒▒▒▒▒░░░░▒▒████▒▒▒▒████▒▒██  \n" + //
                            "  ████░░░░████░░░░████▒▒▒▒████▒▒▒▒████▒▒░░░░▒▒▒▒▒▒████▒▒▒▒████  \n" + //
                            "  ██░░████░░░░████░░░░████▒▒▒▒████▒▒▒▒▒▒░░░░▒▒████▒▒▒▒████▒▒██  \n" + //
                            "  ████░░░░████░░░░██████▒▒████▒▒▒▒████▒▒░░░░▒▒▒▒▒▒████▒▒▒▒████  \n" + //
                            "  ██░░████░░░░████░░░░████▒▒▒▒████▒▒▒▒▒▒░░░░▒▒████▒▒▒▒████▒▒██  \n" + //
                            "  ████░░░░████░░░░██████▒▒████▒▒▒▒████▒▒░░░░▒▒▒▒▒▒████▒▒▒▒████  \n" + //
                            "  ██░░████░░░░████░░░░████▒▒▒▒████▒▒▒▒▒▒░░░░▒▒████▒▒▒▒████      \n" + //
                            "  ████░░░░████░░░░██████░░████▒▒▒▒████▒▒░░░░▒▒░░▒▒████          \n" + //
                            "  ██░░████░░░░████░░░░████▒▒░░████▒▒▒▒▒▒░░░░▒▒████              \n" + //
                            "  ████░░░░████░░░░██████▒▒████▒▒▒▒████▒▒▒▒▒▒▒▒                  \n" + //
                            "      ████░░░░████░░░░████▒▒▒▒████▒▒▒▒▒▒▒▒                      \n" + //
                            "          ████░░░░██████▒▒████▒▒▒▒████                          \n" + //
                            "              ████░░░░████░░▒▒████                              \n" + //
                            "                  ██████▒▒████                                  \n" + //
                            "                      ████                                      \n"
            );}

    public static void main(String[] args) {
        BankAccount bank = null;
        SavingsAccount savings = null;

        Scanner read = new Scanner(System.in);
        String finalUser = "";
        String currentErr = "";
        String successMessage = "";
        boolean exit = false;
        int state = 0;
        boolean firstTimeMessage = true;
        while (!exit) {
            if (state == 0) {
                printIco();
                int choice = 0;
                System.out.println(">===============$ Welcome to Megabank! $===============<\n* Please input a number to do your desired action.");
                System.out.println("(1) Log in\n(2) Register\n(3) Exit");
                try {choice = read.nextInt();} catch (Exception e) {continue;};
                switch(choice) {
                    case 1:
                        state = 1;
                        break;
                    case 2:
                        state = 2;
                        break;
                    case 3:
                        exit=true;
                        break;
                    default:
                        break;
                }
            }
            if (state == 1) {
                printIco();
                System.out.println(">===============$ Megabank: Logging in... $===============<\n* Please input your credentials.");
                if (!currentErr.isEmpty()) {System.out.println("An error has occurred: " + currentErr);}
                System.out.print("Username: "); String user = read.next();
                System.out.print("Password: "); String pass = read.next();
                System.out.print("Pin: "); String pin = read.next();
                currentErr = "";
                if (!user.isEmpty() && !pass.isEmpty() && !pin.isEmpty()) {
                    bank = new BankAccount(user, pass, pin);
                    switch(bank.login()) {
                        case 1: 
                            currentErr = "Username is too short.";
                            break;
                        case 2:
                            currentErr = "Password is too short.";
                            break;
                        case 3:
                            currentErr = "Pin is too short.";
                            break;
                        case 4:
                            currentErr = "User does not exist or the username is wrong.";
                            break;
                        case 5:
                            currentErr = "Password / pin is incorrect.";
                            break;
                        default:
                            state = 4;
                            finalUser = user;
                            firstTimeMessage = true;
                            savings = new SavingsAccount(user, pass, pin);
                            break;
                    }
                }
            }
            if (state == 4) {
                printIco();
                int choice = 0;
                System.out.println(">===============$ Megabank: " + finalUser + "'s "+"BuckBank (B$ " + bank.balance(finalUser) + ") $===============<");
                if (firstTimeMessage) {
                    System.out.println("Logged in successfully as " + finalUser + "!");
                    firstTimeMessage = false;
                }
                if (!successMessage.isEmpty()) {
                    System.out.println("Success: " + successMessage);
                    successMessage = "";
                }
                System.out.println("* Please input a number to do your desired action. ");
                System.out.println("(1) Withdraw\n(2) Deposit\n(3) Transfer money\n(4) Savings mode\n(5) Log out");
                try {choice = read.nextInt();} catch (Exception e) {currentErr = "Please input a number!"; read.next();}
                switch(choice) {
                    case 1:
                        state = 6;
                        break;
                    case 2:
                        state = 5;
                        break;
                    case 3:
                        state = 7;
                        break;
                    case 4:
                        state = 8;
                        break;
                    case 5:
                        state = 0;
                        break;
                    case 0:
                        break;
                }
            }
            if (state == 5) {
                printIco();
                double amt = 0;
                System.out.println(">===============$ Megabank: " + finalUser + "'s "+"BuckBank (B$ " + bank.balance(finalUser) + "); Depositing $===============<");
                if (!currentErr.isEmpty()) {System.out.println("An error has occurred: " + currentErr); currentErr = "";}
                System.out.println("* Please input an amount. (Put 0 to cancel)");
                System.out.print("Amount to deposit: B$ ");
                try {amt = read.nextDouble();} catch (InputMismatchException e) { currentErr = "Please put a number!"; read.next(); continue;}
                if (amt > 0) {
                    bank.deposit(amt);
                    successMessage = "Successfully deposited B$" + amt + " into account!";
                } else if (amt == 0) {
                    state = 4;
                } else if (amt < 0) {
                    currentErr = "Put a number greater than 0.";
                }
            }
            if (state == 6) {
                printIco();
                double amt = 0;
                System.out.println(">===============$ Megabank: " + finalUser + "'s "+"BuckBank (B$ " + bank.balance(finalUser) + "); Withdrawing $===============<");
                if (!currentErr.isEmpty()) {System.out.println("An error has occurred: " + currentErr); currentErr = "";}
                System.out.println("* Please input an amount. (Put 0 to cancel)");
                System.out.print("Amount to withdraw: B$ ");
                try {amt = read.nextDouble();} catch (InputMismatchException e) { currentErr = "Please put a number!"; read.next(); continue;}
                if (amt > 0) {
                    if (amt > bank.balance(finalUser)) {
                        currentErr = "You cannot withdraw more than your current amount of bucks!";
                    } else {
                    bank.withdraw(amt);
                    successMessage = "Successfully withdrew B$" + amt + " from account!";
                    }
                } else if (amt == 0) {
                    state = 4;
                } else if (amt < 0) {
                    currentErr = "Put a number greater than 0.";
                }
            }
            if (state == 2) {
                printIco();
                System.out.println(">===============$ Megabank: Registering... $===============<\n* Please input your credentials.");
                if (!currentErr.isEmpty()) {System.out.println("An error has occurred: " + currentErr);}
                System.out.print("Username: "); String user = read.next();
                System.out.print("Password: "); String pass = read.next();
                System.out.print("Pin: "); String pin = read.next();
                if (!user.isEmpty() && !pass.isEmpty() && !pin.isEmpty()){
                    bank = new BankAccount(user, pass, pin);
                    switch(bank.register()) {
                            case 1: 
                                currentErr = "Username is too short.";
                                break;
                            case 2:
                                currentErr = "Password is too short.";
                                break;
                            case 3:
                                currentErr = "Pin is too short.";
                                break;
                            case 4:
                                currentErr = "User already exists!";
                                break;
                            default:
                                state = 4;
                                finalUser = user;
                                firstTimeMessage = true;
                                break;
                        }
                }

            }
            if (state == 7) {
                printIco();
                System.out.println(">===============$ Megabank: " + finalUser + "'s "+"BuckBank (B$ " + bank.balance(finalUser) + "); Transferring money... $===============<");
                if (!currentErr.isEmpty()) {System.out.println("An error has occurred: " + currentErr); currentErr = "";}
                System.out.println("* Please input the user you would like to transfer to. (Type \"quit\" to cancel.)");
                System.out.print("User: "); String to_user = read.next();
                if (to_user.equals("quit")) {state = 4; continue;}
                System.out.print("Amount to transfer: B$ ");
                try {
                    Double amt = read.nextDouble();
                    if (amt <=0) {currentErr = "Please input a number greater than zero.";}
                    else if (amt > bank.balance(finalUser)) {currentErr = "You cannot transfer more than what you have!";}
                    else if (amt > 0 && amt <= bank.balance(finalUser)) {
                        switch (bank.transfer_money(finalUser, to_user, amt)) {
                        case 1:
                            currentErr = "Account does not exist.";
                            break;
                        default:
                            successMessage = "Sucessfully transferred B$ "+ amt + " to " + to_user + "!";
                            break;
                    }
                };
                } catch (InputMismatchException e) {
                    currentErr = "Please input a number in amount.";
                    read.next();
                }
            }
            if (state == 8) {
                printIco();
                System.out.println(">===============$ Megabank: " + finalUser + "'s "+"BuckBank (B$ " + bank.balance(finalUser) + "); About savings mode.. $===============<");
                System.out.println("* For every minute you are logged into your account, you will gain 10% of your money.");
                System.out.println("* That's all! Type anything to quit out of this screen.");
                String h = read.next();
                state = 4;
            }
            clearScreenPlatformSpecific();
        }
        savings = null;
        read.close();
    }
}
