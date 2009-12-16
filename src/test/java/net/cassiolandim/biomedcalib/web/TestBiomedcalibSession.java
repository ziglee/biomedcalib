package net.cassiolandim.biomedcalib.web;

import junit.framework.Assert;
import net.cassiolandim.biomedcalib.entity.User;
import net.cassiolandim.biomedcalib.web.page.HomePage;

import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Cassio Landim
 */
public class TestBiomedcalibSession {

	private WicketTester tester;
	
	@Before
	public void setUp(){
		tester = new BiomedcalibWicketTester();
		tester.startPage(HomePage.class);
	}
	
	@Test
	public void shouldReturnBiomedcalibSessionInstance(){
		WebSession session = tester.getWicketSession();
		Assert.assertEquals(BiomedcalibSession.class, session.getClass());
		
		session = BiomedcalibSession.get();
		Assert.assertEquals(BiomedcalibSession.class, session.getClass());
	}
	
	@Test
	public void shouldHaveUserInSession(){
		BiomedcalibSession session = (BiomedcalibSession)tester.getWicketSession();
		User userInSession = session.getUser();
		Assert.assertNotNull(userInSession);
		Assert.assertEquals("Cassio session user", userInSession.getName());
	}
}
