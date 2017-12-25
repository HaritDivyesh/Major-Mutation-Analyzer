package prioritizer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import data.MutationInfo;

public class TestSuitePrioritizer {

	public static Map<String, List<String>> prioritize(Map<String, MutationInfo> map) {

		// input : Full Qualified name of a test case --mapped to-- mutation
		// coverage info + run time + associated individual test class and test
		// function names

		// output : Test Class name --mapped to-- names of test functions in
		// that
		// class in order of prioritized execution

		Map<String, List<String>> prioritizedOrder = new HashMap<>();

		// extract all present MutationInfo object map entries and sort them
		// together.
		List<MutationInfo> allMI = new ArrayList<>();
		for (Map.Entry<String, MutationInfo> entry : map.entrySet()) {
			allMI.add(entry.getValue());
		}

		Collections.sort(allMI, new MutationInfo());

		// iterate over the sorted MutationInfo list to separate out information
		// for each test class
		for (MutationInfo entry : allMI) {
			String currClassName = entry.getAssociatedClassName();
			String currFuncName = entry.getAssociatedFuncName();

			if (prioritizedOrder.containsKey(currClassName)) {
				List<String> functionNames = prioritizedOrder.get(currClassName);
				functionNames.add(currFuncName);
			} else {
				List<String> functionNames = new ArrayList<>();
				functionNames.add(currFuncName);
				prioritizedOrder.put(currClassName, functionNames);
			}
		}

		return prioritizedOrder;
	}

}
