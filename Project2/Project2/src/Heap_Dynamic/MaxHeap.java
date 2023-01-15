package Heap_Dynamic;


public class MaxHeap {
    int size;

    Node root;
    int NumOfComparisonsInsert;

    public MaxHeap() {
        root = null;
        this.size = 0;
        NumOfComparisonsInsert=0;

    }

    //Get height of insert new node
    public int Height() {
        int i = 1;

        int sum = 0;

        while (this.size > sum + (1 << i)) {
            sum += (1 << i);
            i++;
        }
        return i;
    }


    public void swapNode(Node first, Node second) {
        int key = first.key;

        first.key = second.key;
        second.key = key;
    }

    public void arrangeNode(Node root) {

        if (root.left != null && root.left.key > root.key) {
            swapNode(root, root.left);
        }
        if (root.right != null && root.right.key > root.key) {
            swapNode(root, root.right);
        }
    }

    public boolean addNode(Node root, int height, int level, int key) {
        if (level >= height) {
            return false;
        }
        if (root != null) {

            if (level - 1 == height && root.left == null || root.right == null) {
                if (root.left == null) {
                    root.left = new Node(key);
                } else {
                    root.right = new Node(key);
                }

                arrangeNode(root);

                return true;
            }

            if (addNode(root.left, height, level + 1, key) || addNode(root.right, height, level + 1, key)) {
                //Check effect of new inserted node
                arrangeNode(root);

                return true;
            }


        }
        return false;
    }

    public void insert(int key) {
        //Test case
        if (root == null) {
            NumOfComparisonsInsert++;
            root = new Node(key);
        } else if (root.left == null) {
            NumOfComparisonsInsert++;
            root.left = new Node(key);
            arrangeNode(root);

        } else if (root.right == null) {
            NumOfComparisonsInsert++;
            root.right = new Node(key);
            arrangeNode(root);
        } else {
            NumOfComparisonsInsert++;
            int height = Height();

            addNode(root, height, 0, key);
        }
        this.size++;
    }



    public void preorder(Node root) {
        if (root != null) {
            System.out.print("  " + root.key);
            preorder(root.left);

            preorder(root.right);
        }
    }

}