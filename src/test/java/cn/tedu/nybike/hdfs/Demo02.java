package cn.tedu.nybike.hdfs;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Demo02 {
    public static void main(String[] args) throws IOException {
        String str = "..........";
        File file = new File("xxxx.json");
        Writer writer = new FileWriter(file);
        writer.write(str);
    }
}

class MyThread1 extends Thread{
    @Override
    public void run() {
        System.out.println("创建线程的第一种方式。。。。。");
    }
}

class MyThread2 implements Runnable{
    @Override
    public void run() {
        System.out.println("创建线程的第二种方式---------");
    }
}
