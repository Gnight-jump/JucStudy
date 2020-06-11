import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/***
 * @author: G_night
 * 转载请声明作者
 * Reprint please state the author
 ***/
public class collection_thread {

    public static void main(String[] args) {
        //线程安全的方法1
        List list = Collections.synchronizedList(new ArrayList<>());
        Set set = Collections.synchronizedSet(new HashSet<>());
        //线程安全的方法2
        List copyOnWriteArrayList=new CopyOnWriteArrayList();
        Set copyOnWriteArraySet=new CopyOnWriteArraySet();
        //hashmap线程安全的方法
        Map<String, String> map = new ConcurrentHashMap<>();

        //演示
        for (int i = 1; i <=30; i++) {
            new Thread(()->{
                map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring( 0,5));
                System.out.println(map);
            },String.valueOf(i)).start();
        }
    }

}
