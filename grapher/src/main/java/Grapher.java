import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.grapher.GrapherModule;
import com.google.inject.grapher.InjectorGrapher;
import com.google.inject.grapher.graphviz.GraphvizModule;
import com.google.inject.grapher.graphviz.GraphvizRenderer;

import fspotcloud.peer.BotModule;
import fspotcloud.server.main.FSpotCloudModule;

public class Grapher {
	
	Injector serverInjector = Guice.createInjector(new FSpotCloudModule());
	Injector peerInjector = Guice.createInjector(new BotModule());
	
  private void graph(String filename, Injector demoInjector) throws IOException {
    PrintWriter out = new PrintWriter(new File("target/" + filename), "UTF-8");

    Injector injector = Guice.createInjector(new GrapherModule(), new GraphvizModule());
    GraphvizRenderer renderer = injector.getInstance(GraphvizRenderer.class);
    renderer.setOut(out).setRankdir("TB");

    injector.getInstance(InjectorGrapher.class)
        .of(demoInjector)
        .graph();
  }

  public void plot() throws IOException {
	  graph("server.dot", serverInjector);
	  graph("peer.dot", peerInjector);
  }
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		Grapher g = new Grapher();
		g.plot();
	}
}
