package output;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import data.MutationAnalyserOutput;

public class MutationScoreView implements OutputView {

	private JFrame mainFrame;

	public MutationScoreView(JFrame mainFrame) {
		this.setMainFrame(mainFrame);
	}

	@Override
	public void displayComponent(MutationAnalyserOutput output) {
		// mutation kill rate for the current code
		JPanel labels = new JPanel(new GridLayout(10, 10));
		JLabel allMutants = new JLabel("Total Number of Mutants Present : " + output.getTotalMutantCount() + "\n");
		JLabel killedMutants = new JLabel("Total Number of Mutants Killed : " + output.getMutantsKilled() + "\n");
		JLabel killRate = new JLabel(
				"Mutation Score : " + (output.getMutantsKilled() * 100.0) / output.getTotalMutantCount() + "%" + "\n");
		labels.add(allMutants);
		labels.add(killedMutants);
		labels.add(killRate);
		this.mainFrame.add(labels, BorderLayout.CENTER);
	}

	public JFrame getMainFrame() {
		return mainFrame;
	}

	private void setMainFrame(JFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

}
