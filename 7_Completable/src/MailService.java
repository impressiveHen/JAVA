import java.util.concurrent.CompletableFuture;

public class MailService {
    public void send() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Mail was sent");
    }

    // convert synchronous code to async by wrapping in completableFuture
    public CompletableFuture<Void> sendAsync() {
        return CompletableFuture.runAsync(() -> send());
    }
}
