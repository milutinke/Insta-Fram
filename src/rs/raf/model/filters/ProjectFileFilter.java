package rs.raf.model.filters;

import java.io.File;

import javax.swing.filechooser.FileFilter;

import rs.raf.settings.Settings;

public class ProjectFileFilter extends FileFilter {
	@Override
	public boolean accept(File file) {
		return file.isDirectory() || file.getName().toLowerCase().endsWith(Settings.APP_PROJECT_EXTENSION);
	}

	@Override
	public String getDescription() {
		return "Directories and InstaFram Project Files (*" + Settings.APP_PROJECT_EXTENSION + ")";
	}

}
