package net.cassiolandim.biomedcalib.web;

import org.apache.wicket.util.tester.WicketTester;

/**
 * @author Cassio Landim
 */
public class BiomedcalibWicketTester extends WicketTester {

	public BiomedcalibWicketTester() {
		super(new BiomedcalibApplicationForTesting());
	}
}
