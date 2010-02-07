package net.cassiolandim.biomedcalib.web;

import net.cassiolandim.biomedcalib.web.page.AdminBasePage;
import net.cassiolandim.biomedcalib.web.page.signInOut.SignInPage;

import org.apache.wicket.Component;
import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.authorization.Action;
import org.apache.wicket.authorization.IAuthorizationStrategy;
import org.apache.wicket.authorization.IUnauthorizedComponentInstantiationListener;

public class BiomedcalibAuthorizationStrategy implements IAuthorizationStrategy, IUnauthorizedComponentInstantiationListener {

	@Override
	public boolean isActionAuthorized(Component component, Action action) {
		return true;
	}

	@Override
	public <T extends Component> boolean isInstantiationAuthorized(Class<T> componentClass) {
		if(AdminBasePage.class.isAssignableFrom(componentClass)){
			return BiomedcalibSession.get().isAuthenticated();
		}
		return true;
	}
	
	@Override
	public void onUnauthorizedInstantiation(Component component) {
		throw new RestartResponseAtInterceptPageException(SignInPage.class);
	}
}
