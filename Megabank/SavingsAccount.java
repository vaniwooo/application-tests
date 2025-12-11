import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class SavingsAccount extends BankAccount {
    SavingsAccount(String user, String pass, String pin) {
        super(user, pass, pin);
        ScheduledExecutorService executorService = Executors
        .newSingleThreadScheduledExecutor();
        Runnable runnable = () -> {
            if (super.balance(user) > 1000000) return;
            super.deposit(super.balance(user) * 0.05);
        };

        executorService.scheduleAtFixedRate(runnable, 0, 60000, TimeUnit.MILLISECONDS);
    };
}
        
    // if money is left untouched for 1 minute, add 10% to money UNTIL user hits 1m.