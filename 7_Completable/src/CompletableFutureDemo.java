import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class CompletableFutureDemo {
    public static void show() {
        // default ForkJoinPool.commonPool() threadpool
        Runnable task0 = () -> System.out.println("Runn");
        var future = CompletableFuture.runAsync(task0);
        try {
            future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        Supplier<Integer> task1 = () -> 1;
        // for return value
        var future1 = CompletableFuture.supplyAsync(task1);
        try {
            var result1 = future1.get();
            System.out.println(result1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void demo() {
        var future = CompletableFuture.supplyAsync(() -> 1);
//         CompletionStage method
//         runnable
//        future.thenRun(() -> {
//            System.out.println(Thread.currentThread().getName());
//            System.out.println("Done");
//        });

        future.thenRunAsync(() -> {
           System.out.println(Thread.currentThread().getName());
           System.out.println("Done");
        });
//
//        future.thenAcceptAsync(result -> {
//            System.out.println(Thread.currentThread().getName());
//            System.out.println(result);
//        });
    }

    public static void showException() {
        var future = CompletableFuture.supplyAsync(() -> {
            System.out.println("Getting weather");
            throw new IllegalStateException();
        });

        try {
            // exceptionally maps an exception to different type
            // exceptionally returns a new CompletableFuture
            var temperature = future.exceptionally(ex -> 1).get();
            System.out.println(temperature);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static int toFahrenheit(int celsius) {
        return (int) (celsius * 1.8) + 32;
    }

    public static void transform() {

        var future = CompletableFuture.supplyAsync(() -> {
            return 20;
        });
            // thenApply returns new CompletableFuture
        future
            .thenApply(CompletableFutureDemo::toFahrenheit)
            .thenAccept(f -> System.out.println(f));
    }

    public static CompletableFuture<String> getUserEmailAsync() {
        return CompletableFuture.supplyAsync(() -> "Email from db");
    }

    public static CompletableFuture<String> getPlaylistAsync(String email) {
        return CompletableFuture.supplyAsync(() -> "Playlist from db with email");
    }

    public static void showPlaylist() {
        getUserEmailAsync()
            .thenCompose(CompletableFutureDemo::getPlaylistAsync)
            .thenAccept(playlist -> System.out.println(playlist));
    }

    public static void showCombine() {
        // start both tasks concurrently and start a new task once both tasks are finished
        var first = CompletableFuture
            .supplyAsync(() -> "20USD")
            .thenApply((str) -> {
                var price = str.replace("USD", "");
                return Integer.parseInt(price);
            });
        var second = CompletableFuture.supplyAsync(() -> 0.9);
        first
            .thenCombine(second, (price, rate) -> price * rate)
            .thenAccept(result -> System.out.println(result));
    }

    public static void showCombineMany() {
        var first = CompletableFuture.supplyAsync(() -> 1);
        var second = CompletableFuture.supplyAsync(() -> 2);
        var third = CompletableFuture.supplyAsync(() -> 3);
        var all = CompletableFuture.allOf(first, second, third);
        all.thenRun(() -> {
            // execute when all tasks are completed
            try {
                var firstResult = first.get();
                System.out.println(firstResult);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
    }

    public static void showTimeout() {
           var future = CompletableFuture.supplyAsync(() -> {
               LongTask.simulate();
               return 100;
           });

           try {
               var result = future
                   .completeOnTimeout(1, 2, TimeUnit.SECONDS)
                   .get();
               System.out.println(result);
           } catch (InterruptedException e) {
               e.printStackTrace();
           } catch (ExecutionException e) {
               e.printStackTrace();
           }

    }
}