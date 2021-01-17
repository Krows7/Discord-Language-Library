package net.jlp.core;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * Language class contains data of name and abbreviation of the language. 
 * Here is containing some of the official languages (the list will expand). 
 * This class Supports creating of custom language.
 * 
 * @since 1.0.0
 * 
 * @author Krows
 *
 */
public class Language implements Serializable {
	
/**
 * 
 * Serial version UID.
 * 
 */
	private static final long serialVersionUID = 1L;

/**
 * 
 * HashMap contains data of all loaded languages. Uses for containing and sending languages.
 * 
 */
	private final static Map<String, Language> abbrMap = new HashMap<>();
	
/**
 * 
 * Attached English language. Used "en" abbreviation.
 * 
 */
	public static final Language ENGLISH = new Language("English", "en");
	
/**
 * 
 * Attached Russian language. Used "ru" abbreviation.
 * 
 */
	public static final Language RUSSIAN = new Language("Русский", "ru");
	
/**
 * 
 * Attached Ukrainian language. Used "ua" abbreviation.
 * 
 */
	public static final Language UKRAINIAN = new Language("Український", "ua");
	
/**
 * 
 * Attached German language. Used "de" abbreviation.
 * 
 */
	public static final Language GERMAN = new Language("Deutsch", "de");
	
/**
 * 
 * Attached French language. Used "fr" abbreviation.
 * 
 */
	public static final Language FRENCH = new Language("Français", "fr");
	
/**
 * 
 * Attached Japanese language. Used "ja" abbreviation.
 * 
 */
	public static final Language JAPANESE = new Language("日本", "ja");
	
/**
 * 
 * Attached Belarusian language. Used "be" abbreviation.
 * 
 */
	public static final Language BELARUSIAN = new Language("беларускі", "be");
	
/**
 * 
 * String contains name of language.
 * 
 */
	private String language;
	
/**
 * 
 * String contains abbreviation of language.
 * 
 */
	private String abbr;
	
/**
 * 
 * Language class constructor receives name and abbreviation of language and adds it in to the loaded languages list.
 * It's desirable to receiving language name on language that it.
 * 
 * @param language Language name.
 * @param abbr Language abbreviation.
 * 
 */
	public Language(String language, String abbr) {
		
		this.language = language;
		
		this.abbr = abbr;
		
		abbrMap.put(abbr, this);
	}
	
/**
 * 
 * Returns {@code Language} object by it abbreviation from parameter. If language with this abbreviation was not loaded returns null.
 * 
 * @param abbr Language abbreviation.
 * 
 * @return Language object with it abbreviation if was loaded, otherwise returns null.
 * 
 */
	public static Language getByAbbreviation(String abbr) {
		
		return abbrMap.get(abbr);
	}
	
/**
 * 
 * Returns formatted String of all loaded languages. For example:
 * <blockquote>English(en), Français(fr), Deutsch(de).</blockquote>
 * 
 * @return String with all loaded languages.
 * 
 */
	public static String getLanguages() {
		
		String result = "";
		
		for(Language lang : abbrMap.values()) {
			
			result += lang + ", ";
		}
		
		result = result.substring(0, result.length() - 2);
		result += ".";
		
		return result;
	}
	
/**
 * 
 * Returns name of this {@link Language} object.
 * 
 * @return name of this language.
 * 
 */
	public String getName() {

		return language;
	}
	
/**
 * 
 * Returns abbreviation of this {@link Language} object.
 * 
 * @return abbreviation of this language.
 * 
 */
	public String getAbbreviation() {

		return abbr;
	}
	
	@Override
	public String toString() {

		return language + "(" + abbr + ")"; 
	}
}