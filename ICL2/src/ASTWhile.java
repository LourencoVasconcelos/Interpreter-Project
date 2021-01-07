
public class ASTWhile implements ASTNode {

	ASTNode cond, act;
	
	public ASTWhile(ASTNode t1, ASTNode t2) {
		cond = t1;
		act=t2;
	}
	@Override
	public IValue eval(Environment e) throws TypeError, NotFound {
		IValue v1 = cond.eval(e);
		if( v1 instanceof VBool) {
			while(((VBool)v1).getval()) {
				act.eval(e);
				v1 = cond.eval(e);
			}
			return new VEmpty();
		}
		else {
			throw new TypeError("while: condition is not a boolean");
		}
		}

	@Override
	public void compile(CodeBlock c, Environment e) {

	}

}
