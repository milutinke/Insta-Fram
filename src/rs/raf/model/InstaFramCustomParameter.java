package rs.raf.model;

public class InstaFramCustomParameter extends InstaFramParameter {
	private static final long serialVersionUID = -8749732664759964981L;
	private String loc;
	private String comboBoxOptions;
	private boolean userInput;
	private boolean hasFile;
	private boolean comboBox;

	public InstaFramCustomParameter(String name, String description, String type, String value, String loc,
			String comboBoxOptions, boolean userInput, boolean hasFile, boolean comboBox) {
		super(name, description, type, value);

		this.loc = loc;
		this.comboBoxOptions = comboBoxOptions;
		this.userInput = userInput;
		this.hasFile = hasFile;
		this.comboBox = comboBox;
	}

	public String getLoc() {
		return loc;
	}

	public String getComboBoxOptions() {
		return comboBoxOptions;
	}

	public boolean isUserInput() {
		return userInput;
	}

	public boolean isHasFile() {
		return hasFile;
	}

	public boolean isComboBox() {
		return comboBox;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
