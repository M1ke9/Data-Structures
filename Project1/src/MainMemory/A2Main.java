package MainMemory;

import java.util.Random;

public class A2Main {

    public static void main(String[] args) {

        Hash MainMemoryHash = new Hash();
        Random R = new Random();
        Point[] ArrayOfPoints;

        ArrayOfPoints = new Point[100];
        int Result = 0;

///////// K=1000

        for (int i = 0; i < 1000; i++) {
            Point X = new Point(R.nextInt(MainMemoryHash.N), R.nextInt(MainMemoryHash.N));
            MainMemoryHash.AddPoint(X);

            if (i <= 99)
                ArrayOfPoints[i] = X;
        }

        for (int i = 0; i < 100; i++) {
            Result += MainMemoryHash.Search(ArrayOfPoints[i]);
        }

        System.out.println("The Average for 1000 Points is:" + Result / 100);


        // CLeaning
        for (int i = 0; i < MainMemoryHash.M; i++) {
            MainMemoryHash.ArrayOfLISTS[i].getListOfPoints().clear();
            MainMemoryHash.ArrayOfLISTS[i].Tail = 0;
        }

        Result = 0;


        ////////////// K=10.000


        for (int i = 0; i < 10000; i++) {
            Point X = new Point(R.nextInt(MainMemoryHash.N), R.nextInt(MainMemoryHash.N));
            MainMemoryHash.AddPoint(X);

            if (i <= 99)
                ArrayOfPoints[i] = X;
        }

        for (int i = 0; i < 100; i++) {
            Result += MainMemoryHash.Search(ArrayOfPoints[i]);
        }

        System.out.println("The Average for 10.000 Points is:" + (double) Result / 100);


/////// K=30.000

/////// CLeaning
        for (int i = 0; i < MainMemoryHash.M; i++) {
            MainMemoryHash.ArrayOfLISTS[i].getListOfPoints().clear();
            MainMemoryHash.ArrayOfLISTS[i].Tail = 0;
        }

        Result = 0;

        for (int i = 0; i < 30000; i++) {
            Point X = new Point(R.nextInt(MainMemoryHash.N), R.nextInt(MainMemoryHash.N));
            MainMemoryHash.AddPoint(X);

            if (i <= 99)
                ArrayOfPoints[i] = X;
        }

        for (int i = 0; i < 100; i++) {
            Result += MainMemoryHash.Search(ArrayOfPoints[i]);
        }

        System.out.println("The Average for 30.000 Points is:" + (double) Result / 100);

///////////// CLEANING
        for (int i = 0; i < MainMemoryHash.M; i++) {
            MainMemoryHash.ArrayOfLISTS[i].getListOfPoints().clear();
            MainMemoryHash.ArrayOfLISTS[i].Tail = 0;
        }

        Result = 0;

///////////// K=50.000
        for (int i = 0; i < 50000; i++) {
            Point X = new Point(R.nextInt(MainMemoryHash.N), R.nextInt(MainMemoryHash.N));
            MainMemoryHash.AddPoint(X);

            if (i <= 99)
                ArrayOfPoints[i] = X;
        }

        for (int i = 0; i < 100; i++) {
            Result += MainMemoryHash.Search(ArrayOfPoints[i]);
        }

        System.out.println("The Average for 50.000 Points is:" + (double) Result / 100);



        ///////////// CLEANING
        for (int i = 0; i < MainMemoryHash.M; i++) {
            MainMemoryHash.ArrayOfLISTS[i].getListOfPoints().clear();
            MainMemoryHash.ArrayOfLISTS[i].Tail = 0;
        }

        Result = 0;

///////////// K=70.000
        for (int i = 0; i < 70000; i++) {
            Point X = new Point(R.nextInt(MainMemoryHash.N), R.nextInt(MainMemoryHash.N));
            MainMemoryHash.AddPoint(X);

            if (i <= 99)
                ArrayOfPoints[i] = X;
        }

        for (int i = 0; i < 100; i++) {
            Result += MainMemoryHash.Search(ArrayOfPoints[i]);
        }

        System.out.println("The Average for 70.000 Points is:" + (double) Result / 100);


        ///////////// CLEANING
        for (int i = 0; i < MainMemoryHash.M; i++) {
            MainMemoryHash.ArrayOfLISTS[i].getListOfPoints().clear();
            MainMemoryHash.ArrayOfLISTS[i].Tail = 0;
        }

        Result = 0;

///////////// K=100.000
        for (int i = 0; i < 100000; i++) {
            Point X = new Point(R.nextInt(MainMemoryHash.N), R.nextInt(MainMemoryHash.N));
            MainMemoryHash.AddPoint(X);

            if (i <= 99)
                ArrayOfPoints[i] = X;
        }

        for (int i = 0; i < 100; i++) {
            Result += MainMemoryHash.Search(ArrayOfPoints[i]);
        }

        System.out.println("The Average for 100.000 Points is:" + (double) Result / 100);





    }

    

}