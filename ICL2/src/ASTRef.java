
public class ASTRef implements ASTNode {

	ASTNode id;
	
	public ASTRef (ASTNode id ) {
		this.id = id;
	}
	
	
	@Override
	public IValue eval(Environment e) throws TypeError, NotFound {
		IValue r = id.eval(e);
		if (r instanceof VMCell)
			return ((VMCell) r).get();
		throw new TypeError("This reference doesn't have any memory cell associated");
	}

	@Override
	public void compile(CodeBlock c, Environment e) {

	}

}
