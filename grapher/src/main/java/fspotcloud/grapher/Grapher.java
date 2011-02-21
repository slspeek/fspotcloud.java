package fspotcloud.grapher;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import com.google.gwt.inject.rebind.adapter.GinModuleAdapter;
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
	Injector clientInjector = Guice.createInjector(new GinModuleAdapter(
			new FakeForGrapherAppModule()));

	private void graph(String filename, Injector demoInjector)
			throws IOException {
		PrintWriter out = new PrintWriter(new File("target/" + filename),
				"UTF-8");
		Injector injector = Guice.createInjector(new GrapherModule(),
				new GraphvizModule());
		GraphvizRenderer renderer = injector
				.getInstance(GraphvizRenderer.class);
		renderer.setOut(out).setRankdir("TB");

		injector.getInstance(InjectorGrapher.class).of(demoInjector).graph();
	}

	public void plot() throws IOException {
		graph("client.dot", clientInjector);
		graph("server.dot", serverInjector);
		graph("peer.dot", peerInjector);
	}

	public void postProcess() throws Exception {
		Runtime runtime = Runtime.getRuntime();
		Process p = runtime.exec("sed -i -e s/invis/solid/g target/client.dot target/peer.dot target/server.dot");
		p.waitFor();
		runtime.exec("dot -Tpng target/peer.dot -o target/peer.png ");
		runtime.exec("dot -Tpng target/server.dot -o target/server.png");
		runtime.exec("dot -Tpng target/client.dot -o target/client.png");
	}

	public static void main(String[] args) throws Exception {
		Grapher g = new Grapher();
		g.plot();
		g.postProcess();
	}
}
