package lc1114;

import java.util.concurrent.Semaphore;

/**
 * @author payno
 * @date 2019/11/22 15:16
 * @description
 */
public class Solution2 {
    public static class Foo1 implements Foo{
        private volatile int semaphore=0;
        public synchronized void add(){
            /**
             * 也可以采用cas，只要保证主内存和线程之间的一致性
             */
            semaphore+=1;
        }
        public void one(){
            System.out.println("one");
            add();
        }
        public void two(){
            while(true){
                /**
                 * 当条件不满足，让线程自旋而非阻塞，因为这些线程切换速度很快
                 */
                if(semaphore==1){
                    System.out.println("two");
                    add();
                    break;
                }
            }
        }
        public void three(){
            while(true){
                if(semaphore==2){
                    System.out.println("three");
                    break;
                }
            }
        }
    }

    public static class Foo2 implements Foo{
        /**
         * 直接使用同步容器,可以放两个信号量作为开关，也可以只放一个作为限流器
         * 依旧使用非阻塞的方式
         * 建议使用多个信号量，否则会因为信号量竞争
         */
        private Semaphore semaphore=new Semaphore(1);
        public void one(){
            System.out.println("one");
            semaphore.release(1);
        }
        public void two(){
            while (true){
                if(semaphore.tryAcquire(2)){
                    System.out.println("two");
                    semaphore.release(3);
                    break;
                }
            }
        }
        public void three(){
            while (true){
                if(semaphore.tryAcquire(3)){
                    System.out.println("three");
                    semaphore.release(3);
                    break;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception{
        //Test.test(new Foo1());
        Test.test(new Foo2());
    }
}
