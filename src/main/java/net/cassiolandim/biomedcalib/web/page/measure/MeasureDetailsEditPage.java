package net.cassiolandim.biomedcalib.web.page.measure;

import java.util.Date;

import net.cassiolandim.biomedcalib.entity.MeasuresAggregate;
import net.cassiolandim.biomedcalib.entity.MeasuresAggregateBinding;
import net.cassiolandim.biomedcalib.service.MeasuresAggregatePersistableService;
import net.cassiolandim.biomedcalib.web.page.BasePage;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class MeasureDetailsEditPage extends BasePage {

	@SpringBean(name = "measuresAggregatePersistableService")
	private MeasuresAggregatePersistableService measuresAggregatePersistableService;
	
	public MeasureDetailsEditPage(final Long measuresAggregateId) {
		final MeasuresAggregate measuresAggregate = measuresAggregatePersistableService.find(measuresAggregateId);
		
		MeasuresAggregateBinding binding = new MeasuresAggregateBinding();
		
		add(new Label("creationDate", new PropertyModel<Date>(measuresAggregate, binding.creationDate().getPath())));
		add(new Label("laboratory.name", new PropertyModel<String>(measuresAggregate, binding.laboratory().name().getPath())));
		add(new MeasuresPerLevelPanel("measures1", measuresAggregate.getMeasures1().getId()));
		add(new MeasuresPerLevelPanel("measures2", measuresAggregate.getMeasures2().getId()));
		add(new MeasuresPerLevelPanel("measures3", measuresAggregate.getMeasures3().getId()));

		Form<MeasuresAggregate> form = new Form<MeasuresAggregate>("form");
		add(form);
		
		TextArea<String> observationTextArea = new TextArea<String>("observation", new PropertyModel<String>(measuresAggregate, binding.observation().getPath()));
		form.add(observationTextArea);
		
		Button save = new Button("save"){
			@Override
			public void onSubmit() {
				measuresAggregatePersistableService.save(measuresAggregate);
			}
		};
		form.add(save);
		
		add(new Link<MeasureListPage>("measureListLink"){
			@Override
			public void onClick() {
				setResponsePage(MeasureListPage.class);
			}
        });
		
		addHomeLink();
		
		add(new Link<MeasuresPerLevelEditPage>("editMeasures1"){
			@Override
			public void onClick() {
				setResponsePage(new MeasuresPerLevelEditPage(measuresAggregate.getMeasures1().getId()));
			}
        });
		
		add(new Link<MeasuresPerLevelEditPage>("editMeasures2"){
			@Override
			public void onClick() {
				setResponsePage(new MeasuresPerLevelEditPage(measuresAggregate.getMeasures2().getId()));
			}
        });
		
		add(new Link<MeasuresPerLevelEditPage>("editMeasures3"){
			@Override
			public void onClick() {
				setResponsePage(new MeasuresPerLevelEditPage(measuresAggregate.getMeasures3().getId()));
			}
        });
		
		/* final Measure measure = new Measure(measures1);
		Form<Measure> measure1Form = new Form<Measure>("formMeasures1", new Model<Measure>(measure));
		add(measure1Form);
		
		final IModel<Long> valueModel = new PropertyModel<Long>(measure, "value");
		final TextField<Long> valueTextField = new TextField<Long>("value", valueModel);
		measure1Form.add(valueTextField);
		
		Button ajaxButton = new AjaxButton("submit") {
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				Measure measure = new Measure(measures1);
				Measure measure2 = (Measure)form.getModel().getObject();
				measure.setValue(measure2.getValue());
				measure.setValue(valueModel.getObject());
				measures1.addMeasure(measure);
				target.addComponent(measuresPerLevelEditPanel1);
			}
		};
		measure1Form.add(ajaxButton); */
	}
}
