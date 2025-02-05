package neetCode250;

import java.util.ArrayList;
import java.util.List;

class MyHashSet {

    List<Integer> s;
    
    public MyHashSet() {
        s = new ArrayList<>();
    }
    
    public void add(int key) {
        if (!contains(key)) {
            s.add(key);
        }
    }
    
    public void remove(int key) {
        s.remove(Integer.valueOf(key));
    }
    
    public boolean contains(int key) {
        for (int i : s) {
            if (i==key) {
                return true;
            }
        }
        return false;
    }
}
