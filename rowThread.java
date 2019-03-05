import java.util.ArrayList;

public class rowThread extends Thread
{
    int[] array;
    int minimum = 0;
    ArrayList<Integer> minIndex = new ArrayList<>();

    rowThread(int [] array)
    {
        super();
        this.array = array;

    }
    public void run ()
    {
        setMinValue(array);
        for(int i = 0; i < array.length; i++)
        {
            if(array[i] == minimum)
            {
                minIndex.add(i);
            }
        }
    }

    public void setMinValue(int[] numbers)
    {
        int minValue = numbers[0];
        for(int i=1;i<numbers.length;i++)
        {
            if(numbers[i] < minValue)
            {
                minValue = numbers[i];
            }
        }
        minimum = minValue;
    };

    public static void printMatrix(int[][] matrix)
    {
        for(int i = 0; i < matrix.length; i++)
        {
            int[] row = matrix[i];
            for (int j = 0; j < row.length; j++)
            {
                System.out.print(row[j] + "\t");
            }
            System.out.println("\n");
        }
    }


    public static void main(String[] args) throws InterruptedException
    {
        int indexRow = 0;
        int indexColumn = 0;
        int saddlePoint=0;
        boolean hasSaddlePoint = false;

        /*
        int n = 4;
        int m = 4;
        int[][] matrix = new int[n][m];

        for (int i=0; i<matrix.length; i++)
        {
            for (int j=0; j<matrix[i].length; j++)
            {
                matrix[i][j] = (int) (Math.random()*10);
            }
        }
        */

        int [][] matrix = new int [][]  {   {3,7,2,3,2},
                                            {3,2,9,2,2},
                                            {6,5,6,4,8},
                                            {5,2,2,3,2}
                                        };


        rowThread threadRows [] = new rowThread[matrix.length];
        columnThread threadColumnThreads[] = new columnThread[matrix[0].length];

        System.out.println("\nMatrix M\n");
        printMatrix(matrix);

        for(int i = 0; i < matrix.length; i++)
        {
            int [] array = matrix[i];
            threadRows[i] = new rowThread(array);
        }

        for(int i = 0; i < matrix[0].length; i++)
        {
            int [] array = new int[matrix.length];
            for (int j = 0; j < matrix.length; j++)
            {
                array[j] = matrix[j][i];
            }
            threadColumnThreads[i] = new columnThread(array);
        }

        for(int i = 0; i < matrix.length; i++)
        {
            threadRows[i].start();
        }

        for(int i = 0; i < matrix.length; i++)
        {
            threadRows[i].join();
        }

        for(int i = 0; i < matrix[0].length; i++)
        {
            threadColumnThreads[i].start();
        }

        for(int i = 0; i < matrix[0].length; i++)
        {
            threadColumnThreads[i].join();
        }

        for (int i = 0; i < matrix.length; i++)
        {
            for (int j = 0; j < threadRows[i].minIndex.size(); j++)
            {
                int index = threadRows[i].minIndex.get(j);
                if (threadRows[i].minimum == threadColumnThreads[index].max)
                {
                    hasSaddlePoint = true;
                    //saddlePoint = threadRows[i].minimum;
                    saddlePoint = matrix[i][index];
                    indexRow = i;
                    indexColumn = index;
                }
            }
        }

        if (hasSaddlePoint)
            System.out.println("The saddle point is in M[" + indexRow  + "," + indexColumn + "] = " + saddlePoint);
        else
            System.out.println("There is no saddle point");

    }
}
