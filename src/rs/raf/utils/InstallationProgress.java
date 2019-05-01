package rs.raf.utils;

public class InstallationProgress implements Runnable {
	public InstallationProgress() {
	}

	@Override
	public void run() {
		done();
	}
	
	public void done() {};
}
