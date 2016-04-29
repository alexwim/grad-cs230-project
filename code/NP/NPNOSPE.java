// PATTERN: NP_NULL_ON_SOME_PATH_EXCEPTION
// TYPE: False Positive
// SOURCE: https://sourceforge.net/p/findbugs/bugs/1443

public class NPNOSPE {
	public NPNOSPE getSubClassObject() {
		NPNOSPE testNPInstance = null;
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