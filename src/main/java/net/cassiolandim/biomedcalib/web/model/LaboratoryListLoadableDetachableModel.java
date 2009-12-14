package net.cassiolandim.biomedcalib.web.model;

import java.util.List;

import net.cassiolandim.biomedcalib.entity.Laboratory;
import net.cassiolandim.biomedcalib.service.LaboratoryPersistableService;

import org.apache.wicket.model.LoadableDetachableModel;

/**
 * @author Cassio Landim
 */
public class LaboratoryListLoadableDetachableModel extends LoadableDetachableModel<List<Laboratory>> {
	
	private final LaboratoryPersistableService service;

	public LaboratoryListLoadableDetachableModel(LaboratoryPersistableService service) {
		this.service = service;
	}

	@Override
	protected List<Laboratory> load() {
		return service.findAll();
	}
}
