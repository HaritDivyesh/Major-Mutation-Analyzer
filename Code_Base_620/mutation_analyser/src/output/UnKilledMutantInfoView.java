package output;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import data.MutationAnalyserOutput;

public class UnKilledMutantInfoView implements OutputView {

	private JFrame mainFrame;

	public UnKilledMutantInfoView(JFrame mainFrame) {
		this.setMainFrame(mainFrame);
	}

	@Override
	public void displayComponent(MutationAnalyserOutput output) {
		// Count and ids of un-killed mutants
		JPanel labels = new JPanel(new GridLayout(10, 10));
		JPanel labels2 = new JPanel(new GridLayout(10, 10));
		JLabel count = new JLabel("Total no. of Un-Killed Mutants : " + output.getMutantIdsNotKilled().size() + "\n");
		labels.add(count);
		if (output.getMutantIdsNotKilled().size() > 0) {
			JLabel listOfMutants = new JLabel(
					"Un-Killed Mutant Ids : " + output.getMutantIdsNotKilled().toString() + "\n");
			labels2.add(listOfMutants);
		}
		this.mainFrame.add(labels, BorderLayout.CENTER);
		this.mainFrame.add(labels2, BorderLayout.SOUTH);
	}

	public JFrame getMainFrame() {
		return mainFrame;
	}

	private void setMainFrame(JFrame mainFrame) {
		this.mainFrame = mainFrame;
	}
}
