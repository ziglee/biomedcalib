package net.cassiolandim.biomedcalib;

import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.bio.SocketConnector;
import org.mortbay.jetty.webapp.WebAppContext;

/**
 * Seperate startup class for people that want to run the app directly.
 * @author Cassio Landim
 */
public final class Start {
	
	private Start() {}
	
	/**
	 * Main function, starts the jetty server.
	 *
	 * @param args
	 */
	public static void main(final String[] args) {
		Server server = new Server();
		SocketConnector connector = new SocketConnector();
		connector.setPort(9090);
		server.setConnectors(new Connector[] {connector});

		WebAppContext context = new WebAppContext();
		context.setServer(server);
		context.setContextPath("/biomedcalib");
		context.setWar("src/main/webapp");

		server.addHandler(context);
		try {
			server.start();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
