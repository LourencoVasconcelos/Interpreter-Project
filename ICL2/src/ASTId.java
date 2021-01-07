public class ASTId implements ASTNode {

    String id;

    public IValue eval(Environment e) throws NotFound  {
        
            try {
    				return e.find(id);
			} catch (NotFound e1) {
				throw new NotFound("identifier not found");
			}
    }

    public ASTId(String id) {
        this.id = id;
        }

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

}