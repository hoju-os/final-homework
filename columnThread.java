public class columnThread extends Thread
{
    int [] array;
    int max = 0;

    columnThread(int [] array)
    {
        super();
        this.array = array;
    }

    public void setMaximum(int [] array)
    {
        int maxValue = array[0];
        for(int i=1;i<array.length;i++)
        {
            if(array[i] > maxValue)
            {
                maxValue = array[i];
            }
        }
        max = maxValue;
    }

    @Override
    public void run() {
        setMaximum(array);
    }
}