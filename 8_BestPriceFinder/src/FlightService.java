import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FlightService {
    public CompletableFuture<Quote> getQuote(String site) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("Start getQuote from " + site);

            Random rand = new Random();

            LongTask.simulate(1_000 + rand.nextInt(2_000));

            int price = 100 + rand.nextInt(10);

            return new Quote(site, price);
        });
    }

    public Stream<CompletableFuture<Quote>> getQuotes() {
        var sites = List.of("site1", "site2", "site3");
        return sites.stream()
                .map(this::getQuote);
    }

    public static void show() {
        var service = new FlightService();
        service.getQuote("site1")
            .thenAccept(quote -> System.out.println(quote));
    }

    public static void showMany() {
        var startTime = LocalTime.now();

        var service = new FlightService();
        var futures = service.getQuotes()
            .map(future -> future.thenAccept(quote -> System.out.println(quote)))
            .collect(Collectors.toList());

        // pass array of CompletableFuture objects to allOf
        CompletableFuture
            .allOf(futures.toArray(new CompletableFuture[0]))
            .thenRun(() -> {
                var endTime = LocalTime.now();
                var duration = Duration.between(startTime, endTime);
                System.out.println("Retrieved all quotes in " + duration.Millis() + "msec");
            });

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        showMany();
    }
}
