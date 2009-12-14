package net.cassiolandim.biomedcalib.persistence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.cassiolandim.biomedcalib.entity.Laboratory;
import net.cassiolandim.biomedcalib.service.LaboratorySimplePersistableService;

/**
 * @author Cassio Landim
 */
public class LaboratoryData {

	private static long idIncrement = 0;
	private final List<Laboratory> laboratories = new ArrayList<Laboratory>();
	private final MockLaboratoryService laboratoryService = new MockLaboratoryService();

	public void newLaboratory(Laboratory laboratory) {
		laboratory.setId(++idIncrement);
		laboratories.add(laboratory);
	}

	public MockLaboratoryService getLaboratoryService() {
		return laboratoryService;
	}

	public boolean isLaboratoryDaoDeleteCalled() {
		return laboratoryService.deleteCalled;
	}

	public boolean isLaboratoryDaoSaveCalled() {
		return laboratoryService.saveCalled;
	}

	/**
	 * @author Cassio Landim
	 */
	private final class MockLaboratoryService implements LaboratorySimplePersistableService {

		private boolean deleteCalled;
		private boolean saveCalled;

		public boolean remove(Laboratory laboratory) {
			deleteCalled = true;
			laboratories.remove(find(laboratory.getId()));
			return true;
		}

		public Laboratory find(Long id) {
			for (Laboratory laboratory : laboratories) {
				if (laboratory.getId().equals(id)) {
					return laboratory;
				}
			}
			return null;
		}

		public void persist(Laboratory laboratory) {
			laboratory.setId(++idIncrement);
			laboratories.add(laboratory);

			saveCalled = true;
		}

		public void save(Laboratory laboratory) {
			Laboratory labFound = find(laboratory.getId());
			if(labFound != null){
				labFound.setName(laboratory.getName());
			}else{
				persist(laboratory);
			}
			
			saveCalled = true;
		}
		
		public List<Laboratory> findAll() {
			Collections.sort(laboratories);
			return laboratories;
		}
	}
}
