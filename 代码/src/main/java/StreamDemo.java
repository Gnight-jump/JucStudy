import java.util.*;
import java.util.stream.Collectors;

public class StreamDemo {
    public static void main(String[] args) {
        User u1 = new User("W", 12, "男");
        User u2 = new User("X", 22, "男");
        User u3 = new User("A", 32, "女");
        User u4 = new User("B", 42, "女");
        User u5 = new User("C", 52, "男");
        List<User> list = Arrays.asList(u1, u2, u3,u4,u5);
        //Stream流式计算
        list.stream().filter(u->{ return u.getAge()<40;})
                .filter(u->{return  u.getSex().equals("男");})
                .map(u->{return u.getName();})
                .sorted((cu1,cu2)->{return cu2.compareTo(cu1);})
                .forEach(System.out::println);
        Map<String, List<User>> collect = list.stream().collect(Collectors.groupingBy(User::getSex));
    }
}
class User{
    String name;
    int age;
    String sex;

    public User(String name, int age, String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}