import java.util.HashMap;
import java.util.Scanner;
import java.util.List;
import java.time.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class BankAccount {
    /*
    BankAccount.java: Meant to be for logins, operations on the account etc.
    */
    private HashMap<String, Object> account = new HashMap<>();

    BankAccount(String user, String password, String pin) {
        // Temporarily store the user's inputted username and password. After login, immediately delete user and password data.
        account.put("user", user);
        account.put("password", password);
        account.put("pin", pin);
        account.put("status", 0);
        account.put("bal", 0.0d);

        // Check if there is an account.data file in the current folder's location.
        try {
            File AccountData = new File("account.data");

            if (AccountData.createNewFile()) {
                System.out.println("account.data file was missing. Successfully generated new empty account.data file.");
            } 
        } catch (IOException e) {
            System.out.println("An error occurred within the account.data check. Please try again later.");
        }
    }

    // login(): used for logging into the account if it exists.
    /*
        Error codes for logins:
            1: username is too short!
            2: password is too short!
            3: pin is too short!
            4: account does not exist!
            5: password / pin is wrong.
    */
    public int login() {
        String user = (String) account.get("user");
        String pwd = (String) account.get("password");
        String pin = (String) account.get("pin");

        // Check for invalidations in the user's input
        if (user.length() < 8) return 1;
        if (pwd.length() < 8) return 2;
        if (pin.length() < 4) return 3;

        // Find account
        if (!findAccount(user)) return 4;
        
        // Verify if password and pin is correct
        if (!password(user, pwd, pin)) return 5;

        // if all checks passed, set login status and update balance!
        account.put("status", 1);
        account.put("bal",balance(user) );
        System.out.println(compress());
        return 0;
    }

    // register(): used for making a new account.
    public int register() {
        String user = (String) account.get("user");
        String pwd = (String) account.get("password");
        String pin = (String) account.get("pin");

        // Check for invalidations in the user's input
        if (user.length() < 8) return 1;
        if (pwd.length() < 8) return 2;
        if (pin.length() < 4) return 3;

        // Make sure account does not exist
        if (findAccount(user)) return 4;

        // Shove all data into a single string
        String new_data = user + "%" + pwd + "%" + pin + "%" + "0.0d" + "%" + Instant.now().getEpochSecond();
        save(new_data, true);

        return 0;
    }

    // TEMPORARY: file format
    // user%pass%pin%bal%TransactionHistory, separated with \ instead of /; make sure user is not able to use \
    // trans. history format: event\\value\\unicodetimestamp||
    protected boolean findAccount(String user) {
        File account_data = new File("account.data");
        try (Scanner line_reader = new Scanner(account_data)) {
            while (line_reader.hasNextLine()) {
                String credentials = line_reader.nextLine();
                String[] split_credentials = credentials.split("%");
                if (split_credentials[0].equals(user)) {
                    // account exists
                    return true;
                }
            }
            
        } catch (FileNotFoundException e) {
            // TODO: handle exception
        }
        return false;
    }

    // password(): private function used to verify whether credentials are right or not
    protected boolean password(String user, String pwd, String pin) {
        File account_data = new File("account.data");
        try (Scanner line_reader = new Scanner(account_data)) {
            while (line_reader.hasNextLine()) {
                String creds = line_reader.nextLine();
                String[] split_creds = creds.split("%");
                if (split_creds[0].equals(user)) {
                    if (split_creds[1].equals(pwd) && split_creds[2].equals(pin)) return true;
                }
            }
        } catch (FileNotFoundException e) {

        }
        return false;
    }

    // parse_account_info(user): returns their account info.
    protected HashMap<String, Object> parse_account_info(String user) {
        HashMap<String, Object> a = new HashMap<String, Object>();
        Path account_data = Paths.get("account.data");
        try {
            List<String> lines = Files.readAllLines(account_data);
            for (int i = 0; i < lines.size(); i++) {
                if(lines.get(i).split("%")[0].equals(user)) {
                    String[] s = lines.get(i).split("%");
                    a.put("found", true);
                    a.put("user", s[0]);
                    a.put("password", s[1]);
                    a.put("pin", s[2]);
                    a.put("bal", s[3]);
                    return a;
                } else {
                    a.put("found", false);
                }
            }
        } catch (IOException e) {

        }
        return a;
    }

    // save(): saves account data to file.
    protected void save(String data, boolean new_acc) {
        // decompress data
        String[] d = data.split("%");
        Path account_data = Paths.get("account.data");
        if (!new_acc) {
            try {
                List<String> lines = Files.readAllLines(account_data);
                for (int i = 0; i < lines.size(); i++) {
                    // parse name
                    if (lines.get(i).split("%")[0].equals(d[0])) {
                        // save name
                        lines.set(i, data);
                    }
                }
                // write to the file
                Files.write(account_data, lines);
            } catch (Exception e) {
                // TODO: handle exception
            }

        } else {
            try {
                List<String> lines = Files.readAllLines(account_data);
                lines.add(data);
                // write to the file
                Files.write(account_data, lines);
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    } 

    protected String compress() {
        return account.get("user") + "%" + account.get("password") + "%" + account.get("pin") + "%" + account.get("bal") + "%" + Instant.now().getEpochSecond();
    }

    // ACTUAL BANKING STUFF NOW

    // balance(user): used to grab balance of a certain user.
    public double balance(String user) {
        return Double.parseDouble((String)parse_account_info(user).get("bal"));
    }
    // deposit(): used to add money to the bank account
    public void deposit(double amount) {
        double balance = (double) account.get("bal");
        balance += amount;
        account.put("bal", balance);
        save(compress(), false);
    }
    // withdraw(): used to take money from the bank account
    public void withdraw(double amount) {
        double balance = (double) account.get("bal");
        balance -= amount;
        account.put("bal", balance);
        save(compress(), false);
    }
    
    // transfer_money(): used to transfer money from an account to another
    public int transfer_money(String from_user, String to_user, double amt) {
        if (!findAccount(to_user)) return 1;
        HashMap<String,Object> to_user_info = parse_account_info(to_user);
        if (!(boolean) to_user_info.get("found")) return 2;
        withdraw(amt);

        double new_bal = Double.parseDouble((String)to_user_info.get("bal"));
        new_bal += amt;
        to_user_info.put("bal", Double.toString(new_bal));
        String new_data = (String) to_user_info.get("user") + "%" + (String) to_user_info.get("password") + "%" + (String) to_user_info.get("pin") + "%" + (String) to_user_info.get("bal");
        save(new_data, false);
        return 0;
    }
}
