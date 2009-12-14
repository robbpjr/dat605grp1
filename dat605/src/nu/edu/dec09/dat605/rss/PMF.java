package nu.edu.dec09.dat605.rss;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

public final class PMF {
    private static final PersistenceManagerFactory pmfInstance =
        JDOHelper.getPersistenceManagerFactory("transactions-optional");

    private PMF() {}

    public static PersistenceManagerFactory get() {
        return pmfInstance;
    }
    // can get PMF instane via
    // PersistenceManager pm = PMF.get().getPersistenceManager();
    // when finished, close it via
    // pm.close();
}