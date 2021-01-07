
public interface ASTNode {

	IValue eval(Environment e) throws TypeError, NotFound;
	
	void compile(CodeBlock c, Environment e);
}
