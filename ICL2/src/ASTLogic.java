
public class ASTLogic implements ASTNode {

	ASTNode l1, l2;
	String op;
	
	public ASTLogic(ASTNode lhs, ASTNode rhs, String op) {
		l1 = lhs;
		l2 = rhs;
		this.op=op;
	}
	
	@Override
	public IValue eval(Environment e) throws TypeError, NotFound {
		IValue v1 = l1.eval(e);
		if (v1 instanceof VBool) {
			IValue v2=l2.eval(e);
			if(v2 instanceof VBool) {
				if(op.equalsIgnoreCase("&&"))
					return new VBool( ((VBool)v1).getval() && ((VBool)v2).getval());
				if(op.equalsIgnoreCase("||"))
					return new VBool( ((VBool)v1).getval() || ((VBool)v2).getval());
				
			}
			throw new TypeError("logical operation: right is not a boolean value");
		}
		throw new TypeError("logical operation: left is not a boolean value");
	}

	@Override
	public void compile(CodeBlock c, Environment e) {

	}

}
