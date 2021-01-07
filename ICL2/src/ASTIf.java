
public class ASTIf implements ASTNode {

	ASTNode cond, lhs, rhs;
	
	public ASTIf (ASTNode condition, ASTNode first, ASTNode second) {
		cond = condition;
		lhs = first;
		rhs = second;
	}
	
	
	
	@Override
	public IValue eval(Environment e) throws TypeError, NotFound {
		
		IValue v0 = cond.eval(e);
		if(v0 instanceof VBool) {
			if ( ((VBool)v0).getval() ) {
				return lhs.eval(e);
			}
			else if(rhs!=null)
				return rhs.eval(e);
			else 
				throw new TypeError("if: condition is false and there's no else"); // no else sysout: "v0 is not true" 
		}
		
		throw new TypeError("Condition is not a boolean");
	}

	@Override
	public void compile(CodeBlock c, Environment e) {

	}

}
