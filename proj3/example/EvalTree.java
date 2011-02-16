import java.io.InputStream;
import java.io.FileInputStream;

public class EvalTree {
    public static void main(String[] args) throws Exception {
        InputStream input = null;
        if ( args.length>0 ) input = new FileInputStream(args[0]);
        else input = System.in;
        Interpreter interp = new Interpreter();
        interp.interp(input);
    }
}
