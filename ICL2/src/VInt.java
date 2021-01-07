
public class VInt implements IValue {
	int v;
	
	
	VInt(int v0) {
		v = v0;	
	}
	
	
	public int getval(){ 
		return v;
	}
	
	public String toString() {
		return String.valueOf(v);
	}
	}