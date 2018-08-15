package jacryd.dev.swggy.autoassignment.input;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonInputReader<T>{

	private static final ObjectMapper objMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	public List<T> listData;
	
	public static <V extends JsonInputReader<?>> V read(String file, Class<V> clazz) throws JsonParseException, JsonMappingException, IOException {
		return objMapper.readValue(new File(file), clazz);
	}
	
	
}
