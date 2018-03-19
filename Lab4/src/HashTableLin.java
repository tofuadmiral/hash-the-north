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
        numkeys = 0; // initialize number of keys
    }


    // function generates the smallest prime number greater than the double n
    // otherwise, if it can't find any, returns 1
    private int closestPrime(double n){
        for (int i = (int)n; i < n+1000; i++){
            if (isPrime(i) && i>n)
                return i;
        }
        return 1;
    }

    // checks if the integer passed is a prime number
    private boolean isPrime(int n){
        for (int i=2; i < n; i++){
            if (n%i == 0)
                return false;
        }
        return true;
    }

    // PUBLIC METHODS --------------------------------------------------------------------

    // insert key if the key isn't already in there. if will be above load factor, rehash
    public void insert(int n){
        if (this.isIn(n)) { // we can't add if it's in so print error message
            System.out.println("we can't insert element: " + n + " it's already in the table");
            return;
        }
        if ((double)(numkeys+1)/size > maxloadfactor) {
            rehash();
            return;
        }
        if (table[n%size] == null ){ // spot is empty, so insert
            table[n%size] = n;
            numkeys++;
            return;
        }
        else{ // spot isn't empty, so have to linearly probe
            int i = 1; // start one away
            while (table[(n+i)%size] != null){ // keep incrementing if we're not at an empty spot
                i++;
            }
            table[(n+i)%size] = n; // we've found empty spot so insert it
            numkeys++; // increment number of keys bc we increased them
            return;
        }
    }

    // make new table at least 2* larger but also prime size & rehash w linear probing all prev keys
    private void rehash(){
        // set dimensions of new table
        int newsize = closestPrime((double)this.size*2);
        int newmax = (int)(this.getMaxLoadFactor()*(double)newsize);

        // newmax num of keys is new size * load factor, so pass these to new table
        HashTableLin temptable = new HashTableLin(newmax, this.getMaxLoadFactor());

        // loop through old table and insert all of them into the new table
        for(int i = 0; i < this.getSize(); i++) {
            if (this.table[i] != null){ // only insert if not null
                temptable.insert(this.table[i]);
            }
        }

        // now that we've added everything, make sure to set new table as the old table stuff
        // change all the instance fields except maxloadfactor, and then we're done rehashing!
        this.maxloadfactor = this.getMaxLoadFactor();
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
                    if (table[(n+i)%size] == null)
                        return false;
                    else if (table[(n+i)%size] == n){
                        return true;
                    }
                }
            }
            return false; // if we looped through then we didn't find it, return false
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
                System.out.println("Index: " + i + ", Key: " + table[i]+",");
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
            System.out.println("we can't insert element: " + n + " it's already in the table");
            return 0;
        }
        if ((double)(numkeys+1)/size > maxloadfactor) {
            rehash();
        }
        if (table[n%size] == null ){ // spot is empty, so insert
            table[n%size] = n;
            numkeys++;
            return 1;
        }
        else{ // spot isn't empty, so have to linearly probe
            int i = 1; // start one away
            while (table[(n+i)%size] != null){ // keep incrementing if we're not at an empty spot
                i++;
            }
            table[(n+i)%size] = n; // we've found empty spot so insert it
            numkeys++; // increment number of keys bc we increased them
            return i+1; // return number of additional probes + the first one to look
        }
    }


} // end class
