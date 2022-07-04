package deadlock;

public class PhilosophersEatingStarve {
    /**
     * 阿基米德一直没有筷子吃饭，处于饥饿状态
     */
    public static void main(String[] args) {
        Chopstick1 c1 = new Chopstick1("1");
        Chopstick1 c2 = new Chopstick1("2");
        Chopstick1 c3 = new Chopstick1("3");
        Chopstick1 c4 = new Chopstick1("4");
        Chopstick1 c5 = new Chopstick1("5");
        new Philosopher1("苏格拉底", c1, c2).start();
        new Philosopher1("柏拉图", c2, c3).start();
        new Philosopher1("亚里士多德", c3, c4).start();
        new Philosopher1("赫拉克利特", c4, c5).start();
        new Philosopher1("阿基米德", c1, c5).start();
    }
}

class Philosopher1 extends Thread {
    Chopstick1 left;
    Chopstick1 right;

    public Philosopher1(String name, Chopstick1 left, Chopstick1 right) {
        super(name);
        this.left = left;
        this.right = right;
    }

    @Override
    public void run() {
        while (true) {
            // 尝试获得左筷子
            synchronized (left) {
                // 尝试获得右筷子
                synchronized (right) {
                    eat();
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

class Chopstick1 {
    public String name;

    public Chopstick1(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Chopstick{" +
                name +
                '}';
    }
}
