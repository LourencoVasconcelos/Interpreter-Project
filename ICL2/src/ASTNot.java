
public class ASTNot implements ASTNode {

	ASTNode node;
	
	public ASTNot(ASTNode n) {
		node = n;
	}
	
	@Override
	public IValue eval(Environment e) throws TypeError, NotFound {
		IValue v1 = node.eval(e);
		if(v1 instanceof VBool) {
			return new VBool(!((VBool)v1).getval());
		}
		throw new TypeError("logical operation: is not a boolean value");
	}

	@Override
	public void compile(CodeBlock c, Environment e) {

	}

}
