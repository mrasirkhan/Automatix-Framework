package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadProperties {
	
	public static Properties load(String filename,String location){		 
		Properties prop = new Properties();
		ClassLoader loader = ReadProperties.class.getClassLoader();
     
            try (InputStream stream = loader.getResourceAsStream(location+filename)) {
                if (stream == null) {
                    throw new FileNotFoundException();
                }
                prop.load(stream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return prop;
       }
	
	public static String getProperty(String filename,String path,String key)
	{		
		Properties props = ReadProperties.load(filename,path);
		return props.getProperty(key) ;
		
	}
	

}