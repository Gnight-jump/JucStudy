package ForkjoinDemo;/*
 *  @author: G_night
 *  转载请申明作者
 *  Reprint please state the author
 */

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class Test {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Long> task = new forkDemo(0L, 100000000L);
        ForkJoinTask<Long> submit = forkJoinPool.submit(task);
        Long aLong = submit.get();
        System.out.println(aLong);
    }
}
