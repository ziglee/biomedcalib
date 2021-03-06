package net.cassiolandim.biomedcalib.web.page.measure;

import java.util.Date;
import java.util.List;

import net.cassiolandim.biomedcalib.entity.ControlSerum;
import net.cassiolandim.biomedcalib.entity.Measure;
import net.cassiolandim.biomedcalib.entity.MeasuresAggregate;
import net.cassiolandim.biomedcalib.service.ControlSerumPersistableService;
import net.cassiolandim.biomedcalib.service.MeasuresAggregatePersistableService;
import net.cassiolandim.biomedcalib.util.Constants;
import net.cassiolandim.biomedcalib.web.model.EntityListLoadableDetachableModel;
import net.cassiolandim.biomedcalib.web.page.BasePage;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 * @author Cassio Landim
 */
public class MeasureDetailsEditPage extends BasePage {

	@SpringBean(name = "measuresAggregatePersistableService")
	private MeasuresAggregatePersistableService measuresAggregatePersistableService;
	
	@SpringBean(name = "controlSerumPersistableService")
	private ControlSerumPersistableService controlSerumPersistableService;

	private final MeasuresAggregate measuresAggregate;
	
	public MeasureDetailsEditPage() {
		measuresAggregate = new MeasuresAggregate(getBiomedicalSession().getLaboratory());
		pageConstructor();
	}
	
	public MeasureDetailsEditPage(final Long measuresAggregateId) {
		measuresAggregate = measuresAggregatePersistableService.find(measuresAggregateId);
		pageConstructor();
	}
	
	private void pageConstructor(){
		this.setOutputMarkupId(true);
		addHomeLink();

		FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
		add(feedbackPanel);
		
		Form<MeasuresAggregate> form = new Form<MeasuresAggregate>("form");
		add(form);
		
		Label laboratoryName = new Label("laboratory.name",	new PropertyModel<String>(measuresAggregate, "laboratory.name"));
		form.add(laboratoryName);
		
		TextArea<String> observationTextArea = new TextArea<String>("observation", new PropertyModel<String>(measuresAggregate, "observation"));
		form.add(observationTextArea);
		
		EntityListLoadableDetachableModel<ControlSerum, List<ControlSerum>> controlSerumListModel = new EntityListLoadableDetachableModel<ControlSerum, List<ControlSerum>>(controlSerumPersistableService){
			@Override
			protected List<ControlSerum> load() {
				return controlSerumPersistableService.findAllActive();
			}
		};
		
		final ChoiceRenderer<ControlSerum> choiceRenderer = new ChoiceRenderer<ControlSerum>("name","id");
		final DropDownChoice<ControlSerum> controlSerums = new DropDownChoice<ControlSerum>("controlSerum", new PropertyModel<ControlSerum>(measuresAggregate, "controlSerum"), controlSerumListModel);
		controlSerums.setLabel(new ResourceModel("controlSerum"));
		controlSerums.setRequired(true);
		controlSerums.setChoiceRenderer(choiceRenderer);
		form.add(controlSerums);
		
		final WebMarkupContainer controlSerumContainer = new WebMarkupContainer("controlSerumContainer");
		controlSerumContainer.setOutputMarkupId(true);
		form.add(controlSerumContainer);
		
		controlSerumContainer.add(new Label("controlSerum.manufacturer", new PropertyModel<String>(measuresAggregate, "controlSerum.manufacturer")));
		controlSerumContainer.add(new Label("controlSerum.mean", new PropertyModel<Double>(measuresAggregate, "controlSerum.mean")));
		controlSerumContainer.add(new Label("controlSerum.standardDeviation", new PropertyModel<Double>(measuresAggregate, "controlSerum.standardDeviation")));
		controlSerumContainer.add(new Label("controlSerum.coefficientOfVariation", new PropertyModel<Double>(measuresAggregate, "controlSerum.coefficientOfVariation")));
		controlSerumContainer.add(new Label("controlSerum.statusString", new PropertyModel<String>(measuresAggregate, "controlSerum.statusString")));
		
		controlSerums.add(new AjaxFormComponentUpdatingBehavior("onchange") {
            protected void onUpdate(AjaxRequestTarget target) {
            	measuresAggregate.setControlSerum(controlSerums.getModelObject());
                target.addComponent(controlSerumContainer);
            }
        });
		
		DataView<Measure> dataView = new DataView<Measure>("measures", new ListDataProvider<Measure>(measuresAggregate.getMeasures())) {
			@Override
			protected void populateItem(final Item<Measure> item){
				final Measure measure = item.getModelObject();
				
				DateTextField dateTextField = new DateTextField("date", new PropertyModel<Date>(measure, "date"), Constants.DATE_PATTERN);
				dateTextField.setRequired(true);
				item.add(dateTextField);
				
				TextField<Long> value = new TextField<Long>("value", new PropertyModel<Long>(measure, "value"));
				value.setRequired(true);
				item.add(value);
				
				Button remove = new AjaxButton("remove") {
					@Override
					public void onSubmit(AjaxRequestTarget target, Form<?> form) {
						measuresAggregate.removeMeasure(measure);
						target.addComponent(MeasureDetailsEditPage.this);
					}
				};
				item.add(remove);
			}
		};
		form.add(dataView);
		
		Button add = new AjaxButton("add") {
			@Override
			public void onSubmit(AjaxRequestTarget target, Form<?> form) {
				measuresAggregate.addMeasure(new Measure(measuresAggregate));
				target.addComponent(MeasureDetailsEditPage.this);
			}
		};
		form.add(add);
		
		Button save = new Button("save"){
			@Override
			public void onSubmit() {
				measuresAggregatePersistableService.save(measuresAggregate);
				setResponsePage(new MeasureDetailsPage(measuresAggregate.getId()));
			}
		};
		form.add(save);
		
		add(new Link<MeasureListPage>("measureListLink"){
			@Override
			public void onClick() {
				setResponsePage(MeasureListPage.class);
			}
        });
	}
}
