package net.cassiolandim.biomedcalib.web.page.report;

import java.text.SimpleDateFormat;
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
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.CheckBoxMultipleChoice;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
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
	private ArrayList<MeasuresAggregate> chosenMeasures = new ArrayList<MeasuresAggregate>();
	
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
		
		final SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.DATE_PATTERN);
		
		final CheckBoxMultipleChoice<MeasuresAggregate> checkboxes = new CheckBoxMultipleChoice<MeasuresAggregate>("choices", new Model(chosenMeasures), measures);
		checkboxes.setOutputMarkupId(true);
		checkboxes.setChoiceRenderer(new ChoiceRenderer<MeasuresAggregate>(){
			@Override
			public Object getDisplayValue(MeasuresAggregate object) {
				StringBuilder display = new StringBuilder();
				display.append(dateFormat.format(object.getFirstDate()));
				display.append(" - ");
				display.append(dateFormat.format(object.getLastDate()));
				display.append(" - ");
				display.append(object.getControlSerum().getName());
				return display.toString();
			}
			
			@Override
			public String getIdValue(MeasuresAggregate object, int index) {
				return object.getId().toString();
			}
		});
		form.add(checkboxes);

		labs.add(new AjaxFormComponentUpdatingBehavior("onchange") {
            protected void onUpdate(AjaxRequestTarget target) {
            	measures.clear();
            	List<MeasuresAggregate> measuresFiltered = measuresAggregatePersistableService.findActiveByLaboratory(laboratory, firstDate, lastDate);
            	for(MeasuresAggregate measuresAggregate : measuresFiltered){
            		measures.add(measuresAggregate);
            	}
                target.addComponent(checkboxes);
            }
        });
		
		Button submit = new Button("submit"){
			@Override
			public void onSubmit() {
				if(chosenMeasures.size() == 0){
					error("Selecione pelo menos 1 corrida");
				}else{
					List<Long> ids = new ArrayList<Long>();
					for(MeasuresAggregate measuresAggregate : chosenMeasures) ids.add(measuresAggregate.getId());
					setResponsePage(new ReportPage(ids));
				}
			}
		};
		form.add(submit);
	}
}
