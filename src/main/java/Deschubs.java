/**
 * Software Engineering II
 * Fall 2019
 * Zachary Albrecht
 *
 * Based on Sedgewick's file compression unit
 *
 * Decompress Huffman or LZW encoded files and archives
 * compile: javac Deschubs.java
 * execute: java Deschubs <file1> <file2> <file3> ...
 */

import java.io.*;

public class Deschubs {
    private static BinaryIn bin;
    private static BinaryOut bout;
    private static final int R = 256;
    private static final int L = 4096;
    private static final int W = 12;

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
            assert ((left == null) && (right == null)) || ((left != null) && (right != null));
            return (left == null) && (right == null);
        }

        public int compareTo(Node that) {
            return this.freq - that.freq;
        }
    }

    public static void expandLZW(String filename, String filetype) throws IOException {
        bin = new BinaryIn(filename);
        String original = filename.replace("." + filetype, "");
        if(filetype.equals("zl"))
          original = original + ".tar";
        bout = new BinaryOut(original);
        String[] st = new String[L];
        int count;

        for(count = 0; count < R; count++)
            st[count] = "" + (char) count;
        st[count++] = "";

        int codeword = bin.readInt(W);
        if(codeword == R)
            return;
        String val = st[codeword];

        while(true) {
            bout.write(val);
            codeword = bin.readInt(W);
            if(codeword == R)
                break;
            String s = st[codeword];
            if(count == codeword)
                s = val + val.charAt(0);
            if(count < L)
                st[count++] = val + s.charAt(0);
            val = s;
        }
        bout.close();

        if(filetype.equals("zl"))
            DeschubsArc.main(new String[] { original });
    }

    public static void expandHuffman(String filename, String filetype) throws IOException {
        bin = new BinaryIn(filename);
        String original = filename.replace("." + filetype, "");
        bout = new BinaryOut(original);

        Node root = readTrie();
        int length = bin.readInt();

        for (int i = 0; i < length; i++) {
            Node x = root;
            while (!x.isLeaf()) {
                boolean bit = bin.readBoolean();
                if (bit)
                    x = x.right;
                else
                    x = x.left;
            }
            bout.write(x.ch);
        }
        bout.close();
    }


    private static Node readTrie() {
        boolean isLeaf = bin.readBoolean();
        if (isLeaf) {
            return new Node(bin.readChar(), -1, null, null);
        }
        else {
            return new Node('\0', -1, readTrie(), readTrie());
        }
    }

    public static void main(String[] args) throws IOException {
        for(int i = 0; i < args.length; i++) {
            String filename = args[i];
            String filetype = args[i].substring(args[i].lastIndexOf(".") + 1);
            if(filetype.charAt(1) == 'l')
                expandLZW(filename, filetype);
            else if(filetype.equals("hh"))
                expandHuffman(filename, filetype);
            else
                System.out.println("This file type not supported");
        }
    }
}
