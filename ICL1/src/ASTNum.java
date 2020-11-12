public class ASTNum implements ASTNode {

int val;

        public int eval(Environment e) { return val; }

        public ASTNum(int n)
        {
	   val = n;
        }

        public void compile(CodeBlock c, Environment env) {
        	c.emit( String.format("sipush %d", val) );
        }
}

