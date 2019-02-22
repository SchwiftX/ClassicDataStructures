import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Trie {
    private TrieNode root;
    Trie(){
        root = new TrieNode();
    }
    public void insert(String str){
        TrieNode cur = root;
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (cur.children.containsKey(c))
                cur = cur.children.get(c);
            else{
                TrieNode node = new TrieNode();
                cur.children.put(c, node);
                cur = node;
            }
            if(i == chars.length - 1)
                cur.wordEnd = true;
        }
    }

    public boolean search(String str){
        TrieNode node = searchWordNode(str);
        return node == null ? false : node.wordEnd;
    }

    public boolean startsWith(String str){
        return searchWordNode(str) != null;
    }

    private TrieNode searchWordNode(String str){
        TrieNode cur = root;
        char[] chars = str.toCharArray();
        for (char c : chars) {
            if (cur.children.containsKey(c))
                cur = cur.children.get(c);
            else
                return null;
        }
        return cur;
    }

    private class TrieNode {
        private boolean wordEnd;
        Map<Character, TrieNode> children = new HashMap<>();
    }
}