package net.cassiolandim.biomedcalib.web.page.measure;

import java.util.Date;

import net.cassiolandim.biomedcalib.component.DateChooserPanel;
import net.cassiolandim.biomedcalib.entity.Measure;
import net.cassiolandim.biomedcalib.entity.MeasureBinding;
import net.cassiolandim.biomedcalib.entity.MeasuresPerLevel;
import net.cassiolandim.biomedcalib.entity.MeasuresPerLevelBinding;
import net.cassiolandim.biomedcalib.service.MeasuresPerLevelPersistableService;
import net.cassiolandim.biomedcalib.web.page.BasePage;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class MeasuresPerLevelEditPage extends BasePage {
	
	@SpringBean(name = "measuresPerLevelPersistableService")
	private MeasuresPerLevelPersistableService measuresPerLevelPersistableService;
	
	public MeasuresPerLevelEditPage(final Long measuresPerLevelId) {
		final MeasuresPerLevel measuresPerLevel = measuresPerLevelPersistableService.find(measuresPerLevelId);
		
		MeasuresPerLevelBinding binding = new MeasuresPerLevelBinding();
		
		add(new Label("controlSerum", new PropertyModel<MeasuresPerLevel>(measuresPerLevel, binding.controlSerum().name().getPath())));
		add(new Label("mean", new PropertyModel<MeasuresPerLevel>(measuresPerLevel, binding.mean().getPath())));
		add(new Label("standardDeviation", new PropertyModel<MeasuresPerLevel>(measuresPerLevel, binding.standardDeviation().getPath())));
		add(new Label("coefficientOfVariation", new PropertyModel<MeasuresPerLevel>(measuresPerLevel, binding.cofficientOfVariation().getPath())));
		
		Form<MeasuresPerLevel> form = new Form<MeasuresPerLevel>("form");
		add(form);
		
		final WebMarkupContainer measuresTable = new WebMarkupContainer("measuresTable");
		measuresTable.setOutputMarkupId(true);
		form.add(measuresTable);
		
		DataView<Measure> dataView = new DataView<Measure>("measures", new ListDataProvider<Measure>(measuresPerLevel.getMeasures())) {
			@Override
			protected void populateItem(final Item<Measure> item){
				final Measure measure = item.getModelObject();
				
				MeasureBinding binding = new MeasureBinding();
				
				DateChooserPanel dateTextFieldPanel = new DateChooserPanel("date", new PropertyModel<Date>(measure, binding.date().getPath()));
				item.add(dateTextFieldPanel);
				
				TextField<Long> value = new TextField<Long>("value", new PropertyModel<Long>(measure, binding.value().getPath()));
				value.setRequired(true);
				item.add(value);
			}
		};
		measuresTable.add(dataView);
		
		Button ajaxButton = new AjaxButton("add") {
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				Measure measure = new Measure(measuresPerLevel);
				measuresPerLevel.addMeasure(measure);
				target.addComponent(measuresTable);
			}
		};
		form.add(ajaxButton);
		
		Button save = new Button("save"){
			@Override
			public void onSubmit() {
				measuresPerLevelPersistableService.save(measuresPerLevel);
			}
		};
		form.add(save);
	}
}
