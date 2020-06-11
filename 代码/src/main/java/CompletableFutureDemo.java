import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CompletableFutureDemo {

    public static void main(String[] args) throws Exception {
//        runAsync();
//        supplyAsync();
//        thenApply();
        thenCombine();

    }
    //无返回值
    public static void runAsync() throws Exception {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
            }
            System.out.println("running");
        });
        future.get();//获取阻塞执行结果
    }

    //有返回值
    public static void supplyAsync() throws Exception {
        CompletableFuture<Long> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
            }
            return 100L;
        });
        Long result = future.whenComplete((t, u) -> {
            System.out.println("ok--> "+t);//返回正确执行的结果
            System.out.println("no--> "+u);//输出错误信息
        }).exceptionally((e) -> {
            e.printStackTrace();
            return 200L;
        })//错误执行时的返回结果
          .get();
        System.out.println("result = "+result);
    }

    private static void thenApply() throws Exception {
        CompletableFuture<Long> future = CompletableFuture.supplyAsync(()->{
                long result = new Random().nextInt(100);
                System.out.println("result1="+result);
                return result;
            }).thenApply((t)->{
                long result = t*5;
                System.out.println("result2="+result);
                return result;
            });
        long result = future.get();
        System.out.println(result);
    }


    private static void thenCombine() throws Exception {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(()->{
                return "hello";
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(()->{
                return "hello";
        });
        CompletableFuture<String> result = future1.thenCombine(future2, (t,u)->{
                return t+" "+u;
        });
        System.out.println(result.get());
    }

}
