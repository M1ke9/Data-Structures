package MainMemory;

import java.util.LinkedList;

public class Listt {
    LinkedList<Point> ListOfPoints;
    int Head;
    int Tail;
    final int  N=262144 ;

    public Listt() {

        this.ListOfPoints = new LinkedList<>();

        this.Head=0;
        this.Tail=0;

    }


    public int  AddPoint(Point A){

        if(ListOfPoints.contains(A))
            return -1;

        if( A.getX()>=0 && A.getX()<=N-1 && A.getY()>=0 && A.getY()<=N-1) {
            this.ListOfPoints.add(Tail, A);
            Tail++;
        }

        else
            return -1;


return 0;
    }
    //Searches for a specific point in the List
    // returns the number of comparisons or -1 on error

    public int  Search(Point B){
        int i=0;
        int CompNumber = 0;
        Boolean check=false;
        for( i=0;i<ListOfPoints.size();i++) {
            if (B.getX() == ListOfPoints.get(i).getX() && B.getY() == ListOfPoints.get(i).getY()) {
                check = true;
                CompNumber=i+1;
                break;
            }

        }

        if(check) {
            System.out.println("MainMemory.Point:" + "("+B.getX()+","+B.getY()+")" +" Found , The  number of comparisons is: " + CompNumber);
            return i;
        }

        else {
            System.out.println("MainMemory.Point:" + "("+B.getX()+","+B.getY()+")" +" did not found");
            return -1;

        }

    }

    public void PrintList() {
        int i=0;
        for ( i = 0; i < ListOfPoints.size(); i++)
            System.out.print( "("+ListOfPoints.get(i).getX()+","+ListOfPoints.get(i).getY()+")"+" ");


    }

    public LinkedList<Point> getListOfPoints() {
        return ListOfPoints;
    }
}