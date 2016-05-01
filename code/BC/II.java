// PATTERN: BC_IMPOSSIBLE_INSTANCEOF
// DEF: This instanceof test will always return false.  Although this is safe, make sure it isn't an indication of some misunderstanding or some other logic error.
// TYPE: False Positive

import javax.annotation.*;

public class II {
	
	public Boolean foo(int in) {
		Object o = new Object();
		
		for(int i = 0; i < in; ++i) {
			o = "";
		}
		
		return o instanceof String;
	}
}