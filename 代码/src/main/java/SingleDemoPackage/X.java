package SingleDemoPackage;/*
 *  @author: G_night
 *  转载请申明作者
 *  Reprint please state the author
 */

public class X{
    private static X a=new X();
    private X(){}
    public static X getInstance(){
        return a;
    }
}