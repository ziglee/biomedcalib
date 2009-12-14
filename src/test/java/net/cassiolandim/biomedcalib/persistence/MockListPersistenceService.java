package net.cassiolandim.biomedcalib.persistence;

import java.util.Collections;
import java.util.List;

import net.cassiolandim.biomedcalib.entity.BaseEntity;
import net.cassiolandim.biomedcalib.service.PersistableService;

public abstract class MockListPersistenceService<T extends BaseEntity<T>> implements PersistableService<T> {

	private static long idIncrement;
	private final List<T> list;
	private boolean deleteCalled = false;
	private boolean saveCalled = false;
	
	public MockListPersistenceService(List<T> list) {
		this.list = list;
		idIncrement = list.size();
	}
	
	public T find(Long id) {
		for (T t : list) {
			if (t.getId().equals(id)) {
				return t;
			}
		}
		return null;
	}

	public List<T> findAll() {
		Collections.sort(list);
		return list;
	}

	public void persist(T entity) {
		entity.setId(++idIncrement);
		list.add(entity);

		saveCalled = true;
	}

	public boolean remove(T entity) {
		list.remove(find(entity.getId()));
		deleteCalled = true;
		return true;
	}
	
	public boolean getDeleteCalled(){
		return deleteCalled;
	}
	
	public boolean getSaveCalled(){
		return saveCalled;
	}
	
	public void setSaveCalled(boolean saveCalled) {
		this.saveCalled = saveCalled;
	}
}
