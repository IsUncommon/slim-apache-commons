package is.uncommon.slimcommons;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

public class IOUtils {

    private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;
    public static final int EOF = -1;

    public static void closeQuietly(Reader input) {
        closeQuietly((Closeable) input);
    }

    public static void closeQuietly(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException ioe) {
            // ignore
        }
    }

    public static void copy(InputStream input, Writer output)
            throws IOException {
        InputStreamReader in = new InputStreamReader(input);
        copy(in, output);
    }

    public static int copy(Reader input, Writer output) throws IOException {
        long count = copyLarge(input, output);
        if (count > Integer.MAX_VALUE) {
            return -1;
        }
        return (int) count;
    }

    public static long copyLarge(Reader input, Writer output)
            throws IOException {
        char[] buffer = new char[DEFAULT_BUFFER_SIZE];
        long count = 0;
        int n = 0;
        while (-1 != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
            count += n;
        }
        return count;
    }

    /**
     * Get the contents of an <code>InputStream</code> as a String.
     * @param input the <code>InputStream</code> to read from
     * @param encoding The name of a supported character encoding. See the
     *   <a href="http://www.iana.org/assignments/character-sets">IANA
     *   Charset Registry</a> for a list of valid encoding types.
     * @return the requested <code>String</code>
     * @throws IOException In case of an I/O problem
     */
    public static String toString( InputStream input,
            String encoding )
            throws IOException
    {
        StringWriter sw = new StringWriter();
        CopyUtils.copy( input, sw, encoding );
        return sw.toString();
    }

}
