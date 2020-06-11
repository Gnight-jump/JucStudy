package VolatileDemoPackage;/*
 *  @author: G_night
 *  转载请申明作者
 *  Reprint please state the author
 */

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class yuanzixing {
/*
    方法一：加锁实现原子性
*/
    public static volatile int num1=0;

    public static synchronized void add1(){
        num1++;
    }

//原子类！
    public static AtomicInteger num2=new AtomicInteger(0);
    public static void add2(){
        num2.getAndIncrement();
    }

    public static void main(String[] args) {
        for(int i=0;i<10;i++){
            new Thread(()->{
                for(int j=0;j<2000;j++){
                    add1();
                }
            }).start();
            new Thread(()->{
                for(int j=0;j<2000;j++){
                    add2();
                }
            }).start();
        }
        while(Thread.activeCount()>2){ }//默认main、gc线程，
        // 意思是等那十个线程都执行完了！再输出
        System.out.println("num1 = "+num1);
        System.out.println("num2 = "+num2);
    }
}
