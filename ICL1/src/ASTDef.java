import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;

public class ASTDef implements ASTNode {

	List<String> ids;
	List<ASTNode> inits;
	
	ASTNode body;
	
	public int eval(Environment e) {
		int preval=0;
		for(int i =0;  i<inits.size(); i++) {
			preval = inits.get(i).eval(e);
			e = e.beginScope();
			e.assoc(ids.get(i), preval);
		}
		int endval = body.eval(e);
		for(int j =0;  j<inits.size(); j++) {
			e = e.endScope();
		}
		return endval;
	}

	public ASTDef() {
		ids = new LinkedList<String>();
		inits = new LinkedList<ASTNode>();
	}
	
	public void add(String id, ASTNode init) {
		ids.add(id);
		inits.add(init);
	}
	
	public ASTNode body(ASTNode body) {
		this.body = body;
		return this;
	}
	
	
	public void compile(CodeBlock c, Environment env) {
		
		//beginscope
		Environment e = env.beginScope();
		int depth = env.depth();
		try {
		FileOutputStream file = new FileOutputStream(String.format("frame_%d.j", depth));
		PrintStream p = new PrintStream(file);
		p.append(String.format(".class public frame_%d\n",depth));
		p.append(".super java/lang/Object\n");
		
		if(depth==0) {
			   c.emit("new java/lang/Object");
			   c.emit("dup");
			   c.emit("invokespecial java/lang/Object/<init>()V");
			   c.emit("astore_3");
		}
		
		c.emit(String.format("new frame_%d", depth));
		c.emit("dup");
		c.emit(String.format("invokespecial frame_%d/<init>()V", depth));
		c.emit("dup");
		c.emit("aload_3");
		
		if(depth==0) {
			p.append(".field public sl Ljava/lang/Object;\n");
			c.emit(String.format("putfield frame_%d/sl Ljava/lang/Object;",depth));
		}
		else {
			p.append(String.format(".field public sl Lframe_%d;\n",depth-1));
			c.emit(String.format("putfield frame_%d/sl Lframe_%d;", depth, depth-1));
		}
		c.emit("dup");
		c.emit("astore_3");
		
		//assoc
		for(int i=0; i<inits.size();i++) {
			p.append(String.format(".field public v%d I\n", i));
			c.emit("dup");
			e.assoc(ids.get(i), new Coordinates(depth, i));
			inits.get(i).compile(c, e);
			c.emit(String.format("putfield frame_%d/v%d I", depth, i));
		}
		
		p.append("\n");
		p.append(".method public <init>()V\n");
		p.append("aload_0\n");
		p.append("invokenonvirtual java/lang/Object/<init>()V\n");
		p.append("return\n");
		p.append("\n\n");
		
		//in
		c.emit("pop");
		
		//body
		body.compile(c, e);
		
		//endscope
		c.emit("aload_3");
		if(depth==0)
			c.emit(String.format("getfield frame_%d/sl Ljava/lang/Object;", depth));
		else
			c.emit(String.format("getfield frame_%d/sl Lframe_%d;", depth, depth-1));
		
		c.emit("astore_3");
		p.append(".end method");
		e.endScope();
		
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
	}
}
