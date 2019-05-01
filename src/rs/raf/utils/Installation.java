package rs.raf.utils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JProgressBar;
import javax.swing.Timer;

import rs.raf.model.InstaFramProduct;

public class Installation {
	private InstaFramProduct product = null;
	private String path = "";
	private String entryPoint = "";
	private boolean createShortcut;
	private boolean runAfterInstallation;
	private int progress = 0;
	private Timer timer;

	public Installation(InstaFramProduct product) {
		this.product = product;
		this.path = SaveLoadHelper.getProjectInstallationPath(product);
		this.entryPoint = SaveLoadHelper.getProjectEntryPoint(product);
		this.createShortcut = true;
		this.runAfterInstallation = true;
		this.progress = 0;
		this.timer = null;
	}

	public void install(JProgressBar progressBar, Runnable callback) throws InterruptedException {
		timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (progress >= 100)
					callback.run();

				progress += 10;
				progressBar.setValue(progress);

				if (progress == 50)
					SaveLoadHelper.serializeResources(product, new File(path), false);
			}
		});

		timer.setRepeats(true);
		timer.start();
	}

	public InstaFramProduct getProduct() {
		return product;
	}

	public String getPath() {
		return path;
	}

	public String getEntryPoint() {
		return entryPoint;
	}

	public void setProduct(InstaFramProduct product) {
		this.product = product;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setEntryPoint(String entryPoint) {
		this.entryPoint = entryPoint;
	}

	public boolean isCreateShortcut() {
		return createShortcut;
	}

	public boolean isRunAfterInstallation() {
		return runAfterInstallation;
	}

	public void setCreateShortcut(boolean createShortcut) {
		this.createShortcut = createShortcut;
	}

	public void setRunAfterInstallation(boolean runAfterInstallation) {
		this.runAfterInstallation = runAfterInstallation;
	}

	public Timer getTimer() {
		return timer;
	}
}
