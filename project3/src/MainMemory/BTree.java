package MainMemory;

// Searching a key on a B-tree in Java

public class BTree {

    int SearchCounter=0;
    int InsertCounter=0;
    int DeleteCounter=0;

    public int getSearchCounter() {
        return SearchCounter;
    }

    public void setSearchCounter(int searchCounter) {
        SearchCounter = searchCounter;
    }

    public int getInsertCounter() {
        return InsertCounter;
    }

    public void setInsertCounter(int insertCounter) {
        InsertCounter = insertCounter;
    }

    public int getDeleteCounter() {
        return DeleteCounter;
    }

    public void setDeleteCounter(int deleteCounter) {
        DeleteCounter = deleteCounter;
    }

    private int T;

    // Node creation
    public class Node {
        int n;
        int key[] = new int[2 * T - 1];
        Node child[] = new Node[2 * T];
        boolean leaf = true;

        public int Find(int k) {
            for (int i = 0; i < this.n; i++) {
                DeleteCounter++;
                if (this.key[i] == k) {
                    return i;
                }
            }
            return -1;
        }


        Node search(int k) { // returns NULL if k is not present.

            // Find the first key greater than or equal to k
            int i = 0;
            while (i < n && k > key[i])
                i++;

            // If the found key is equal to k, return this node
            if (key[i] == k)
                return this;

            // If the key is not found here and this is a leaf node
            if (leaf)
                return null;

            // Go to the appropriate child
            return child[i].search(k);

        }
    }


    public BTree(int t) {
        T = t;
        root = new Node();
        root.n = 0;
        root.leaf = true;
    }

    public Node root;



    // Search key
    public Node Search(Node x, int key) {
        int i = 0;
        if (x == null) {
            SearchCounter++;
            return x;
        }
        SearchCounter++;
        for (i = 0; i < x.n; i++) {
            if (key < x.key[i]) {
                SearchCounter++;
                break;
            }
            if (key == x.key[i]) {
                SearchCounter++;
                return x;
            }
        }
        if (x.leaf) {
            SearchCounter+=2;
            return null;
        } else {
            return Search(x.child[i], key);
        }
    }

    // Splitting the node
    private void Split(Node x, int pos, Node y) {
        Node z = new Node();
        z.leaf = y.leaf;
        z.n = T - 1;
        for (int j = 0; j < T - 1; j++) {
            z.key[j] = y.key[j + T];
        }
        if (!y.leaf) {
            InsertCounter++;
            for (int j = 0; j < T; j++) {
                z.child[j] = y.child[j + T];
            }
        }
        y.n = T - 1;
        for (int j = x.n; j >= pos + 1; j--) {
            x.child[j + 1] = x.child[j];
        }
        x.child[pos + 1] = z;

        for (int j = x.n - 1; j >= pos; j--) {
            x.key[j + 1] = x.key[j];
        }
        x.key[pos] = y.key[T - 1];
        x.n = x.n + 1;
    }

    // insert key
    public void insert(final int key) {
        Node r = root;
        if (r.n == 2 * T - 1) {
            InsertCounter++;
            Node s = new Node();
            root = s;
            s.leaf = false;
            s.n = 0;
            s.child[0] = r;
            Split(s, 0, r);
            _insert(s, key);
        } else {
            InsertCounter+=2;
            _insert(r, key);
        }
    }

    // insert node
    final private void _insert(Node x, int k) {

        if (x.leaf) {
            InsertCounter+=2;
            int i = 0;
            for (i = x.n - 1; i >= 0 && k < x.key[i]; i--) {
                x.key[i + 1] = x.key[i];
            }
            x.key[i + 1] = k;
            x.n = x.n + 1;
        } else {
            InsertCounter+=1;
            int i = 0;
            for (i = x.n - 1; i >= 0 && k < x.key[i]; i--) {
            }
            ;
            i++;
            Node tmp = x.child[i];
            if (tmp.n == 2 * T - 1) {
                InsertCounter+=2;
                Split(x, i, tmp);
                if (k > x.key[i]) {
                    InsertCounter+=3;
                    i++;
                }
            }
            _insert(x.child[i], k);
        }

    }
    public void Show() {
        Show(root);
    }

    // Display
    private void Show(Node x) {
        assert (x == null);
        for (int i = 0; i < x.n; i++) {
            System.out.print(x.key[i] + " ");
        }
        if (!x.leaf) {
            for (int i = 0; i < x.n + 1; i++) {
                Show(x.child[i]);
            }
        }
    }

    // Check if present
    public boolean Contain(int k) {
        if (this.Search(root, k) != null) {
            return true;
        } else {
            return false;
        }
    }

    private void Remove(Node x, int key) {
        int pos = x.Find(key);
        if (pos != -1) {
            DeleteCounter++;
            if (x.leaf) {
                DeleteCounter+=2;
                int i = 0;
                for (i = 0; i < x.n && x.key[i] != key; i++) {
                }
                ;
                for (; i < x.n; i++) {
                    DeleteCounter++;
                    if (i != 2 * T - 2) {
                        DeleteCounter++;
                        x.key[i] = x.key[i + 1];
                    }
                }
                x.n--;
                return;
            }
            if (!x.leaf) {
                DeleteCounter+=3;

                Node pred = x.child[pos];
                int predKey = 0;
                if (pred.n >= T) {
                    DeleteCounter+=4;
                    for (;;) {
                        if (pred.leaf) {
                            DeleteCounter+=5;
                           // System.out.println(pred.n);
                            predKey = pred.key[pred.n - 1];
                            break;
                        } else {
                            DeleteCounter+=5;
                            pred = pred.child[pred.n];
                        }
                    }
                    Remove(pred, predKey);
                    x.key[pos] = predKey;
                    return;
                }

                Node nextNode = x.child[pos + 1];
                if (nextNode.n >= T) {
                    DeleteCounter+=4;
                    int nextKey = nextNode.key[0];
                    if (!nextNode.leaf) {
                        DeleteCounter+=4;
                        nextNode = nextNode.child[0];
                        for (;;) {
                            if (nextNode.leaf) {
                                DeleteCounter+=6;
                                nextKey = nextNode.key[nextNode.n - 1];
                                break;
                            } else {
                                DeleteCounter+=6;
                                nextNode = nextNode.child[nextNode.n];
                            }
                        }
                    }
                    Remove(nextNode, nextKey);
                    x.key[pos] = nextKey;
                    return;
                }

                int temp = pred.n + 1;
                pred.key[pred.n++] = x.key[pos];
                for (int i = 0, j = pred.n; i < nextNode.n; i++) {
                    pred.key[j++] = nextNode.key[i];
                    pred.n++;
                }
                for (int i = 0; i < nextNode.n + 1; i++) {
                    pred.child[temp++] = nextNode.child[i];
                }

                x.child[pos] = pred;
                for (int i = pos; i < x.n; i++) {
                    if (i != 2 * T - 2) {
                        DeleteCounter+=4;
                        x.key[i] = x.key[i + 1];
                    }
                }
                for (int i = pos + 1; i < x.n + 1; i++) {
                    if (i != 2 * T - 1) {
                        DeleteCounter+=4;
                        x.child[i] = x.child[i + 1];
                    }
                }
                x.n--;
                if (x.n == 0) {
                    DeleteCounter+=4;
                    if (x == root) {
                        DeleteCounter+=5;
                        root = x.child[0];
                    }
                    x = x.child[0];
                }
                Remove(pred, key);
                return;
            }
        } else {
            DeleteCounter+=2;
            for (pos = 0; pos < x.n; pos++) {
                DeleteCounter++;
                if (x.key[pos] > key) {
                    break;
                }
            }
            Node tmp = x.child[pos];
            if (tmp.n >= T) {
                DeleteCounter+=3;
                Remove(tmp, key);
                return;
            }
            if (true) {
                Node nb = null;
                int devider = -1;

                if (pos != x.n && x.child[pos + 1].n >= T) {
                    DeleteCounter+=5;
                    devider = x.key[pos];
                    nb = x.child[pos + 1];
                    x.key[pos] = nb.key[0];
                    tmp.key[tmp.n++] = devider;
                    tmp.child[tmp.n] = nb.child[0];
                    for (int i = 1; i < nb.n; i++) {
                        nb.key[i - 1] = nb.key[i];
                    }
                    for (int i = 1; i <= nb.n; i++) {
                        nb.child[i - 1] = nb.child[i];
                    }
                    nb.n--;
                    Remove(tmp, key);
                    return;
                } else if (pos != 0 && x.child[pos - 1].n >= T) {
                    DeleteCounter+=7;

                    devider = x.key[pos - 1];
                    nb = x.child[pos - 1];
                    x.key[pos - 1] = nb.key[nb.n - 1];
                    Node child = nb.child[nb.n];
                    nb.n--;

                    for (int i = tmp.n; i > 0; i--) {
                        tmp.key[i] = tmp.key[i - 1];
                    }
                    tmp.key[0] = devider;
                    for (int i = tmp.n + 1; i > 0; i--) {
                        tmp.child[i] = tmp.child[i - 1];
                    }
                    tmp.child[0] = child;
                    tmp.n++;
                    Remove(tmp, key);
                    return;
                } else {
                    DeleteCounter+=7;
                    Node lt = null;
                    Node rt = null;
                    boolean last = false;
                    if (pos != x.n) {
                        DeleteCounter+=8;
                        devider = x.key[pos];
                        lt = x.child[pos];
                        rt = x.child[pos + 1];
                    } else {
                        DeleteCounter+=8;
                        devider = x.key[pos - 1];
                        rt = x.child[pos];
                        lt = x.child[pos - 1];
                        last = true;
                        pos--;
                    }
                    for (int i = pos; i < x.n - 1; i++) {
                        x.key[i] = x.key[i + 1];
                    }
                    for (int i = pos + 1; i < x.n; i++) {
                        x.child[i] = x.child[i + 1];
                    }
                    x.n--;
                    lt.key[lt.n++] = devider;

                    for (int i = 0, j = lt.n; i < rt.n + 1; i++, j++) {
                        if (i < rt.n) {
                            lt.key[j] = rt.key[i];
                        }
                        lt.child[j] = rt.child[i];
                    }
                    lt.n += rt.n;
                    if (x.n == 0) {
                        DeleteCounter++;
                        if (x == root) {
                            DeleteCounter+=2;
                            root = x.child[0];
                        }
                        x = x.child[0];
                    }
                    Remove(lt, key);
                    return;
                }
            }
        }
    }


    public void resetCounters(){
        this.SearchCounter=0;
        this.DeleteCounter=0;
        this.InsertCounter=0;
    }

    public void Remove(int key) {
        Node x = Search(root, key);

        if (x == null) {
            return;
        }

        Remove(root, key);
    }
}
