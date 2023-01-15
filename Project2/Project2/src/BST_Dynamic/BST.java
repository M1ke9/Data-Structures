package BST_Dynamic;

public class BST {
    // Root of BST
    Node root;
    int NumOfComparisonsInsert;
    int NumOfComparisonsDelete;

    // Constructor
    public BST() {
        root = null;
        NumOfComparisonsInsert=0;
        NumOfComparisonsDelete=0;
    }

    public BST(int value) {
        root = new Node(value);
    }


    public Node search(Node root, int key)
    {
        // Base Cases: root is null or key is present at root
        if (root==null || root.key==key)
            return root;

        // Key is greater than root's key
        if (root.key < key)
            return search(root.right, key);

        // Key is smaller than root's key
        return search(root.left, key);
    }




    // This method mainly calls insertRec()
   public  void insert(int key) {

        root = insertRec(root, key);
    }

    /* A recursive function to
       insert a new key in BST */
    private Node insertRec(Node root, int key) {

        /* If the tree is empty,
           return a new node */
        if (root == null) {
            NumOfComparisonsInsert++;
            root = new Node(key);
            return root;
        }

        /* Otherwise, recur down the tree */
        if (key < root.key) {
            NumOfComparisonsInsert++;
            root.left = insertRec(root.left, key);
        }
        else if (key > root.key) {
            root.right = insertRec(root.right, key);
            NumOfComparisonsInsert++;
        }

        /* return the (unchanged) node pointer */
        return root;
    }

    // This method mainly calls InorderRec()
    public void inorder() {
        inorderRec(root);
    }

    // A utility function to
    // do inorder traversal of BST
    private void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.println(root.key);
            inorderRec(root.right);
        }
    }


    // This method mainly calls deleteRec()
    void deleteKey(int key) { root = deleteRec(root, key); }

    /* A recursive function to
      delete an existing key in BST
     */
    private Node deleteRec(Node root, int key)
    {
        /* Base Case: If the tree is empty */
        if (root == null) {
            return root;
        }
        /* Otherwise, recur down the tree */
        if (key < root.key) {
            NumOfComparisonsDelete++;
            root.left = deleteRec(root.left, key);
        }
        else if (key > root.key) {
            NumOfComparisonsDelete+=2;
            root.right = deleteRec(root.right, key);
        }

            // if key is same as root's
            // key, then This is the
            // node to be deleted
        else {
            NumOfComparisonsDelete++;
            // node with only one child or no child
            if (root.left == null) {
                return root.right;
            }
            else if (root.right == null) {
                return root.left;
            }

            // node with two children: Get the inorder
            // successor (smallest in the right subtree)
            root.key = minValue(root.right);

            // Delete the inorder successor
            root.right = deleteRec(root.right, root.key);
        }

        return root;
    }

    int minValue(Node root)
    {
        int minv = root.key;
        while (root.left != null)
        {
            minv = root.left.key;
            root = root.left;
        }
        return minv;
    }
}