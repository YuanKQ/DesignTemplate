import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Auther: yuan
 * @Date: 18-10-28 16:13
 * @Description: Chapter 21 单例模式
 */
public class Chap21Singleton {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//         SingleThreadSingleton singleton1 = SingleThreadSingleton.getInstance("hahah");
//         SingleThreadSingleton singleton2 = SingleThreadSingleton.getInstance("lalala");

        MyCallable callable1 = new MyCallable();
        MyCallable callable2 = new MyCallable();
        FutureTask<MultiThreadSingleton> task1 = new FutureTask<>(callable1);
        FutureTask<MultiThreadSingleton> task2 = new FutureTask<>(callable2);
        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);
        thread1.start();
        thread2.start();
        thread1.sleep(100);
        thread2.sleep(100);
        MultiThreadSingleton m1 = task1.get();
        MultiThreadSingleton m2 = task2.get();
        System.out.println(m1 == m2);
        System.out.println(m1);
        System.out.println(m2);
    }
}

class SingleThreadSingleton {
    private String name;
    private static int count;
    private static SingleThreadSingleton instance;

    private SingleThreadSingleton() {

    }

    public static SingleThreadSingleton getInstance(String name) {
        if (instance == null) {
            instance = new SingleThreadSingleton();
            instance.name = name;
            count += 1;
            return instance;
        }
        return instance;
    }

    @Override
    public String toString() {
        return "SingleThreadSingleton{" +
                "name='" + name + '\'' +
                ", count=" + count +
                '}';
    }
}

class MultiThreadSingleton {
    private static int count;
    private static MultiThreadSingleton instance;
    private static Object lock = new Object();  // 双重锁定需要一个锁，不能把instance作为锁。

    private MultiThreadSingleton() {

    }

    public static MultiThreadSingleton getInstance() {
        if (instance == null) {
//            synchronized (instance) {     // 双重锁定需要一个锁，不能把instance作为锁。
            synchronized (lock) {
                if (instance == null) {
                    instance = new MultiThreadSingleton();
                    count += 1;
                    return instance;
                }
            }
        }
        return instance;
    }

    @Override
    public String toString() {
        return "MultiThreadSingleton{" +
                ", count=" + count +
                '}';
    }
}

class MyCallable implements Callable<MultiThreadSingleton> {
    @Override
    public MultiThreadSingleton call() throws Exception {
        return MultiThreadSingleton.getInstance();
    }
}