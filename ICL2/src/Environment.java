import java.util.HashMap;
import java.util.Map;

public class Environment {

	Map<String,IValue> map;
	Map<String,Coordinates> cmap;
	Environment ancestor;
	
	public Environment() {
		map = new HashMap<String,IValue>();
		cmap= new HashMap<String,Coordinates>();
	}
	
	public Environment(Environment prev) {
		map = new HashMap<String,IValue>();
		cmap= new HashMap<String,Coordinates>();
		this.ancestor = prev;
		
	}
	
	Environment beginScope() {
		return new Environment(this);
	}
	
	Environment endScope() {
		return ancestor;
	}
	
	void assoc(String id, IValue val) {
		map.put(id, val);
	}
	
	IValue find(String id) throws NotFound {
		if(map.get(id)==null)
			return ancestor.find(id);
		
		return map.get(id);
	}

	
	
	
	//Compiler
	
	int depth() {
		if(ancestor!=null)
			return ancestor.adepth(0);
		return 0;
	}
	int adepth(int depth) {
		if(ancestor!=null)
			return 1+ancestor.adepth(depth);
		return 1+depth;
	}
	
	void assoc(String id, Coordinates c) {
		cmap.put(id, c);
	}
	
	Coordinates findc(String id) {
		if(cmap.get(id)==null)
			return ancestor.findc(id);
		return cmap.get(id);
	}
}
