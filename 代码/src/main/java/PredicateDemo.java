import java.util.function.Predicate;

public class PredicateDemo {
    public static void main(String[] args) {
        Predicate<String> predicate=(str)->{
            if(str.isEmpty()) return true;
            else return false;
        };
        System.out.println(predicate.test(""));
    }
}
