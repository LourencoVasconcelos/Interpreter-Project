
public class ASTEq implements ASTNode {

	ASTNode lhs, rhs;
	String op;
	
	
	public ASTEq(ASTNode n1, ASTNode n2, String op) {
		this.lhs = n1;
		this.rhs = n2;
		this.op = op;
	}
	
	
	
	public IValue eval(Environment env) throws TypeError, NotFound {
		IValue v1 = lhs.eval(env);
		if (v1 instanceof VInt) {
		IValue v2 = rhs.eval(env);
		if (v2 instanceof VInt) {
		
			if( op.equalsIgnoreCase("==") ) {
				return new VBool(((VInt)v1).getval() == ((VInt)v2).getval());
			}
			if( op.equalsIgnoreCase("<") ) {
				return new VBool(((VInt)v1).getval() < ((VInt)v2).getval());
			}
			if( op.equalsIgnoreCase(">") ) {
				return new VBool(((VInt)v1).getval() > ((VInt)v2).getval());
			}
			if( op.equalsIgnoreCase("<=") ) {
				return new VBool(((VInt)v1).getval() <= ((VInt)v2).getval());
			}
			if( op.equalsIgnoreCase(">=") ) {
				return new VBool(((VInt)v1).getval() >= ((VInt)v2).getval());
			}
			
		}
		throw new TypeError("relational operation: arguments are not from the same type");
		}
		
		if (v1 instanceof VBool) {
			IValue v2 = rhs.eval(env);
			if(v2 instanceof VBool) {
				if( op.equalsIgnoreCase("==")) {
					return new VBool( ((VBool)v1).getval() == ((VBool)v2).getval() );
				}
				if( op.equalsIgnoreCase("<") ) {
					if( ((VBool)v1).getval() == false && ((VBool)v2).getval() == true)
						return new VBool(true);
					else
						return new VBool(false);
				}
				if( op.equalsIgnoreCase(">") ) {
					if( ((VBool)v1).getval() == true && ((VBool)v2).getval() == false)
						return new VBool(true);
					else
						return new VBool(false);
				}
				if( op.equalsIgnoreCase("<=") ) {
					if( (((VBool)v1).getval() == false && ((VBool)v2).getval() == true) ||
							((VBool)v1).getval()==((VBool)v2).getval() )
						return new VBool(true);
					else
						return new VBool(false);
				}
				if( op.equalsIgnoreCase(">=") ) {
					if( ((VBool)v1).getval() == false && ((VBool)v2).getval() == true)
						return new VBool(false);
					else
						return new VBool(true);
				}
				
			}
			else {
				throw new TypeError("relational operation: arguments are not from the same type");
			}
		}
		
		throw new TypeError("relation operation: argument isn't neither an integer or boolean");
	}
	
	
	@Override
	public void compile(CodeBlock c, Environment e) {
		
	}

}
