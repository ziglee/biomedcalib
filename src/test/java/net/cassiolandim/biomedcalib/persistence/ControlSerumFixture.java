package net.cassiolandim.biomedcalib.persistence;

import net.cassiolandim.biomedcalib.entity.ControlSerum;
import net.cassiolandim.biomedcalib.sampleDataGeneration.ControlSerumDataGenerator;
import net.cassiolandim.biomedcalib.web.MockContext;

/**
 * @author Cassio Landim
 */
public class ControlSerumFixture {
	
	public static final String[] NAMES = ControlSerumDataGenerator.NAMES;
	public static final Double[] MININUMS = ControlSerumDataGenerator.MININUMS;
	public static final Double[] MAXIMUMS = ControlSerumDataGenerator.MAXIMUMS;
	public static final Double[] SDS = ControlSerumDataGenerator.SDS;
	public static final Double[] CVS = ControlSerumDataGenerator.CVS;
	
	private final ControlSerumData controlSerumData = new ControlSerumData();

	public void addStubs(MockContext context) {
		context.putBean("controlSerumPersistableService", controlSerumData.getControlSerumService());
		for (int i = 0; i < NAMES.length; i++) {
			ControlSerum controlSerum = new ControlSerum();
			controlSerum.setName(NAMES[i]);
			controlSerum.setMinimum(MININUMS[i]);
			controlSerum.setMaximum(MAXIMUMS[i]);
			controlSerum.setStandardDeviation(SDS[i]);
			controlSerum.setCoefficientOfVariation(CVS[i]);
			controlSerumData.newControlSerum(controlSerum);
		}
	}

	public ControlSerumData getControlSerumData() {
		return controlSerumData;
	}
}
