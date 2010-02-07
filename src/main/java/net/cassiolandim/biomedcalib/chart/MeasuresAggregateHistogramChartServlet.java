package net.cassiolandim.biomedcalib.chart;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.cassiolandim.biomedcalib.entity.Measure;
import net.cassiolandim.biomedcalib.entity.MeasuresAggregate;
import net.cassiolandim.biomedcalib.service.MeasuresAggregatePersistableService;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.HistogramDataset;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class MeasuresAggregateHistogramChartServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		final Long measureAggregateId = new Long(req.getParameter("measureAggregateId"));
		final ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		final MeasuresAggregatePersistableService myDao = (MeasuresAggregatePersistableService) context.getBean("measuresAggregatePersistableService");
		final MeasuresAggregate measuresAggregate = myDao.find(measureAggregateId);
		final HistogramDataset dataset = createDataset(measuresAggregate);
		final JFreeChart chart = createChart(dataset);
		ChartUtilities.writeChartAsJPEG(resp.getOutputStream(), chart, 600, 300);
	}
	
	private HistogramDataset createDataset(MeasuresAggregate measuresAggregate) {
		List<Measure> measures = measuresAggregate.getMeasures();
		
        final HistogramDataset dataset = new HistogramDataset();
        
        double[] values = new double[measures.size()];
        
        for(int x = 0; x < measures.size(); x++){
        	values[x] = measures.get(x).getValue();
        }
        
        dataset.addSeries(measuresAggregate.getControlSerum().getName(), values, 20);
        
        return dataset;
    }
	
	private JFreeChart createChart(final HistogramDataset dataset) {
        // create the chart...
        final JFreeChart chart = ChartFactory.createHistogram(
            "Histograma",       			// chart title
            null,					// domain axis label
            null,                   // range axis label
            dataset,                   // data
            PlotOrientation.VERTICAL,  // orientation
            true,                      // include legend
            true,                      // tooltips
            false                      // urls
        );
        
        return chart;
    }
}
