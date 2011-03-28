import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;
import java.util.ArrayList;


public class Function {

	String mName;
	CommonTree mBlock;
	ArrayList<String> mParams;

	public Function() {
		this.mName = new String();
		this.mBlock = null;
		this.mParams = new ArrayList<String>();
	}

	public Function(String _name, CommonTree _block, ArrayList<String> _params) {
		this.mName = _name;
		this.mBlock = _block;
		this.mParams = _params;
	}

	public ArrayList<String> getParams()
	{
		return mParams;
	}

	public String getParam(int i)
	{
		return mParams.get(i);
	}


}

		
