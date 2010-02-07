package net.cassiolandim.biomedcalib.component;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.image.Image;

public class MeasuresAggregateLineChartImage extends Image {

	private Long measuresAggregateId;
	
	public MeasuresAggregateLineChartImage(String id, Long measuresAggregateId) {
		super(id);
		this.measuresAggregateId = measuresAggregateId;
	}
	
	@Override
	protected void onComponentTag(ComponentTag tag) {
		super.onComponentTag(tag);
		
		tag.put("src", "measuresAggregateLine.chart?measureAggregateId=" + measuresAggregateId.intValue());
	}
}
