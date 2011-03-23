import java.lang.Number;

import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;

public class Value extends Number {
	private Number mValue;
	private int mType;

	public Value( Number value, int type ) {
		super();
		mValue = value;
		mType = type;
	}
	
	@Override
	public double doubleValue() {
		return mValue.doubleValue();
	}
	
	@Override
	public float floatValue() {
		return mValue.floatValue();
	}
	
	@Override
	public long longValue() {
		return mValue.longValue();
	}
	
	@Override
	public int intValue() {
		return mValue.intValue();
	}
	
	public int getType() {
		return  mType;
	}
	
	public Number getValueBasedOnType() {
		switch ( mType ) {
			case LogoTurtleParser.INT:
				return this.intValue();
			case LogoTurtleParser.FLOAT:
				return this.floatValue();
			default:
				return null;
		}
	}
	
	@Override
	public boolean equals( Object other ) {
		return this.getValueBasedOnType().equals( ((Value)other).getValueBasedOnType() );
	}
}
