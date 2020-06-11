import java.util.function.Function;

public class FunctionDemo {
    public static void main(String[] args) {
        //一般不用这个写法
        Function<String,String> function = new Function<String,String>() {
            @Override
            public String apply(String str) {
                return str;
            }
        };
        //简单的写法
        Function<String,String> function2 = (str)->{return str;};
        System.out.println(function2.apply("Function："+"finished"));

    }
}