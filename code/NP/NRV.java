// PATTERN: NP_NONNULL_RETURN_VIOLATION
// TYPE: False Negative

import javax.annotation.*;

public class NRV {
	
	public @Nonnull Object foo() {
		return definitelyNull();
	}
	
	public Object definitelyNull() {
		return null;
	}
}