import org.junit.Test;
import java.io.*;
import org.apache.commons.io.FileUtils;

public class SchubsLTest
{
    String fs = System.getProperty("file.separator");

@Test // checks if the program can successfully LZW a single file
    public void testSingleSchubs() throws IOException
    {
        SchubsL.main(new String[] { "src" + fs + "files" + fs + "alpha.txt" });

        FileInputStream file1 = new FileInputStream(new File("src" + fs + "files" + fs + "alpha.txt.ll"));
        FileInputStream file2 = new FileInputStream(new File("src" + fs + "files" + fs + "alphaRef.txt.ll"));

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

@Test // checks if the program can successfully LZW multiple (in this case, 3) files at once
    public void testMultipleSchubs() throws IOException
    {
        SchubsL.main(new String[] { "src" + fs + "files" + fs + "alpha.txt", "src" + fs + "files" + fs + "beta.txt", "src" + fs + "files" + fs + "gamma.txt" });

        FileInputStream file1 = new FileInputStream(new File("src" + fs + "files" + fs + "alpha.txt.ll"));
        FileInputStream file2 = new FileInputStream(new File("src" + fs + "files" + fs + "alphaRef.txt.ll"));

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

        file1 = new FileInputStream(new File("src" + fs + "files" + fs + "beta.txt.ll"));
        file2 = new FileInputStream(new File("src" + fs + "files" + fs + "betaRef.txt.ll"));

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

        file1 = new FileInputStream(new File("src" + fs + "files" + fs + "gamma.txt.ll"));
        file2 = new FileInputStream(new File("src" + fs + "files" + fs + "gammaRef.txt.ll"));

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

@Test // checks if the program can successfully un-LZW a single file
    public void testSingleDeschubs() throws IOException
    {
        SchubsL.main(new String[] { "src" + fs + "files" + fs + "alpha.txt" });
        Deschubs.main(new String[] { "src" + fs + "files" + fs + "alpha.txt.ll" });

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

@Test // checks if the program can successfully un-LZW multiple (in this case, 2) files at once
    public void testMultipleDeschubs() throws IOException
    {
        SchubsL.main(new String[] { "src" + fs + "files" + fs + "alpha.txt", "src" + fs + "files" + fs + "beta.txt", "src" + fs + "files" + fs + "gamma.txt" });
        Deschubs.main(new String[] { "src" + fs + "files" + fs + "beta.txt.ll", "src" + fs + "files" + fs + "gamma.txt.ll" });

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
