
public class ASTPrint implements ASTNode {

	ASTNode print;
	
	public ASTPrint(ASTNode p1) {
		print = p1;
	}
	@Override
	public IValue eval(Environment e) throws TypeError, NotFound {
		if(print!=null) {
		IValue v1 = print.eval(e);
		System.out.print(v1.toString());
		}
		else {
			System.out.print("");
		}
		
		return new VEmpty();
	}

	@Override
	public void compile(CodeBlock c, Environment e) {

	}

}
