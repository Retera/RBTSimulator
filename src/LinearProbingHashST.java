/*************************************************************************
 *  Compilation:  javac LinearProbingHashST.java
 *  Execution:    java LinearProbingHashST
 *
 *  Symbol table implementation with linear probing hash table.
 *
 *  % java LinearProbingHashST
 *  128.112.136.11
 *  208.216.181.15
 *  null
 *
 *
 *************************************************************************/
import java.util.*;

public class LinearProbingHashST<Key extends Comparable<Key>, Value>{
    private static final int INIT_CAPACITY = 22222223;
    private int N;           // number of key-value pairs in the symbol table
    private int M;           // size of linear probing table
    private Key[] keys;      // the keys
    private Value[] vals;    // the values
    static double startTest, endTest;
    // create an empty hash table - use 16 as default size
    public LinearProbingHashST() {
        this(INIT_CAPACITY);
    }
    
    // create linear proving hash table of given capacity
    public LinearProbingHashST(int capacity) {
        M = capacity;
        keys = (Key[])   new Comparable[M];
        vals = (Value[]) new Object[M];
    }
    
    // return the number of key-value pairs in the symbol table
    public int size() {
        return N;
    }
    
    // is the symbol table empty?
    public boolean isEmpty() {
        return size() == 0;
    }
    
    // does a key-value pair with the given key exist in the symbol table?
    public boolean contains(Key key) {
        return search(key) != null;
    }
    
    // hash function for keys - returns value between 0 and M-1
    private int hash(Key key) {
        return (key.hashCode()  & 0x7fffffff ) % M;
    }
    
    // insert the key-value pair into the symbol table
    public void insert(Key key, Value val) {
        if (val == null) {
            delete(key);
            return;
        }
        
        // double table size if 50% full
        
        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % M) {
            if (keys[i].equals(key)) { vals[i] = val; return; }
        }
        keys[i] = key;
        vals[i] = val;
        N++;
    }
    
    // return the value associated with the given key, null if no such value
    public Value search(Key key) {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % M)
            if (keys[i].equals(key))
                return vals[i];
        return null;
    }
    
    // delete the key (and associated value) from the symbol table
    public void delete(Key key) {
        if (!contains(key)) return;
        
        // find position i of key
        int i = hash(key);
        while (!key.equals(keys[i])) {
            i = (i + 1) % M;
        }
        
        // delete key and associated value
        keys[i] = null;
        vals[i] = null;
        
        // rehash all keys in same cluster
        i = (i + 1) % M;
        while (keys[i] != null) {
            // delete keys[i] an vals[i] and reinsert
            Key   keyToRehash = keys[i];
            Value valToRehash = vals[i];
            keys[i] = null;
            vals[i] = null;
            N--;
            insert(keyToRehash, valToRehash);
            i = (i + 1) % M;
        }
        
        N--;
        

    }
    
    // return all of the keys as in Iterable
    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<Key>();
        for (int i = 0; i < M; i++)
            if (keys[i] != null) queue.enqueue(keys[i]);
        return queue;
    }
    
    public int rank(Key key){
        /* TODO: Implement rank here... */
    	int rank = 0;
    	for(int i = 0; i < M; i++ ) {
    		if( keys[i] != null && keys[i].compareTo(key) < 0 ) {
    			rank++;
    		}
    	}
    	return rank;
    }
    
    public Key getValByRank(int k){
        /* TODO: Implement getValByRank here... */
    	if( k < 0 ) {
    		return null;
    	}
    	List<Key> initializer = new LinkedList<Key>();
    	int count = 0;
    	int index = 0;
    	for(index = 0; count < k + 1 && index < M; index++) {
    		if( keys[index] != null ) {
    			initializer.add(keys[index]);
        		count++;
    		}
    	}
    	PriorityQueue<Key> queue = new PriorityQueue<Key>(k + 1, Collections.reverseOrder());
    	queue.addAll(initializer);
    	for(int i = index; i < M; i++ ) {
    		Key key = keys[i];
    		if( key != null ) {
    			queue.offer(key);
    			queue.poll();
    		}
    	}
    	return queue.poll();
    }
    
    public Iterable<Key> kSmallest(int k){
        /* TODO: Implement kSmallest here... */
    	if( k < 0 ) {
    		return null;
    	}
    	List<Key> initializer = new LinkedList<Key>();
    	int count = 0;
    	int index = 0;
    	for(index = 0; count < k && index < M; index++) {
    		if( keys[index] != null ) {
    			initializer.add(keys[index]);
        		count++;
    		}
    	}
    	PriorityQueue<Key> queue = new PriorityQueue<Key>(k, Collections.reverseOrder());
    	queue.addAll(initializer);
    	for(int i = index; i < M; i++ ) {
    		Key key = keys[i];
    		if( key != null ) {
    			queue.offer(key);
    			queue.poll();
    		}
    	}
    	return queue;
    }
    
    public Iterable<Key> kLargest(int k){
        /* TODO: Implement kLargest here... */
        /* TODO: Implement kSmallest here... */
    	if( k < 0 ) {
    		return null;
    	}
    	List<Key> initializer = new LinkedList<Key>();
    	int count = 0;
    	int index = 0;
    	for(index = 0; count < k && index < M; index++) {
    		if( keys[index] != null ) {
    			initializer.add(keys[index]);
        		count++;
    		}
    	}
    	PriorityQueue<Key> queue = new PriorityQueue<Key>(k);
    	queue.addAll(initializer);
    	for(int i = index; i < M; i++ ) {
    		Key key = keys[i];
    		if( key != null ) {
    			queue.offer(key);
    			queue.poll();
    		}
    	}
    	return queue;
    }
    
    public int rangeCount(Key low, Key high){
        /* TODO: Implement rangeCount here... */
    	int count = 0;
    	for(int i = 0; i < M; i++ ) {
    		if( keys[i] != null && keys[i].compareTo(low) >= 0 && keys[i].compareTo(high) <= 0 ) {
    			count++;
    		}
    	}
    	return count;
    }
    
    
}
