package net.jlp.core;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * JLPack class implements different quantity of language maps which contains different values and keys.
 * 
 * @since 1.0.0
 * 
 * @author Krows
 *
 */
public class JLPack implements Serializable {
	
/**
 * 
 * Serial Version UID.
 * 
 */
	private static final long serialVersionUID = 1L;

/**
 * 
 * {@link HashMap} contains different HashMaps which contains keys and localization values.
 * 
 */
	private HashMap<Language, HashMap<String, String>> languageMap;

/**
 * 
 * Constructor creates JLPack and saves language map.
 * 
 * @param map {@link HashMap} which contains language maps.
 * 
 */
	protected JLPack(HashMap<Language, HashMap<String, String>> map) {
		
		this.languageMap = map;
	}
	
/**
 * 
 * This method creates JLPack.
 * 
 * @param map {@link HashMap} which contains language maps.
 * 
 * @return new JLPack.
 * 
 */
	public static JLPack create(HashMap<Language, HashMap<String, String>> map) {
		
		return new JLPack(map);
	}
	
/**
 * 
 * This method returns needed {@link Map} with selected {@link Language}.
 * 
 * @param language Needed map language.
 * 
 * @return Selected language map.
 * 
 */
	public Map<String, String> getByLanguage(Language language) {
		
		return languageMap.get(language);
	}

/**
 * 
 * This method finds needed language by selected abbreviation and returns needed {@link Map} with needed {@link Language}.
 * 
 * @param abbr Abbreviation of needed map language.
 * 
 * @return Selected language map.
 * 
 */
	public Map<String, String> getByAbbreviation(String abbr) {
		
		return languageMap.get(Language.getByAbbreviation(abbr));
	}

/**
 * 
 * Returns formatted String of all loaded languages in to this object. For example:
 * <blockquote>English(en), Français(fr), Deutsch(de).</blockquote>
 * 
 * @return String with all loaded languages.
 * 
 */
	public String getLanguages() {
		
		String result = "";
		
		for(Language lang : languageMap.keySet()) {
			
			result += lang + ", ";
		}
		
		result = result.substring(0, result.length() - 2);
		result += ".";
		
		return result;
	}
	
/**
 * 
 * Reads all data from {@link ObjectInputStream} object in to this object with Java Serialization method. 
 * Procedure of reading: <br>
 * 1. Creates needing collections for saving. <br>
 * 2. Reads number of saved languages. <br>
 * 3. Reads all languages with it abbreviations by iteration
 * (Remark: if while writing in to the file used custom language, 
 * it must be loaded before creating of pack, otherwise throws {@link NullPointerException}). <br>
 * 4. Reads all localization data from input stream by iteration.
 * 
 * @param o {@link ObjectInputStream} object for reading.
 * 
 * @throws ClassNotFoundException If something goes wrong, for example by reason of file corruption.
 * @throws IOException If something goes wrong, for example by reason of file corruption.
 * 
 */
	@SuppressWarnings({"unchecked"})
	private void readObject(ObjectInputStream o) throws ClassNotFoundException, IOException {

		languageMap = new HashMap<>();
		
		List<Language> langList = new ArrayList<>();
		
		int count = o.readInt();
		
		for(int i = 0; i < count; i++) {
			
			langList.add(Language.getByAbbreviation(o.readUTF()));
		}
		
		for(Language lang : langList) {
			
			languageMap.put(lang, (HashMap<String, String>) o.readObject());
		}
	}
	
/**
 * 
 * Writes this object in to the ".jlp" file with Java Serialization method. Procedure of serialization: <br>
 * 1. Writes number of loaded languages. <br>
 * 2. Writes abbreviations of loaded languages by iteration.<br>
 * 3. Writes localization data from {@link #languageMap}.
 * 
 * @param o {@link ObjectOutputStream} object for writing.
 * 
 * @throws IOException If something goes wrong, for example by reason of file corruption.
 * 
 */
	private void writeObject(ObjectOutputStream o) throws IOException {

		o.writeInt(languageMap.size());
		
		for(Language lang : languageMap.keySet()) {
			
			o.writeUTF(lang.getAbbreviation());
		}
		
		for(HashMap<String, String> map : languageMap.values()) {
			
			o.writeObject(map);
		}
	}
}