package lc1114;

/**
 * @author payno
 * @date 2019/11/22 15:10
 * @description
 */
public class Test {
    public static void test(Foo foo) throws Exception{
        new Thread(()->{
            foo.three();
        }).start();
        new Thread(()->{
            foo.two();
        }).start();
        new Thread(()->{
            foo.one();
        }).start();
        Thread.sleep(1000);
    }
}
