package net.cassiolandim.biomedcalib.web.model;

import net.cassiolandim.biomedcalib.entity.Laboratory;
import net.cassiolandim.biomedcalib.service.LaboratorySimplePersistableService;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;

public class LaboratoryDetachableModelTest {
	
	@Test
	public void testLoad() {
		LaboratorySimplePersistableService service = EasyMock.createMock(LaboratorySimplePersistableService.class);
		
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