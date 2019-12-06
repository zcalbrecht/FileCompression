/**
 * Software Engineering II
 * Fall 2019
 * Zachary Albrecht
 *
 * Based on Sedgewick's file compression unit
 *
 * Archive multiple files in a compressed LZW archive
 * compile: javac SchubsArc.java
 * execute: java SchubsArc <archivename>.zl <file1> <file2> <file3> ...
 */

import java.io.*;

public class SchubsArc {
		public static void main(String[] args) throws IOException {
				BinaryIn bin = null;
				BinaryOut bout = null;
				File in = null;
				char sep = (char) 255;

				String filetype = args[0].substring(args[0].lastIndexOf(".") + 1);
				if(filetype.charAt(1) == 'h'){
						System.out.println("This file type not supported");
						return;
				}

				try {
						bout = new BinaryOut(args[0]);

						for(int i = 1; i < args.length; i++) {

								in = new File(args[i]);
								if(!in.exists() || !in.isFile())
										return;

								long filesize = in.length();
								int filenamesize = args[i].length();

								bout.write(filenamesize);
								bout.write(sep);
								bout.write(args[i]);
								bout.write(sep);
								bout.write(filesize);
								bout.write(sep);

								bin = new BinaryIn(args[i]);
								while(!bin.isEmpty())
										bout.write(bin.readChar());
						}
				}
				finally {
						if(bout != null)
								bout.close();

						if(filetype.charAt(1) == 'l')
                SchubsL.main(new String[] { args[0] });
            else if(filetype.charAt(1) == 'h')
                System.out.println("This file type not supported");
				}
		}
}
