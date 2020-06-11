import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/***
 * @author: G_night
 * 转载请声明作者
 * Reprint please state the author
 ***/

public class PrintABC {
    public static void main(String[] args) {
        Print print = new Print();
        for(int i=0;i<6;i++){
            new Thread(()->{
                print.printA();
            },"a").start();
            new Thread(()->{
                print.printB();
            },"b").start();
            new Thread(()->{
                print.printC();
            },"c").start();
        }
    }
}

class Print{

    private int num=1;//区分三者执行条件
    Lock lock=new ReentrantLock();
    Condition conditionA=lock.newCondition();
    Condition conditionB=lock.newCondition();
    Condition conditionC=lock.newCondition();
    public void printA(){
        lock.lock();
        try{
            while(num!=1){
                conditionA.await();
            }
            System.out.println(Thread.currentThread().getName()+"执行了");
            num=2;
            conditionB.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void printB(){
        lock.lock();
        try{
            while(num!=2){
                conditionB.await();
            }
            System.out.println(Thread.currentThread().getName()+"执行了");
            num=3;
            conditionC.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void printC(){
        lock.lock();
        try{
            while(num!=3){
                conditionC.await();
            }
            System.out.println(Thread.currentThread().getName()+"执行了");
            num=1;
            conditionA.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

}