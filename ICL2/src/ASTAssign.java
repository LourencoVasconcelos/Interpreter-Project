
public class ASTAssign implements ASTNode {

	ASTNode lhs, rhs;
	
	public ASTAssign(ASTNode l, ASTNode r)
    {
	lhs = l; rhs = r;
    }
	
	public IValue eval(Environment env) throws TypeError, NotFound {
		IValue v1 = lhs.eval(env);
		if (v1 instanceof VMCell) {
		IValue v2 = rhs.eval(env);
		if(v2 instanceof VMCell) {
			IValue vv = ((VMCell) v2).get() ;
			((VMCell)v1).set(vv);
			return vv;
		}
		((VMCell)v1).set(v2);
		return v2;
		}
		throw new TypeError("assignment := lhs is not a reference");
		}

	@Override
	public void compile(CodeBlock c, Environment e) {

	}

}
