import java.util.function.Supplier;

public class SupplierDemo {
    public static void main(String[] args) {
        Supplier<String> supplier=()->{
            return "hello";
        };
        System.out.println(supplier.get());
    }
}
