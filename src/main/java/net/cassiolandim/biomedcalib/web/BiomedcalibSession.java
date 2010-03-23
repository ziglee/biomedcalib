package net.cassiolandim.biomedcalib.web;

import java.text.NumberFormat;

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
	private NumberFormat numberFormat;
	
	public BiomedcalibSession(Request request) {
		super(request);
	}

	public static BiomedcalibSession get(){
		return (BiomedcalibSession)Session.get();
	}

	public synchronized User getUser() {
		return user;
	}
	
	public synchronized void setUser(User user) {
		this.user = user;
		this.numberFormat = user.getLaboratory().getNumberFormat(getLocale());
	}
	
	public synchronized Laboratory getLaboratory() {
		return user.getLaboratory();
	}
	
	public boolean isAuthenticated(){
		return (user != null);
	}
	
	public boolean isAdmin(){
		return (isAuthenticated() && user.getAdmin());
	}
	
	public NumberFormat getNumberFormat(){
		return numberFormat;
	}
}
