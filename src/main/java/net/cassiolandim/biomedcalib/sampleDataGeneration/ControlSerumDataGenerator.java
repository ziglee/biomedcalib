package net.cassiolandim.biomedcalib.sampleDataGeneration;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.cassiolandim.biomedcalib.entity.ControlSerum;
import net.cassiolandim.biomedcalib.entity.Laboratory;
import net.cassiolandim.biomedcalib.service.ControlSerumPersistableService;

/**
 * @author Cassio Landim
 */
public class ControlSerumDataGenerator {

	public static final String[] NAMES = { "HGH - 3N" , "GGH - 3P", "ABN - 2N", "GON - 2P" };
	public static final Double[] MININUMS = { 90d , 87d, 98d, 89d };
	public static final Double[] MAXIMUMS = { 120d , 92d, 118d, 97d };
	public static final Double[] SDS = { 17.5 , 33.5, 21.2, 28.6 };
	public static final Double[] CVS = { 40d , 41d, 38.2, 41.6 };
	
	public static List<ControlSerum> generateData(ControlSerumPersistableService controlSerumService, List<Laboratory> laboratories){
		List<ControlSerum> list = new ArrayList<ControlSerum>();
		for (int i = 0; i < NAMES.length; i++){
			ControlSerum controlSerum = new ControlSerum(laboratories.get(i % NAMES.length));
			controlSerum.setName(NAMES[i]);
			controlSerum.setManufacturer("Control Lab");
			controlSerum.setMinimum(MININUMS[i]);
			controlSerum.setMaximum(MAXIMUMS[i]);
			controlSerum.setStandardDeviation(SDS[i]);
			controlSerum.setCoefficientOfVariation(CVS[i]);
			controlSerum.setExpiration(new Date());
			controlSerum.setDeployment(new Date());
			controlSerumService.persist(controlSerum);
			list.add(controlSerum);
		}
		return list;
	}
}
