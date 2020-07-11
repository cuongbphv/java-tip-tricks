package com.fis.ib.util;

@Component
public class Utility {

	@Autowired
	private SQLHelper sqlHelper;
	
	public String getUserTypeByUserId(String userId) {
		List<StoredProcedureInput<?>> inputs = new ArrayList<>();
		inputs.add(new StoredProcedureInput<>("id", userId, String.class));
		return sqlHelper.callStoredProcedureWithSingleOutput("SP_GET_USERTYPE", inputs, "type", String.class);
	}

	public String getErrorDesc(String errorCode, String language) {
		Map<String, Object> params = new HashMap<>();
		params.put("code", errorCode);
		return sqlHelper.callFunction("SP_GET_ERRORDESC", params, String.class);
	}

}
