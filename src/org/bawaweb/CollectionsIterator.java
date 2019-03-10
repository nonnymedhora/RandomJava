/**
 * 
 */
package org.bawaweb;

import java.util.*;

/**
 * @author Navroz
 * Implementation of Iterator pattern 
 * for a collection of collections
 * 
 * getNext	---	returns the next element
 *
 */
public class CollectionsIterator<T> implements Iterator<T>{
	
	private Collection<? extends Collection<T>> theCollections;
	
	
	private int currentCollectionIndex;
	private int currentIndex;
	
	private int allCollsSize = 0;
	private int currentCollectionSize = 0;
	
	public CollectionsIterator(Collection<? extends Collection<T>> col) {
		if ( col != null & col.size() > 0) {
			theCollections = col;
			
			currentCollectionIndex = 0;
			currentIndex = 0;
			
			allCollsSize = col.size();
//			currentCollectionSize =  (Collection) theCollections.
		}
	}
	
	private void getNext() {
		/*return theCollections.stream()
		        .filter(Objects::nonNull)
		        .flatMap(Collection::stream)
		        .filter(Objects::nonNull)
		        .iterator();*/
		
		next();
		
	}

	private boolean isThereMore() {
		/*if( currentCollectionIndex < allCollsSize-1 ) {
			return true;
			
		} else if ( currentCollectionIndex == allCollsSize-1 ) {
			
		}
		
		return false;*/
		
		return hasNext();
	}
	
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public T next() {
		// TODO Auto-generated method stub
		return null;
	}
	



	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Integer> l1 	= Arrays.asList(new Integer[] {1,2,3,4});
		List<Integer> l2 	= Arrays.asList(new Integer[] {5,6,7,8,9});
		List<Integer> l3	= Arrays.asList(new Integer[] {10,11,12,13});
		
		Collection<List<Integer>> coll = new ArrayList<List<Integer>>();
		coll.add(l1);
		coll.add(l2);
		coll.add(l3);
		
		CollectionsIterator cIt = new CollectionsIterator(coll);
		
		while( cIt.isThereMore() ) {
			cIt.getNext();
		}

	}
	
	
	//https://stackoverflow.com/questions/3327077/interview-design-an-iterator-for-a-collection-of-collections
	//Eyal Schneider
	
	class MultiIterator1 <T> implements Iterator<T>{

	    private Iterator<? extends Collection<T>> it;
	    private Iterator<T> innerIt;
	    private T next;
	    private boolean hasNext = true;

	    public MultiIterator1(Collection<? extends Collection<T>> collections) {
	        it = collections.iterator();    
	        prepareNext();
	    }

	    private void prepareNext() {
	        do {
	            if (innerIt == null || !innerIt.hasNext()) {
	                if (!it.hasNext()) {
	                    hasNext = false;
	                    return;
	                } else
	                    innerIt = it.next().iterator();
	            }
	        } while (!innerIt.hasNext());

	        next = innerIt.next();
	    }

	    @Override
	    public boolean hasNext() {
	        return hasNext;
	    }

	    @Override
	    public T next() {
	        if (!hasNext)
	            throw new NoSuchElementException();
	        T res = next;
	        prepareNext();
	        return res;
	    }

	    @Override
	    public void remove() {
	        //TODO
	    }

	}
	
	
	
	//also-see
	//https://github.com/floodlight/floodlight/blob/master/src/main/java/net/floodlightcontroller/util/MultiIterator.java
	/**
	 * Iterator over all values in an iterator of iterators
	 *
	 * @param <T> the type of elements returned by this iterator
	 */
	public class MultiIterator<T> implements Iterator<T> {
	    Iterator<Iterator<T>> subIterator;
	    Iterator<T> current = null;
	    
	    public MultiIterator(Iterator<Iterator<T>> subIterator) {
	        super();
	        this.subIterator = subIterator;
	    }

	    @Override
	    public boolean hasNext() {
	        if (current == null) {
	            if (subIterator.hasNext()) {
	                current = subIterator.next();
	            } else {
	                return false;
	            }
	        }
	        while (!current.hasNext() && subIterator.hasNext()) {
	            current = subIterator.next();
	        }
	        
	        return current.hasNext();
	    }

	    @Override
	    public T next() {
	        if (hasNext())
	            return current.next();
	        throw new NoSuchElementException();
	    }

	    @Override
	    public void remove() {
	        if (hasNext())
	            current.remove();
	        throw new NoSuchElementException();
	    }
	}



	
}
