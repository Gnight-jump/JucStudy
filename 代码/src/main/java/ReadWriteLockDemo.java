import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/***
 * @author: G_night
 * 转载请声明作者
 * Reprint please state the author
 ***/

public class ReadWriteLockDemo {
    public static void main(String[] args) {
        myRWLock myRWLock = new myRWLock();
        for(int i=1;i<=10;i++){
            final int t=i;
            new Thread(()->{
                myRWLock.put(t,t);
            },"Write_Thread_"+String.valueOf(t)).start();
        }
        for(int i=1;i<=10;i++){
            final int t=i;
            new Thread(()->{
                myRWLock.get(t);
            },"Read_Thread_"+String.valueOf(t)).start();
        }
    }
}

class myRWLock{
    //初始化读写锁
    ReentrantReadWriteLock lock=new ReentrantReadWriteLock();
    //存储数据的map
    HashMap<Integer,Integer> map=new HashMap<>();
    //存入
    public void put(int k,int v){
        lock.writeLock().lock();
        try{
            System.out.println(Thread.currentThread().getName()+"开始写入");
            map.put(k,v);
            System.out.println(Thread.currentThread().getName()+"结束写入");
        }finally {
            lock.writeLock().unlock();
        }

    }

    public void get(int v){
        lock.readLock().lock();
        try{
            System.out.println(Thread.currentThread().getName()+"开始读取");
            System.out.println("获取："+map.get(v));
            System.out.println(Thread.currentThread().getName()+"结束读取");
        }finally {
            lock.readLock().unlock();
        }
    }

}