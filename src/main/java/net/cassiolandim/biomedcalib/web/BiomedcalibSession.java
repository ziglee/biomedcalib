package net.cassiolandim.biomedcalib.web;

import org.apache.wicket.Request;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebSession;

public class BiomedcalibSession extends WebSession {

	private static final long serialVersionUID = 1L;
	
	public BiomedcalibSession(Request request) {
		super(request);
	}

	public static BiomedcalibSession get(){
		return (BiomedcalibSession)Session.get();
	}

}
