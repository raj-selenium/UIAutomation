package base;


public interface Page {
	
	// get the current URL of the Page and returns the same
	String getCurrentURL();
	
	// waits until the URL is the given URL
	boolean waitForUrlToBe(String url);
	
	// waits until the URL contains the given url part
	boolean waitUntilUrlContains(String partOfUrl);
	


}
