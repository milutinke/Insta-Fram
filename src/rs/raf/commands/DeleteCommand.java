package rs.raf.commands;

import javax.swing.SwingUtilities;

import rs.raf.gui.MainFrameInstaFram;
import rs.raf.model.InstaFramModel;

public class DeleteCommand extends Command {
	private static final long serialVersionUID = -2930853224415933352L;
	private InstaFramModel model = null;

	public DeleteCommand(InstaFramModel instaFramModel) {
		try {
			this.model = (InstaFramModel) ((InstaFramModel) instaFramModel).clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("static-access")
	@Override
	public void doCommand() {
		try {
			this.model = (InstaFramModel) ((InstaFramModel) MainFrameInstaFram.getInstance().getInstance().getTree()
					.getModel()).clone();
			System.out
					.println(this.model.hashCode() == MainFrameInstaFram.getInstance().getTree().getModel().hashCode());
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

		SwingUtilities.updateComponentTreeUI(MainFrameInstaFram.getInstance().getTree());
		System.out.println("Model hash (Do command): " + model.hashCode());
	}

	@Override
	public void undoCommand() {
		MainFrameInstaFram.getInstance().getTree().setModel(this.model);
		SwingUtilities.updateComponentTreeUI(MainFrameInstaFram.getInstance().getTree());
		MainFrameInstaFram.getInstance().setSaved(false);

		System.out.println("Model hash (Undo command): "
				+ ((InstaFramModel) MainFrameInstaFram.getInstance().getTree().getModel()).hashCode());
	}
}
