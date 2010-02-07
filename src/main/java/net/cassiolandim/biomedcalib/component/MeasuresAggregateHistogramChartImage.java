package net.cassiolandim.biomedcalib.component;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.image.Image;

public class MeasuresAggregateHistogramChartImage extends Image {

	private Long measuresAggregateId;
	
	public MeasuresAggregateHistogramChartImage(String id, Long measuresAggregateId) {
		super(id);
		this.measuresAggregateId = measuresAggregateId;
	}
	
	@Override
	protected void onComponentTag(ComponentTag tag) {
		super.onComponentTag(tag);
		
		tag.put("src", "measuresAggregateHistogram.chart?measureAggregateId=" + measuresAggregateId.intValue());
	}
}
