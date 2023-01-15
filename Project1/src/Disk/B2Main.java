package Disk;

import MainMemory.Point;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

public class B2Main {

    public static void main(String[] args) throws IOException {

        DiskHashList DiskHashh=new DiskHashList("B2File");
        Random R=new Random();
        Point[] ArrayOfPoints=new Point[100];
        int Result=0;
        /*
////////////////////////////////// K=1000
for(int i=0;i<1000;i++)
{
    Point X=new Point(R.nextInt(DiskHashh.N),R.nextInt(DiskHashh.N));

    DiskHashh.InsertPoint(X);

    if(i<=99)
        ArrayOfPoints[i]=X;


}

for(int i=0;i<100;i++)
{
    Result+=DiskHashh.SearchPoint(ArrayOfPoints[i]);
}

System.out.println("The average for 1000 points is:"+Result/100);


/////////////////////////////////////  K=10.000

        for(int i=0;i<10000;i++)
        {
            Point X=new Point(R.nextInt(DiskHashh.N),R.nextInt(DiskHashh.N));

            DiskHashh.InsertPoint(X);

            if(i<=99)
                ArrayOfPoints[i]=X;


        }

        for(int i=0;i<100;i++)
        {
            Result+=DiskHashh.SearchPoint(ArrayOfPoints[i]);
        }

        System.out.println("The average for 10.000 points is:"+Result/100);



//////////////////////////////////////// K=30.000
           for(int i=0;i<30000;i++)
        {
            Point X=new Point(R.nextInt(DiskHashh.N),R.nextInt(DiskHashh.N));

            DiskHashh.InsertPoint(X);

            if(i<=99)
                ArrayOfPoints[i]=X;


        }

        for(int i=0;i<100;i++)
        {
            System.out.println("return:"+DiskHashh.SearchPoint(ArrayOfPoints[i]));
        }

        System.out.println("The average for 30.000 points is:"+Result/100);



        //////////////////////////////////////// K=50.000
           for(int i=0;i<50000;i++)
        {
            Point X=new Point(R.nextInt(DiskHashh.N),R.nextInt(DiskHashh.N));

            DiskHashh.InsertPoint(X);

            if(i<=99)
                ArrayOfPoints[i]=X;


        }

        for(int i=0;i<100;i++)
        {
            System.out.println("return:"+DiskHashh.SearchPoint(ArrayOfPoints[i]));
        }

        System.out.println("The average for 50.000 points is:"+Result/100);




        //////////////////////////////////////// K=70.000
           for(int i=0;i<70000;i++)
        {
            Point X=new Point(R.nextInt(DiskHashh.N),R.nextInt(DiskHashh.N));

            DiskHashh.InsertPoint(X);

            if(i<=99)
                ArrayOfPoints[i]=X;


        }

        for(int i=0;i<100;i++)
        {
            System.out.println("return:"+DiskHashh.SearchPoint(ArrayOfPoints[i]));
        }

        System.out.println("The average for 70.000 points is:"+Result/100);







*/
///////////////////////////////// K=100.000
        for(int i=0;i<100000;i++)
        {
            Point X=new Point(R.nextInt(DiskHashh.N),R.nextInt(DiskHashh.N));

            DiskHashh.InsertPoint(X);

            if(i<=99)
                ArrayOfPoints[i]=X;


        }

        for(int i=0;i<100;i++)
        {

            Result+=DiskHashh.SearchPoint(ArrayOfPoints[i]);
            System.out.println("return:"+DiskHashh.SearchPoint(ArrayOfPoints[i]));
        }

        System.out.println("The average for 100.000 points is:"+Result/100);



    }




}