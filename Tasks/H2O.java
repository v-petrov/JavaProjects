package exercises;

import java.util.concurrent.*;

public class H2O {

    String water;
    CyclicBarrier barrier;

    public H2O(String water) {
        this.water = water;
        barrier = new CyclicBarrier(3);
    }


    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException{
        try {
            barrier.await();
            releaseHydrogen.run();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException{
        try {
            barrier.await();
            releaseOxygen.run();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        H2O h2o = new H2O("HOHHOHHOHHOHHOHHOHHOHHOHHOHHOH");
        String water = h2o.water;

        Runnable H = () -> {
            try {
                h2o.hydrogen(() -> System.out.print('H'));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        Runnable O = () -> {
            try {
                h2o.oxygen(() -> System.out.print('O'));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        ExecutorService service = Executors.newFixedThreadPool(3);

        for(int i = 1; i <= water.length(); i+=3) {
            service.submit(O);
            service.submit(H);
            service.submit(H);
        }

        service.shutdown();
    }
}
