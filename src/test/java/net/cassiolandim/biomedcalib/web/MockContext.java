package net.cassiolandim.biomedcalib.web;

import org.apache.wicket.injection.web.InjectorHolder;
import org.apache.wicket.spring.ISpringContextLocator;
import org.apache.wicket.spring.injection.annot.AnnotSpringInjector;
import org.apache.wicket.spring.injection.annot.test.AnnotApplicationContextMock;
import org.springframework.context.ApplicationContext;

/**
 * @author Cassio Landim
 */
public class MockContext extends AnnotApplicationContextMock {
	
	private static final long serialVersionUID = 1L;

	public MockContext() {
		InjectorHolder.setInjector(new AnnotSpringInjector(new ISpringContextLocator() {
			public ApplicationContext getSpringContext() {
				return MockContext.this;
			}
		}));
	}
}
