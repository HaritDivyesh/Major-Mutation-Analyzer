package output;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import data.MutationAnalyserOutput;

public class MutationKillSummaryView implements OutputView {

	private JFrame mainFrame;

	public MutationKillSummaryView(JFrame mainFrame) {
		this.setMainFrame(mainFrame);
	}

	@Override
	public void displayComponent(MutationAnalyserOutput output) {
		// Summary on TimeOut Based Kill
		JPanel labels = new JPanel(new GridLayout(10, 10));
		JLabel count = new JLabel(
				"Total no. of Mutants killed due to TimeOut : " + output.getMutantsKilledTimeout() + "\n");
		labels.add(count);
		this.mainFrame.add(labels, BorderLayout.CENTER);
	}

	public JFrame getMainFrame() {
		return mainFrame;
	}

	private void setMainFrame(JFrame mainFrame) {
		this.mainFrame = mainFrame;
	}
}
