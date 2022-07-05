package singleton_mode;

/**
 * 方法4：双重校验锁-线程安全
 * uniqueInstance 只需要被实例化一次，之后就可以直接使用了。
 * 加锁操作只需要对实例化那部分的代码进行，只有当uniqueInstance没有被实例化时，才需要进行加锁。
 * 双重校验锁先判断 uniqueInstance 是否已经被实例化，如果没有被实例化，那么才对实例化语句进行加锁。
 * <p>
 * 如果只使用一个if判断语句，情况如下：
 * 在uniqueInstance == null的情况下，如果两个线程都执行了if语句，
 * 那么两个线程都会进入if语句块内。虽然在if语句块内有加锁操作，但是两个线程都会执行uniqueInstance = new Singleton()这条语句，
 * 只是先后的问题，那么就会进行两次实例化。
 * 因此必须使用双重校验锁，也就是需要使用两个if语句：
 * 第一个if语句用来避免uniqueInstance已经被实例化之后的加锁操作，
 * 而第二个if语句进行了加锁，所以只能有一个线程进入，就不会出现 uniqueInstance == null 时两个线程同时进行实例化操作。
 * <p>
 * uniqueInstance采用volatile关键字修饰也是很有必要的，uniqueInstance = new Singleton()这段代码其实是分为三步执行：
 * (1)为uniqueInstance分配内存空间
 * (2)初始化uniqueInstance
 * (3)将uniqueInstance指向分配的内存地址
 * 但是由于JVM具有指令重排的特性，执行顺序有可能变成 1>3>2。指令重排在单线程环境下不会出现问题，
 * 但是在多线程环境下会导致一个线程获得还没有初始化的实例。
 * 例如，线程T1执行了1和3，此时T2调用getUniqueInstance()后发现uniqueInstance不为空，因此返回uniqueInstance，
 * 但此时 uniqueInstance还未被初始化。
 * 使用volatile可以禁止JVM的指令重排，保证在多线程环境下也能正常运行。
 */

public class Singleton4 {
    private volatile static Singleton4 uniqueInstance;

    private Singleton4() {

    }

    public static Singleton4 getUniqueInstance() {
        if (uniqueInstance == null) {
            synchronized (Singleton4.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new Singleton4();
                }
            }
        }
        return uniqueInstance;
    }
}
