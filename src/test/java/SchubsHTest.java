import org.junit.Test;
import java.io.*;
import org.apache.commons.io.FileUtils;

public class SchubsHTest
{
    String fs = System.getProperty("file.separator");

@Test // checks if the program can successfully Huffman a single file
    public void testSingleSchubs() throws IOException
    {
        SchubsH.main(new String[] { "src" + fs + "files" + fs + "alpha.txt" });

        FileInputStream file1 = new FileInputStream(new File("src" + fs + "files" + fs + "alpha.txt.hh"));
        FileInputStream file2 = new FileInputStream(new File("src" + fs + "files" + fs + "alphaRef.txt.hh"));

        int i1 = file1.read();
        int i2 = file2.read();
        while (i1 != -1) {
            if (i1 != i2) {
                assert(false);
            }
            i1 = file1.read();
            i2 = file2.read();
        }
        file1.close();
        file2.close();

        assert(true); // this isn't tautological, just asserting true if it doesn't assert false prior

    }

@Test // checks if the program can successfully Huffman multiple (in this case, 3) files at once
    public void testMultipleSchubs() throws IOException
    {
        SchubsH.main(new String[] { "src" + fs + "files" + fs + "alpha.txt", "src" + fs + "files" + fs + "beta.txt", "src" + fs + "files" + fs + "gamma.txt" });

        FileInputStream file1 = new FileInputStream(new File("src" + fs + "files" + fs + "alpha.txt.hh"));
        FileInputStream file2 = new FileInputStream(new File("src" + fs + "files" + fs + "alphaRef.txt.hh"));

        int i1 = file1.read();
        int i2 = file2.read();
        while (i1 != -1) {
            if (i1 != i2) {
                assert(false);
            }
            i1 = file1.read();
            i2 = file2.read();
        }
        file1.close();
        file2.close();

        file1 = new FileInputStream(new File("src" + fs + "files" + fs + "beta.txt.hh"));
        file2 = new FileInputStream(new File("src" + fs + "files" + fs + "betaRef.txt.hh"));

        i1 = file1.read();
        i2 = file2.read();
        while (i1 != -1) {
            if (i1 != i2) {
                assert(false);
            }
            i1 = file1.read();
            i2 = file2.read();
        }
        file1.close();
        file2.close();

        file1 = new FileInputStream(new File("src" + fs + "files" + fs + "gamma.txt.hh"));
        file2 = new FileInputStream(new File("src" + fs + "files" + fs + "gammaRef.txt.hh"));

        i1 = file1.read();
        i2 = file2.read();
        while (i1 != -1) {
            if (i1 != i2) {
                assert(false);
            }
            i1 = file1.read();
            i2 = file2.read();
        }
        file1.close();
        file2.close();

        assert(true); // this isn't tautological, just asserting true if it doesn't assert false prior
    }

@Test // checks if the program can successfully un-Huffman a single file
    public void testSingleDeschubs() throws IOException
    {
        SchubsH.main(new String[] { "src" + fs + "files" + fs + "alpha.txt" });
        Deschubs.main(new String[] { "src" + fs + "files" + fs + "alpha.txt.hh" });

        FileInputStream file1 = new FileInputStream(new File("src" + fs + "files" + fs + "alpha.txt"));
        FileInputStream file2 = new FileInputStream(new File("src" + fs + "files" + fs + "alphaRef.txt"));

        int i1 = file1.read();
        int i2 = file2.read();
        while (i1 != -1) {
            if (i1 != i2) {
                assert(false);
            }
            i1 = file1.read();
            i2 = file2.read();
        }
        file1.close();
        file2.close();

        assert(true); // this isn't tautological, just asserting true if it doesn't assert false prior
    }

@Test // checks if the program can successfully un-Huffman multiple (in this case, 2) files at once
    public void testMultipleDeschubs() throws IOException
    {
        SchubsH.main(new String[] { "src" + fs + "files" + fs + "alpha.txt", "src" + fs + "files" + fs + "beta.txt", "src" + fs + "files" + fs + "gamma.txt" });
        Deschubs.main(new String[] { "src" + fs + "files" + fs + "beta.txt.hh", "src" + fs + "files" + fs + "gamma.txt.hh" });

        FileInputStream file1 = new FileInputStream(new File("src" + fs + "files" + fs + "beta.txt"));
        FileInputStream file2 = new FileInputStream(new File("src" + fs + "files" + fs + "betaRef.txt"));

        int i1 = file1.read();
        int i2 = file2.read();
        while (i1 != -1) {
            if (i1 != i2) {
                assert(false);
            }
            i1 = file1.read();
            i2 = file2.read();
        }
        file1.close();
        file2.close();

        file1 = new FileInputStream(new File("src" + fs + "files" + fs + "gamma.txt"));
        file2 = new FileInputStream(new File("src" + fs + "files" + fs + "gammaRef.txt"));

        i1 = file1.read();
        i2 = file2.read();
        while (i1 != -1) {
            if (i1 != i2) {
                assert(false);
            }
            i1 = file1.read();
            i2 = file2.read();
        }
        file1.close();
        file2.close();

        assert(true); // this isn't tautological, just asserting true if it doesn't assert false prior
    }
}
