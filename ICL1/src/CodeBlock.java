
import java.io.PrintStream;
public class CodeBlock {

	String code[];
	int pos;
	
	public CodeBlock() {
		pos=0;
		code = new String[100];
	}
	void emit(String bytecode) {
		code[pos] = bytecode;
		pos++;
	}
	
	void dump(PrintStream f) {

		f.append(".class public finishfile\r\n"
				+ ".super java/lang/Object\r\n"
				+ "\r\n"
				+ ";\r\n"
				+ "; standard initializer\r\n"
				+ ".method public <init>()V\r\n"
				+ "   aload_0\r\n"
				+ "   invokenonvirtual java/lang/Object/<init>()V\r\n"
				+ "   return\r\n"
				+ ".end method\r\n"
				+ "\r\n"
				+ ".method public static main([Ljava/lang/String;)V\r\n"
				+ "       ; set limits used by this method\r\n"
				+ "       .limit locals  4\r\n"
				+ "       .limit stack 256\r\n"
				+ "\r\n"
				+ "       ; setup local variables:\r\n"
				+ "\r\n"
				+ "       ;    1 - the PrintStream object held in java.lang.System.out\r\n"
				+ "       getstatic java/lang/System/out Ljava/io/PrintStream;\r\n"
				+ "\r\n"
				+ "       ; place your bytecodes here\r\n"
				+ "       ; START  \r\n");
				
				f.append("\n");
				for(int i=0;i<pos;i++) {
					f.append("\t   "+code[i]+"\n");
				}
				f.append("\n\n");
				f.append("       ; END\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "       ; convert to String;\r\n"
				+ "       invokestatic java/lang/String/valueOf(I)Ljava/lang/String;\r\n"
				+ "       ; call println \r\n"
				+ "       invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V\r\n"
				+ "\r\n"
				+ "       return\r\n"
				+ "\r\n"
				+ ".end method");

		
		
	}
}
