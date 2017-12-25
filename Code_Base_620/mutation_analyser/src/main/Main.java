package main;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JFrame;

import data.MutationAnalyserOutput;
import data.MutationInfo;
import mutation_analyser.MutationAnalyser;
import output.CompositeView;
import output.MutationKillSummaryView;
import output.MutationScoreView;
import output.RunTimeInfoView;
import output.UnKilledMutantInfoView;
import prePass.PrePassAnalyser;
import prioritizer.MutationInfoTracker;
import prioritizer.TestSuitePrioritizer;
import util.TestCaseClassLoader;

@SuppressWarnings("rawtypes")
public class Main {

	public static void main(String[] args) {

		// requires path location to (a) compiled jar file for mutants (b)
		// mutants.log file
		if (args.length != 2) {
			System.out.println(
					"Please correctly enter : (1) Path to Compile Source Jar File ; (2) Path to Mutant log file");
			System.out.println("Exiting!!!");
			return;
		}

		System.out.println("-----Starting Mutation Analysis-----");
		String jarLocation = args[0];
		String mutationLogFileLocation = args[1];

		// track the running time of the program
		long startOfCode = System.currentTimeMillis();

		// STEP 0 : Extract all relevant test classes from the provided Jar and
		// load them.
		List<Class> testClassList = TestCaseClassLoader.loadTestClasses(jarLocation);

		// STEP 1 : Pre-pass phase to analyse coverage and run-time information
		// for each test-case.
		// Return a map of full test case names (className+FuncName) v/s an
		// object of MutationInfo containing the mutant ids it covers and the
		// run time of the corresponding test case.
		System.out.println("\n STEP 1 : Pre-Pass (Collecting Mutation coverage and run-time info for each test case)");

		PrePassAnalyser ppa = new PrePassAnalyser();
		ppa.setLoadedTestClasses(testClassList);
		Map<String, MutationInfo> prepPassInfo = ppa.performPrePassAnalysis();

		// STEP 2 : Use info populated in the map from STEP 1 to prioritize test
		// suite for running actual Mutation analyser.
		// Returns a map of classNames v/s the respective order of each test
		// case for execution in that class
		// Also maps each Test class to the mutant Ids it covers.
		// To be used later for optimisation.
		System.out.println("\n STEP 2 : Performing Test Suite Prioritization");

		Map<String, List<String>> prioritizedTests = TestSuitePrioritizer.prioritize(prepPassInfo);
		Map<String, Set<Integer>> coveredMutantsByClass = MutationInfoTracker.trackCoveredMutantsPerClass(prepPassInfo);

		// STEP 3 : Conducting Strong Mutation Analysis by running each test
		// case on each mutant to track mutation kill information
		// Returns an object with Mutation Kill Info populated.
		System.out.println("\n STEP 3 : Performing Strong Mutation Analysis");

		MutationAnalyser mutationAnalyser = new MutationAnalyser();

		mutationAnalyser.setLoadedTestClasses(testClassList);
		mutationAnalyser.setMutationLogFileLocation(mutationLogFileLocation);
		mutationAnalyser.setPrioritizedTestSuiteInfo(prioritizedTests);
		mutationAnalyser.setMutationInfoPerClass(coveredMutantsByClass);

		// store output (Mutation Kill Information, Kill Summary and Code
		// RunTime)
		MutationAnalyserOutput output = null;
		try {
			output = mutationAnalyser.analyseMutants();
		} catch (Exception e) {
			e.printStackTrace();
		}

		long endOfCode = System.currentTimeMillis();
		output.setProgramRunTime(endOfCode - startOfCode);

		// Display the desired components of the output.
		JFrame mainFrame = new JFrame("Major Mutation Framework : Mutation Analyser");
		mainFrame.setLayout(new FlowLayout());

		// Initialize the main composite view and add all the desired
		// constituents
		CompositeView cv = new CompositeView();

		cv.addConstituentView(new MutationScoreView(mainFrame));
		cv.addConstituentView(new MutationKillSummaryView(mainFrame));
		cv.addConstituentView(new UnKilledMutantInfoView(mainFrame));
		cv.addConstituentView(new RunTimeInfoView(mainFrame));

		cv.displayComponent(output);

		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(new Dimension(1500, 1500));
		mainFrame.setResizable(false);
		mainFrame.setVisible(true);
		
		System.out.println("Done!!");
	}

}
