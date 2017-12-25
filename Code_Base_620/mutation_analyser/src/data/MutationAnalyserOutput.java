package data;

import java.util.ArrayList;
import java.util.List;

public class MutationAnalyserOutput {

	/**
	 * Stores : Mutation Kill Score, Summary and Note on Un-Killed Mutants. Also
	 * stores run time of the code.
	 */

	private int totalMutantCount;
	private int mutantsKilled;
	private int mutantsKilledTimeout;
	private List<Integer> mutantIdsNotKilled;
	private long programRunTime;

	public MutationAnalyserOutput() {
		mutantIdsNotKilled = new ArrayList<>();
	}

	public int getTotalMutantCount() {
		return totalMutantCount;
	}

	public void setTotalMutantCount(int totalMutantCount) {
		this.totalMutantCount = totalMutantCount;
	}

	public int getMutantsKilled() {
		return mutantsKilled;
	}

	public void setMutantsKilled(int mutantsKilled) {
		this.mutantsKilled = mutantsKilled;
	}

	public int getMutantsKilledTimeout() {
		return mutantsKilledTimeout;
	}

	public void setMutantsKilledTimeout(int mutantsKilledTimeout) {
		this.mutantsKilledTimeout = mutantsKilledTimeout;
	}

	public List<Integer> getMutantIdsNotKilled() {
		return mutantIdsNotKilled;
	}

	public void setMutantIdsNotKilled(List<Integer> mutantIdsNotKilled) {
		this.mutantIdsNotKilled = mutantIdsNotKilled;
	}

	public long getProgramRunTime() {
		return programRunTime;
	}

	public void setProgramRunTime(long programRunTime) {
		this.programRunTime = programRunTime;
	}

}
