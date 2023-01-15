package MainMemory;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Hash {

    Listt[] ArrayOfLISTS;
    int[] ArrayOfPointers;

    final  int M=500;
    final int N=262144;


    public  Hash(){


        ArrayOfPointers=new int[M];

        for(int i=0;i<M;i++)
            ArrayOfPointers[i]=0;

        ArrayOfLISTS=new Listt[M];

        for(int i=0;i<M;i++)
            ArrayOfLISTS[i]=new Listt();


    }

    public void AddPoint(Point A) {
        int H=(int)(((long)A.getX()*N + A.getY())%M);

        //System.out.println("H=" + H);

      ArrayOfLISTS[H].AddPoint(A);
      ArrayOfPointers[H]++;


        }

        public int  Search(Point B)
        {
            boolean check=false;
            int CompNumber=0;
            int CompNumberFinal=0;
            for(int i=0;i<ArrayOfLISTS.length;i++) {
                if (ArrayOfLISTS[i].Head != ArrayOfLISTS[i].Tail)
                    for (int j = 0; j < ArrayOfLISTS[i].getListOfPoints().size(); j++) {
                        if(ArrayOfLISTS[i].ListOfPoints.get(j)!=null)
                            CompNumber+=1;


                        if (B.getX() == ArrayOfLISTS[i].getListOfPoints().get(j).getX() && B.getY() == ArrayOfLISTS[i].getListOfPoints().get(j).getY()) {
                            check = true;
                            CompNumberFinal=CompNumber;
                            break;


                        }
                    }
            }
                        if(check) {
                            System.out.println("Point:" + "("+B.getX()+","+B.getY()+")" +" Found , The  number of comparisons is: " + CompNumberFinal);
                            return CompNumberFinal;
                        }

                        else {
                            System.out.println("Point:" + "("+B.getX()+","+B.getY()+")" +" did not found");
                            return -1;

                        }

                }



    public void Print(){
        for(int i=0;i<ArrayOfLISTS.length;i++)
        {
            System.out.print("-"+i+":");
            ArrayOfLISTS[i].PrintList();
            System.out.println();
            System.out.print("Tail of List"+"("+i+")"+":"+ArrayOfPointers[i]);
            System.out.println();
        }


    }

}
