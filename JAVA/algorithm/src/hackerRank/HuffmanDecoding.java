package hackerRank;

import org.w3c.dom.Node;

class Huff{
    public  int frequency; // the frequency of this tree
    public  char data;
    public  Huff left, right;
}

public class HuffmanDecoding {
    void decode(String s, Huff root) {
        Huff current = root;
        for (char c : s.toCharArray()) {           
            if (c == '0') {
                current = current.left;
            } else {
                current = current.right;
            }
            
            if (current != null && current.data != '\0') {
                System.out.print(current.data);
                current = root;
            }
            
        }
       
    }
}
