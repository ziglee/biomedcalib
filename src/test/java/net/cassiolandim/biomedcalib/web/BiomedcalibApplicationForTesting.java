package net.cassiolandim.biomedcalib.web;

import org.apache.wicket.protocol.http.HttpSessionStore;
import org.apache.wicket.session.ISessionStore;
import org.springframework.context.ApplicationContext;

/**
 * @author Cassio Landim
 */
public class BiomedcalibApplicationForTesting extends BaseBiomedcalibApplication {
	
	public final MockContext context = new MockContext();

	@Override
	public ApplicationContext context() {
		return context;
	}

	@Override
	protected ISessionStore newSessionStore() {
		return new HttpSessionStore(this);
	}
}
