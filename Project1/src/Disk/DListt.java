package Disk;

import MainMemory.Point;
import java.io.*;
import java.util.Arrays;


public class DListt {
    static int DataPageSize = 256;
    long posFile;
    int pos;
    byte[] WriteBuffer;
    byte[] ReadBuffer;
    Boolean check;
    RandomAccessFile MyFile;
    long posRead;
    int count;
    int CountOfPagesSearching;
    int ReadBytes;
    final int  N=262144 ;

    public DListt(String filename) throws FileNotFoundException, IOException {

        this.posFile = 0;
        this.pos = 0;
        WriteBuffer = new byte[8];
        ReadBuffer = new byte[256];
        check = false;
        posRead = 0;
        MyFile = new RandomAccessFile(filename, "rw");
        this.count = 0;
        this.CountOfPagesSearching = 0;
        this.ReadBytes=0;

    }

    public int AddPoint(Point A) throws IOException {

if(A.getX()>=0 && A.getX()<=N-1 && A.getY()>=0 && A.getY()<=N-1) {
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    DataOutputStream out = new DataOutputStream(bos);
    out.writeInt(A.getX());
    out.writeInt(A.getY());
    out.close();

    byte[] buffer = bos.toByteArray();

    System.arraycopy(buffer, 0, WriteBuffer, pos, buffer.length); // Copy buffer data to DataPage of DataPageSize


//With the following instructions(in comments) we can write the all the WriteBuffer to the disk(no a point at a time).
// if we write 256 bytes at the disk we should increase the posFile+=256,and reset pos to Zero(0).
    //pos += 8;
    //if (pos == 256)
    {

        MyFile.seek(posFile);
        MyFile.write(WriteBuffer); // write data to file

        posFile += 8;


        Arrays.fill(WriteBuffer, (byte) 0);
        //pos = 0;


    //}
    }

}
else
    return -1;

return 0;

    }



    public int Search(Point B) throws IOException {
        posRead=0;

        while(!check)
        {
            MyFile.seek(posRead);

            ReadBytes= MyFile.read(ReadBuffer); // read the data from file

            try {

                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                DataOutputStream out = new DataOutputStream(bos);
                out.writeInt(B.getX());
                out.writeInt(B.getY());


                byte[] int_bytes = bos.toByteArray();
                bos.close();


                if(IndexOf(ReadBuffer,int_bytes))
                {
                      check = true;
                      //count= ++CountOfPagesSearching;
                      CountOfPagesSearching= ++count;
                    System.out.println("Count:" + CountOfPagesSearching);
                      return CountOfPagesSearching;

                  }


                 if (ReadBytes < 0) {
                    System.out.println("End Of File");
                    return -1;

                }

            } catch (EOFException A) {
                System.out.println("To stoixio den vrethike");
                check = true;
                return -1;


            }

            Arrays.fill(ReadBuffer, (byte) 0); //Cleaning the ReadBuffer for the Next Page.

            posRead +=256;  // Increase the File pointer.

            count++;


        }


        return CountOfPagesSearching;
    }

//Checks if AllBytes Table contains SearchByteArray Table.
    public boolean IndexOf(byte[] AllBytes,byte[] searchByteArray){

        for(int i = 0 ; i < AllBytes.length; i = i + searchByteArray.length ){
            for(int j = 0 ; j < searchByteArray.length ; j++ ){
                if(AllBytes[i + j] != searchByteArray[j])
                    break;

                if(j == searchByteArray.length - 1)
                    return true;

            }
        }
        return false;
    }

    public void FileClose(){
        try{
            MyFile.close();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}