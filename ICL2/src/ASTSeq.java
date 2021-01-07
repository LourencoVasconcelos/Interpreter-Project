import java.util.LinkedList;
import java.util.List;

public class ASTSeq implements ASTNode{

	private List<ASTNode> seq;
	
	public ASTSeq() {
		seq = new LinkedList<ASTNode>();
	}
	
	@Override
	public IValue eval(Environment e) throws TypeError, NotFound {

		IValue result = null;
		
		for(int i = 0; i<seq.size();i++) {
			result = seq.get(i).eval(e);
		}
		return result;
	}

	public void addNode(ASTNode node) {
		seq.add(node);
	}
	@Override
	public void compile(CodeBlock c, Environment e) {
		
	}

}
