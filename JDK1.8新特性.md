# 函数式接口

函数式接口和Lambda表达式一般协同使用，让代码看起来简洁！很多框架底层大量应用这些代码，所以未来以后的学习，这些也是必学的！

**Function**

Function 函数型接口, 有一个输入参数，有一个输出

需要重写的方法是：apply()

```java
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
        System.out.println(function2.apply("finished"));
    }
}
```

**Predicate**

传入一个参数，通过一系列判断，然后返回布尔值！

需要重写的方法是：test()

```java
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

```

**Consumer**

只有输入，返回值为void（即没有），消费者！   

需要重写的方法是：accept()

```java
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
```

**Supplier**

没有输入，只有输出，生产者！

需要重写的方法是：get()

```java
import java.util.function.Supplier;

public class SupplierDemo {
    public static void main(String[] args) {
        Supplier<String> supplier=()->{
            return "hello";
        };
        System.out.println(supplier.get());
    }
}

```

# Stream流式计算

假设存在一个对象User：

```java
class User{
    String name;
    int age;
    String sex;

    public User(String name, int age, String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }
/** 省略getter&setter **/
}

//需要输出的list集合
User u1 = new User("W", 12, "男");
User u2 = new User("X", 22, "男");
User u3 = new User("A", 32, "女");
User u4 = new User("B", 42, "女");
User u5 = new User("C", 52, "男");
List<User> list = Arrays.asList(u1, u2, u3,u4,u5);
```

输出的条件：

1. 性别男
2. 年龄小于40
3. 只输出名字（按字典序倒序输出！）

早期我们需要遍历一个集合的时候需要这么写：

```java
List<String> userName = new ArrayList<>();
for(int i=0;i<list.size();i++){
User user=list.get(i);
if(user.getAge()<40&&user.getSex().equals("男")){
    	userName.add(user.getName());
	}
}
userName.sort(Comparator.reverseOrder());
for(int i=0;i<userName.size();i++){
    System.out.println(userName.get(i));
}
//很麻烦
```

使用流式计算（搭配Lambda表达式使用）：

```java
//Stream流式计算
list.stream()
    .filter(u->{ return u.getAge()<40;}) 
    .filter(u->{return  u.getSex().equals("男");})
    .map(u->{return u.getName();})
    .sorted((cu1,cu2)->{return cu2.compareTo(cu1);})
    .forEach(System.out::println);
```

### 流式计算各个方法介绍

1.  filter (条件) ：过滤器，设置我们想要的条件

2.  distinct ()：去重！删除重复的元素

3.  limit (n)：返回流的前n个元素，少于n则全部返回

4.  sorted ( (s1,s2) -> s2.age - s1.age )：排序，这个是年龄逆序

5.  skip (n)：跳过前面n个元素再输出

6.  map (u->u.getName())：映射，返回我们所需要的数据

7.  allMatch (u->u.getAge<60)：判断是否全部元素age都小于60

8.  anyMatch (u->u.getAge<20)：判断是否存在元素age小于20

9.  noneMatch (u->u.getAge<30)：判断是否存在元素age小于30，如果存在返回false，若不存在满足该条件的元素则返回true

10. reduce (0,(a,b) -> a+b )：归约，可以用于求和

    ```java
    //对所有年龄求和
    System.out.println(list.stream().mapToInt(User::getAge).reduce(0,(a,b)->a+b));
    ```

11.  collect (Collectors.toList())：收集器，可以将结果封装，这里是封装成list

12.  counting ()：计算总数

    ```java
    long COUNT = list.stream().count();  //这里是count
    long COUNT2 = list.stream().collect(Collectors.counting());//这里是counting！
    ```

    

13.  joining (",")：字符串拼接，如输出所有user的名字，用“，”隔开

    ```java
    System.out.println(list.stream().map(User::getName).collect(Collectors.joining(",")));//输出为：W,X,A,B,C
    ```

    

14.  Collertors.groupingBy (User::getSex)：按照性别分类

    ```java
    System.out.println(list.stream().collect(Collectors.groupingBy(User::getSex)));
    //也可以对返回结果map操作
    Map<String, List<User>> collect = list.stream().collect(Collectors.groupingBy(User::getSex));
    ```

    



