package net.cassiolandim.biomedcalib.web;

import net.cassiolandim.biomedcalib.entity.Laboratory;
import net.cassiolandim.biomedcalib.entity.User;

import org.apache.wicket.Request;
import org.apache.wicket.Response;
import org.apache.wicket.Session;
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
	
	@Override
	public Session newSession(Request request, Response response) {
		BiomedcalibSession biomedcalibSession = new BiomedcalibSession(request);
		User user = new User();
		user.setName("Cassio session user");
		user.setAdmin(true);
		Laboratory laboratory = new Laboratory();
		laboratory.setName("Lab teste");
		user.setLaboratory(laboratory);
		biomedcalibSession.setUser(user);
		return biomedcalibSession;
	}
}
