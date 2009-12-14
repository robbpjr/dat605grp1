package nu.edu.dec09.dat605.rss;

import java.util.ArrayList;

public class AppSession { // our custom session manager (ourSM) for application scope/session
	private static AppSession instance = null;
	private ArrayList<String> loggedInUsers = new ArrayList<String>();
	
	public AppSession() {
		
	}
	
	public static AppSession getInstance() {
		if (instance == null) {
			instance = new AppSession();
		}
		return instance;
	}
	
	public void addLoggedInUser(String name) {
		if (loggedInUsers.indexOf(name) < 0) {
			loggedInUsers.add(name);
		}
	}
	
	public void delLoggedInUser(String name) {
		if (loggedInUsers.indexOf(name) > -1) {
			loggedInUsers.remove(loggedInUsers.indexOf(name));
		}
	}
	
	public ArrayList<String> getLoggedInUsers() {
		return loggedInUsers;
	}
	
}