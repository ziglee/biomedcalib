package net.cassiolandim.biomedcalib.web.model;

import java.util.List;

import net.cassiolandim.biomedcalib.entity.ControlSerum;
import net.cassiolandim.biomedcalib.service.ControlSerumPersistableService;

import org.apache.wicket.model.LoadableDetachableModel;

/**
 * @author Cassio Landim
 */
public class ControlSerumListLoadableDetachableModel extends LoadableDetachableModel<List<ControlSerum>> {
	
	private final ControlSerumPersistableService service;

	public ControlSerumListLoadableDetachableModel(ControlSerumPersistableService service) {
		this.service = service;
	}

	@Override
	protected List<ControlSerum> load() {
		return service.findAll();
	}
}
