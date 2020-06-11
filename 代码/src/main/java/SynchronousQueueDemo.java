import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class SynchronousQueueDemo {
    public static void main(String[] args) {
        BlockingQueue<String> queue=new SynchronousQueue<>();
        new Thread(()->{
            try {
                System.out.println("put : a");
                queue.put("a");
                System.out.println("put : b");
                queue.put("b");
                System.out.println("put : c");
                queue.put("c");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"1").start();
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println("take : "+queue.take());
                TimeUnit.SECONDS.sleep(2);
                System.out.println("take : "+queue.take());
                TimeUnit.SECONDS.sleep(2);
                System.out.println("take : "+queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"2").start();
    }
}
