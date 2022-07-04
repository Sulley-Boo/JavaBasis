package deadlock;

import java.util.concurrent.locks.ReentrantLock;

public class PhilosophersEatingSolve {
    /**
     * 哲学家就餐问题的解决：使用ReentrantLock
     * 其中的tryLock方法，在无法获得锁时，树主动放弃手里的锁，从而避免死锁的发生。
     */
    public static void main(String[] args) {
        Chopstick2 c1 = new Chopstick2("1");
        Chopstick2 c2 = new Chopstick2("2");
        Chopstick2 c3 = new Chopstick2("3");
        Chopstick2 c4 = new Chopstick2("4");
        Chopstick2 c5 = new Chopstick2("5");
        new Philosopher2("苏格拉底", c1, c2).start();
        new Philosopher2("柏拉图", c2, c3).start();
        new Philosopher2("亚里士多德", c3, c4).start();
        new Philosopher2("赫拉克利特", c4, c5).start();
        new Philosopher2("阿基米德", c5, c1).start();
    }
}

class Philosopher2 extends Thread {
    Chopstick2 left;
    Chopstick2 right;

    public Philosopher2(String name, Chopstick2 left, Chopstick2 right) {
        super(name);
        this.left = left;
        this.right = right;
    }

    @Override
    public void run() {
        while (true) {
            // 尝试获得左筷子
            if (left.tryLock()) {
                try {
                    // 尝试获得右筷子
                    if (right.tryLock()) {
                        try {
                            eat();
                        } finally {
                            right.unlock();
                        }
                    }
                } finally {
                    left.unlock(); // 释放了自己手里的左手筷子
                }
            }
        }
    }

    private void eat() {
        System.out.println(Thread.currentThread().getName() + "is eating...");
        try {
            sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Chopstick2 extends ReentrantLock {
    public String name;

    public Chopstick2(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Chopstick{" +
                name +
                '}';
    }
}

