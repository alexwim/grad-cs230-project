// PATTERN: NP_NONNULL_FIELD_NOT_INITIALIZED_IN_CONSTRUCTOR
// DEF: The field is marked as non-null, but isn't written to by the constructor.  The field might be initialized elsewhere during constructor, or might always be initialized before use.
// TYPE: False Negative

import javax.annotation.*;

public class NFNIIC {
	@Nonnull Object o;
	
	NFNIIC (NFNIIC other) {
		Boolean b = false;
		if (b) {
			o = other.o;
		}
	}
	
	public Object get() {
		return o;
	}
}