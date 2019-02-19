public class threadMatrix extends Thread {
    private int i, j;
    private int [][] matrixOne, matrixTwo, matrixThree;
    int product = 0;


    threadMatrix(int i, int j, int [][] matrixOne, int [][] matrixTwo, int [][] matrixThree)
    {
        this.i = i;
        this.j = j;
        this.matrixOne = matrixOne;
        this.matrixTwo = matrixTwo;
        this.matrixThree = matrixThree;
    }

    public void run ()
    {
        int n = matrixOne[0].length;
        for(int k = 0; k < n; k++)
        {
            product += (matrixOne[i][k] * matrixTwo[k][j]);
        }
        matrixThree[i][j] = product;

    }

    public static void main(String[] args) throws InterruptedException {


        /*int [][] matrixOne = {
                {3,7,6,6,6,3,3},
                {3,7,6,6,6,3,3},
                {3,7,6,6,6,3,3}

        };
        int [][] matrixTwo = {
                {3,7,2,5},
                {3,2,9,9},
                {3,2,9,3},
                {3,7,2,7},
                {3,2,9,3},
                {3,7,2,7},
                {3,7,2,7}

        };

        int n = matrixOne.length;
        int m = matrixOne[0].length;
        int k = matrixTwo[0].length; */

        int [][] matrixOne, matrixTwo, matrixThree;

        int n = 30;
        int m = 20;
        int k = 20;

        matrixOne = new int [m][n];
        matrixTwo = new int [n][k];
        matrixThree = new int [m][k];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrixOne[i][j] = (int)(Math.random()*5);
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < k; j++) {
                matrixTwo[i][j] = (int)(Math.random()*5);
            }
        }

        Thread [][] threads = new Thread[n][k];

        System.out.println("Matrix A\n");
        for (int i = 0; i < m; i++)
        {
            for(int j = 0; j < n; j++)
            {
                System.out.print(matrixOne[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("\n\n");


        System.out.println("Matrix B\n");
        for (int i = 0; i < n; i++)
        {
            for(int j = 0; j < k; j++)
            {
                System.out.print(matrixTwo[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("\n\n");


        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < k; j++)
            {
                threads[i][j]= new threadMatrix(i,j,matrixOne,matrixTwo,matrixThree);
                threads[i][j].start();
            }
        }


        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < k; j++)
            {
                threads[i][j].join();
            }
        }


        System.out.println("Matrix C\n");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < k; j++) {
                System.out.print(matrixThree[i][j] + "\t\t");
            }
            System.out.println();
        }
    }
}