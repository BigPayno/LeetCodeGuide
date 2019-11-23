package lc1115;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author payno
 * @date 2019/11/22 16:40
 * @description
 *      (看来jdk选择这个是有原因的)可重入锁+信号通知>volatile+自旋？？？？？
 *
 */
public class GoodSolution {
    class FooBar {
        private int n;

        private volatile boolean flag = false;

        private ReentrantLock lock = new ReentrantLock();

        private Condition condition = lock.newCondition();

        public FooBar(int n) {
            this.n = n;
        }

        public void foo(Runnable printFoo) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                lock.lock();
                if(flag){
                    condition.await();
                }
                printFoo.run();
                flag = true;
                condition.signalAll();
                lock.unlock();
            }
        }

        public void bar(Runnable printBar) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                lock.lock();
                if(!flag){
                    condition.await();
                }
                printBar.run();
                flag = false;
                condition.signalAll();
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
    }
}
