// We wont want to serialize this
@SuppressWarnings("serial")
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
			case LogoJVM1Parser.INT:
				return this.intValue();
			case LogoJVM1Parser.FLOAT:
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
