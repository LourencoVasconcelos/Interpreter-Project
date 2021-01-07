public class ASTSub implements ASTNode {

ASTNode lhs, rhs;


	public IValue eval(Environment env) throws TypeError, NotFound {
		IValue v1 = lhs.eval(env);
		if (v1 instanceof VInt) {
			IValue v2 = rhs.eval(env);
	
			if (v2 instanceof VInt) 
				return new VInt(  ((VInt)v1).getval()- ((VInt)v2).getval() );
		}
		throw new TypeError("-: argument is not an integer");
		}
    
        public ASTSub(ASTNode l, ASTNode r)
        {
		lhs = l; rhs = r;
        }
        
        public void compile(CodeBlock c, Environment env) {
        	lhs.compile(c,env);
        	rhs.compile(c,env);
        	c.emit("isub");
        }
}

