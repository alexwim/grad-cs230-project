// PATTERN: NP_NONNULL_RETURN_VIOLATION
// DEF: This method may return a null value, but the method (or a superclass method which it overrids) is declared to return @Nonnull.
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