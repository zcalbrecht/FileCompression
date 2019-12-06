/**
 * Software Engineering II
 * Fall 2019
 * Zachary Albrecht
 *
 * Based on Sedgewick's file compression unit
 *
 * Compress multiple files at once through LZW encoding
 * compile: javac SchubsL.java
 * execute: java SchubsL <file1> <file2> <file3> ...
 */


public class SchubsL {


    private static BinaryIn bin;
    private static BinaryOut bout;
    private static final int R = 256;
    private static final int L = 4096;
    private static final int W = 12;

    public static void compress(String filename) {
        bin = new BinaryIn(filename);
        bout = new BinaryOut(filename + ".ll");
        String input = bin.readString();
        TST<Integer> st = new TST<Integer>();
        for(int i = 0; i < R; i++) {
            st.put("" + (char) i, i);
        }
        int code = R+1;
        while(input.length() > 0)
        {
            String s = st.longestPrefixOf(input);
            bout.write(st.get(s), W);
            int t = s.length();
            if(t < input.length() && code < L)
                st.put(input.substring(0, t + 1), code++);
            input = input.substring(t);
        }
        bout.write(R, W);
        bout.close();
    }

    public static void compressArchive(String filename) {
        bin = new BinaryIn(filename);
        bout = new BinaryOut(filename);
        String input = bin.readString();
        TST<Integer> st = new TST<Integer>();
        for(int i = 0; i < R; i++)
            st.put("" + (char) i, i);
        int code = R+1;

        while(input.length() > 0) {
            String s = st.longestPrefixOf(input);
            bout.write(st.get(s), W);
            int t = s.length();
            if(t < input.length() && code < L)
                st.put(input.substring(0, t + 1), code++);
            input = input.substring(t);
        }
        bout.write(R, W);
        bout.close();
    }

    public static void main(String[] args) {
        for(int i = 0; i < args.length; i++) {
            String filename = args[i];
            String filetype = args[i].substring(args[i].lastIndexOf(".") + 1);
            if(filetype.equals("zl"))
                compressArchive(filename);
            else
                compress(filename);
        }
    }
}
