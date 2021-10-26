import java.util.concurrent.CountDownLatch;

public class Road extends Stage {
    private static CountDownLatch cdl;
    private static int CARS_COUNT;

    public Road(int length, CountDownLatch cdl, int CARS_COUNT) {
        this.length = length;
        this.description = "Дорога " + length + " метров";
        this.cdl = cdl;
        this.CARS_COUNT = CARS_COUNT;
    }

    @Override
    public void go(Car c, boolean lastLap) {
        try {
            System.out.println(c.getName() + " начал этап: " + description);
            Thread.sleep(length / c.getSpeed() * 1000);
            System.out.println(c.getName() + " закончил этап: " + description);
            if (lastLap) {
                cdl.countDown();
                if (cdl.getCount() == (CARS_COUNT - 1)) {
                    System.out.println(c.getName() + " ПОБЕДИТЕЛЬ!!! ");
                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
