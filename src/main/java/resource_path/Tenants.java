package resource_path;

public class Tenants {

	
	public static String add_array_to_tenant_path(String tenant_id) {
		return "/tenants.json/"+tenant_id+"/arrays";
	}
	public static String get_current_tenant_path() {
		return "/tenants.json/current";
	}
	
	public static String delete_array_for_tenan_path(String tenant_id, String array_id) {		
		return "/tenants.json/"+tenant_id+"/arrays/"+array_id;		
	}

	public static String add_tenant_path() {
		return "/tenants.json";
	}
	
	public static String delete_tenant_path(String tenant_id) {
		return "/tenants.json/"+tenant_id;
	}

}
