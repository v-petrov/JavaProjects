package exercises;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FooBar {

    private final int n;
    private int ff = 0;

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException{
        for(int i = 0; i < this.n; i++) {
            ff = 1;
            synchronized (this) {
                printFoo.run();
                this.notify();
                this.wait();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException{
        for(int i = 0; i < this.n; i++) {
            synchronized (this) {
                if(ff == 0) {
                    this.wait();
                }
                printBar.run();
                this.notify();
                this.wait();
            }
        }
    }

    public static void main(String... args){
        ExecutorService service = Executors.newFixedThreadPool(2);

        FooBar o = new FooBar(6);

        Runnable foo = () -> System.out.print("foo");
        Runnable A = () -> {
            try {
                o.foo(foo);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Runnable bar = () -> System.out.println("bar");
        Runnable B = () -> {
            try {
                o.bar(bar);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        service.submit(A);
        service.submit(B);

        service.shutdown();

    }
}
