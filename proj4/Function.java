import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;
import java.util.List;


public class Function {

	private String mName;
	private CommonTree mBlock;
	private List<CommonTree> mParams;

	public Function(String _name, CommonTree _block, List<CommonTree> _params) {
		this.mName = _name;
		this.mBlock = _block;
		this.mParams = _params;
	}

	public List<CommonTree> getParams()
	{
		return mParams;
	}

	public CommonTree getParam(int i)
	{
		return mParams.get(i);
	}


}
