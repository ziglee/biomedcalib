package net.cassiolandim.biomedcalib.entity;

import org.junit.Assert;
import org.junit.Test;

public class TestBaseEntityEquality {

	@Test
	public void testUserEquality(){
		User user1 = new User();
		user1.setId(new Long(1));
		
		User user2 = new User();
		user2.setId(new Long(1));
		
		Assert.assertTrue(user1.equals(user2));
		Assert.assertTrue(user2.equals(user1));
	}
	
	@Test
	public void testUserDiferent(){
		User user1 = new User();
		user1.setId(new Long(2));
		
		User user2 = new User();
		user2.setId(new Long(1));
		
		Assert.assertFalse(user1.equals(user2));
		Assert.assertFalse(user2.equals(user1));
	}
}
