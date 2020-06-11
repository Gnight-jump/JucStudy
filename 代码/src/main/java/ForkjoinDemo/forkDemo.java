package ForkjoinDemo;/*
 *  @author: G_night
 *  转载请申明作者
 *  Reprint please state the author
 */

import java.util.concurrent.RecursiveTask;

public class forkDemo extends RecursiveTask<Long> {

    private Long start;
    private Long end;
    // 临界值
    private Long temp = 10000L;

    public forkDemo(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    // 计算方法
    @Override
    protected Long compute() {
        if ((end-start)<temp){
            Long sum = 0L;
            for (Long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        }else { // forkjoin 递归
            long middle = (start + end) / 2; // 中间值
            forkDemo task1 = new forkDemo(start, middle);
            task1.fork(); // 拆分任务，把任务压入线程队列
            forkDemo task2 = new forkDemo(middle+1, end);
            task2.fork(); // 拆分任务，把任务压入线程队列
            //返回！！！
            return task1.join() + task2.join();
        }
    }
}
