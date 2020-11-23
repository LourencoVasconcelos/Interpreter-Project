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

    //   	if(env.depth()>0)
    public void compile(CodeBlock c, Environment env) {
		Coordinates coord = env.findc(id);
		int depth = env.depth();
    	c.emit("aload_3");
    	while(coord.getDepth()<depth-1) {
    		c.emit(String.format("getfield frame_%d/sl Lframe_%d;", depth-1 ,depth-2));
    		depth=depth-1;
    		
    	}
    	c.emit(String.format("getfield frame_%d/v%d I", coord.getDepth(),coord.getVar()));
    }
//    	else
//    		c.emit(bytecode);

}