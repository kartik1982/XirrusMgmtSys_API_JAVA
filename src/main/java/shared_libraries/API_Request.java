package shared_libraries;


public class API_Request {
//	String api_url=null;
//	String baseURL=null;
//	String token=null;
//	Response resp =null;
//	
//	/**
//	 * enum for request action
//	 * @author kaiyar
//	 *
//	 */
//	enum APIAction{
//	    GET, PUT,POST, POSTFILE, DELETE, DELETEWITHARGS;
//	}
//	/**
//	 * General get function for any request
//	 * @param path
//	 * @param msgBody
//	 */
//	public void get(String path, String msgBody){
//		request(APIAction.GET, path, msgBody);
//	}
//	/**
//	 * General post function for any kind of request
//	 * @param path
//	 * @param msgBody
//	 */
//	public void post(String path, String msgBody){
//		request(APIAction.POST, path, msgBody);
//		
//	}
//	public void postFile(String path, String msgBody){
//		request(APIAction.POSTFILE, path, msgBody);
//		
//	}
//	public void put(String path, String msgBody){
//		request(APIAction.PUT, path, msgBody);
//		
//	}
//	/**
//	 * General delete function for any kind of request
//	 * @param path
//	 * @param msgBody
//	 * @return 
//	 */
//	public void delete(String path, String msgBody){
//		request(APIAction.DELETE, path, msgBody);
//		
//	}
//	public void deleteWithArgs(String path, String msgBody){
//		request(APIAction.DELETEWITHARGS, path, msgBody);
//		
//	}
//	public void request(APIAction method, String resource_path, String msgbody){
//		RestAssured.baseURI=baseURL+"/api/v2";
//		switch (method){
//		case GET:
//			resp =  given().
//					header("Content-Type", "application/json").
//					param("access_token", token).
//					when ().
//					contentType (ContentType.JSON).
//					get (resource_path);
//			break;
//
//		case POST:
//			api_url+=path;
//			resp =  given().body (msgbody).
//					queryParam("access_token", token).
//					when ().
//					contentType (ContentType.JSON).
//					post (api_url);		
//			break;
//
//		case POSTFILE:
//			break;
//
//		case PUT:
//			api_url+=path;
//			resp =  given().body (msgbody).
//					queryParam("access_token", token).
//					when ().
//					contentType (ContentType.JSON).
//					put (api_url);	
//			break;
//
//		case DELETE:
//			api_url+=path;
//			resp =  given().
//					param("access_token", token).
//					when ().
//					contentType (ContentType.JSON).
//					delete(api_url);	
//			break;
//
//		case DELETEWITHARGS:
//			break;
//
//		default:
//			break;		
//		}
//
//	}

}
