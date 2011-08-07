package fspotcloud.peer;

public class NamingUtil {

	public void validateMethod(String method) throws MethodNotFoundException{
		if ("sendImageData".equals(method) || "sendPhotoData".equals(method)
				|| "sendMetaData".equals(method)
				|| "sendTagData".equals(method)) {
			return;
		} else {
			throw new MethodNotFoundException();
		}
	}
	
	private String getEntity(String method) {
		String result = method.substring(4).replace("Data", ""); 
		return result;
	}

	public String getDataMethod(String method) throws MethodNotFoundException {
		validateMethod(method);
		return method.replace("send", "get");
	}

	public String getRemoteMethod(String method) throws MethodNotFoundException {
		validateMethod(method);
		String entity = getEntity(method);
		if (entity.equals("Image")) {
			entity = "Photo";
		}
		return method.replace("send", entity + "Reciever.recieve");
	}
}
