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
            rehash();
            return;
        }
        else if (table[n%size] == null ){ // spot is empty, so insert
            table[n%size] = n;
            numkeys++;
        }
        else{ // spot isn't empty, so have to linearly probe
            int i = 1; // start one away
            while (table[(n+i)%size] != null){ // keep incrementing if we're not at an empty spot
                i++;
            }
            table[(n+i)%size] = n; // we've found empty spot so insert it
            numkeys++; // increment number of keys bc we increased them
        }
    }

    // make new table at least 2* larger but also prime size & rehash w linear probing all prev keys
    private void rehash(){
        // set dimensions of new table
        int newsize = closestPrime(size*2);
        int newmax = (int)this.getMaxLoadFactor()*newsize;

        // new maxnum keys is new size * load factor, so pass these to new table
        HashTableLin temptable = new HashTableLin(newmax, this.getMaxLoadFactor());

        // loop through old table and insert all of them into the new table
        for(int i = 0; i < this.getSize(); i++) {
            temptable.insert(this.table[i]);
        }

        // now that we've added everything, make sure to set new table as the old table stuff
        // change all the instance fields except maxloadfactor, and then we're done rehashing!
        this.maxloadfactor = newmax;
        this.table = temptable.table;
        this.size = newsize;
    }

    // return true if n is in the hash table, false otherwise
    public boolean isIn(int n){
        if(table[n%size] == null)
            return false;
        else { // spot has something in it so check linearly
            if(table[n%size] == n ) // has the element n in it
                return true;
            else{ // have to check linear probing, check through entire table so n spots - 1
                for (int i = 1; i < size; i++){
                    if (table[(n+i)%size] == n)
                        return true;
                }
            }
            return false; // if we looped through then we didn't find it so return false
        }
    }

    // print the keys of the hash table, order doesn't matter
    public void printKeys(){
        for (int i = 0; i< size; i++){
            if (table[i] != null)
                System.out.println(table[i]+", ");
        }
    }

    // print all the keys in this hash table with their corresponding indexes
    // in order of array indexes
    public void printKeysAndIndexes(){
        for (int i = 0; i< size; i++){
            if (table[i] != null)
                System.out.println("Index: " + i + ", Key: " table[i]+",");
        }
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
    public int insertandcount(int n){
        if (this.isIn(n)) { // we can't add if it's in so print error message
            System.out.println("we can't insert that element: it's already in the table");
            return 0;
        }
        else if(numkeys+1/size > maxloadfactor){ // we're too full so rehash the table
            rehash();
            return 0;
        }
        else if (table[n%size] == null ){ // spot is empty, so insert
            table[n%size] = n;
            numkeys++;
            return 1; // had to check one spot so took one probe
        }
        else{ // spot isn't empty, so have to linearly probe
            int i = 1; // start one away
            while (table[(n+i)%size] != null){ // keep incrementing if we're not at an empty spot
                i++;
            }
            table[(n+i)%size] = n; // we've found empty spot so insert it
            numkeys++;
            return i + 1; // bc even if zero still one probe
        }
    }
}
