package Adapter;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadData {
	JSONParser jsonParser = new JSONParser();
	String ruteFile;

	public ReadData(String ruteFileJson) {
		this.ruteFile = ruteFileJson;
	}

	public JSONObject readNode(String testCaseName) {
		JSONObject dataTestCase = null;
		try {
			// Read JSON file
			Object object = jsonParser.parse(new FileReader(ruteFile));
			JSONObject jsonObject = (JSONObject) object;
			dataTestCase = (JSONObject) jsonObject.get(testCaseName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dataTestCase;

	}
}
