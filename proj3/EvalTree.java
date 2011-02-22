import java.io.InputStream;
import java.io.FileInputStream;

public class EvalTree {
    public static void main(String[] args) throws Exception {
        InputStream input = null;
        input = System.in;
		Interpreter interp;
        if ( args.length == 1 ) {
			if (args[0] == "-d"){
        		interp = new Interpreter(true);
			}
			else {
        		interp = new Interpreter();
			}
		}
		else {
        	interp = new Interpreter();
		}
        interp.interp(input);
    }
}
