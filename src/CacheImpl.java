/**
 * Defines the methods for a Cache data structure
 * The cache stores item of type V with keys of type K. 
 * Keys must be unique
 * */
public final class CacheImpl<K, V> implements Cache<K, V>{

	HashMap<K, V> hm;
	int hitsCounter;
	int missCounter;
	
	public CacheImpl(int size){
		hm = new HashMap<K, V>(size*4/5, size*1/5, size); 
	}
	
	/**
	 * Look for data associated with key. 
	 * @param key the key to look for
	 * @return The associated data or null if it is not found
	 */
	public V lookUp(K key) {
		
		V value = hm.search(key);
		if (value != null)
			hitsCounter++;
		else
			missCounter++;
		
		return value;

	}

	/**
	 * Stores data with associated with the given key. If required, it evicts a
	 * data record to make room for the new one
	 * @param key the key to associate with the data
	 * @param value the actual data
	 */
	@Override
	public void store(K key, V value) {	
		hm.add(key, value);
	}

	/**
	 * Returns the hit ratio, i.e. the number of times a lookup was successful divided by the number of lookup 
	 * @return the cache hit ratio
	 */
	@Override
	public double getHitRatio() {
		return (double) hitsCounter/(hitsCounter + missCounter);
	}

	/**
	 * Returns the absolute number of cache hits, i.e. the number of times a lookup found data in the cache
	 */
	@Override
	public long getHits() {
		return hitsCounter;
	}

	/**
	 * Returns the absolute number of cache misses, i.e. the number of times a lookup returned null
	 */
	@Override
	public long getMisses() {
		return missCounter;
	}

	/**
	 * Returns the total number of lookups performed by this cache 
	 */
	@Override
	public long getNumberOfLookUps() {		
		return hitsCounter+missCounter;
	}

}
