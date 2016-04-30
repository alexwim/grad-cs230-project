// PATTERN: NP_NONNULL_PARAM_VIOLATION
// TYPE: False Negative

import javax.annotation.*;

public class NPV {
	public void bar(@Nonnull Object o) {
		o.toString();
	}
	
	public void foo(Boolean b) {
		bar(maybe(b));
	}
	
	public Object maybe(Boolean b) {
		if (b) {
			return new Object();
		} else {
			return null;
		}
	}
}