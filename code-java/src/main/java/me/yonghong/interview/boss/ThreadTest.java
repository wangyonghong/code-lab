package me.yonghong.interview.boss;

public class ThreadTest extends Thread {

    private final String tName;

    public ThreadTest(String name) {
        this.tName = name;
    }

    public static void main(String[] args) throws InterruptedException {
        Thread a = new ThreadTest("a");
        Thread b = new ThreadTest("b");
        Thread c = new ThreadTest("c");
        a.start();
        a.join();
        b.start();
        b.join();
        c.start();
        c.join();
    }

    @Override
    public void run() {
        System.out.println("线程执行: " + this.tName);
    }
}
