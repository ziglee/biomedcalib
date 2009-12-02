package net.cassiolandim.biomedcalib.web;

import junit.framework.Assert;
import net.cassiolandim.biomedcalib.entity.User;
import net.cassiolandim.biomedcalib.web.page.HomePage;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

public class TestBiomedcalibSession {

	private WicketTester tester;
	
	@Before
	public void setUp(){
		tester = new BiomedcalibWicketTester();
		tester.startPage(HomePage.class);
	}
	
	@Test
	public void testThereIsUserInSession(){
		BiomedcalibSession session = (BiomedcalibSession)tester.getWicketSession();
		User userInSession = session.getUser();
		Assert.assertNotNull(userInSession);
		Assert.assertEquals("Cassio Landim", userInSession.name);
	}
}
