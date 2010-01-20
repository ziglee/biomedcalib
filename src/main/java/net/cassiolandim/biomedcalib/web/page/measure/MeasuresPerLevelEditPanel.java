package net.cassiolandim.biomedcalib.web.page.measure;

import java.util.Date;

import net.cassiolandim.biomedcalib.Constants;
import net.cassiolandim.biomedcalib.entity.Measure;
import net.cassiolandim.biomedcalib.entity.MeasureBinding;
import net.cassiolandim.biomedcalib.entity.MeasuresPerLevel;
import net.cassiolandim.biomedcalib.entity.MeasuresPerLevelBinding;
import net.cassiolandim.biomedcalib.service.MeasuresPerLevelPersistableService;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class MeasuresPerLevelEditPanel extends Panel {
	
	@SpringBean(name = "measuresPerLevelPersistableService")
	private MeasuresPerLevelPersistableService measuresPerLevelPersistableService;
	
	public MeasuresPerLevelEditPanel(String id, final Long measuresPerLevelId) {
		super(id);
		this.setOutputMarkupId(true);
		
		final MeasuresPerLevel measuresPerLevel = measuresPerLevelPersistableService.find(measuresPerLevelId);
		
		MeasuresPerLevelBinding binding = new MeasuresPerLevelBinding();
		
		add(new Label("controlSerum", new PropertyModel<MeasuresPerLevel>(measuresPerLevel, binding.controlSerum().name().getPath())));
		add(new Label("mean", new PropertyModel<MeasuresPerLevel>(measuresPerLevel, binding.mean().getPath())));
		add(new Label("standardDeviation", new PropertyModel<MeasuresPerLevel>(measuresPerLevel, binding.standardDeviation().getPath())));
		add(new Label("coefficientOfVariation", new PropertyModel<MeasuresPerLevel>(measuresPerLevel, binding.cofficientOfVariation().getPath())));
		
		FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
		add(feedbackPanel);
		
		Form<MeasuresPerLevel> form = new Form<MeasuresPerLevel>("form");
		add(form);
		
		DataView<Measure> dataView = new DataView<Measure>("measures", new ListDataProvider<Measure>(measuresPerLevel.getMeasures())) {
			@Override
			protected void populateItem(final Item<Measure> item){
				final Measure measure = item.getModelObject();
				
				MeasureBinding binding = new MeasureBinding();
				
				DateTextField dateTextField = new DateTextField("date", new PropertyModel<Date>(measure, binding.date().getPath()), Constants.DATE_PATTERN);
				dateTextField.setRequired(true);
				item.add(dateTextField);
				
				TextField<Long> value = new TextField<Long>("value", new PropertyModel<Long>(measure, binding.value().getPath()));
				value.setRequired(true);
				item.add(value);
				
				Button remove = new AjaxButton("remove") {
					@Override
					public void onSubmit(AjaxRequestTarget target, Form<?> form) {
						measuresPerLevel.removeMeasure(measure);
						target.addComponent(MeasuresPerLevelEditPanel.this);
					}
				};
				item.add(remove);
			}
		};
		form.add(dataView);
		
		Button add = new AjaxButton("add") {
			@Override
			public void onSubmit(AjaxRequestTarget target, Form<?> form) {
				measuresPerLevel.addMeasure(new Measure(measuresPerLevel));
				target.addComponent(MeasuresPerLevelEditPanel.this);
			}
		};
		form.add(add);
		
		Button save = new AjaxButton("save"){
			@Override
			public void onSubmit(AjaxRequestTarget target, Form<?> form) {
				measuresPerLevelPersistableService.save(measuresPerLevel);
				target.addComponent(MeasuresPerLevelEditPanel.this);
			}
		};
		form.add(save);
	}
}
