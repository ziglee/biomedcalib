package net.cassiolandim.biomedcalib.persistence;

import java.util.ArrayList;
import java.util.List;

import net.cassiolandim.biomedcalib.entity.Laboratory;
import net.cassiolandim.biomedcalib.service.LaboratorySimplePersistableService;

public class LaboratoryData {
	
	private final List<Laboratory> laboratorys = new ArrayList<Laboratory>();
	private final MockLaboratoryService laboratoryService = new MockLaboratoryService();

	public void newLaboratory(Laboratory laboratory) {
		laboratorys.add(laboratory);
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
			laboratorys.remove(find(laboratory.getId()));
			return true;
		}

		public Laboratory find(Long id) {
			for (Laboratory laboratory : laboratorys) {
				if (laboratory.getId() == id) {
					return laboratory;
				}
			}
			return null;
		}

		public void persist(Laboratory laboratory) {
			saveCalled = true;
			laboratorys.add(laboratory);
		}

		public void save(Laboratory laboratory) {
			saveCalled = true;
			laboratorys.add(laboratory);
		}
		
		public List<Laboratory> findAll() {
			return laboratorys;
		}
	}
}