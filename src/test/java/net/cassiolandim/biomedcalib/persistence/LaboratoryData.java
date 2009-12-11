package net.cassiolandim.biomedcalib.persistence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.cassiolandim.biomedcalib.entity.Laboratory;
import net.cassiolandim.biomedcalib.service.LaboratorySimplePersistableService;

public class LaboratoryData {
	
	private final List<Laboratory> laboratories = new ArrayList<Laboratory>();
	private final MockLaboratoryService laboratoryService = new MockLaboratoryService();

	public void newLaboratory(Laboratory laboratory) {
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
				if (laboratory.getId() == id) {
					return laboratory;
				}
			}
			return null;
		}

		public void persist(Laboratory laboratory) {
			saveCalled = true;
			laboratories.add(laboratory);
		}

		public void save(Laboratory laboratory) {
			saveCalled = true;
			Laboratory lab = find(laboratory.getId());
			lab.setName(laboratory.getName());
		}
		
		public List<Laboratory> findAll() {
			Collections.sort(laboratories);
			return laboratories;
		}
	}
}