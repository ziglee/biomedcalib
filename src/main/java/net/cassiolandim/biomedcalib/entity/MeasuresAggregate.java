package net.cassiolandim.biomedcalib.entity;

import java.util.ArrayList;
import java.util.List;

import net.cassiolandim.biomedcalib.math.CoeffientOfVariation;

import org.apache.commons.math.stat.descriptive.moment.Mean;
import org.apache.commons.math.stat.descriptive.moment.StandardDeviation;

/**
 * @author Cassio Landim
 */
public class MeasuresAggregate {

	private List<Measure> measures;
	
	public MeasuresAggregate() {
		measures = new ArrayList<Measure>();
	}
	
	public void addMeasure(Measure measure) {
		measures.add(measure);
	}

	public double getMean() {
		Mean mean = new Mean();
		
		for(Measure measure : measures)
			mean.increment(measure.value);
		
		return mean.getResult();
	}
	
	public double getStandardDeviation(){
		StandardDeviation sd = new StandardDeviation();
		
		for(Measure measure : measures)
			sd.increment(measure.value);
		
		return sd.getResult();
	}

	public double getCofficientOfVariation() {
		CoeffientOfVariation cv = new CoeffientOfVariation(getMean(), getStandardDeviation());
		return cv.getResult();
	}
}
