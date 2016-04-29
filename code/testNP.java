// PATTERN: NP_NULL_ON_SOME_PATH_EXCEPTION
// SOURCE: File taken from someplace, where?

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