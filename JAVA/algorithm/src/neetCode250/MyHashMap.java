package neetCode250;


class Hash {
    int key;
    int val;
    Hash next;

    Hash(int key, int val) {
        this.key = key;
        this.val = val;
        this.next = null;
    }
}


public class MyHashMap {
    private Hash[] map;

    public MyHashMap() {
        map = new Hash[1000];
        for (int i = 0; i < 1000; i++) {
            map[i] = new Hash(-1, -1);
        }
    }
    
    public void put(int key, int value) {
        int hash = hash(key);
        Hash cur = map[hash];

        while (cur.next != null) {
            if (cur.next.key == key) {
                cur.next.val = value;
                return;
            }
            cur = cur.next;
        }

        cur.next = new Hash(key, value);
    }

    public int get(int key) {
        int hash = hash(key);
        Hash cur = map[hash].next;

        while (cur != null) {
            if (cur.key == key)
                return cur.val;
            cur = cur.next;
        }

        return -1;
    }

    public void remove(int key) {
        int hash = hash(key);
        Hash cur = map[hash];

        while (cur.next != null) {
            if (cur.next.key == key) {
                cur.next = cur.next.next;
                return;
            }
            cur = cur.next;
        }
    }

    private int hash(int key) {
        return key % 1000;
    }
}
