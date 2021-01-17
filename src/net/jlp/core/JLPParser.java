package net.jlp.core;

import static net.coretools.core.io.file.FileTools.JAVA_LANGUAGE_OBJECT_EXTENSION;

import java.io.File;
import java.util.HashMap;

import net.coretools.core.io.file.FileTools;

/**
 * 
 * JLPParser class parses files to {@link JLPack} objects.
 * 
 * @since 1.3.0
 * 
 * @author Krows
 *
 */
public final class JLPParser {
	
/**
 * 
 * The sign of variable begin.
 * 
 */
	private static final String VARIABLE_BEGIN_SIGN = "$.";
	
/**
 * 
 * The sign of variable end.
 * 
 */
	private static final String VARIABLE_END_SIGN = ":";
	
/**
 * 
 * The sign of value begin.
 * 
 */
	private static final String VALUE_BEGIN_SIGN = "{";
	
/**
 * 
 * The sign of value end.
 * 
 */
	private static final String VALUE_END_SIGN = "}";
	
/**
 * 
 * The abbreviation variable name.
 * 
 */
	private static final String ABBREVIATION_VARIABLE = "abbr";
	
/**
 * 
 * Closed constructor.
 *
 */
	private JLPParser() {
		
	}
	
/**
 * 
 * Parses all Java Language Object(.jlo) files in specified directory and packs all data to {@link JLPack} object.
 * <p><em>File formating note:</em>
 * <br>1. Each variable should begin from "$." sign and end to ":" sign.
 * <br>2. Each value should begin from "{" character and end to "}" character.
 * <br>3. The file should be encode to "UTF-8".
 * <br>3. The file text should follow the order "variable-value" in free number of times.
 * <br>4. The file should contain "abbr" variable(language abbreviation variable).
 * <br>5. The language of parsing data should be containing in {@link Language} class as constant or loaded language before parsing.
 * 
 * @param directory Directory to find files for parsing(not path of certain file, only directory).
 * 
 * @return JLPack object with result of file parsing.
 * 
 */
	public static JLPack parse(File directory) {
		
//		Map for pack
		HashMap<Language, HashMap<String, String>> map = new HashMap<>();
		
//		Parsed variable
		String var;
//		Parsed value
		String value;
//		File text
		String text;
		
//		First parse index
		int firstIndex;
//		Second parse index
		int secondIndex;
			
		File[] files = directory.listFiles((pathname) -> {
			
			return pathname.isFile() && pathname.getName().endsWith(JAVA_LANGUAGE_OBJECT_EXTENSION);
		});
		
		for(File file : files) {
			
			HashMap<String, String> locMap = new HashMap<>();
			
			text = FileTools.getText(file);
			
//			Reset indexes
			firstIndex = 0;
			secondIndex = 0;
			
			while(text.indexOf(VARIABLE_BEGIN_SIGN, secondIndex) != - 1) {
				
				firstIndex = text.indexOf(VARIABLE_BEGIN_SIGN, secondIndex);
				
				secondIndex = text.indexOf(VARIABLE_END_SIGN, firstIndex);
				
//				Get var
				var = text.substring(firstIndex + VARIABLE_BEGIN_SIGN.length(), secondIndex);
				
				firstIndex = text.indexOf(VALUE_BEGIN_SIGN, secondIndex);
				
				secondIndex = text.indexOf(VALUE_END_SIGN, firstIndex);
				
//				Get value
				value = text.substring(firstIndex + VALUE_BEGIN_SIGN.length(), secondIndex);
				
//				Put after one variable iteration
				locMap.put(var, value);
			}
			
//			Put after one file iteration
			map.put(Language.getByAbbreviation(locMap.get(ABBREVIATION_VARIABLE)), locMap);
		}
		
		return new JLPack(map);
	}
}