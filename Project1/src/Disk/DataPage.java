package Disk;

import java.io.DataOutputStream;

public class DataPage {

    public static long PosOnDisk;
    public int NumOfPoints;
    public  int   NextPage;
    public byte[] PointsBuffer;

    public DataPage(){
        this.NumOfPoints=0;
        NextPage=0;
        PosOnDisk=0;

        PointsBuffer=new byte[16];
    }


    public long getPosOnDisk() {
        return PosOnDisk;
    }

    public int getNumOfPoints() {
        return NumOfPoints;
    }

    public long getNextPage() {
        return NextPage;
    }

    public byte[] getPointsBuffer() {
        return PointsBuffer;
    }

    public void setNumOfPoints(int numOfPoints) {
        NumOfPoints = numOfPoints;
    }

    public  void setNextPage(int nextPage) {
        NextPage = nextPage;
    }

    public void setPointsBuffer(byte[] pointsBuffer) {
        PointsBuffer = pointsBuffer;
    }
}