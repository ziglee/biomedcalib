package net.cassiolandim.biomedcalib.web.page.report;

import java.util.ArrayList;
import java.util.List;

import net.cassiolandim.biomedcalib.entity.ControlSerum;
import net.cassiolandim.biomedcalib.entity.MeasuresAggregate;
import net.cassiolandim.biomedcalib.service.MeasuresAggregatePersistableService;
import net.cassiolandim.biomedcalib.web.page.AdminBasePage;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class ReportPage extends AdminBasePage {

	@SpringBean(name = "measuresAggregatePersistableService")
	private MeasuresAggregatePersistableService measuresAggregatePersistableService;
	
	List<MeasuresAggregate> measuresAggregates = new ArrayList<MeasuresAggregate>();
	
	public ReportPage(List<Long> ids) {
		addAdminHomeLink();

		for(Long id : ids){
			MeasuresAggregate measureAggregate = measuresAggregatePersistableService.find(id);
			measuresAggregates.add(measureAggregate);
		}

		DataView<MeasuresAggregate> levelsView = new DataView<MeasuresAggregate>("levelsView", new ListDataProvider<MeasuresAggregate>(measuresAggregates)) {
			@Override
			protected void populateItem(final Item<MeasuresAggregate> item){
				item.add(new Label("name", Integer.toString(item.getIndex() + 1)));
			}
		};
		add(levelsView);

		DataView<MeasuresAggregate> controlSerumsView = new DataView<MeasuresAggregate>("controlSerumsView", new ListDataProvider<MeasuresAggregate>(measuresAggregates)) {
			@Override
			protected void populateItem(final Item<MeasuresAggregate> item){
				final MeasuresAggregate ma = (MeasuresAggregate)item.getModelObject();
				item.add(new Label("name", ma.getControlSerum().getName()));
			}
		};
		add(controlSerumsView);
		
		DataView<MeasuresAggregate> meansInUseView = new DataView<MeasuresAggregate>("meansView", new ListDataProvider<MeasuresAggregate>(measuresAggregates)) {
			@Override
			protected void populateItem(final Item<MeasuresAggregate> item){
				final MeasuresAggregate ma = (MeasuresAggregate)item.getModelObject();
				item.add(new Label("mean", ma.getControlSerum().getMean().toString()));
			}
		};
		add(meansInUseView);
		
		DataView<MeasuresAggregate> sdsInUseView = new DataView<MeasuresAggregate>("sdsView", new ListDataProvider<MeasuresAggregate>(measuresAggregates)) {
			@Override
			protected void populateItem(final Item<MeasuresAggregate> item){
				final MeasuresAggregate ma = (MeasuresAggregate)item.getModelObject();
				item.add(new Label("sd", ma.getControlSerum().getStandardDeviation().toString()));
			}
		};
		add(sdsInUseView);
		
		add(new Label("laboratory", measuresAggregates.get(0).getLaboratory().getName()));
	}
}
