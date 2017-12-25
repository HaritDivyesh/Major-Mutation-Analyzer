package mutation_analyser;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import data.MutationAnalyserOutput;

@SuppressWarnings("rawtypes")
public class MutationAnalyser {

	private List<Class> loadedTestClasses;
	private String mutationLogFileLocation;
	private Map<String, List<String>> prioritizedTestSuiteInfo;
	private Map<String, Set<Integer>> mutationInfoPerClass;

	public MutationAnalyserOutput analyseMutants() {

		// compute the total no. of mutants using info from log file.
		int sizeOfMutants = getCountOfMutants();

		MutationAnalyserOutput output = new MutationAnalyserOutput();
		output.setTotalMutantCount(sizeOfMutants);

		int killedCount = 0;
		int killedCountTimeOut = 0;
		List<Integer> unkilledMutantIds = new ArrayList<>();

		System.out.println("\n Total no. of mutants = " + sizeOfMutants);
		// for each mutant, analyze kill information
		// Status Codes determine : Un-Killed (0) - Killed (1) - Killed due to
		// TimeOut (2)
		for (int mutantId = 1; mutantId <= sizeOfMutants; mutantId++) {
			int killedStatus = analyseEachMutant(mutantId);
			if (killedStatus > 0) {
				killedCount++;
				if (killedStatus == 2) {
					killedCountTimeOut++;
				}
			} else {
				unkilledMutantIds.add(mutantId);
			}

			if (mutantId % 100 == 0) {
				System.out.println(mutantId + " mutants analysed!!");
			}
		}

		// populate output object and return
		output.setMutantsKilled(killedCount);
		output.setMutantsKilledTimeout(killedCountTimeOut);
		output.setMutantIdsNotKilled(unkilledMutantIds);
		return output;
	}

	@SuppressWarnings("unused")
	private int getCountOfMutants() {
		int countOfMutants = 0;
		try {
			FileInputStream fstream = new FileInputStream(this.mutationLogFileLocation);
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			String strLine = "";
			// read log file line by line to update mutant count
			while ((strLine = br.readLine()) != null) {
				countOfMutants++;
			}
			fstream.close();
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
		return countOfMutants;
	}

	private int analyseEachMutant(int mutantId) {

		// to handle timeout - run an executor service that runs each test case
		// and handles time-out information
		ExecutorService executor = Executors.newSingleThreadExecutor();
		Future<Integer> future = executor.submit(new AnalyseTask(this.loadedTestClasses, mutantId,
				this.prioritizedTestSuiteInfo, this.mutationInfoPerClass));

		int runStatus = -1;
		try {
			// Timeout of 3 Seconds.
			runStatus = future.get(1, TimeUnit.SECONDS);
		} catch (TimeoutException e) {
			// Status Code 2 : TimeOut for Killed Mutant
			runStatus = 2;
			future.cancel(true);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

		// shut-down after current iteration
		executor.shutdownNow();
		return runStatus;
	}

	public String getMutationLogFileLocation() {
		return mutationLogFileLocation;
	}

	public void setMutationLogFileLocation(String mutationLogFileLocation) {
		this.mutationLogFileLocation = mutationLogFileLocation;
	}

	public Map<String, List<String>> getPrioritizedTestSuiteInfo() {
		return prioritizedTestSuiteInfo;
	}

	public void setPrioritizedTestSuiteInfo(Map<String, List<String>> prioritizedTestSuiteInfo) {
		this.prioritizedTestSuiteInfo = prioritizedTestSuiteInfo;
	}

	public List<Class> getLoadedTestClasses() {
		return loadedTestClasses;
	}

	public void setLoadedTestClasses(List<Class> loadedTestClasses) {
		this.loadedTestClasses = loadedTestClasses;
	}

	public Map<String, Set<Integer>> getMutationInfoPerClass() {
		return mutationInfoPerClass;
	}

	public void setMutationInfoPerClass(Map<String, Set<Integer>> mutationInfoPerClass) {
		this.mutationInfoPerClass = mutationInfoPerClass;
	}

}