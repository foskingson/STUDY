package hackerRank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;


class TrieNode {
    HashMap<Character, TrieNode> children = new HashMap<>();
    boolean isLeaf = false;
}

class Trie {
    private final TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    public boolean addWord(String word) {
        TrieNode current = root;

        for (char c : word.toCharArray()) {
            if (current.children.containsKey(c)) {
                if (current.children.get(c).isLeaf) {
                    return false; // prefix 존재
                }
            } else {
                current.children.put(c, new TrieNode());
            }
            current = current.children.get(c);
        }

        if (!current.children.isEmpty()) {
            return false;
        }

        current.isLeaf = true;
        return true;
    }
}

public class NoPrefix {
    public static void noPrefix(List<String> words) {
        Trie trie = new Trie();

        for (String word : words) {
            if (!trie.addWord(word)) {
                System.out.println("BAD SET");
                System.out.println(word);
                return;
            }
        }
        System.out.println("GOOD SET");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        List<String> words = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            words.add(scanner.nextLine());
        }

        noPrefix(words);
        scanner.close();
    }
}
