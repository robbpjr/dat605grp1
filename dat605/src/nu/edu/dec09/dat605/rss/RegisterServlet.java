package nu.edu.dec09.dat605.rss;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class RegisterServlet extends HttpServlet {
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		AppSession appSession = AppSession.getInstance(); // get the current app session
		
		HttpSession session = req.getSession(true); // get the session or make one if it doesn't exist.
	
		String pArgs = req.getParameter("args");
		String[] allArgs = pArgs.split(",");
		
		if (allArgs.length > 1) {
			String name = allArgs[0];
			String pass = allArgs[1];
			String fn = allArgs[2];
			String ln = allArgs[3];
			Date date = new Date();
			// TODO really validate user using JDO
			PersistenceManager pm = PMF.get().getPersistenceManager();
		    String query = "select from " + AppUser.class.getName() + " where auName == '" + name + "'";
		    List<AppUser> AppUsers = (List<AppUser>) pm.newQuery(query).execute();
		    if (AppUsers.isEmpty()) {
		    	// no user with that name is found so create(register) it
				
		    	session.setAttribute("username", name);
				session.setAttribute("loggedin", true);
				appSession.addLoggedInUser(name);
				
				AppUser anAppUser = new AppUser(name, pass, date, fn, ln);
		        try {
		            pm.makePersistent(anAppUser);
		        } finally {
		            pm.close();
		        }
		    	resp.getWriter().println("success"); // returns success message to the gui
		    } else {
		    	resp.getWriter().println("failure"); // returns failure message to the gui
		    }

		}	
	}
}