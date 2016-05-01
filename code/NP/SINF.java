// PATTERN: NP_STORE_INTO_NONNULL_FIELD
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