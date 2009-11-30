package net.cassiolandim.biomedcalib.web;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Application object for your web application. 
 * If you want to run this application without deploying, run the Start class.
 * 
 * @author Cassio Landim
 * @see net.cassiolandim.biomedcalib.Start#main(String[])
 */
public class BiomedcalibApplication extends BaseBiomedcalibApplication {    
	
	@Override
	public ApplicationContext context(){
		return WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
	}

}
