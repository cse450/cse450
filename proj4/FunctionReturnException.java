import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;

import java.io.*;

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
