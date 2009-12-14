package net.cassiolandim.biomedcalib.sampleDataGeneration;

import java.util.ArrayList;
import java.util.List;

import net.cassiolandim.biomedcalib.entity.ControlSerum;
import net.cassiolandim.biomedcalib.service.ControlSerumPersistableService;

/**
 * @author Cassio Landim
 */
public class ControlSerumDataGenerator {

	private static final String[] NAMES = { "HGH - 3N" , "GGH - 3P", "ABN - 2N", "GON - 2P" };
	private static final Double[] MININUMS = { 90d , 87d, 98d, 89d };
	private static final Double[] MAXIMUMS = { 120d , 92d, 118d, 97d };
	private static final Double[] SDS = { 17.5 , 33.5, 21.2, 28.6 };
	private static final Double[] CVS = { 40d , 41d, 38.2, 41.6 };
	
	public static List<ControlSerum> generateData(ControlSerumPersistableService controlSerumService){
		List<ControlSerum> list = new ArrayList<ControlSerum>();
		for (int i = 0; i < NAMES.length; i++){
			ControlSerum controlSerum = new ControlSerum();
			controlSerum.setName(NAMES[i]);
			controlSerum.setMinimum(MININUMS[i]);
			controlSerum.setMaximum(MAXIMUMS[i]);
			controlSerum.setStandardDeviation(SDS[i]);
			controlSerum.setCoefficientOfVariation(CVS[i]);
			controlSerumService.persist(controlSerum);
			list.add(controlSerum);
		}
		return list;
	}
}
