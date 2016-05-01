// PATTERN: NP_NONNULL_PARAM_VIOLATION
// DEF: This method passes a null value as the parameter of a method which must be non-null. Either this parameter has been explicitly marked as @Nonull, or analysis has determined that this parameter is always dereferenced.
// TYPE: False Negative

import javax.annotation.*;

public class NPV {
	public void bar(@Nonnull Object o) {
		o.toString();
	}
	
	public void foo() {
		bar(definitelyNull());
	}
	
	public Object definitelyNull() {
		return null;
	}
}