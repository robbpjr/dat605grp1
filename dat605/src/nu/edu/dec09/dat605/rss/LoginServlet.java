package nu.edu.dec09.dat605.rss;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		AppSession appSession = AppSession.getInstance(); // get the current app session
		
		HttpSession session = req.getSession(true); // get the session or make one if it doesn't exist.
	
		String pArgs = req.getParameter("args");
		String[] allArgs = pArgs.split(",");
		
		if (allArgs.length > 1) {
				String loginName = allArgs[0];
				String loginPass = allArgs[1];
				// TODO really validate user using JDO
			    PersistenceManager pm = PMF.get().getPersistenceManager();
			    String query = "select from " + AppUser.class.getName() + " where auName == '" + loginName + "'";
			    List<AppUser> AppUsers = (List<AppUser>) pm.newQuery(query).execute();
			    if (AppUsers.size() == 1) {
			    	if (AppUsers.get(0).getAuPass().equals(loginPass)) {
						session.setAttribute("username", loginName); // TODO remove temporary login
						session.setAttribute("loggedin", true);
						appSession.addLoggedInUser(loginName);
						resp.getWriter().println("success");
			    	}
			    }
			    
			    if (AppUsers.isEmpty()) {
			    	// no user found
			    	resp.getWriter().println("failure");
			    } else {
			    	
			    }
		} else {
			if (allArgs[0].equalsIgnoreCase("/logout/")) {
				appSession.delLoggedInUser((String)session.getAttribute("username"));
				session.invalidate();
				resp.getWriter().println("success");
			}
		}
		/* returns a list of users
		ArrayList<String> loggedInUsers = appSession.getLoggedInUsers();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < loggedInUsers.size(); i++) {
			sb.append(loggedInUsers.get(i) + ",");
		}
		if (sb.length() > 0) {
			sb.delete(sb.length()-1, sb.length());
		}
		resp.getWriter().println(sb.toString()); */
	}
}