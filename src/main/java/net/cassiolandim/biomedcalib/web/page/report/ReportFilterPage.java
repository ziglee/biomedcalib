package net.cassiolandim.biomedcalib.web.page.report;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.cassiolandim.biomedcalib.entity.Laboratory;
import net.cassiolandim.biomedcalib.entity.MeasuresAggregate;
import net.cassiolandim.biomedcalib.entity.User;
import net.cassiolandim.biomedcalib.service.LaboratoryPersistableService;
import net.cassiolandim.biomedcalib.service.MeasuresAggregatePersistableService;
import net.cassiolandim.biomedcalib.util.Constants;
import net.cassiolandim.biomedcalib.web.model.EntityListLoadableDetachableModel;
import net.cassiolandim.biomedcalib.web.page.AdminBasePage;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class ReportFilterPage extends AdminBasePage {
	
	@SpringBean(name = "laboratoryPersistableService")
	private LaboratoryPersistableService laboratoryPersistableService;
	
	@SpringBean(name = "measuresAggregatePersistableService")
	private MeasuresAggregatePersistableService measuresAggregatePersistableService;
	
	private Laboratory laboratory;
	private Date firstDate;
	private Date lastDate;
	private List<MeasuresAggregate> measures = new ArrayList<MeasuresAggregate>();
	private List<MeasuresAggregate> chosenMeasures = new ArrayList<MeasuresAggregate>();
	
	public ReportFilterPage() {
		addAdminHomeLink();
		
		FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
		add(feedbackPanel);

		Form<User> form = new Form<User>("form");
		add(form);
		
		EntityListLoadableDetachableModel<Laboratory, List<Laboratory>> labListModel = new EntityListLoadableDetachableModel<Laboratory, List<Laboratory>>(laboratoryPersistableService);

		ChoiceRenderer<Laboratory> choiceRenderer = new ChoiceRenderer<Laboratory>("name","id");
		DropDownChoice<Laboratory> labs = new DropDownChoice<Laboratory>("laboratory", new PropertyModel<Laboratory>(this, "laboratory"), labListModel);
		labs.setLabel(new ResourceModel("laboratory"));
		labs.setRequired(true);
		labs.setChoiceRenderer(choiceRenderer);
		form.add(labs);
		
		DateTextField firstDateTextField = new DateTextField("firstDate", new PropertyModel<Date>(this, "firstDate"), Constants.DATE_PATTERN);
		form.add(firstDateTextField);

		DateTextField lastDateTextField = new DateTextField("lastDate", new PropertyModel<Date>(this, "lastDate"), Constants.DATE_PATTERN);
		form.add(lastDateTextField);
		
		final WebMarkupContainer measuresContainer = new WebMarkupContainer("measuresContainer");
		measuresContainer.setOutputMarkupId(true);
		form.add(measuresContainer);
		
		final WebMarkupContainer chosenMeasuresContainer = new WebMarkupContainer("chosenMeasuresContainer");
		chosenMeasuresContainer.setOutputMarkupId(true);
		form.add(chosenMeasuresContainer);
		
		final ListDataProvider<MeasuresAggregate> measuresListDataProvider = new ListDataProvider<MeasuresAggregate>(measures);
		DataView<MeasuresAggregate> dataView = new DataView<MeasuresAggregate>("measuresAggregates", measuresListDataProvider) {
			@Override
			protected void populateItem(final Item<MeasuresAggregate> item){
				final MeasuresAggregate measure = item.getModelObject();
				item.add(new Label("firstDate", new PropertyModel<Date>(measure, "firstDate")));
				item.add(new Label("lastDate", new PropertyModel<Date>(measure, "lastDate")));
				item.add(new Label("controlSerum.name", new PropertyModel<String>(measure, "controlSerum.name")));
				
				final Button choose = new Button("choose"){
					@Override
					public boolean isVisible() {
						//return !chosenMeasures.contains(measure.getId());
						return true;
					}
				};
				choose.setOutputMarkupId(true);
				
				choose.add(new AjaxFormComponentUpdatingBehavior("onclick") {
		            protected void onUpdate(AjaxRequestTarget target) {
		            	choose.setVisible(false);
		            	chosenMeasures.add(measure);
		            	target.addComponent(choose);
		            	target.addComponent(chosenMeasuresContainer);
		            }
		        });
				
				item.add(choose);
			}
		};
		measuresContainer.add(dataView);
		
		final ListDataProvider<MeasuresAggregate> chosenMeasuresListDataProvider = new ListDataProvider<MeasuresAggregate>(measures);
		DataView<MeasuresAggregate> chosenDataView = new DataView<MeasuresAggregate>("measuresAggregates", chosenMeasuresListDataProvider) {
			@Override
			protected void populateItem(final Item<MeasuresAggregate> item){
				final MeasuresAggregate measure = item.getModelObject();
				item.add(new Label("firstDate", new PropertyModel<Date>(measure, "firstDate")));
				item.add(new Label("lastDate", new PropertyModel<Date>(measure, "lastDate")));
				item.add(new Label("controlSerum.name", new PropertyModel<String>(measure, "controlSerum.name")));
			}
		};
		chosenMeasuresContainer.add(chosenDataView);
		
		labs.add(new AjaxFormComponentUpdatingBehavior("onchange") {
            protected void onUpdate(AjaxRequestTarget target) {
            	measures.clear();
            	List<MeasuresAggregate> measuresFiltered = measuresAggregatePersistableService.findByLaboratory(laboratory);
            	for(MeasuresAggregate measuresAggregate : measuresFiltered){
            		measures.add(measuresAggregate);
            	}
                target.addComponent(measuresContainer);
            }
        });
		
		Button submit = new Button("submit"){
			@Override
			public void onSubmit() {
				setResponsePage(ReportFilterPage.class);
			}
		};
		form.add(submit);
	}
}
