import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Car implements Runnable {
    private static int CARS_COUNT;
    private Race race;
    private int speed;
    private String name;
    CyclicBarrier cyclicBarrier;
    private boolean lastLap;


    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(CyclicBarrier cb ,Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
        cyclicBarrier= cb;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));

            System.out.println(this.name + " готов");
            cyclicBarrier.await();

        } catch (Exception e) {
            e.printStackTrace();
        }


        //для членов листа от 0 до размера листа выполняем получение объекта из листа и метод go для него
        for (int i = 0; i < race.getStages().size(); i++) {
            //если последний этап, то взводим флаг последнего этапа
            if (i == race.getStages().size()-1) {
                lastLap = true;
            }
            race.getStages().get(i).go(this, lastLap);


        }
    }
}
