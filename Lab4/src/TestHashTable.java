import static java.lang.System.out;
import java.util.Random;

public class TestHashTable {
    public static void main(String[] args) {

        // test 1: create table, but it's empty
        System.out.println("Test 1: create hash table");
        HashTableLin test1 = new HashTableLin(20, 0.5);
        test1.printKeysAndIndexes();
        System.out.println("Size: "+test1.getSize()+", num keys "+test1.getNumKeys()+", " + "maxloadfactor: "+test1.getMaxLoadFactor());

        // test 2: insert a few elements into the table
        System.out.println("\nTest 2: insert some items into the table:");
        test1.insert(3); // should be at index 3
        test1.insert(29); // index 29
        test1.insert(59); // index 18
        test1.printKeysAndIndexes();
        System.out.println("Size: "+test1.getSize()+", num keys "+test1.getNumKeys()+", " + "maxloadfactor: "+test1.getMaxLoadFactor());

        // test 3: linear probing
        System.out.println("\nTest 3: try linear probing of the table:");
        test1.insert(18); // should go to 19
        test1.printKeysAndIndexes();
        System.out.println("Size: "+test1.getSize()+", num keys "+test1.getNumKeys()+", " + "maxloadfactor: "+test1.getMaxLoadFactor());

        // test 4: insert something already in there
        System.out.println("\nTest 4: insert a repeat:");
        test1.insert(18);
        System.out.println("Size: "+test1.getSize()+", num keys "+test1.getNumKeys()+", " + "maxloadfactor: "+test1.getMaxLoadFactor());

        // test 5: insert until we have to rehash
        System.out.println("\nTest 5: insert till rehash needed:");
        for (int i = 0; i < 20; i++){
            test1.insert(i);
        }
        test1.printKeysAndIndexes();
        System.out.println("Size: "+test1.getSize()+", num keys "+test1.getNumKeys()+", " + "maxloadfactor: "+test1.getMaxLoadFactor());

        // test 6: try insert and count
        System.out.println("\nTest 6: try insert and count, 1 probe: ");
        System.out.println("Probes taken: "+ test1.insertandcount(50));
        test1.printKeysAndIndexes();
        System.out.println("Size: "+test1.getSize()+", num keys "+test1.getNumKeys()+", " + "maxloadfactor: "+test1.getMaxLoadFactor());

        // test 7: try insert and count
        System.out.println("\nTest 6: try insert and count, 2 probes: ");
        System.out.println("Probes taken: "+ test1.insertandcount(166));
        test1.printKeysAndIndexes();
        System.out.println("Size: "+test1.getSize()+", num keys "+test1.getNumKeys()+", " + "maxloadfactor: "+test1.getMaxLoadFactor());

        // test 8: get average probes for successful search
        HashTableLin testpoint1 = new HashTableLin(100000, 0.1);


    }
}
