// PATTERN: NP_NULL_ON_SOME_PATH_EXCEPTION
// SOURCE: https://sourceforge.net/p/findbugs/bugs/1443

public class testNP {
	public testNP getSubClassObject() {
		testNP testNPInstance = null;
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