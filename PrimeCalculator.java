import java.util.ArrayList;

public class PrimeCalculator extends Thread{
    private int numOfFoundPrimeNumbers;
    private int start, end;
    private ArrayList<Integer> primeNumberList;

    PrimeCalculator(int start, int end)
    {
        super();
        numOfFoundPrimeNumbers = 0;
        this.start = start;
        this.end = end;
        primeNumberList = new ArrayList<Integer>();
    }
    public void run()
    {
        boolean isPrime;
        int j;

        System.out.println(Thread.currentThread().getName() + " [" + start + "," + end + "] started ...");
        for (int curNumber = start; curNumber <= end; curNumber++) {
            isPrime = true;
            if (curNumber == 1)
                isPrime = false;
            for (j = 2; j < curNumber; j++) {
                if (curNumber % j == 0)
                    isPrime = false;
            }
            if (isPrime) {
                numOfFoundPrimeNumbers++;
                primeNumberList.add(j);
            }
        }
        System.out.println("..." + Thread.currentThread().getName() + " [" + start + "," + end + "] completed. " + "Prime Numbers found: " + numOfFoundPrimeNumbers + " (" + getPrimeNumbers() + ")");
    }
    public int getNumOfPrimeNumbers()
    {
        return numOfFoundPrimeNumbers;
    }
    public String getPrimeNumbers()
    {
        String s = "";
        for (int i = 0; i < primeNumberList.size(); i++)
        {
            s += primeNumberList.get(i) + " ";
        }
        return s;
    }

    public static void main(String[] args) throws InterruptedException{

        int firstNumber = 1;
        int lastNumber = 20;
        int numOfThreads = 3;
        int numFoundPrimeNumbers = 0;

        System.out.println("first = " + firstNumber);
        System.out.println("last = " + lastNumber);
        System.out.println("numOfThreads = " + numOfThreads + "\n");


        int range = (int) (lastNumber - firstNumber + 1) / numOfThreads;

        PrimeCalculator threads[] = new PrimeCalculator[numOfThreads];

        int fromNumber = -1;
        int toNumber = -1;

        for (int i = 0; i < numOfThreads; i++)
        {
            fromNumber = i * range + firstNumber;
            toNumber = (i < numOfThreads-1)?(fromNumber + range-1):(lastNumber);
            threads[i] = new PrimeCalculator(fromNumber,toNumber);
        }
        for (int i = 0; i < numOfThreads; i++)
        {
            threads[i].start();
        }
        for (int i = 0; i < numOfThreads; i++)
        {
            threads[i].join();
        }
        for (int i = 0; i < numOfThreads; i++)
        {
            numFoundPrimeNumbers += threads[i].getNumOfPrimeNumbers();
        }
        System.out.println("\n\nTotal number of prime numbers found = " + numFoundPrimeNumbers);
    }
}
