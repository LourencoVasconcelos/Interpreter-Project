
public class VBool implements IValue {
	
	boolean b;
	
	public VBool(boolean t) {
		b=t;
	}

	public boolean getval(){ 
		return b;
	}
	
	public String toString() {
		return String.valueOf(b);
	}
}
