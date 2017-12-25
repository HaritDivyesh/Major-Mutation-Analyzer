package util;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("rawtypes")
public class JarClassLoaderUtil extends URLClassLoader {

	public JarClassLoaderUtil(URL[] urls) {
		super(urls);
	}

	public void addFile(String path) {
		// add jar url path
		String urlPath = "jar:file://" + path + "!/";
		try {
			addURL(new URL(urlPath));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	public List<Class> getLoadedClasses(List<String> classNames) {
		// load individual classes based on their name
		List<Class> cList = new ArrayList<>();
		for (String testClass : classNames) {
			try {
				cList.add(loadClass(testClass));
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return cList;
	}

}
