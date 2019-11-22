package lc1114;

/**
 * @author payno
 * @date 2019/11/22 14:46
 * @description
 *  我们提供了一个类：
 *
 * public class Foo {
 *   public void one() { print("one"); }
 *   public void two() { print("two"); }
 *   public void three() { print("three"); }
 * }
 *
 * 三个不同的线程将会共用一个 Foo 实例。
 *
 *     线程 A 将会调用 one() 方法
 *     线程 B 将会调用 two() 方法
 *     线程 C 将会调用 three() 方法
 *
 *     分析:如何保证多线程访问临界资源的顺序
 *              借某个答案所言就是设置屏障
 *     1.多线程的访问是否设计对临界资源的修改，没有，因此不可变
 *     唯一需要关注的就是作为信号量的可见性
 * @Solution1:设置两个屏障，保证屏障的可见性(线程和主内存的一致性)
 * @Solution2:设置信号量作为屏障，对信号量进行sync或者使用Semaphore（cas+volatile）
 * @SolutionMore:2个CountLatchDown作为屏障...
 * 使用串行化的思路，有很多，但还是使用非阻塞的两个解法，以上解法都是通过临界资源做控制
 * 也可以通过线程队列控制，就是完全的串行化
 */
public class Lc1114PrintSorted {
    public class Foo {
       public void one(){
           System.out.println("one");
       }
       public void two(){
           System.out.println("two");
       }
       public void three(){
           System.out.println("three");
       }
    }
}
