// PATTERN: NP_ARGUMENT_MIGHT_BE_NULL
// TYPE: False Negative

import javax.annotation.*;

public class NPAMBN {
	public Boolean foo(@CheckForNull Boolean b) { // b is never checked!
		return b;
	}
	
	public void bar(Boolean b) {
		foo(null).toString(); // Derefenced here
	}
}