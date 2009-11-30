package net.cassiolandim.biomedcalib.entity;

/**
 * @author Cassio Landim
 */
import java.util.ArrayList;
import java.util.List;

import net.cassiolandim.biomedcalib.math.CoeffientOfVariation;

import org.apache.commons.math.stat.descriptive.moment.Mean;
import org.apache.commons.math.stat.descriptive.moment.StandardDeviation;

public class MeasuresPerLevel {

	private ControlSerum controlSerum;
	private List<Measure> measures;
	
	public MeasuresPerLevel() {
		measures = new ArrayList<Measure>();
	}
	
	public List<Measure> getMeasures() {
		return measures;
	}
	
	public void addMeasure(Measure measure) {
		measures.add(measure);
	}

	public double getMean() {
		Mean mean = new Mean();
		
		for(Measure measure : measures)
			mean.increment(measure.getValue());
		
		return mean.getResult();
	}
	
	public double getStandardDeviation(){
		StandardDeviation sd = new StandardDeviation();
		
		for(Measure measure : measures)
			sd.increment(measure.getValue());
		
		return sd.getResult();
	}

	public double getCofficientOfVariation() {
		CoeffientOfVariation cv = new CoeffientOfVariation(getMean(), getStandardDeviation());
		return cv.getResult();
	}
}
