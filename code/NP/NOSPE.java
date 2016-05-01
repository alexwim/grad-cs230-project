// PATTERN: NP_NULL_ON_SOME_PATH_EXCEPTION
// DEF: A reference value which is null on some exception control path is dereferenced here.  This may lead to a NullPointerException when the code is executed.
// TYPE: False Positive
// SOURCE: https://sourceforge.net/p/findbugs/bugs/1443

public class NOSPE {
	public NOSPE getSubClassObject() {
		NOSPE testNPInstance = null;
		try {
			testNPInstance = this.getClass().newInstance();
		}
		catch(Exception e) { }
		if (testNPInstance == null) {
			throw new NullPointerException("pointer is null at testNP.getSubClassObject()");
		}
		return testNPInstance;
	}
}