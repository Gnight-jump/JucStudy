package VolatileDemoPackage;

import java.util.concurrent.TimeUnit;

public class VolatileDemo {

    public static void main(String[] args) {
        VolatileNum num=new VolatileNum(0);
        new Thread(()->{
            while(num.getNum()==0){
//System.out.println("show:"+num.getNum());
//如果加了sout就可以见了，会自动结束！
/*因为System.out.println:
public void println(String x) {
    synchronized (this) {
        print(x);
        newLine();
    }
} */

            }
        }).start();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        num.setNum(1);
        System.out.println(num.getNum());
    }
}

class VolatileNum{
    private volatile int num;

    public VolatileNum(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public synchronized void setNum(int num) {
        this.num = num;
    }
}