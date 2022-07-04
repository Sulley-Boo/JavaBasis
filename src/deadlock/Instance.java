package deadlock;

import static java.lang.Thread.sleep;

public class Instance {
    /**
     * 死锁
     * 一个线程需要同时多把锁，这时就很容易产生死锁
     * <p>
     * t1线程获得A对象锁，接下来想要获得B对象的锁。
     * t2线程获得A对象锁，接下来想要获得A对象的锁。
     */

    public static void main(String[] args) {
        Object objA = new Object();
        Object objB = new Object();

        Thread t1 = new Thread(() -> {
            synchronized (objA) {
                System.out.println("lock A");
                try {
                    sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (objB) {
                    System.out.println("lock B");
                    System.out.println("操作...");
                }
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            synchronized (objB) {
                System.out.println("lock B");
                try {
                    sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (objA) {
                    System.out.println("lock A");
                    System.out.println("操作...");
                }
            }
        }, "t2");

        t1.start();
        t2.start();

    }
}
