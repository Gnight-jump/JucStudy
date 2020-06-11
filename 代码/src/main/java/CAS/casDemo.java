package CAS;/*
 *  @author: G_night
 *  转载请申明作者
 *  Reprint please state the author
 */

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class casDemo {

    // 业务中，一般比较的是一个个对象。第二个参数是初始版本号
    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(1,1);

    public static void main(String[] args) {

        new Thread(()->{
            int stamp = atomicStampedReference.getStamp(); // 获得版本号
            System.out.println("版本号 => "+stamp);

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Lock lock = new ReentrantLock(true);

            atomicStampedReference.compareAndSet(1, 2,
                    atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);

            System.out.println("版本号 => "+atomicStampedReference.getStamp());

            System.out.println("捣乱线程执行："+atomicStampedReference.compareAndSet(2, 1,
                    atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1));

            System.out.println("版本号 => "+atomicStampedReference.getStamp());

        },"a").start();


        new Thread(()->{
            int stamp = atomicStampedReference.getStamp(); // 获得版本号
            System.out.println("期望获取的版本号 => "+stamp);

            //休息长时间，让捣乱线程执行
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("期望线程执行："+atomicStampedReference.compareAndSet(1, 6,
                    stamp, stamp + 1));

            System.out.println("版本号 => "+atomicStampedReference.getStamp());

        },"b").start();

    }
}