package Disk;

import MainMemory.Point;
import jdk.jfr.Unsigned;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Random;

public class DiskHashList {


   public  final int M = 500;
    public  final int N = 262144;

    private static int NewDataPageSize = 256;


    DiskHash[] ArrayHash;

    PageList[] ArrayOfListsOfPages;
    int[] ArrayOfIntegers;
    RandomAccessFile MyFile;
   // byte[] WriteBuffer;
    int posWrite;
    int posForFirstPages;
    DataPage page;
    int posFile;
    int dataPage;
    int NumOfPages;
    Boolean CHECK;
    byte[] ReadBuffer;
    int CountOfPagesSearching;
    int Count;


    public DiskHashList(String filename) throws FileNotFoundException {

        page = new DataPage();
        this.ArrayHash = new DiskHash[M];
        this.ArrayOfListsOfPages = new PageList[M];
        this.ArrayOfIntegers=new int[M];

        for(int i=0;i<M;i++)
            ArrayOfIntegers[i]=0;

        for (int i = 0; i < M; i++) {
            ArrayOfListsOfPages[i] = new PageList();
        }

        this.posWrite = 0;
        posForFirstPages = 0;


        MyFile = new RandomAccessFile(filename, "rw");

        this.posFile = 0;
        dataPage = 0;
        this.NumOfPages = 0;
        CHECK=false;
        ReadBuffer=new byte[256];
        CountOfPagesSearching=0;
        Count=0;


    }


    public void PrintAllLists() {
        for (int i = 0; i < M; i++) {
            if (ArrayOfListsOfPages[i] != null) {
                ArrayOfListsOfPages[i].printPages();
            }
        }
    }


    //function returns 0 to exit.
    public int InsertPoint(Point A) throws IOException {

        int H=(int)(((long)A.getX()*N + A.getY())%M);


//INITIALIZATION(for every H in of DiskHash)
        if (ArrayHash[H] == null) {     //if position H is null(the beginning)

            ArrayHash[H] = new DiskHash();

            DataPage page=new DataPage();
            ArrayOfListsOfPages[H].ADDpage(page);
            page.setNumOfPoints(0);

            ArrayHash[H].setHead(dataPage);
            ArrayHash[H].setTail(ArrayHash[H].getHead() + NewDataPageSize);


        }


//CREATION OF PAGE[0] FOR SPECIFIC H.
        if (ArrayHash[H] != null && ArrayOfListsOfPages[H].NumOfPages == 0) // after the initialization,we create the first page for the specific position H.
        {
            if (ArrayOfListsOfPages[H].getPages().get(0).NumOfPoints == 31) {
                page.setNumOfPoints(30);
            }

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(bos);
            out.writeInt(A.getX());
            out.writeInt(A.getY());

            //out.close();

            byte[] buffer = bos.toByteArray();
            bos.close();

            if (ArrayOfListsOfPages[H].getPages().get(0).NumOfPoints < 1) {
                dataPage += 256;
                NumOfPages++;

            }


            if (ArrayOfListsOfPages[H].getPages().get(0).NumOfPoints < 31) {

                MyFile.seek((ArrayHash[H].getHead() +  ArrayOfListsOfPages[H].getPages().get(0).NumOfPoints* 8L +4));

                MyFile.write(buffer); // write data to file

                posForFirstPages += 8;

                ArrayOfListsOfPages[H].getPages().get(0).NumOfPoints++;

                MyFile.seek(ArrayHash[H].getHead());
                MyFile.writeInt(ArrayOfListsOfPages[H].getPages().get(0).NumOfPoints);


                if (ArrayOfListsOfPages[H].getPages().get(0).NumOfPoints == 31) {


                    ArrayOfListsOfPages[H].getPages().get(0).NextPage = (NumOfPages * NewDataPageSize);
                    MyFile.seek(ArrayHash[H].getHead() +  ArrayOfListsOfPages[H].getPages().get(0).NumOfPoints* 8L +4);
                    MyFile.writeInt(ArrayOfListsOfPages[H].getPages().get(0).NextPage);
                    ArrayHash[H].setTail((int) ArrayOfListsOfPages[H].getPages().get(0).NextPage);
                    ArrayOfListsOfPages[H].NumOfPages++;

                    return 0;

                }

            }

        }



//CREATION OF PAGE[1] FOR SPECIFIC H
        if (ArrayHash[H] != null && ArrayOfListsOfPages[H].NumOfPages == 1) {

            DataPage NewPage=new DataPage();

            ArrayOfListsOfPages[H].ADDpage(NewPage);


            if (ArrayOfListsOfPages[H].getPages().get(1).NumOfPoints == 0) {

                ArrayHash[H].setTail(NumOfPages * NewDataPageSize);

                ArrayOfListsOfPages[H].getPages().get(0).setNextPage(ArrayHash[H].getTail());
                MyFile.seek(ArrayHash[H].getHead() + 2 * 8L + 4);
                MyFile.writeInt(ArrayHash[H].getTail());

            }

            if (ArrayOfListsOfPages[H].getPages().get(ArrayOfListsOfPages[H].NumOfPages).NumOfPoints < 1) {
                dataPage += 256;
                NumOfPages++;

            }

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(bos);
            out.writeInt(A.getX());
            out.writeInt(A.getY());


            byte[] buffer = bos.toByteArray();
            bos.close();

            if (ArrayOfListsOfPages[H].getPages().get(ArrayOfListsOfPages[H].NumOfPages).NumOfPoints < 31) {

                MyFile.seek((ArrayHash[H].getTail() + ArrayOfListsOfPages[H].getPages().get(ArrayOfListsOfPages[H].NumOfPages).NumOfPoints * 8L + 4));
                MyFile.write(buffer); // write data to file


                DataPage.PosOnDisk = DataPage.PosOnDisk + 8;

                ArrayOfListsOfPages[H].getPages().get(ArrayOfListsOfPages[H].NumOfPages).NumOfPoints++;

                ByteArrayOutputStream Bos = new ByteArrayOutputStream();
                DataOutputStream Out= new DataOutputStream(Bos);
                Out.writeInt(ArrayOfListsOfPages[H].getPages().get(ArrayOfListsOfPages[H].NumOfPages).NumOfPoints);

                //out.close();

                byte[] Buffer = Bos.toByteArray();
                Bos.close();

                MyFile.seek(ArrayHash[H].getTail());
                MyFile.write(Buffer);

                if (ArrayOfListsOfPages[H].getPages().get(ArrayOfListsOfPages[H].NumOfPages).NumOfPoints == 31) {

                    ArrayOfListsOfPages[H].getPages().get(ArrayOfListsOfPages[H].getNumOfPages()).NextPage = NumOfPages * NewDataPageSize;

                    ByteArrayOutputStream Boss = new ByteArrayOutputStream();
                    DataOutputStream Outt = new DataOutputStream(Boss);
                    Outt.writeInt(ArrayOfListsOfPages[H].getPages().get(ArrayOfListsOfPages[H].getNumOfPages()).NextPage);

                    byte[] Bufferr = Boss.toByteArray();
                    MyFile.seek((ArrayHash[H].getTail() + ArrayOfListsOfPages[H].getPages().get(ArrayOfListsOfPages[H].NumOfPages).NumOfPoints * 8L + 4));
                    MyFile.write(Bufferr);


                    ArrayOfListsOfPages[H].NumOfPages++;

                    return 0;

                }
            }
        }


        //NEXT PAGES 2,3,4...ETC ,FOR SPECIFIC H
        else {

            if (ArrayOfListsOfPages[H].NumOfPages >= 2) {

                DataPage NewPAGE=new DataPage();

                ArrayOfListsOfPages[H].ADDpage(NewPAGE);


                if(ArrayOfListsOfPages[H].getPages().get(ArrayOfListsOfPages[H].NumOfPages).NumOfPoints==0) {

                    ArrayOfListsOfPages[H].getPages().get(ArrayOfListsOfPages[H].NumOfPages-1).setNextPage(NumOfPages * NewDataPageSize);
                    int NewTail=NumOfPages*NewDataPageSize;

                    MyFile.seek(ArrayHash[H].getTail() + 2 * 8L + 4);
                    MyFile.writeInt(NewTail);


                    ArrayHash[H].setTail(NumOfPages*NewDataPageSize);

                }

                if (ArrayOfListsOfPages[H].getPages().get(ArrayOfListsOfPages[H].NumOfPages).NumOfPoints < 1) {
                    dataPage += 256;
                    NumOfPages++;

                }



                ByteArrayOutputStream Boos = new ByteArrayOutputStream();
                DataOutputStream Ouut = new DataOutputStream(Boos);
                Ouut.writeInt(A.getX());
                Ouut.writeInt(A.getY());


                byte[] BUFFER = Boos.toByteArray();
                Boos.close();

                if (ArrayOfListsOfPages[H].getPages().get(ArrayOfListsOfPages[H].NumOfPages).NumOfPoints < 31) {




                    MyFile.seek((ArrayHash[H].getTail() +ArrayOfListsOfPages[H].getPages().get(ArrayOfListsOfPages[H].NumOfPages).NumOfPoints* 8L +4));
                    MyFile.write(BUFFER); // write data to file

                    ArrayOfListsOfPages[H].getPages().get(ArrayOfListsOfPages[H].NumOfPages).NumOfPoints++;
                    DataPage.PosOnDisk = DataPage.PosOnDisk + 8;

                    ByteArrayOutputStream Bos = new ByteArrayOutputStream();
                    DataOutputStream Out = new DataOutputStream(Bos);
                    Out.writeInt(ArrayOfListsOfPages[H].getPages().get(ArrayOfListsOfPages[H].NumOfPages).NumOfPoints);

                    //out.close();

                    byte[] buffer = Bos.toByteArray();
                    Bos.close();

                    MyFile.seek(ArrayHash[H].getTail());

                    MyFile.write(buffer);

                    //ArrayHash[H].setTail((int) DataPage.PosOnDisk);
                    if (ArrayOfListsOfPages[H].getPages().get(ArrayOfListsOfPages[H].NumOfPages).NumOfPoints == 31) {

                        ArrayOfListsOfPages[H].getPages().get(ArrayOfListsOfPages[H].NumOfPages).NextPage = NumOfPages * NewDataPageSize;

                        ByteArrayOutputStream Boss = new ByteArrayOutputStream();
                        DataOutputStream Outt = new DataOutputStream(Boss);
                        Outt.writeInt(ArrayOfListsOfPages[H].getPages().get(ArrayOfListsOfPages[H].getNumOfPages()).NextPage);

                        //Out.close();

                        byte[] Bufferr = Boss.toByteArray();
                        MyFile.seek(ArrayHash[H].getTail() + ArrayOfListsOfPages[H].getPages().get(ArrayOfListsOfPages[H].NumOfPages).NumOfPoints * 8L + 4);

                        ArrayOfListsOfPages[H].NumOfPages++;
                        return 0;
                    }
                }
            }


        }


        System.out.println();
        return 0;
    }



    public byte[] intstobytearray(int x) {
        byte[] res=new byte[4];
        ByteBuffer bx = ByteBuffer.allocate(4);
        bx.putInt(x);
        byte[] byx=bx.array();
        System.arraycopy(byx, 0, res, 0, 4);
        return res;
    }


    public int SearchPoint(Point A) throws IOException {

        int H=(int)(((long)A.getX()*N + A.getY())%M);
        int Top;

        if(ArrayOfListsOfPages[H].NumOfPages==0)
            Top=1;
        else
            Top=ArrayOfListsOfPages[H].NumOfPages;

        int posRead = ArrayHash[H].getHead();

        for (int i = 0; i < Top; i++) {

            {
                MyFile.seek(posRead);
                int ReadBytes = MyFile.read(ReadBuffer); // read the data from file

                try {

                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    DataOutputStream out = new DataOutputStream(bos);
                    out.writeInt(A.getX());
                    out.writeInt(A.getY());
                    System.out.println();

                    byte[] int_bytes = bos.toByteArray();
                    bos.close();

                    if (IndexOf(ReadBuffer, int_bytes)) {
                        CHECK = true;
                        System.out.println("POINT:" + "(" + A.getX() + "," + A.getY() + ")" + " FOUND!!!");
                        CountOfPagesSearching= ++Count;
                        return CountOfPagesSearching;
                    }


                    if (ReadBytes < 0) {
                        System.out.println("End Of File");
                        return -1;

                    }


                } catch (EOFException D) {
                    System.out.println("To stoixio den vrethike");
                    CHECK = true;
                    return -1;


                }

                Arrays.fill(ReadBuffer, (byte) 0);

                posRead = ArrayOfListsOfPages[H].getPages().get(i).NextPage;


                Count++;

            }


        }



                return CountOfPagesSearching;
            }



    public boolean IndexOf(byte[] AllBytes,byte[] searchByteArray){

        for(int i = 0 ; i < AllBytes.length; i = i + searchByteArray.length ){
            for(int j = 0 ; j < searchByteArray.length ; j++ ){
                if(AllBytes[i + j +4] != searchByteArray[j])
                    break;

                if(j == searchByteArray.length - 1)
                    return true;

            }
        }
        return false;
    }



    }