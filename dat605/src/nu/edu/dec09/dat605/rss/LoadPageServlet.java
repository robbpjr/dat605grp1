package nu.edu.dec09.dat605.rss;

import java.io.IOException;

import javax.servlet.http.*;

@SuppressWarnings("serial")
public class LoadPageServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		StringBuffer output = new StringBuffer();
		
		//OurSM currentSession = OurSM.getInstance(); // get the current session
		//Boolean loggedIn = currentSession.isLoggedIn(); // get the users logged in status
		HttpSession session = req.getSession(true); // get the session or make one if it doesn't exist.
		Boolean loggedIn = (Boolean) session.getAttribute("loggedin");
		if (loggedIn == null) {
			loggedIn = false;
		}
				
		String pArgs = req.getParameter("args");
		
		/*String output = "";
		for(Enumeration e = req.getParameterNames(); e.hasMoreElements(); ){
						output += req.getParameter((String)e.nextElement()) + "<br/>";
					}
		
		*/
		if (pArgs == null) {
			pArgs = "";
		}
		//System.out.println("pArgs = " + pArgs);		
		//resp.getWriter().println("pArgs = " + pArgs);
		
		if (loggedIn) { // user is logged in
			output.append("true," + session.getAttribute("username"));
		} else { // user is not logged in
			output.append("false");
		}
		resp.setContentType("text/plain");
		resp.getWriter().println(output.toString());
		System.out.println(output.toString());
	}
}