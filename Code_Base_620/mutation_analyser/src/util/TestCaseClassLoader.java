package util;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

@SuppressWarnings("rawtypes")
public class TestCaseClassLoader {

	public static List<Class> loadTestClasses(String jarLocation) {

		// extract all relevant test file names
		List<String> classNames = getAllClassesFromTestSuite(jarLocation);

		// load each extracted file name in the current jar and return the
		// populated list
		JarClassLoaderUtil jarCLU = new JarClassLoaderUtil(new URL[] {});
		jarCLU.addFile(jarLocation);
		List<Class> cList = jarCLU.getLoadedClasses(classNames);
		return cList;
	}

	private static List<String> getAllClassesFromTestSuite(String path) {
		List<String> classNames = new ArrayList<>();
		JarFile jarFile = null;
		try {
			// iterate over all entries in the jar and extract "Test" related
			// .class file names
			jarFile = new JarFile(path);
			Enumeration<JarEntry> enumt = jarFile.entries();
			while (enumt.hasMoreElements()) {
				extractClassName((JarEntry) enumt.nextElement(), classNames);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return classNames;
	}

	private static void extractClassName(JarEntry entry, List<String> classNames) {
		String name = entry.getName();
		if (name.endsWith("Test.class") && !name.contains("$")) {
			// get all file names that have a "Test" present in them. Store the
			// whole qualified name after doing slight pre-processing with
			// storage.
			// to discard ".class" in the end
			String[] str = name.split("\\.");
			// to replace "/" path value to "." to be used for loading later
			String string = str[0].replaceAll("/", ".");
			classNames.add(string);
		}
	}

}
