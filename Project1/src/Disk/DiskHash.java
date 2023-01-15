package Disk;

public class DiskHash {

    private int Head;
    private int Tail;


    public DiskHash(){
        this.Head=-1;
        this.Tail=-1;
    }

    public int getHead() {
        return Head;
    }

    public void setHead(int head) {
        Head = head;
    }

    public int getTail() {
        return Tail;
    }

    public int setTail(int tail) {
        Tail = tail;
        return tail;
    }

    public void AddTail(){
        this.Tail++;
    }
}
