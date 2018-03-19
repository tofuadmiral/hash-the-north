import static java.lang.System.out;
import java.util.Random;
import java.lang.Math;

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
        System.out.println("\nTest 7: try insert and count, >1 probes: ");
        System.out.println("Probes taken: "+ test1.insertandcount(166));
        test1.printKeysAndIndexes();
        System.out.println("Size: "+test1.getSize()+", num keys "+test1.getNumKeys()+", " + "maxloadfactor: "+test1.getMaxLoadFactor());

        // test 8: get average probes for successful search
        System.out.println("\nTest 8: find successful search count ");
        HashTableLin testpoint1 = new HashTableLin(100000, 0.1);
        Random rando = new Random();
        int point1count = 0;
        double point1avg = 0.0;
        double theoretical = 0.5*(1+1/(1-0.1));
        // have a good average i.e. repeat 100 times
        for (int i = 0; i<100; i++){
            for (int j = 0; j<100000; j++){ // insert the 100 000 randos and count
                point1count += testpoint1.insertandcount(Math.abs(rando.nextInt()));
            }
        }


        point1avg = (double)point1count/(100*100000 - testpoint1.getDuplicates());
        System.out.println("Avg number of probes was: "+point1avg);
        System.out.println(" Theoretical avg num was: "+theoretical);


        // test 9 BONUS: get average for unsuccessful search
        // first populate with 100 000 randoms, then look for 100 000 randoms and find average time
        System.out.println("\nTest 9 BONUS: unsuccessful search count: ");
        HashTableLin test2 = new HashTableLin(100000, 0.1);
        double theoretical2 = 0.5*(1+1/(Math.pow(1-0.1, 2)));
        for (int i = 0; i<100000; i++){
            // populate table
            test2.insert(Math.abs(rando.nextInt()));
        }
        // now take populated table and search for randos, only count unsuccessful searches
        int numsearches = 0;
        for (int i = 0; i<100000; i++){
            numsearches+=test2.search(Math.abs(rando.nextInt()));
        }
        double avgUnsucProbes = (double)numsearches/(100000 - test2.getDuplicates());
        System.out.println("Avg number of unsuccessful probes was: "+avgUnsucProbes);
        System.out.println(" Theoretical avg num unsuccessful was: "+theoretical2);
    }
}
