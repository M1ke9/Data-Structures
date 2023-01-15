package Heap_Array;

public class MaxHeap {

        int[] H;
        public int[] Heap;
        private int size;
        private int maxsize;
        private  int Size;
        int NumOfComparisonsInsert;
        int NumOfComparisonsDelete;
        int NumOfCompForAll;


        // Constructor to initialize an
        // empty max heap with given maximum
        // capacity
        public MaxHeap(int maxsize)
        {
            NumOfCompForAll=0;
            NumOfComparisonsDelete=0;
            NumOfComparisonsInsert=0;
            this.Size=0;
            // This keyword refers to current instance itself
            this.maxsize = maxsize;
            this.size = 0;
            Heap = new int[this.maxsize];
            H=new int[this.maxsize];
        }

    public void createHeap(int [] arrA){
        if(arrA.length>0){
            for(int i=0;i<arrA.length;i++){
                insert(arrA[i]);
            }
        }
    }

        // Method 1
        // Returning position of parent
       public static int parent(int pos) { return (pos - 1) / 2; }

        // Method 2
        // Returning left children
        public  int leftChild(int pos) { return (2 * pos) + 1; }

        // Method 3
        // Returning right children
        private int rightChild(int pos){ return (2 * pos) + 2; }

        // Method 4
        // Returning true of given node is leaf
        private boolean isLeaf(int pos)
        {
            if (pos > (size / 2) && pos <= size) {
                return true;
            }
            return false;
        }

        // Method 5
        // Swapping nodes
        private void swap(int fpos, int spos)
        {
            int tmp;
            tmp = Heap[fpos];
            Heap[fpos] = Heap[spos];
            Heap[spos] = tmp;
        }

        // Method 6
        // Recursive function to max heapify given subtree
        private void maxHeapify(int pos)
        {
            if (isLeaf(pos)) {

                return;
            }

            if (Heap[pos] < Heap[leftChild(pos)]
                    || Heap[pos] < Heap[rightChild(pos)]) {
                NumOfComparisonsDelete++;

                if (Heap[leftChild(pos)]
                        > Heap[rightChild(pos)]) {
                    NumOfComparisonsDelete++;
                    swap(pos, leftChild(pos));
                    maxHeapify(leftChild(pos));
                }
                else {
                    NumOfComparisonsDelete++;
                    swap(pos, rightChild(pos));
                    maxHeapify(rightChild(pos));
                }
            }
        }

        // Method 7
        // Inserts a new element to max heap
        public void insert(int element)
        {
            Heap[size] = element;

            // Traverse up and fix violated property
            int current = size;
            while (Heap[current] > Heap[parent(current)]) {
                NumOfComparisonsInsert++;
                swap(current, parent(current));
                current = parent(current);
            }
            size++;
        }

        // Method 8
        // To display heap
        public void print()
        {

            for(int i=0;i<size/2;i++){

                System.out.print("Parent Node : " + Heap[i] );

                if(leftChild(i)<size) //if the child is out of the bound of the array
                    System.out.print( " Left Child Node: " + Heap[leftChild(i)]);

                if(rightChild(i)<size) //if the right child index must not be out of the index of the array
                    System.out.print(" Right Child Node: "+ Heap[rightChild(i)]);

                System.out.println(); //for new line

            }

        }

        // Method 9
        // Remove an element from max heap
        public int extractMax()
        {
            int popped = Heap[0];
            if(size>=1)
            Heap[0] = Heap[size--];
            maxHeapify(0);
            return popped;
        }

//======================================    PRIORITY QUEUE( 1 ELEM PER TIME)==================================

   public  void insertForQueue(int p)
    {
        H[Size] = p;
        Size = Size + 1;

        // Shift Up to maintain
        // heap property
        shiftUp(Size);
    }

    public  void shiftUp(int i)
    {
        while (i > 0 &&
                H[parent(i)] < H[i])
        {
            NumOfCompForAll++;
            // Swap parent and current node
            swapQ(parent(i), i);

            // Update i to parent of i
            i = parent(i);
        }
    }

    public void swapQ(int i, int j)
    {
        int temp= H[i];
        H[i] = H[j];
        H[j] = temp;
    }


    // =================================   PRIORITY QUEUE(ALL ELEMENTS TOGETHER)==================================

    public  void insertForQueueAll(int[] p)
    {
        for (int i=0;i<p.length;i++) {
            H[size] = p[i];
            if(size==999999)
                break;

            size = size + 1;
            // Shift Up to maintain
            // heap property
            shiftUp(size);
        }

    }



    public void PrintQueue(){

        int i = 0;

        // Priority queue before extracting max
        System.out.print("Priority Queue : ");
        while (i <= size)
        {
            System.out.print(H[i] + " ");
            i++;
        }

    }




    }