package cn.tedu.nybike.thread;

public class Demo01 {
    public static void main(String[] args) {
        System.out.println("程序开始执行......");
        new MyThread1().start();
//        new MyThread2().start();
        System.out.println("程序执行结束++++++++++++++");

    }
}
class MyThread1 extends Thread{
    @Override
    public void run() {
        for(int i=0; i<100; i++){
            System.out.println("----------------------");
        }
        new MyThread2().start();
    }
}

class MyThread2 extends Thread{
    @Override
    public void run() {
        for(int i=0; i<100; i++){
            System.out.println("...........................");
        }
    }
}
