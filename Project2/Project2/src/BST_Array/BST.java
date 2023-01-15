package BST_Array;


import java.util.List;

public class BST {
    public final int N = 10^6;
    int[] Info;
    int[] Right;
    int[] Left;
    int Root;
    int stackInit;
    int Avail;
    long NumOfComparisonsInsert;
    long NumOfComparisonsDelete;

int actualNodes;

    public BST(int N) {
        actualNodes=0;
        Info = new int[N];
       Left = new int[N];
        Right = new int[N];

        this.NumOfComparisonsInsert=0;
        this.NumOfComparisonsDelete=0;


        this.stackInit = 1;

        this.Root = -1;
        this.Avail=0;


        for (int i = 0; i < N; i++) {
            Info[i] = -1;
            Left[i] = -1;
            Right[i] = stackInit++;

        }

        Right[N - 1] = -1;

    }


    public void insert(int key)
    {
        int curr = Root;


        if(Root == -1)
        {
            Info[Avail] = key;
            Root = Avail;
            Avail = Right[Avail];
            Right[Root] = -1;

            return;
        }

        while(true)
        {

            NumOfComparisonsInsert++;
            //left insertion
            if(key < Info[curr])
            {

                if(Left[curr] == -1)
                {

                    int oldPos = Avail;

                    // update child reference.
                    Left[curr] = Avail;

                    //insert the new child.
                    Info[Avail] = key;

                    //get the next available pos.
                    Avail = Right[Avail];

                    //wipe the right children linkage of the child.
                    Right[oldPos] =  -1;


                    return;
                }

                // move left.
                curr = Left[curr];

            }
            else
            {

                // right insertion.
                if(Right[curr] == -1)
                {


                    //mark the creation index.
                    int oldPos = Avail;


                    // update child reference of father.
                    Right[curr] = Avail;

                    //insert the new child, children at the left side, wipe right children of this child coming from init().
                    Info[Avail] = key;


                    // get the next available position.
                    Avail = Right[Avail];


                    //wipe any child leftovers from init, in the new child.
                    Right[oldPos] = -1;

                    return;
                }
                // move right.
                curr = Right[curr];
            }
        }


    }



    public int search(int key)
    {

        int pos=0;

        while(pos != -1)
        {

            // key found
            if(Info[pos] == key)
                return pos;

            //left movement
            if(Info[pos] > key)
                pos = Left[pos];
            else
                pos = Right[pos];

        }

        System.out.println("Key:"+key +" Does not exists in the Binary Search Tree");
        return -1;
    }

    public int DeleteKey(int  root, int key)
    {
        /* Base Case: If the tree is empty */
        if (root == -1) {
            return root;
        }

        /* Otherwise, recur down the tree */

        if (key < Info[root]) {
            NumOfComparisonsDelete++;

            Left[root] = DeleteKey(Left[root], key);
        }
        else if (key > Info[root]) {
            NumOfComparisonsDelete+=2;
            Right[root] = DeleteKey(Right[root], key);


        }

            // if key is same as root's
            // key, then This is the
            // node to be deleted
        else {

            NumOfComparisonsDelete++;
            // node with only one child or no child
            if (Left[root] == -1) {
                int indexToReturn =Right[root];
                Info[root]=-1;
                Right[root]=Avail;
                this.Avail=root;
                return indexToReturn;
            }
            else if (Right[root] == -1) {
                int indexToReturn =Left[root];
                Info[root]=-1;
                Right[root]=Avail;
                this.Avail=root;
                return indexToReturn;
            }

            // node with two children: Get the inorder
            // successor (smallest in the right subtree)
            Info[root] = minValue(Right[root]);

            // Delete the inorder successor
            Right[root] = DeleteKey(Right[root], Info[root]);
        }

        return root;
    }

    int minValue(int root)
    {
        int minv = Info[root];
        while (Left[root] != -1)
        {
            //minv = root.left.key;
            minv=Info[Left[root]];
            root = Left[root];
        }
        return minv;
    }



    public void printTree() {
System.out.println("info "+ "left" + " "+ "right");
        for(int i=0;i<N;i++)
        {

            System.out.println(Info[i] + "  "+Left[i]+"  "+Right[i]);
        }

    }
}