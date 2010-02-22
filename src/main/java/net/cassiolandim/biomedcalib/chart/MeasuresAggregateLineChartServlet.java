package net.cassiolandim.biomedcalib.chart;

import java.awt.BasicStroke;
import java.awt.Color;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.cassiolandim.biomedcalib.entity.ControlSerum;
import net.cassiolandim.biomedcalib.entity.Measure;
import net.cassiolandim.biomedcalib.entity.MeasuresAggregate;
import net.cassiolandim.biomedcalib.service.MeasuresAggregatePersistableService;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.Range;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class MeasuresAggregateLineChartServlet extends HttpServlet {

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
		final CategoryDataset dataset = createDataset(measuresAggregate);
		final JFreeChart chart = createChart(measuresAggregate, dataset);
		ChartUtilities.writeChartAsJPEG(resp.getOutputStream(), chart, 600, 300);
	}
	
	private CategoryDataset createDataset(MeasuresAggregate measuresAggregate) {
		List<Measure> measures = measuresAggregate.getMeasures();
		
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        int index = 0;
        for(Measure measure : measures){
        	dataset.addValue(measure.getValue(), measuresAggregate.getControlSerum().getName(), Integer.toString(++index));
        }
        
        return dataset;
    }
	
	private JFreeChart createChart(final MeasuresAggregate measuresAggregate, final CategoryDataset dataset) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		TextTitle subtitle = new TextTitle(sdf.format(measuresAggregate.getFirstDate()));
		
        // create the chart...
        final JFreeChart chart = ChartFactory.createLineChart(
            "Medidas",       			// chart title
            "Corridas",// domain axis label
            "Valor",                   // range axis label
            dataset,                   // data
            PlotOrientation.VERTICAL,  // orientation
            true,                      // include legend
            true,                      // tooltips
            false                      // urls
        );

        chart.setBackgroundPaint(Color.white);
        chart.addSubtitle(subtitle);
        
        final CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setRangeGridlinePaint(Color.white);

        ControlSerum controlSerum = measuresAggregate.getControlSerum();
        
		Double standardDeviation = controlSerum.getStandardDeviation();
		Double minimum = controlSerum.getMinimum() - (2 * standardDeviation);
		Double maximum = controlSerum.getMaximum() + (2 * standardDeviation);
		
		Range range = new Range(minimum, maximum);
		NumberTickUnit numberTickUnit = new NumberTickUnit(standardDeviation);
		
        // customise the range axis...
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setAutoRange(true);
        rangeAxis.setTickUnit(numberTickUnit);
        rangeAxis.setRange(range);
        
        // customise the renderer...
        final LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));
        renderer.setShapesVisible(true);
        
        return chart;
    }
}
