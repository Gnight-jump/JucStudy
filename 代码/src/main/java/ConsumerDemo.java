import java.util.function.Consumer;

public class ConsumerDemo {
    public static void main(String[] args) {
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String str) {
                System.out.println(str);
            }
        };
        Consumer<String> consumer2 = (str)->{System.out.println(str);};
        consumer2.accept("finished");
    }
}