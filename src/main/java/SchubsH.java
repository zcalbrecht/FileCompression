/**
 * Software Engineering II
 * Fall 2019
 * Zachary Albrecht
 *
 * Based on Sedgewick's file compression unit
 *
 * Compress multiple files at once through Huffman encoding
 * compile: javac SchubsH.java
 * execute: java SchubsH <file1> <file2> <file3> ...
 */

public class SchubsH {

    private static BinaryIn bin;
    private static BinaryOut bout;
    private static final int R = 256;

    private static class Node implements Comparable<Node> {
        private final char ch;
        private final int freq;
        private final Node left, right;

        Node(char ch, int freq, Node left, Node right) {
            this.ch    = ch;
            this.freq  = freq;
            this.left  = left;
            this.right = right;
        }

        private boolean isLeaf() {
            assert (left == null && right == null) || (left != null && right != null);
            return left == null && right == null;
        }

        public int compareTo(Node that) {
            return this.freq - that.freq;
        }
    }

    public static void compress() {
        String s = bin.readString();
        char[] input = s.toCharArray();
        int[] freq = new int[R];

        for(int i = 0; i < input.length; i++)
            freq[input[i]]++;

        Node root = buildTrie(freq);
        String[] st = new String[R];
        buildCode(st, root, "");

        writeTrie(root);
        bout.write(input.length);

        for(int i = 0; i < input.length; i++) {
            String code = st[input[i]];
            for(int j = 0; j < code.length(); j++) {
                if(code.charAt(j) == '0')
                    bout.write(false);
                else if(code.charAt(j) == '1')
                    bout.write(true);
                else throw new RuntimeException("Illegal state");
            }
        }
        bout.close();
    }

    private static Node buildTrie(int[] freq) {

        MinPQ<Node> pq = new MinPQ<Node>();
        for(char i = 0; i < R; i++)
            if (freq[i] > 0)
                pq.insert(new Node(i, freq[i], null, null));

        while(pq.size() > 1) {
            Node left  = pq.delMin();
            Node right = pq.delMin();
            Node parent = new Node('\0', left.freq + right.freq, left, right);
            pq.insert(parent);
        }
        return pq.delMin();
    }

    private static void writeTrie(Node x) {
        if(x.isLeaf()) {
            bout.write(true);
            bout.write(x.ch);

            return;
        }

        bout.write(false);

        writeTrie(x.left);
        writeTrie(x.right);
    }

    private static void buildCode(String[] st, Node x, String s) {
        if(!x.isLeaf()) {
            buildCode(st, x.left,  s + '0');
            buildCode(st, x.right, s + '1');
        }
        else
            st[x.ch] = s;
    }

    public static void main(String[] args) {
        for(int i = 0; i < args.length; i++) {
            bin = new BinaryIn(args[i]);
            bout = new BinaryOut(args[i] + ".hh");
            compress();
        }
    }

}
