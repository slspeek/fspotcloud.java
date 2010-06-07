package fspotcloud.server;

import java.util.ArrayList;
import java.util.List;

public class Calculator {
	public int add(int i1, int i2) {
		return i1 + i2;
	}

	public int subtract(int i1, int i2) {
		return i1 - i2;
	}
	
	public String[] list() {
		String[] r = { "10", "13" };
		return r;
	}
	
	public int recieve(Object[] a1, Object[] a2) {
		System.out.println(a1[0] );
		return 0;
	}
}
