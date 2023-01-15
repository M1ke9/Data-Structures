package Heap_Dynamic;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        MaxHeap maxHeap=new MaxHeap();


        // =====================================    INSERT  ========================================================


        RandomAccessFile FileToInsert=new RandomAccessFile("keys_1000000_BE.bin","r");
        long  Start=0;
        long End=0;
        boolean check=false;
        boolean Check=false;


        byte[] buffer=new byte[4];
        int ReadBytes=0;
        long pos = 0;

        while(ReadBytes>=0) {
            FileToInsert.seek(pos);
            ReadBytes = FileToInsert.read(buffer);
            //System.out.println("READBYTES:"+ReadBytes);

            ByteArrayInputStream bis = new ByteArrayInputStream(buffer);
            DataInputStream ois = new DataInputStream(bis);

            int x=ois.readInt();
             System.out.println(x);


            if(ReadBytes==-1) {
                break;
            }

            if(!check)
            {
                Start=System.nanoTime();
                check=true;
            }

            maxHeap.insert(x);

            if(pos==3999996) {
                End = System.nanoTime();
            }


            pos+=4;

        }
        FileToInsert.close();


        long TimeDuration=End-Start;

        System.out.println("TimeDuration  : "+TimeDuration+" ns");


        int CompNumber= (int) (maxHeap.NumOfComparisonsInsert/1000000);

        System.out.println("NumOfComparisons:"+CompNumber);






    }
}
