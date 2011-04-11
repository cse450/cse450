tree grammar LogoTree;

options {
	tokenVocab=LogoJVM1;
	ASTLabelType=CommonTree;
	output=template;
}

@header {
	import java.util.HashMap;
}

@members {
	HashMap locals;
}

prog[int numOps, HashMap locals] @init { this.locals = locals; }
  : (s+=stat)+ -> initJasmin( instructions={$s},
			      maxStackDepth={numOps+2},
			      maxLocals={locals.size()+1}
  ;

stat:;

