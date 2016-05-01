// PATTERN: NP_ARGUMENT_MIGHT_BE_NULL
// DEF: A parameter to this method has been identified as a value that should always be checked to see whether or not it is null, but it is being dereferenced without a preceding null check.
// TYPE: False Negative

import javax.annotation.*;

public class AMBN {
	public Boolean foo(@CheckForNull Boolean b) { // b is never checked!
		return b;
	}
	
	public void bar() {
		Boolean b = foo(null);
		b.toString(); // Derefenced here
		
		foo(null).toString(); // Also dereferenced here
	}
}