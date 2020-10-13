import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class ExcecutorDemo {
    public static void show() {
        var executor = Executors.newFixedThreadPool(2);
        // submit task to threadpool, callable or runnable
        try {
            /creates separate thread and starts task
            var future= executor.submit(() -> {
                LongTask.simulate();
                return 1;
            });
            System.out.println("Do other work");
            try {
                var result = future.get();
                System.out.println(result);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        finally {
            executor.shutdown();
        }
    }

    public static void main(String[] args) {
        ExcecutorDemo.show();
    }
}
