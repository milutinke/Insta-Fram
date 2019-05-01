package rs.raf.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Enumeration;

import rs.raf.gui.MainFrameInstaFram;
import rs.raf.model.InstaFramCustomParameter;
import rs.raf.model.InstaFramNode;
import rs.raf.model.InstaFramParameter;
import rs.raf.model.InstaFramPredefinedParameter;
import rs.raf.model.InstaFramProduct;
import rs.raf.model.InstaFramSession;
import rs.raf.settings.Settings;

public class SaveLoadHelper {
	public static void serializeTree(String fileName, InstaFramProduct product) throws IOException {
		FileOutputStream fileOut = new FileOutputStream(fileName);
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(product);
		out.close();
		fileOut.close();
	}

	public static final InstaFramProduct deserializeTree(String fileName) throws IOException, ClassNotFoundException {
		FileInputStream fileIn = new FileInputStream(fileName);
		ObjectInputStream in = new ObjectInputStream(fileIn);

		InstaFramProduct product = (InstaFramProduct) in.readObject();
		in.close();
		fileIn.close();

		return product;
	}

	public static void serialiseSession(InstaFramSession session) throws IOException {
		FileOutputStream fileOut = new FileOutputStream(System.getenv("APPDATA") + "/" + Settings.APP_SESSION_LOCATION);
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(session);
		out.close();
		fileOut.close();
	}

	public static InstaFramSession deserialiseSession() throws IOException, ClassNotFoundException {
		FileInputStream fileIn = new FileInputStream(System.getenv("APPDATA") + "/" + Settings.APP_SESSION_LOCATION);
		ObjectInputStream in = new ObjectInputStream(fileIn);
		InstaFramSession session = (InstaFramSession) in.readObject();
		in.close();
		fileIn.close();

		return session;
	}

	public static void saveProject(File file, InstaFramProduct product) {
		if (file != null) {
			try {
				String path = file.getAbsolutePath();
				String fileName = product.getName();

				if (!fileName.toLowerCase().endsWith(Settings.APP_PROJECT_EXTENSION))
					fileName += Settings.APP_PROJECT_EXTENSION;

				String fullFilePath = path + "\\" + fileName;

				SaveLoadHelper.serializeResources((InstaFramProduct) product, file, true);
				SaveLoadHelper.serializeTree(fullFilePath, (InstaFramProduct) product);

				MainFrameInstaFram.getInstance().setSaved(true);
				MainFrameInstaFram.getInstance().setCurrentProduct(product);
				MainFrameInstaFram.getInstance().setCurrentProductPath(path);
			} catch (IOException e1) {
				MainFrameInstaFram.getInstance().getExceptionsManager().fatalError();
			}
		}
	}

	public static void serializeResources(InstaFramNode root, File destionation, boolean overrideParameters) {
		@SuppressWarnings("unchecked")
		Enumeration<InstaFramNode> children = root.children();

		if (children != null) {
			while (children.hasMoreElements()) {
				InstaFramNode node = (InstaFramNode) children.nextElement();

				if (node instanceof InstaFramCustomParameter) {
					InstaFramCustomParameter parameter = (InstaFramCustomParameter) node;

					if (parameter.isHasFile()) {
						File fileToBeCopied = new File(parameter.getValue());

						if (fileToBeCopied.exists())
							copyFiles(fileToBeCopied, destionation, parameter, overrideParameters);
					}
				} else if (node instanceof InstaFramPredefinedParameter) {
					InstaFramPredefinedParameter parameter = (InstaFramPredefinedParameter) node;

					if (parameter.isLogo() || parameter.isEntryPoint()) {
						File fileToBeCopied = new File(parameter.getValue());

						if (fileToBeCopied.exists())
							copyFiles(fileToBeCopied, destionation, parameter, overrideParameters);
					}
				}

				serializeResources(node, destionation, overrideParameters);
			}
		}
	}

	private static void copyFiles(File fileToBeCopied, File destionation, InstaFramParameter parameter,
			boolean overrideParameters) {
		String finalPath = resolvePath(destionation, parameter);

		try {
			Files.copy(fileToBeCopied.toPath(), new File(finalPath).toPath());

			if (overrideParameters)
				parameter.setValue(finalPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String resolvePath(File destination, InstaFramParameter parameter) {
		String file = parameter.getValue();
		String fullDestination = "";

		if (isImage(getFileExtension(file))) {
			fullDestination = new StringBuilder().append(destination.getAbsolutePath()).append("\\resources\\images\\")
					.toString();

			if (!(new File(fullDestination).exists()))
				new File(fullDestination).mkdirs();
		} else {
			if (parameter instanceof InstaFramPredefinedParameter) {
				if (((InstaFramPredefinedParameter) parameter).isEntryPoint())
					fullDestination = new StringBuilder().append(destination.getAbsolutePath()).append("\\").toString();
			} else {
				fullDestination = new StringBuilder().append(destination.getAbsolutePath())
						.append("\\resources\\other\\").toString();
			}

			if (!(new File(fullDestination).exists()))
				new File(fullDestination).mkdirs();
		}

		return new StringBuilder(fullDestination).append(Paths.get(file).getFileName().toString()).toString();
	}

	private static String getFileExtension(String name) {
		int lastIndexOf = name.lastIndexOf(".");

		if (lastIndexOf == -1)
			return "";

		return name.substring(lastIndexOf);
	}

	private static boolean isImage(String extension) {
		return extension.contains(".jpg") || extension.contains(".jpeg") || extension.contains(".png")
				|| extension.contains(".webp") || extension.contains(".bpm") || extension.contains(".gif")
				|| extension.contains(".ico");
	}
	
	public static String getProjectInstallationPath(InstaFramNode root) {
		@SuppressWarnings("unchecked")
		Enumeration<InstaFramNode> children = root.children();

		String found = "";
		
		if (children != null) {
			while (children.hasMoreElements()) {
				InstaFramNode node = (InstaFramNode) children.nextElement();

				if (node instanceof InstaFramPredefinedParameter) {
					InstaFramPredefinedParameter parameter = (InstaFramPredefinedParameter) node;

					if (parameter.isSoftware())
						found = parameter.getValue();
				}
				
				if(!found.isEmpty())
					return found;
				
				found = getProjectInstallationPath(node);
			}
		}
		
		return found;
	}
	
	public static String getProjectEntryPoint(InstaFramNode root) {
		@SuppressWarnings("unchecked")
		Enumeration<InstaFramNode> children = root.children();

		String found = "";
		
		if (children != null) {
			while (children.hasMoreElements()) {
				InstaFramNode node = (InstaFramNode) children.nextElement();

				if (node instanceof InstaFramPredefinedParameter) {
					InstaFramPredefinedParameter parameter = (InstaFramPredefinedParameter) node;

					if (parameter.isEntryPoint())
						found = parameter.getValue();
				}
				
				if(!found.isEmpty())
					return found;
				
				found = getProjectEntryPoint(node);
			}
		}
		
		return found;
	}
}
