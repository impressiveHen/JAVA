// Java program to illustrate need 
// of Synchronization 
import java.io.*;
class Count {
    public Integer i = 0;
    public synchronized void increment() {
        i++;
        try {
            Thread.sleep(400);
            System.out.println(i);
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}

class Train extends Thread {
    Count c;
    Train (Count c) {
        this.c = c;
    }
    @Override
    public void run() {
        c.increment();
    }
}

class MyClass {
    public static void main (String[] args) {
        Count c = new Count();
        Train t1 = new Train(c);
        Train t2 = new Train(c);
        t1.start();
        t2.start();
    }
} 