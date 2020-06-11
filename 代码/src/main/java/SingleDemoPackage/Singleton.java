package SingleDemoPackage;/*
 *  @author: G_night
 *  转载请申明作者
 *  Reprint please state the author
 */

public enum Singleton {

        INSTANCE;

        private String instance;

        Singleton(){ instance = new String("hello"); }

        public String getInstance(){
            return instance;
        }
}
