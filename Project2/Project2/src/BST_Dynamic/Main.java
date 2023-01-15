package BST_Dynamic;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Main {

    public static void main(String[] args) throws IOException {

        BST TREE=new BST();



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
           // System.out.println(x);


            if(ReadBytes==-1) {
                break;
            }

            if(!check)
            {
                Start=System.nanoTime();
                check=true;
            }

            TREE.insert(x);

            if(pos==3999996) {
                End = System.nanoTime();
            }


            pos+=4;

        }
        FileToInsert.close();


        long TimeDuration=End-Start;

        System.out.println("TimeDuration  : "+TimeDuration+" ns");


        int CompNumber= (int) (TREE.NumOfComparisonsInsert/1000000);

        System.out.println("NumOfComparisonsInsert:"+CompNumber);





        // =====================================    DELETE  ========================================================



        long posDelete = 0;
        ReadBytes=0;
        long begin=0;
        long finish=0;

        RandomAccessFile FileToDelete=new RandomAccessFile("keys_del_100_BE.bin","r");

        while(ReadBytes>=0) {
            FileToDelete.seek(posDelete);
            ReadBytes = FileToDelete.read(buffer);
            // System.out.println("READBYTES:"+ReadBytes);

            ByteArrayInputStream bis = new ByteArrayInputStream(buffer);
            DataInputStream ois = new DataInputStream(bis);

            int x=ois.readInt();
            //System.out.println(x);


            if(ReadBytes==-1) {
                break;
            }

            if(!Check)
            {
                begin=System.nanoTime();
                Check=true;
            }

            TREE.deleteKey(x);
            finish = System.nanoTime();




            posDelete+=4;

        }


        FileToDelete.close();


        TimeDuration=finish-begin;

        System.out.println("TimeDuration  : "+TimeDuration +" ns");


        int NumCompDel= (int) (TREE.NumOfComparisonsDelete/100);

        System.out.println("NumOfComparisonsDelete:"+NumCompDel);






    }
}