package net.cassiolandim.biomedcalib.persistence;

import net.cassiolandim.biomedcalib.entity.Laboratory;
import net.cassiolandim.biomedcalib.sampleDataGeneration.LaboratoryDataGenerator;
import net.cassiolandim.biomedcalib.web.MockContext;

/**
 * @author Cassio Landim
 */
public class LaboratoryFixture {
	
	public static final String[] NAMES = LaboratoryDataGenerator.NAMES;

	private final LaboratoryData laboratoryData = new LaboratoryData();

	public void addStubs(MockContext context) {
		context.putBean("laboratoryPersistableService", laboratoryData.getLaboratoryService());
		for (int i = 0; i < NAMES.length; i++) {
			Laboratory lab = new Laboratory();
			lab.setName(NAMES[i]);
			laboratoryData.newLaboratory(lab);
		}
	}

	public LaboratoryData getLaboratoryData() {
		return laboratoryData;
	}
}