import static java.lang.System.out;
public class TestHashTable {
    public static void main(String[] args) {

        // test 1: create table, but it's empty
        System.out.println("Test 1: create hash table");
        HashTableLin test1 = new HashTableLin(20, 0.5);
        test1.printKeysAndIndexes();

        // test 2: insert a few elements into the table
        test1.insert();
    }
}
