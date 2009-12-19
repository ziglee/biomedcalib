package net.cassiolandim.biomedcalib.web.page.measure;

import net.cassiolandim.biomedcalib.entity.Measure;
import net.cassiolandim.biomedcalib.entity.MeasuresPerLevel;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;

public class MeasuresPerLevelPanel extends Panel {

	public MeasuresPerLevelPanel(String id, MeasuresPerLevel measuresPerLevel) {
		super(id);
		
		add(new Label("controlSerum", new PropertyModel<MeasuresPerLevel>(measuresPerLevel, "controlSerum.name")));
		
		add(new ListView<Measure>("measures", measuresPerLevel.getMeasures()){
			@Override
			protected void populateItem(ListItem<Measure> item){
				final Measure measure = item.getModelObject();
				CompoundPropertyModel<Measure> compoundModel = new CompoundPropertyModel<Measure>(measure);
				item.setModel(compoundModel);
				item.add(new Label("date"));
				item.add(new Label("value"));
			}
		});
	}
}
