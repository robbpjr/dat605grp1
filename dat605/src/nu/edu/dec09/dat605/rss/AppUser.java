package nu.edu.dec09.dat605.rss;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.users.User;

import java.util.Date;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class AppUser {
    //@PrimaryKey
    //@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    //private Key key;
    
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private String auName;

    @Persistent
    private String auPass;

    @Persistent
    private Date auDate;
    
    @Persistent
    private String auFirstName;
    
    @Persistent
    private String auLastName;

    public AppUser(String name, String pass, Date date, String fn, String ln) {
        this.auName = name;
        this.auPass = pass;
        this.auDate = date;
        this.auFirstName = fn;
        this.auLastName = ln;
    }

    public String getAuName() {
        return auName;
    }
    
    public String getAuPass() {
    	return auPass;
    }
    
    public String getAuFirstName() {
    	return auFirstName;
    }
    
    public String getAuLastName() {
    	return auLastName;
    }
    
}