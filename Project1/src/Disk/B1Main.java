package Disk;

import MainMemory.Point;

import java.io.IOException;
import java.util.Random;

public class B1Main {

    public static void main(String[] args) throws IOException {
        DListt DiskList=new DListt("B1File");
        Random R=new Random();
        Point[] ArrayOfPoints;
        ArrayOfPoints=new Point[100];
        int Result=0;


////////////////////////////////////   K=1000



        for(int i=0;i<1000;i++)
        {
            Point X=new Point(R.nextInt(DiskList.N),R.nextInt(DiskList.N));

            DiskList.AddPoint(X);
            if(i<=99)
            ArrayOfPoints[i]=X;
        }


        for(int i=0;i<100;i++)
        {
            Result+=DiskList.Search(ArrayOfPoints[i]);

        }

        System.out.println("The Average for 1000 Points:"+(double)Result/100);




        ////////////////////////////////////////    K=10.000

        for(int i=0;i<10000;i++)
        {
            Point X=new Point(R.nextInt(DiskList.N),R.nextInt(DiskList.N));

            DiskList.AddPoint(X);
            if(i<=99)
                ArrayOfPoints[i]=X;
        }


        for(int i=0;i<100;i++)
        {
            Result+=DiskList.Search(ArrayOfPoints[i]);


        }

        System.out.println("The Average for 10.000 Points:"+(double)Result/100);



        //////////////////////////////////////////////     K=30.000



        for(int i=0;i<30000;i++)
        {
            Point X=new Point(R.nextInt(DiskList.N),R.nextInt(DiskList.N));

            DiskList.AddPoint(X);
            if(i<=99)
                ArrayOfPoints[i]=X;
        }


        for(int i=0;i<100;i++)
        {
            Result+=DiskList.Search(ArrayOfPoints[i]);


        }

        System.out.println("The Average for 30.000 Points:"+(double)Result/100);



        ///////////////////////////////////////       K=50.000



        for(int i=0;i<50000;i++)
        {
            Point X=new Point(R.nextInt(DiskList.N),R.nextInt(DiskList.N));

            DiskList.AddPoint(X);
            if(i<=99)
                ArrayOfPoints[i]=X;
        }


        for(int i=0;i<100;i++)
        {
            Result+=DiskList.Search(ArrayOfPoints[i]);


        }

        System.out.println("The Average for 50.000 Points:"+(double)Result/100);



        ////////////////////////////////////     K=70.000



        for(int i=0;i<70000;i++)
        {
            Point X=new Point(R.nextInt(DiskList.N),R.nextInt(DiskList.N));

            DiskList.AddPoint(X);
            if(i<=99)
                ArrayOfPoints[i]=X;
        }


        for(int i=0;i<100;i++)
        {
            Result+=DiskList.Search(ArrayOfPoints[i]);


        }

        System.out.println("The Average for 70.000 Points:"+(double)Result/100);


        ////////////////////////////////////          K=100.000



        for(int i=0;i<100000;i++)
        {
            Point X=new Point(R.nextInt(DiskList.N),R.nextInt(DiskList.N));

            DiskList.AddPoint(X);
            if(i<=99)
                ArrayOfPoints[i]=X;
        }



        for(int i=0;i<100;i++)
        {
            Result+=DiskList.Search(ArrayOfPoints[i]);

        }

        System.out.println("The Average for 100.000 Points:"+(double)Result/100);


        DiskList.FileClose();

        

    }
}