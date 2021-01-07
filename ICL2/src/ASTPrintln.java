
public class ASTPrintln implements ASTNode {

	ASTNode print;
	
	public ASTPrintln(ASTNode p) {
		print = p;
	}
	@Override
	public IValue eval(Environment e) throws TypeError, NotFound {
		if(print!=null) {
			IValue v1 = print.eval(e);
			System.out.println(v1.toString());
		}
		else {
			System.out.println();
		}
		return new VEmpty();
	}

	@Override
	public void compile(CodeBlock c, Environment e) {

	}

}
