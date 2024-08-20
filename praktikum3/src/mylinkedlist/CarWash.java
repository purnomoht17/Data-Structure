package mylinkedlist;
//package mylinkedlist;
import java.util.Scanner;

class Car {
    private int arrivalTime;

    public Car(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }
}

class CarWash {
    private final String OVERFLOW = " (Overflow)\n";
    private final String HEADING = "\n\nWaktu\tKeterangan\t\tWaktu Tunggu\n";
    private static final int INFINITY = 10000; // indicates no car being washed
    private static final int MAX_SIZE = 7; // maximum cars allowed in carQueue
    private static final int WASH_TIME = 12; // minutes to wash one car
    private SingleList<Car> carQueue;
    private SingleList<String> results;
    private int currentTime;
    private int nextDepartureTime;
    private int numberOfCars;
    private int waitingTime;
    private int sumOfWaitingTimes;

    public CarWash() {
        carQueue = new SingleList<>();
        results = new SingleList<>();
        results.pushQ(HEADING);
        currentTime = 0;
        numberOfCars = 0;
        waitingTime = 0;
        sumOfWaitingTimes = 0;
        nextDepartureTime = INFINITY; // no car being washed
    }

    protected int size(SingleList<Car> list) {
        int count = 0;
        Node<Car> current = list.head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    public SingleList<String> process(int nextArrivalTime) {
        final String BAD_TIME = "Waktu kedatangan berikutnya tidak boleh kurang dari waktu saat ini.";
        if (nextArrivalTime < currentTime)
            throw new IllegalArgumentException(BAD_TIME);
        while (nextArrivalTime >= nextDepartureTime)
            processDeparture();
        return processArrival(nextArrivalTime);
    }

    protected SingleList<String> processArrival(int nextArrivalTime) {
        final String ARRIVAL = "\tKedatangan";
        currentTime = nextArrivalTime;
        results.pushQ(Integer.toString(currentTime) + ARRIVAL);
        if (size(carQueue) == MAX_SIZE) // Menggunakan metode size yang baru dibuat
            results.pushQ(OVERFLOW);
        else {
            numberOfCars++;
            if (nextDepartureTime == INFINITY) // if no car is being washed
                nextDepartureTime = currentTime + WASH_TIME;
            else
                carQueue.pushQ(new Car(nextArrivalTime));
            results.pushQ("\n");
        }
        return results;
    }

    protected SingleList<String> processDeparture() {
        final String DEPARTURE = "\tKeberangkatan\t\t";
        int arrivalTime;
        currentTime = nextDepartureTime;
        results.pushQ(Integer.toString(currentTime) + DEPARTURE + Integer.toString(waitingTime) + "\n");
        if (!carQueue.isEmpty()) {
            Car car = carQueue.remove();
            arrivalTime = car.getArrivalTime();
            waitingTime = currentTime - arrivalTime;
            sumOfWaitingTimes += waitingTime;
            nextDepartureTime = currentTime + WASH_TIME;
        } else {
            waitingTime = 0;
            nextDepartureTime = INFINITY; // no car is being washed
        }
        return results;
    }

    public SingleList<String> finishUp() {
        while (nextDepartureTime < INFINITY) // while there are unwashed cars
            processDeparture();
        return results;
    }

    public SingleList<String> getResults() {
        final String NO_CARS_MESSAGE = "Tidak ada mobil yang dicuci\n";
        final String AVERAGE_WAITING_TIME_MESSAGE = "\nRata rata waktu tunggu dalam menit :  ";
        final String TOTAL_WAITING_TIME_MESSAGE = "\nTotal waktu tunggu : ";
        if (numberOfCars == 0)
            results.pushQ(NO_CARS_MESSAGE);
        else {
            results.pushQ(TOTAL_WAITING_TIME_MESSAGE + sumOfWaitingTimes);
            results.pushQ(AVERAGE_WAITING_TIME_MESSAGE + Double.toString((double) sumOfWaitingTimes / numberOfCars));
        }
        return results;
    }
}

class CarWashUser {
    private CarWash carWash;

    public CarWashUser() {
        carWash = new CarWash();
    }

    public void run() {
        final int SENTINEL = 999;
        final String INPUT_PROMPT = "\nMasukkan waktu kedatangan selanjutnya(masukkan " + SENTINEL + " untuk keluar): ";
        final String OUT_OF_RANGE = "Masukan harus terdiri dari bilangan bulat non-negatif yang kurang dari sentinel.";
        Scanner sc = new Scanner(System.in);
        int nextArrivalTime;
        while (true) {
            System.out.print(INPUT_PROMPT);
            try {
                nextArrivalTime = sc.nextInt();
                if (nextArrivalTime == SENTINEL)
                    break;
                if (nextArrivalTime < 0 || nextArrivalTime > SENTINEL)
                    throw new NumberFormatException(OUT_OF_RANGE);
                carWash.process(nextArrivalTime);
            } catch (Exception e) {
                System.out.println(e);
                sc.nextLine();
            }
        }
        sc.close();
        carWash.finishUp();
        printResults();
    }

    public void printResults() {
        final String RESULTS_HEADING = "\nBerikut hasil simulasinya:\n";
        SingleList<String> results = carWash.getResults();
        System.out.println(RESULTS_HEADING);
        Node<String> current = results.head; // Mengakses head dari SingleList
        while (current != null) { // Melintasi semua elemen dalam SingleList
            System.out.print(current.data); // Mencetak data dari setiap node
            current = current.next; // Pindah ke node selanjutnya
        }
    }
}

class Main {
    public static void main(String[] args) {
        CarWashUser user = new CarWashUser();
        user.run();
    }
}
