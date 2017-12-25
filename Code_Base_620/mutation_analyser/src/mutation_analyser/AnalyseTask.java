package mutation_analyser;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;

import org.junit.runner.JUnitCore;
import org.junit.runner.Request;
import org.junit.runner.Result;

import major.mutation.Config;

@SuppressWarnings("rawtypes")
public class AnalyseTask implements Callable<Integer> {

	private List<Class> testClasses;
	private int mutantId;
	private Map<String, List<String>> prioritizedTestSuiteInfo;
	private Map<String, Set<Integer>> mutationInfoPerClass;

	public AnalyseTask(List<Class> testClasses, int mutantId, Map<String, List<String>> prioritizedTestSuiteInfo,
			Map<String, Set<Integer>> mutationInfoPerClass) {
		this.testClasses = testClasses;
		this.mutantId = mutantId;
		this.prioritizedTestSuiteInfo = prioritizedTestSuiteInfo;
		this.mutationInfoPerClass = mutationInfoPerClass;
	}

	@Override
	public Integer call() throws Exception {
		int status = 0;
		try {
			// Set Config in "major" tool to current mutant ID
			Config.__M_NO = mutantId;
			for (Class eachClass : testClasses) {
				// iterate over ordered list of test cases in that class
				// run only those tests that were covered by the current test
				// class
				if (!this.mutationInfoPerClass.get(eachClass.getName()).contains(mutantId)) {
					continue;
				}
				List<String> methodNames = prioritizedTestSuiteInfo.get(eachClass.getName());
				for (int i = 0; i < methodNames.size(); i++) {
					Request runRequest = Request.method(eachClass, methodNames.get(i));

					Result runResult = new JUnitCore().run(runRequest);
					if (runResult.getFailureCount() != 0) {
						// Mutant Killed - Test Run Failed - Set Status - Break
						// Loop
						status = 1;
						break;
					}
				}
				// Break loop once mutant has been killed - Assumes one mutant
				// per mutant-ID only
				if (status == 1) {
					break;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		// reset config
		Config.reset();
		return new Integer(status);
	}

	public List<Class> getTestClasses() {
		return testClasses;
	}

	public void setTestClasses(List<Class> testClasses) {
		this.testClasses = testClasses;
	}

	public int getMutantId() {
		return mutantId;
	}

	public void setMutantName(int mutantId) {
		this.mutantId = mutantId;
	}

	public Map<String, Set<Integer>> getMutationInfoPerClass() {
		return mutationInfoPerClass;
	}

	public void setMutationInfoPerClass(Map<String, Set<Integer>> mutationInfoPerClass) {
		this.mutationInfoPerClass = mutationInfoPerClass;
	}

}
