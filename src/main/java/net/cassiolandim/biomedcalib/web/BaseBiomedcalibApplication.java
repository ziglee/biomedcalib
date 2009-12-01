package net.cassiolandim.biomedcalib.web;

import net.cassiolandim.biomedcalib.web.page.HomePage;

import org.apache.wicket.Page;
import org.apache.wicket.Request;
import org.apache.wicket.Response;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.context.ApplicationContext;

/**
 * @author Cassio Landim
 */
public abstract class BaseBiomedcalibApplication extends WebApplication {

	@Override
	public Class<? extends Page> getHomePage() {
		return HomePage.class;
	}

	@Override
	protected void init(){
		super.init();
		addComponentInstantiationListener(new SpringComponentInjector(this, context()));
		getMarkupSettings().setStripWicketTags(true);
	}
	
	@Override
	public Session newSession(Request request, Response response) {
		return new BiomedcalibSession(request);
	}

	public abstract ApplicationContext context();
}
