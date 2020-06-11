import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolDemo {
    public static void main(String[] args) {
        int cpu = Runtime.getRuntime().availableProcessors();//获取可使用的cpu核数
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, cpu, 4,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(3),//等待3区大小
                Executors.defaultThreadFactory());
        try{
            for(int i=1;i<=20;i++){
                threadPoolExecutor.execute(()->{
                    System.out.println(Thread.currentThread().getName()+" finished");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            threadPoolExecutor.shutdown();
        }
    }
}
