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


}
