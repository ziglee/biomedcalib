package net.cassiolandim.biomedcalib.persistence;

import java.util.ArrayList;
import java.util.List;

import net.cassiolandim.biomedcalib.entity.ControlSerum;
import net.cassiolandim.biomedcalib.service.ControlSerumPersistableService;

/**
 * @author Cassio Landim
 */
public class ControlSerumData {

	private static long idIncrement = 0;
	private final List<ControlSerum> list = new ArrayList<ControlSerum>();
	private final MockControlSerumService controlSerumService = new MockControlSerumService(list);

	public void newControlSerum(ControlSerum controlSerum) {
		controlSerum.setId(++idIncrement);
		list.add(controlSerum);
	}

	public MockControlSerumService getControlSerumService() {
		return controlSerumService;
	}

	public boolean isControlSerumDaoDeleteCalled() {
		return controlSerumService.getDeleteCalled();
	}

	public boolean isControlSerumDaoSaveCalled() {
		return controlSerumService.getSaveCalled();
	}

	/**
	 * @author Cassio Landim
	 */
	private final class MockControlSerumService extends MockListPersistenceService<ControlSerum>  implements ControlSerumPersistableService {

		public MockControlSerumService(List<ControlSerum> list) {
			super(list);
		}
		
		public void save(ControlSerum controlSerum) {
			ControlSerum controlSerumFound = find(controlSerum.getId());
			if(controlSerumFound != null){
				controlSerumFound.setName(controlSerum.getName());
				controlSerumFound.setMinimum(controlSerum.getMinimum());
				controlSerumFound.setMaximum(controlSerum.getMaximum());
				controlSerumFound.setStandardDeviation(controlSerum.getStandardDeviation());
				controlSerumFound.setCoefficientOfVariation(controlSerum.getCoefficientOfVariation());
			}else{
				persist(controlSerum);
			}
			
			setSaveCalled(true);
		}
	}
}
