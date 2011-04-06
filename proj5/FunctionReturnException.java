import org.antlr.runtime.tree.CommonTree;

// We don't care about serializing this exception
@SuppressWarnings("serial")
class FunctionReturnException extends Exception
{
  CommonTree retval;

  public FunctionReturnException()
  {
    super();             // call superclass constructor
    retval = null;
  }
  
  public FunctionReturnException(CommonTree _retval)
  {
    super("retval");     // call super class constructor
    retval = _retval;  // save message
  }
  
  public CommonTree getRetval()
  {
    return retval;
  }
}
