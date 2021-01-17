package net.jlp.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 
 * JLPFactory class contains methods for working with {@link JLPack} objects, in particular opportunity of encoding and decoding JLPack from file.
 * 
 * @since 1.0.0
 * 
 * @author Krows
 *
 */
public class JLPFactory {

/**
 * 
 * This method encodes {@link JLPack} into {@link File} through java Serialization.
 * 
 * @param file File is path to encoding.
 * @param pack JLPack to encoding.
 * 
 * @see Serializable
 * 
 */
	public static void encode(File file, JLPack pack) {
		
		try(ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(file))) {
			
			output.writeObject(pack);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
/**
 * 
 * This method decodes {@link JLPack} from {@link File} through java Serialization.
 * 
 * @param file File is path to decoding.
 * 
 * @return Decoded JLPack from file.
 * 
 * @see Serializable
 * 
 */
	public static JLPack decode(File file) {
		
		try(ObjectInputStream input = new ObjectInputStream(new FileInputStream(file))) {
			
			return (JLPack) input.readObject();
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
}