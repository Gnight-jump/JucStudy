import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/***
 *
 *
 * @author: G_night
 * 转载请声明作者
 * Reprint please state the author
 ***/
public class producer_consumer {

    public static void main(String[] args) {
        Person person = new Person();
        for (int i=0;i<6;i++){
            new Thread(()->{
                person.produce();
            },"producer").start();
            new Thread(()->{
                person.consume();
            },"consumer").start();
        }
    }

}

class Person{
    private int num=0;//产品数量
    Lock lock=new ReentrantLock();
    Condition condition=lock.newCondition();
    public void produce(){
        lock.lock();
        try{
            while(num!=0){
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            num++;
            condition.signalAll();
            System.out.println("生产了商品，目前商品存余："+num);
        }finally {
            lock.unlock();
        }
    }
    public void consume(){
        lock.lock();
        try{
            while(num==0){
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            num--;
            condition.signalAll();
            System.out.println("生产了商品，目前商品存余："+num);
        }finally {
            lock.unlock();
        }

    }
}
