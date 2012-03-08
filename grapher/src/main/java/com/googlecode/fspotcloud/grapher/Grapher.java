package com.googlecode.fspotcloud.grapher;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.grapher.GrapherModule;
import com.google.inject.grapher.InjectorGrapher;
import com.google.inject.grapher.graphviz.GraphvizModule;
import com.google.inject.grapher.graphviz.GraphvizRenderer;

public class Grapher {

	Injector injectorToPlot;
	String name;

	public Grapher(Injector injector, String name) throws Exception {
		injectorToPlot = injector;
		this.name = name;
		plot();
	}

	private void graph(String filename, Injector demoInjector)
			throws IOException {
		PrintWriter out = new PrintWriter(new File("target/" + filename), "UTF-8");
		Injector injector = Guice.createInjector(new GrapherModule(),
				new GraphvizModule());
		GraphvizRenderer renderer = injector
				.getInstance(GraphvizRenderer.class);
		renderer.setOut(out).setRankdir("TB");

		injector.getInstance(InjectorGrapher.class).of(demoInjector).graph();
	}

	public void plot() throws Exception {
		graph(name + ".dot", injectorToPlot);
		postProcess();
	}

	public void postProcess() throws Exception {
		Runtime runtime = Runtime.getRuntime();
		Process p = runtime.exec("sed -i -e s/invis/solid/g  target/" + name
				+ ".dot target/" + name + ".dot");
		p.waitFor();
		runtime.exec("dot -Tpng target/"+ name +".dot -o target/" + name +".png ");
	}
}
