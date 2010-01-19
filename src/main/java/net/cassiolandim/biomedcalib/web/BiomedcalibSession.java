package net.cassiolandim.biomedcalib.web;

import net.cassiolandim.biomedcalib.entity.Laboratory;
import net.cassiolandim.biomedcalib.entity.User;

import org.apache.wicket.Request;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebSession;

/**
 * @author Cassio Landim
 */
public class BiomedcalibSession extends WebSession {

	private static final long serialVersionUID = 1L;
	private User user;
	
	public BiomedcalibSession(Request request) {
		super(request);
		this.user = new User();
		this.user.setName("Cassio session user");
	}

	public static BiomedcalibSession get(){
		return (BiomedcalibSession)Session.get();
	}

	public synchronized User getUser() {
		return user;
	}
	
	public synchronized Laboratory getLaboratory() {
		return user.getLaboratory();
	}
}
