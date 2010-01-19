package net.cassiolandim.biomedcalib.web.page.measure;

import net.cassiolandim.biomedcalib.entity.Measure;
import net.cassiolandim.biomedcalib.entity.MeasuresPerLevel;
import net.cassiolandim.biomedcalib.entity.MeasuresPerLevelBinding;
import net.cassiolandim.biomedcalib.service.MeasuresPerLevelPersistableService;
import net.cassiolandim.biomedcalib.web.model.EntityLoadableDetachableModel;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class MeasuresPerLevelPanel extends Panel {

	@SpringBean(name = "measuresPerLevelPersistableService")
	private MeasuresPerLevelPersistableService measuresPerLevelPersistableService;
	
	public MeasuresPerLevelPanel(String id, Long measuresPerLevelId) {
		super(id);
		
		final MeasuresPerLevel measuresPerLevel = measuresPerLevelPersistableService.find(measuresPerLevelId);
		EntityLoadableDetachableModel<MeasuresPerLevel> detachModel = new EntityLoadableDetachableModel<MeasuresPerLevel>(measuresPerLevel, measuresPerLevelPersistableService);
		
		MeasuresPerLevelBinding binding = new MeasuresPerLevelBinding();
		
		add(new Label("controlSerum", new PropertyModel<MeasuresPerLevel>(detachModel, binding.controlSerum().name().getPath())));
		add(new Label("mean", new PropertyModel<MeasuresPerLevel>(detachModel, binding.mean().getPath())));
		add(new Label("standardDeviation", new PropertyModel<MeasuresPerLevel>(detachModel, binding.standardDeviation().getPath())));
		add(new Label("coefficientOfVariation", new PropertyModel<MeasuresPerLevel>(detachModel, binding.cofficientOfVariation().getPath())));

		add(new ListView("measures", new PropertyModel<MeasuresPerLevel>(detachModel, binding.measures().getPath())){
			@Override
			protected void populateItem(ListItem item){
				final Measure measure = (Measure)item.getModelObject();
				CompoundPropertyModel<Measure> compoundModel = new CompoundPropertyModel<Measure>(measure);
				item.setModel(compoundModel);
				item.add(new Label("date"));
				item.add(new Label("value"));
			}
		});
	}
}
