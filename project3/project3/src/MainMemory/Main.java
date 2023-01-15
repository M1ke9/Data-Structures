package MainMemory;


import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class Main {


    public static void main(String[] args) throws IOException {
    	
    	System.out.println("Running,please wait a few seconds...");


       int NumOfNumbers= (int)Math.pow(10, 6);
       int d = (int)Math.pow(10, 5);


        int[] ArrayOfPricesInsertB3 = new int[11];
        int[] ArrayOfPricesInsertB64 = new int[11];

        int[] ArrayOfPriCESB3Search = new int[11];
        int[] ArrayOfPricesB64Search = new int[11];

        int[] ArrayOfPricesB3Delete = new int[11];
        int[] ArrayOfPricesB64Delete = new int[11];



        int[] ArrayOfPricesInsertAVL = new int[11];
        int[] ArrayOfPriCESAVLSearch = new int[11];
        int[] ArrayOfPricesAVLDelete = new int[11];

        File file = new File("keys_1000000_BE.bin");
        FileInputStream fis = new FileInputStream(file);
        byte[] construction = new byte[(int) file.length()];
        fis.read(construction);
        ByteBuffer bb = ByteBuffer.wrap(construction);
        bb.order(ByteOrder.BIG_ENDIAN);
        int[] constructionkeys = new int[NumOfNumbers];
        for (int i = 0; i < NumOfNumbers; i++) {
            constructionkeys[i] = bb.getInt();
        }


        fis.close();

        File fileIn = new File("keys_insert_100_BE.bin");
        FileInputStream fisIn = new FileInputStream(fileIn);
        byte[] insertion = new byte[(int) fileIn.length()];
        fisIn.read(insertion);
        ByteBuffer bbIn = ByteBuffer.wrap(insertion);
        bbIn.order(ByteOrder.BIG_ENDIAN);
        int[] insertionkeys = new int[100];
        for (int i = 0; i < 100; i++) {
            insertionkeys[i] = bbIn.getInt();
        }
        fisIn.close();

        File fileSrch = new File("keys_search_100_BE.bin");
        FileInputStream fisSrch = new FileInputStream(fileSrch);
        byte[] search = new byte[(int) fileSrch.length()];
        fisSrch.read(search);
        ByteBuffer bbSrch = ByteBuffer.wrap(search);
        bbSrch.order(ByteOrder.BIG_ENDIAN);
        int[] searchkeys = new int[100];
        for (int i = 0; i < 100; i++) {
            searchkeys[i] = bbSrch.getInt();
        }


        fisSrch.close();

        File fileDel = new File("keys_delete_100_BE.bin");
        FileInputStream fisDel = new FileInputStream(fileDel);
        byte[] delete = new byte[(int) fileDel.length()];
        fisDel.read(delete);
        ByteBuffer bbDel = ByteBuffer.wrap(delete);
        bbDel.order(ByteOrder.BIG_ENDIAN);
        int[] deletekeys = new int[100];
        for (int i = 0; i < 100; i++) {
            deletekeys[i] = bbDel.getInt();
        }
        fisDel.close();


        for (int i = 1; i < 11; i++) {
            BTree btree3 = new BTree(3);

            AVLTree Avltree=new AVLTree();

            for (int j = 0; j < i *d; j++) {
                btree3.insert(constructionkeys[j]);

                Avltree.insertKey(constructionkeys[j]);

            }

            btree3.resetCounters();
            Avltree.resetCounters();

            for (int j = 0; j < 100; j++) {
                btree3.insert(insertionkeys[j]);
                Avltree.insertKey(insertionkeys[j]);
            }

            ArrayOfPricesInsertB3[i] = btree3.getInsertCounter();
            ArrayOfPricesInsertAVL[i]= AVLTree.getInsertCounter();

            for (int j = 0; j < 100; j++) {
                btree3.Search(btree3.root, searchkeys[j]);
                Avltree.find(searchkeys[j]);
            }

            ArrayOfPriCESB3Search[i] = btree3.getSearchCounter();
            ArrayOfPriCESAVLSearch[i]= AVLTree.getSearchCounter();

            for (int j = 0; j < 100; j++) {
                btree3.Remove(deletekeys[j]);
                Avltree.deleteKey(deletekeys[j]);
            }

            ArrayOfPricesB3Delete[i] = btree3.getDeleteCounter();
            ArrayOfPricesAVLDelete[i]= AVLTree.getDeleteCounter();



            BTree btree64 = new BTree(64);

            for (int j = 0; j < i * d; j++) {
                btree64.insert(constructionkeys[j]);
            }

            btree64.resetCounters();
            for (int j = 0; j < 100; j++) {
                btree64.insert(insertionkeys[j]);
            }

            ArrayOfPricesInsertB64[i] = btree64.getInsertCounter();

            for (int j = 0; j < 100; j++) {
                btree64.Search(btree64.root, searchkeys[j]);
            }

            ArrayOfPricesB64Search[i] = btree64.getSearchCounter();

            for (int j = 0; j < 100; j++) {
                btree64.Remove(deletekeys[j]);
            }

            ArrayOfPricesB64Delete[i] = btree64.getDeleteCounter();
        }


        System.out.println("==============INSERTIONS=====================");
        System.out.print("Btree 3: ");
        for (int j = 1; j < 11; j++) {

            System.out.print(ArrayOfPricesInsertB3[j] / 100 + " ");
        }

        System.out.println();

        System.out.print("Btree 64: ");

        for (int k = 1; k < 11; k++)
            System.out.print(ArrayOfPricesInsertB64[k] / 100 + " ");

        System.out.println();


        System.out.println("==============SEARCH=====================");

        System.out.print("Btree 3: ");
        for (int j = 1; j < 11; j++) {

            System.out.print(ArrayOfPriCESB3Search[j] / 100 + " ");
        }

        System.out.println();

        System.out.print("Btree 64: ");

        for (int k = 1; k < 11; k++)
            System.out.print(ArrayOfPricesB64Search[k] / 100 + " ");

        System.out.println();
        System.out.println("==============DELETIONS=====================");


        System.out.print("Btree 3: ");
        for (int j = 1; j < 11; j++) {

            System.out.print(ArrayOfPricesB3Delete[j] / 100 + " ");
        }

        System.out.println();

        System.out.print("Btree 64: ");

        for (int k = 1; k < 11; k++)
            System.out.print(ArrayOfPricesB64Delete[k] / 100 + " ");



System.out.println();
System.out.println();


            System.out.println("==============INSERTIONS=====================");
            System.out.print("AVL TREE : ");
            for (int p = 1; p < 11; p++) {

                System.out.print(ArrayOfPricesInsertAVL[p] / 100 + " ");
            }

            System.out.println();


            System.out.println("==============SEARCH=====================");

            System.out.print("AVL TREE : ");
            for (int g = 1; g < 11; g++) {

                System.out.print(ArrayOfPriCESAVLSearch[g] / 100 + " ");
            }


            System.out.println();
            System.out.println("==============DELETIONS=====================");


            System.out.print("AVL TREE: ");
            for (int s = 1; s< 11; s++) {

                System.out.print(ArrayOfPricesAVLDelete[s]/100 + " ");
            }

            System.out.println();





    }




}



