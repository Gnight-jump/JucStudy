import java.util.concurrent.locks.ReentrantLock;

public class suo {
    public static void main(String[] args) {
        suotry suotry = new suotry();
        suotry.say();
    }
}

class suotry{
    public synchronized void say(){
        System.out.println("------------say-----------");
        hello();
    }
    public synchronized void hello(){
        System.out.println("==========hello============");
    }
}