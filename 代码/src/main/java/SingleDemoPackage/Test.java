package SingleDemoPackage;/*
 *  @author: G_night
 *  转载请申明作者
 *  Reprint please state the author
 */

import java.lang.reflect.Constructor;

public class Test {
    public static void main(String[] args)throws Exception {
        String a=Singleton.INSTANCE.getInstance();
        String b=Singleton.INSTANCE.getInstance();
        System.out.println(a==b);
    }
}
