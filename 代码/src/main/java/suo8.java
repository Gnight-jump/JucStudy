import java.util.concurrent.TimeUnit;

/***
 * @author: G_night
 * 转载请声明作者
 * Reprint please state the author
 ***/

public class suo8 {
    public static void main(String[] args) {
        phone phone = new phone();
        phone phone1 = new phone();
        new Thread(()->{
            phone.send();
        },"a").start();
        new Thread(()->{
            phone1.call();
        },"b").start();
    }
}

class phone{

    public synchronized void send(){
        System.out.println("send");
    }
    public synchronized void call(){
        System.out.println("call");
    }

    public void hello(){
        System.out.println("hello");
    }
}