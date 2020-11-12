public class ASTId implements ASTNode {

    String id;

    public int eval(Environment e)  {
        
            try {
				return e.find(id);
			} catch (NotFound e1) {
				System.out.println("identifier not found ");;
			}
			return -1;
    }

    public ASTId(String id) {
        this.id = id;
        }

    public void compile(CodeBlock c, Environment env) {
		Coordinates coord = env.findc(id);
		int depth = env.depth();
    	c.emit("aload_3");
 //   	if(env.depth()>0)
    	while(coord.getDepth()<depth-1) {
    		depth=depth-2;
    		c.emit(String.format("getfield frame_%d/sl Lframe_%d;", env.depth()-1,depth));
    		
    	}
//    	else
//    		c.emit(bytecode);
    	c.emit(String.format("getfield frame_%d/v%d I", coord.getDepth(),coord.getVar()));
	}

}