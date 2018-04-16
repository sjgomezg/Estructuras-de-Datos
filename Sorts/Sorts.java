
import java.io.*;
import java.util.Random;
import java.util.Calendar;

public class Sorts {
    
    //Ordenamiento de Burbuja
    public void bubbleSort(int[] unsorted)
    {   
        Calendar  ahora = Calendar.getInstance();
        double time1 = ahora.getTimeInMillis(), time2, total;
        boolean swap = false;
        int temp;
        
        do
        {
          swap = false;
          for(int i = 0; i<(unsorted.length-1);i++)
            if (unsorted[i] > unsorted[i+1])
            {
                temp = unsorted[i];
                unsorted[i] = unsorted[i+1];
                unsorted[i + 1] = temp;
                swap = true;
            }                     
        } 
        while (swap);
        time2 = ahora.getTimeInMillis();
        System.out.print(time1 + "\n");
        total = time2 - time1;
        System.out.print("Tardo: " + total + "\n");
    }
    
    //Ordenamiento por Insersion 
    public void insertionSort(int[] unsorted)
    {
        Calendar  ahora = Calendar.getInstance();
        double time1 = ahora.getTimeInMillis(), time2, total;
        int temp, indexHole;
        
        for(int i = 0;i<unsorted.length;i++)
        {
            temp = unsorted[i];
            indexHole= i;
            while (indexHole > 0 && unsorted[indexHole-1] > temp)
            {
                unsorted[indexHole] = unsorted[indexHole-1];
                indexHole -=1;
            }
            unsorted[indexHole] = temp;
        }
        
        time2 = ahora.getTimeInMillis();
        System.out.print(time1 + "\n");
        total = time2 - time1;
        System.out.print("Tardo: " + total + "\n");
    }
    
    //imprime el array
    public void printArray(int[] array) throws IOException
    {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int i = 0; i<array.length;i++)
        {
            bw.write(array[i] + " ");
        }
        bw.write("\n");
        bw.flush();
    }
    
    //Ordenamiento por mezcla
    public int[] mergeSort(int[] unsorted)
    {
        int size = 0;
        if(unsorted.length > 1)
        {
            size = unsorted.length/2;
            int[] left_array = new int[size];
            int[] right_array = new int[unsorted.length - size];
            for(int i = 0; i < size;i++)
                left_array[i] = unsorted[i];
            for(int i = size;i < unsorted.length;i++)
            {
                right_array[i-size]=unsorted[i];
            }
            left_array = mergeSort(left_array);
            right_array = mergeSort(right_array);
            
            return merge(left_array, right_array);
        }
        return unsorted;
    }
    
    // Funcion de mezcla para el mergesort
    public int[] merge(int[] a, int[] b)
    {
        int size = a.length + b.length;
        int[] c = new int[size];
        int index_a = 0, index_b = 0, index_c = 0;
        
        
        while(index_a < a.length && index_b < b.length)
        {
           if(a[index_a]< b[index_b])
           {
               c[index_c] = a[index_a];
               index_a += 1;
           }
           else
           {
               c[index_c] = b[index_b];
               index_b += 1;
           }
           index_c += 1;
           
           while(index_a < a.length)
           {
               c[index_c] = a[index_a];
               index_a += 1;
               index_c += 1;
           }
           
           while(index_b < b.length)
           {
               c[index_c] = b[index_b];
               index_b += 1;
               index_c += 1;
           }
        }
        return c;
    }
    
    // Generacion de un array
    public  int[] genArray(int n)
    {
        Random rd = new Random();
        int[] r = new int[n];
        for(int i = 0;i<n;i++)
            r[i] = rd.nextInt(n);
        return r;
    }
    
    //Ordenamiento por conteo
    public void countingSort(int[] arr)
    {
        Calendar  ahora = Calendar.getInstance();
        double time1 = ahora.getTimeInMillis(), time2, total;
        int max = Integer.MIN_VALUE;
        
        for(int i=0; i<arr.length; i++)
            if(max<arr[i]) max = arr[i];
        
        int[] aux = new int[max + 1];
        
        for(int i=0;i<arr.length;i++)
            aux[arr[i]] += 1;
        
        for(int i=0,index=0;i<aux.length;i++)
            for(int j=0;j<aux[i];j++)
                {
                    arr[index] = i;
                    index++;
                }
        time2 = ahora.getTimeInMillis();
        System.out.print(time1 + "\n");
        total = time2 - time1;
        System.out.print("Tardo: " + total + "\n");
    }
    
    //Busqueda Binaria
    public int binarySearch(int[] arr,int x)
    {
        int lowerBound = 0;
        int upperBound = arr.length -1;
        int middlePoint = 0;
        int index = -1;
        
        while(lowerBound < upperBound)
        {
            middlePoint = (lowerBound + upperBound) /2;
            if(x == arr[middlePoint])
            {
                index = middlePoint;
                break;
            }
            else if(x<arr[middlePoint])
                upperBound = middlePoint -1;
            else
                lowerBound = middlePoint +1;
        }
        
        if(lowerBound == upperBound && arr[lowerBound] == x)
            index = lowerBound;
        return index;
    }
    
    //Busqueda Binaria Recursiva
    public int binarySearchR(int[] arr, int x, int lB,int uB)
    {
        int middlePoint = (lB + uB)/2;
        if(lB == uB)
        {
            if(x==arr[middlePoint])
                return middlePoint;
            else
                return -1;
        }
        else if(x==arr[middlePoint])
            return middlePoint;
        else
        {
            if(x<arr[middlePoint])
                return binarySearchR(arr,x,lB,middlePoint);
            else
                return binarySearchR(arr,x,middlePoint + 1,uB);
        }
            
    }
    
    //Busqueda por interpolacion
    public int interpolationSearch(int[] arr, int n)
    {
        int lowerBound = 0;
        int upperBound = arr.length-1;
        int middlePoint;
        int index = -1;
        while(lowerBound < upperBound)
        {
            middlePoint = lowerBound + ((upperBound-lowerBound)/(arr[upperBound]-arr[lowerBound]))*(n - arr[lowerBound]);
            if(n==arr[middlePoint])
            {
                index = middlePoint;
                break;
            }
            else if(n<arr[middlePoint])
            {
                upperBound = middlePoint;
            }
            else
            {
                lowerBound = middlePoint + 1;
            }
                    
        }
        if(lowerBound == upperBound && arr[lowerBound]==n)
            index = lowerBound;
        return index;
    }
    public static int[] fibo(int n)
    {
        int[] fb = new int[n];
        fb[0] = 1;
        fb[1] = 1;
        for(int i = 2;i<n;i++)
            fb[i] =   fb[i-1] + fb[i-2];
        return fb;                
    }
    
    
    
    public static void main(String[] args) throws IOException
    {
        Calendar  ahora = Calendar.getInstance();
        long time1 = ahora.getTimeInMillis(), time2, total;
        Sorts sorts = new Sorts();
        int[] a = sorts.genArray(100);
        int[] b = a.clone();
        int[] c = b.clone();
        int[] d = c.clone();
        int[] e = fibo(10);
        
        sorts.printArray(b);
        sorts.bubbleSort(b);
        sorts.printArray(b);
        System.out.print("\n");
        
        System.out.print(sorts.binarySearch(b, 5) + "\n");
        System.out.print(sorts.binarySearchR(b, 5, 0, b.length) + "\n");
        sorts.printArray(e);
        System.out.print(sorts.interpolationSearch(b, 5) + "\n");
        
        sorts.printArray(a);
        sorts.insertionSort(a);
        sorts.printArray(a);
        System.out.print("\n");
               
        sorts.printArray(c);
        c = sorts.mergeSort(c);
        sorts.printArray(c);
        System.out.print("\n");
        
        sorts.printArray(d);
        sorts.countingSort(d);
        sorts.printArray(d);
        System.out.print("\n");
        
        time2 = ahora.getTimeInMillis();
        System.out.print(time1 + "\n");
        total = time2 - time1;
        System.out.print("Tardo: ");
        System.out.print(total);
    }
}
