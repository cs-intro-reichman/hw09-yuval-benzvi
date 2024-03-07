/** A linked list of character data objects.
 *  (Actually, a list of Node objects, each holding a reference to a character data object.
 *  However, users of this class are not aware of the Node objects. As far as they are concerned,
 *  the class represents a list of CharData objects. Likwise, the API of the class does not
 *  mention the existence of the Node objects). */
public class List {
    private Node first;
    private int size;
    public List() {
        first = null;
        size = 0;
    }

    /** Returns the number of elements in this list. */
    public int getSize() {
 	      return size;
    }

    /** Returns the first element in the list */
    public CharData getFirst() {
        return first.cp;
    }

    /** GIVE Adds a CharData object with the given character to the beginning of this list. */
    public void addFirst(char chr) {
        Node newNode = new Node(new CharData(chr)); 
        newNode.next = first; 
        first = newNode; 
                        
        size++;
    }
    
    /** GIVE Textual representation of this list. */
    public String toString() {
        // Your code goes here
        if (size == 0) {
            return "()";
        }
        String str = "(";
        Node current = first;
        while (current != null) {
            str += current.cp + " ";
            current = current.next;
        }
        return str.substring(0, str.length() - 1) + ")";
    }

    /** Returns the index of the first CharData object in this list
     *  that has the same chr value as the given char,
     *  or -1 if there is no such object in this list. */
    public int indexOf(char chr) {
        Node current = first;
        char index = 0;
        while (current != null) {
            if (current.cp.equals(chr)) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1; 
    }

    /**
     * If the given character exists in one of the CharData objects in this list,
     * increments its counter. Otherwise, adds a new CharData object with the
     * given chr to the beginning of this list.
     */
    public void update(char chr) {
        int index = indexOf(chr);
        if (index >= 0) { 
            get(index).count++; 
        } else { 
            addFirst(chr); 
        }
    }

    /**
     * GIVE If the given character exists in one of the CharData objects
     * in this list, removes this CharData object from the list and returns
     * true. Otherwise, returns false.
     */
    public boolean remove(char chr) {
        Node prev = null;
        Node current = first;
        while (current != null && !current.cp.equals(chr)) {
            prev = current;
            current = current.next;
        }
        if (current == null) {
            return false; // not found
        }
        
        if (prev == null) { 
            first = first.next;
        } else {
            prev.next = current.next;
        }
        size--;
        return true;
    }

    /**
     * Returns the CharData object at the specified index in this list.
     * If the index is negative or is greater than the size of this list,
     * throws an IndexOutOfBoundsException.
     */
    public CharData get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node current = first;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.cp;
    }

    /**
     * Returns an array of CharData objects, containing all the CharData objects in
     * this list.
     */
    public CharData[] toArray() {
        CharData[] arr = new CharData[size];
        Node current = first;
        int i = 0;
        while (current != null) {
            arr[i++] = current.cp;
            current = current.next;
        }
        return arr;
    }

    /**
     * Returns an iterator over the elements in this list, starting at the given
     * index.
     */
    public ListIterator listIterator(int index) {
      
        if (size == 0)
            return null;
        
        Node current = first;
        int i = 0;
        while (i < index) {
            current = current.next;
            i++;
        }
        return new ListIterator(current);
    }
}