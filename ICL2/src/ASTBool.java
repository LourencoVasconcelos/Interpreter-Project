
public class ASTBool implements ASTNode{

	Boolean b;
	
	public ASTBool(String t) {
		if(t.equalsIgnoreCase("true"))
			b=true;
		else
			b=false;
	}
	@Override
	public IValue eval(Environment e) {

		return new VBool(b);
	}

	@Override
	public void compile(CodeBlock c, Environment e) {
		
	}

}
