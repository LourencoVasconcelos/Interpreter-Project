public class ASTNum implements ASTNode {

IValue val;

        public IValue eval(Environment e) { return val; }

        public ASTNum(IValue n)
        {
	   val = n;
        }

        public void compile(CodeBlock c, Environment env) {
        	c.emit( String.format("sipush %d", val) );
        }
}

