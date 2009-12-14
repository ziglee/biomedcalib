package net.cassiolandim.biomedcalib.web.model;

import net.cassiolandim.biomedcalib.entity.Laboratory;
import net.cassiolandim.biomedcalib.service.LaboratoryPersistableService;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Cassio Landim
 */
public class LaboratoryDetachableModelTest {
	
	@Test
	public void testLoad() {
		LaboratoryPersistableService service = EasyMock.createMock(LaboratoryPersistableService.class);
		
		Laboratory expected = new Laboratory();
		expected.setId(new Long(007));
		expected.setName("James Bond");
		
		EasyMock.expect(service.find(expected.getId())).andReturn(expected);
		EasyMock.replay(service);
		
		EntityLoadableDetachableModel<Laboratory> model = new EntityLoadableDetachableModel<Laboratory>(expected, service);
		Assert.assertEquals(expected, model.load());
		
		EasyMock.verify(service);
	}
}