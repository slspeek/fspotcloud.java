package fspotcloud.peer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.google.inject.Inject;

import fspotcloud.peer.db.Data;

public class DataFetcherImpl implements DataFetcher{

	final private Data data;
	
	@Inject 
	public DataFetcherImpl(Data data){
		this.data = data;
	}

	@Override
	public Object getData(String dataMethod, Object[] args) throws MethodNotFoundException {
		Method method = findMethod(dataMethod);
		Object result = null;
		try {
			result = method.invoke(data, args);
		} catch (IllegalArgumentException e) {
			throw new MethodNotFoundException(e);
		} catch (IllegalAccessException e) {
			throw new MethodNotFoundException(e);
		} catch (InvocationTargetException e) {
			throw new MethodNotFoundException(e);
		}
		return result;
	}
	
	private Method findMethod(String name) throws MethodNotFoundException {
		Method[] all = data.getClass().getDeclaredMethods();
		for (Method m : all) {
			if (m.getName().equals(name)) {
				return m;
			}
		}
		throw new MethodNotFoundException();
	}
}
