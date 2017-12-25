package data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MutationInfo implements Comparator<MutationInfo> {

	/**
	 * Stores : list of mutants covered and run time for every test-case in each
	 * class. (information for these 4 variables)
	 * 
	 * Also has comparator to be used for test suite prioritization
	 */
	private List<Integer> mutantIds;
	private long runTime;
	private String associatedClassName;
	private String associatedFuncName;

	public MutationInfo() {
		this.mutantIds = new ArrayList<>();
	}

	public List<Integer> getMutantIds() {
		return mutantIds;
	}

	public void setMutantIds(List<Integer> mutantIds) {
		this.mutantIds = mutantIds;
	}

	public long getRunTime() {
		return runTime;
	}

	public void setRunTime(long runTime) {
		this.runTime = runTime;
	}

	@Override
	public int compare(MutationInfo o1, MutationInfo o2) {
		// Returns the one having smaller run-time. For same case, returns the
		// one that covers more mutants in order.
		if (o1.getRunTime() < o2.getRunTime()) {
			return -1;
		} else if (o2.getRunTime() < o1.getRunTime()) {
			return 1;
		} else {
			if (o2.getMutantIds().size() > o1.getMutantIds().size()) {
				return 1;
			} else if (o1.getMutantIds().size() > o2.getMutantIds().size()) {
				return -1;
			}
		}
		return 0;
	}

	public String getAssociatedClassName() {
		return associatedClassName;
	}

	public void setAssociatedClassName(String associatedClassName) {
		this.associatedClassName = associatedClassName;
	}

	public String getAssociatedFuncName() {
		return associatedFuncName;
	}

	public void setAssociatedFuncName(String associatedFuncName) {
		this.associatedFuncName = associatedFuncName;
	}

	@Override
	public String toString() {
		return "RunTime : " + this.runTime + "----" + "funcName : " + this.associatedFuncName + "----" + "className : "
				+ this.associatedClassName + "----" + "Mutant ID : " + this.getMutantIds().size();
	}

}
