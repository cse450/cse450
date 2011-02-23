import java.io.InputStream;
import java.io.FileInputStream;
import java.util.*;

public class EvalTree {
	@SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
			InputStream input = null;
			input = System.in;
			
			Interpreter interpreter = new Interpreter();
			
			if (args.length > 0 && args[0].equals("d")){
				System.out.println("going to debug");
				interpreter.isdebugging = true;
			}				
			
			interpreter.interp(input);
    }
}
