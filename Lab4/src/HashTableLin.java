public class HashTableLin {

    // INSTANCE FIELDS --------------------------------------------------------------------

    private Integer[] table;
    private int size;
    private int numkeys;
    private double maxloadfactor;

    // CONSTRUCTORS --------------------------------------------------------------------

    public HashTableLin(int maxNum, double load){
        // create an empty hash table with max load = load and maximum number of keys = maxKeys
        // allocate the memory too so maxNum keys can fit without it exceeding maxloadfactor

        maxloadfactor = load;
        size = closestPrime((double)maxNum/load); // rearrange load formula
        table = new Integer[size]; // allocate memory
    }


    // function generates the smallest prime number greater than the double n
    // otherwise, if it can't find any, returns 1
    private int closestPrime(double n){
        for (int i = 0; i < n+100; i++){
            if (isPrime(i) && i>n)
                return i;
        }
        return 1;
    }

    // checks if the integer passed is a prime number
    private boolean isPrime(int n){
        for (int i =1; i < n; i++){
            if (n%i == 0)
                return false;
        }
        return true;
    }

    // PUBLIC METHODS --------------------------------------------------------------------

    // insert key if the key isn't already in there. if will be above load factor, rehash
    public void insert(int n){
        if (this.isIn(n)) // we can't add if it's in so print error message
            System.out.println("we can't insert that element: it's already in the table");
        else if (numkeys+1/size > maxloadfactor){ // we're too full so rehash the table
            rehash(n);
            return;
        }
        else{

        }
    }

    // make new table at least 2* larger but also prime size & rehash w linear probing all prev keys
    private void rehash(){

    }

    // return true if n is in the hash table, false otherwise
    public boolean isIn(int n){

    }

    // print the keys of the hash table, order doesn't matter
    public void printKeys)(){

    }

    // print all the keys in this hash table with their corresponding indexes
    // in order of array indexes
    public void printKeysAndIndexes(){

    }

    // PUBLIC ACCESSORS --------------------------------------------------------------------

    public int getSize(){
        return size;
    }

    public double getMaxLoadFactor(){
        return maxloadfactor;
    }

    public int getNumKeys(){
        return numkeys;
    }


    // insert and count probes needed to insert
    public int insert(int n){
        if (isIn(n))
            return 0;
        else {

        }
        return 0;
    }
}
