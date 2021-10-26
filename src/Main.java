import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Main {
        public static final int CARS_COUNT = 4;
        /*private static final ReadWriteLock rwLock = new ReentrantReadWriteLock();*/
        public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");

            CyclicBarrier cb = new CyclicBarrier(CARS_COUNT+1); //4 потока машин + main
            CountDownLatch cd = new CountDownLatch(CARS_COUNT);
            Semaphore semaphore = new Semaphore(CARS_COUNT/2);

            Race race = new Race(new Road(60, cd, CARS_COUNT ), new Tunnel(semaphore), new Road(40, cd, CARS_COUNT));

            Car[] cars = new Car[CARS_COUNT];
            for (int i = 0; i < cars.length; i++) {
                cars[i] = new Car(cb ,race, 20 + (int) (Math.random() * 10));
            }

            for (int i = 0; i < cars.length; i++) {
                new Thread(cars[i]).start();
            }
            cb.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");


            cd.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
        }
    }

