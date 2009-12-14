package net.cassiolandim.biomedcalib.persistence;

import java.util.ArrayList;
import java.util.List;

import net.cassiolandim.biomedcalib.entity.Laboratory;
import net.cassiolandim.biomedcalib.service.LaboratoryPersistableService;

/**
 * @author Cassio Landim
 */
public class LaboratoryData {

	private static long idIncrement = 0;
	private final List<Laboratory> list = new ArrayList<Laboratory>();
	private final MockLaboratoryService laboratoryService = new MockLaboratoryService(list);

	public void newLaboratory(Laboratory laboratory) {
		laboratory.setId(++idIncrement);
		list.add(laboratory);
	}

	public MockLaboratoryService getLaboratoryService() {
		return laboratoryService;
	}

	public boolean isLaboratoryDaoDeleteCalled() {
		return laboratoryService.getDeleteCalled();
	}

	public boolean isLaboratoryDaoSaveCalled() {
		return laboratoryService.getSaveCalled();
	}

	/**
	 * @author Cassio Landim
	 */
	private final class MockLaboratoryService extends MockListPersistenceService<Laboratory> implements LaboratoryPersistableService {

		public MockLaboratoryService(List<Laboratory> list) {
			super(list);
		}

		public void save(Laboratory laboratory) {
			Laboratory labFound = find(laboratory.getId());
			if(labFound != null){
				labFound.setName(laboratory.getName());
			}else{
				persist(laboratory);
			}
			
			setSaveCalled(true);
		}
	}
}
