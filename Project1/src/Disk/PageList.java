package Disk;

import java.util.LinkedList;

public class PageList {

    LinkedList<DataPage> Pages;
    int NumOfPages;


    public PageList(){
        this.Pages=new LinkedList<>();
        this.NumOfPages=0;
    }


    public int ADDpage(DataPage page)
    {
        this.Pages.add(page);
        //NumOfPages++;

        return NumOfPages;
    }
    public void printPages(){

        for(int i=0;i<Pages.size();i++)
        {
            if(Pages.get(i)!=null)
            {
                System.out.println(Pages.get(i).getNumOfPoints());
            }
        }
    }

    public LinkedList<DataPage> getPages() {
        return Pages;
    }

    public int getNumOfPages() {
        return NumOfPages;
    }
}
