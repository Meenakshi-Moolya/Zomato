package pagedef;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import enums.IdentificationPlatform;
import enums.Platform;
import utils.JsonReader;

public class PageDef {
	Identifier identifier = null;
	String key;
	String value;
	Map<String, Map<IdentificationPlatform, Identifier>> map = new HashMap<String, Map<IdentificationPlatform, Identifier>>();

	public PageDef(String path) throws Exception {
		JsonReader reader = new JsonReader(path);
		JsonObject root = reader.getRoot();
		Iterator<Entry<String, JsonElement>> iter = root.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, JsonElement> idJson = iter.next();
			String elemName = idJson.getKey().trim().toLowerCase();
			map.put(elemName, new HashMap<IdentificationPlatform, Identifier>());
			Iterator<Entry<String, JsonElement>> iter2 = idJson.getValue().getAsJsonObject().entrySet().iterator();
			IdentificationPlatform platform = null;
			Identifier identifier = null;
			while (iter2.hasNext()) {
				Entry<String, JsonElement> id = iter2.next();
				platform = IdentificationPlatform.valueOf(id.getKey().trim().toUpperCase());
				String[] parts = id.getValue().getAsString().split("=", 2);
				try {
					identifier = new Identifier(parts[0].trim(), parts[1].trim());
				} catch (Exception e) {
					identifier = null;
				}
				map.get(elemName).put(platform, identifier);
			}
		}
	}

	public synchronized Identifier getIdentifier(Platform platform, String name) {
		return this.map.get(name.toLowerCase()).get(Platform.asIdentificationPlatform(platform));
	}

}
