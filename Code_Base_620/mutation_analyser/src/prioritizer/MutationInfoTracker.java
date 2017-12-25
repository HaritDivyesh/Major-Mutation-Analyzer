package prioritizer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import data.MutationInfo;

public class MutationInfoTracker {

	public static Map<String, Set<Integer>> trackCoveredMutantsPerClass(Map<String, MutationInfo> prepPassInfo) {

		Map<String, Set<Integer>> mutationInfoClassMap = new HashMap<>();
		// track the list of mutants covered per Test Class to optimise on
		// running time during Mutation Analysis
		for (Map.Entry<String, MutationInfo> entry : prepPassInfo.entrySet()) {
			String className = entry.getValue().getAssociatedClassName();
			List<Integer> currMutantIDs = entry.getValue().getMutantIds();
			if (mutationInfoClassMap.containsKey(className)) {
				Set<Integer> idSet = mutationInfoClassMap.get(className);
				idSet.addAll(currMutantIDs);
			} else {
				Set<Integer> idSet = new HashSet<>();
				idSet.addAll(currMutantIDs);
				mutationInfoClassMap.put(className, idSet);
			}
		}

		return mutationInfoClassMap;
	}

}
