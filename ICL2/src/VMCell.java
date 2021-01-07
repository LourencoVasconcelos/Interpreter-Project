
public class VMCell implements IValue {
	
	IValue v;
	
	VMCell(IValue v0) { 
		v = v0; 
	}
	
	IValue get() {
		return v;
	}
	
	void set(IValue v1) {
		v = v1;
	}

	public String toString() {
		return String.valueOf(v);
	}
}