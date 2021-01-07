
public class ASTNew implements ASTNode {

	ASTNode n;
	
	public ASTNew(ASTNode v0) {
		n=v0;
	}
	
	@Override
	public IValue eval(Environment e) throws TypeError, NotFound {

		return new VMCell( n.eval(e) );
		
	}

	@Override
	public void compile(CodeBlock c, Environment e) {
	}

}
