package Heap_Array;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Main {
    public static void main(String[] args) throws IOException {

        MaxHeap MaxHeap=new MaxHeap(1000001);

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


            if(ReadBytes==-1) {
                break;
            }

            if(!check)
            {
                Start=System.nanoTime();
                check=true;
            }

            MaxHeap.insert(x);

            if(pos==3999996) {
                End = System.nanoTime();
            }


            pos+=4;

        }
        FileToInsert.close();


        long TimeDuration=End-Start;

        System.out.println("TimeDuration  : "+TimeDuration+" ns");

        int CompNumber= (int) (MaxHeap.NumOfComparisonsInsert/1000000);

        System.out.println("NumOfComparisons:"+CompNumber);




        // =====================================    DELETE  ========================================================



        long begin=0;
        long finish=0;

  begin=System.nanoTime();
for(int i=0; i<100;i++)
    MaxHeap.extractMax();

finish = System.nanoTime();




        TimeDuration=finish-begin;

        System.out.println("TimeDuration  : "+TimeDuration +" ns");


        int NumCompDel= (int) (MaxHeap.NumOfComparisonsDelete/100);

        System.out.println("NumOfComparisonsDelete:"+NumCompDel);





        //=================================PRIORITY QUEUE (ALL ELEMENTS TOGETHER)=========================================

        long poss = 0;
        int ReadBytess=0;
        long TimeStart=0;
        long TimeFinish=0;
        byte[] Buffer=new byte[4];

        int[] ArrayOfIntegers=new int[1000000];

        RandomAccessFile File=new RandomAccessFile("keys_1000000_BE.bin","r");
        int i=0;

        while(i<=999999) {
            File.seek(poss);
            ReadBytess = File.read(Buffer);
            // System.out.println("READBYTES:"+ReadBytes);

            ByteArrayInputStream bis = new ByteArrayInputStream(Buffer);
            DataInputStream ois = new DataInputStream(bis);




            if(ReadBytess==-1) {
                break;
            }

            int x=ois.readInt();
            //System.out.println(x);

            ArrayOfIntegers[i]=x;

            i++;
            poss+=4;

        }


        File.close();

       TimeStart=System.nanoTime();
        MaxHeap.insertForQueueAll(ArrayOfIntegers);
        TimeFinish=System.nanoTime();



long TimeDurationn=TimeFinish-TimeStart;


        System.out.println("TimeDuration  : "+TimeDurationn +" ns");


      int CompNumm=MaxHeap.NumOfCompForAll/1000000;




/*


long Starts=0;
long Ends=0;

Starts=System.nanoTime();
        //PRIORITY QUEUE (1 ELEM PER TIME)
        for(int j=0;j<ArrayOfIntegers.length;j++)
        {
            MaxHeap.insertForQueue(ArrayOfIntegers[j]);
        }

        Ends=System.nanoTime();

        long TimeDur=Ends-Starts;

        System.out.println("Time duration for 1 elem per time:"+TimeDur+" ns");


 */




    }
}