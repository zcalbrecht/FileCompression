/**
 * Software Engineering II
 * Fall 2019
 * Zachary Albrecht
 *
 * Based on Sedgewick's file compression unit
 *
 * Untar archives after being compressed and extract files 
 * compile: javac DeschubsArc.java
 * execute: java DeschubsArc <archive>
 */

import java.io.IOException;
import java.io.File;

public class DeschubsArc
{
    public static void main(String[] args) throws IOException {
        BinaryIn bin = null;
        BinaryOut bout = null;

        char sep = (char) 255;
        bin = new BinaryIn(args[0]);

        while(!bin.isEmpty()) {
            try {
                int filenamesize = bin.readInt();
                sep = bin.readChar();
                String filename="";
                for(int i = 0; i < filenamesize; i++)
                    filename += bin.readChar();

                sep = bin.readChar();
                long filesize = bin.readLong();
                sep = bin.readChar();
                bout = new BinaryOut(filename);
                for(int i = 0; i < filesize; i++)
                  	bout.write(bin.readChar());
            } finally {
                if(bout != null)
                    bout.close();
            }
        }
    }
}
