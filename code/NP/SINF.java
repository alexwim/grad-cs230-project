// PATTERN: NP_STORE_INTO_NONNULL_FIELD
// DEF: A value that could be null is stored into a field that has been annotated as @Nonnull.
// TYPE: False Negative

import javax.annotation.*;

public class SINF {
	@Nonnull Object o = new Object();
	
	public void foo() {
		o = definitelyNull();
	}
	
	public Object definitelyNull() {
		return null;
	}
}