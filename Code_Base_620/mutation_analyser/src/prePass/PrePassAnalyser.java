package prePass;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.runner.JUnitCore;
import org.junit.runner.Request;

import data.MutationInfo;
import major.mutation.Config;

@SuppressWarnings("rawtypes")
public class PrePassAnalyser {

	// stores the list of pre-loaded test classes
	private List<Class> loadedTestClasses;

	public Map<String, MutationInfo> performPrePassAnalysis() {

		Map<String, MutationInfo> mutationInfoMap = new HashMap<>();

		for (Class eachClass : this.loadedTestClasses) {
			Method[] methods = eachClass.getDeclaredMethods();
			for (int i = 0; i < methods.length; i++) {
				// for each test case in each test class -> set Config parameter
				// = 0 from "major" tool and track mutant coverage information
				Config.__M_NO = 0;
				Request runRequest = Request.method(eachClass, methods[i].getName());

				// run method and record running time
				long startTime = System.currentTimeMillis();
				new JUnitCore().run(runRequest);
				long endTime = System.currentTimeMillis();

				// record mutants covered by this test case
				List<Integer> l = Config.getCoverageList();

				MutationInfo newInfo = new MutationInfo();
				newInfo.setRunTime(endTime - startTime);
				newInfo.setMutantIds(l);
				newInfo.setAssociatedClassName(eachClass.getName());
				newInfo.setAssociatedFuncName(methods[i].getName());

				// populate map information and reset config parameter from
				// "major"
				mutationInfoMap.put(eachClass.getName() + "." + methods[i].getName(), newInfo);
				Config.reset();
			}
		}
		return mutationInfoMap;
	}

	public List<Class> getLoadedTestClasses() {
		return loadedTestClasses;
	}

	public void setLoadedTestClasses(List<Class> loadedTestClasses) {
		this.loadedTestClasses = loadedTestClasses;
	}

}
