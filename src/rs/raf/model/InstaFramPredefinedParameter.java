package rs.raf.model;

public class InstaFramPredefinedParameter extends InstaFramParameter {
	private static final long serialVersionUID = -1090482681424154492L;
	private boolean companyName;
	private boolean system;
	private boolean logo;
	private boolean langauge;
	private boolean lookAndFeel;
	private boolean version;
	private boolean software;
	private boolean entryPoint;

	public InstaFramPredefinedParameter(String name, String description, String type, String value, boolean companyName,
			boolean system, boolean logo, boolean langauge, boolean lookAndFeel, boolean version, boolean software,
			boolean entryPoint) {
		super(name, description, type, value);

		this.companyName = companyName;
		this.system = system;
		this.logo = logo;
		this.langauge = langauge;
		this.lookAndFeel = lookAndFeel;
		this.version = version;
		this.software = software;
		this.entryPoint = entryPoint;
	}

	public boolean isCompanyName() {
		return companyName;
	}

	public boolean isSystem() {
		return system;
	}

	public boolean isLogo() {
		return logo;
	}

	public boolean isLangauge() {
		return langauge;
	}

	public boolean isLookAndFeel() {
		return lookAndFeel;
	}

	public boolean isVersion() {
		return version;
	}

	public boolean isSoftware() {
		return software;
	}

	public boolean isEntryPoint() {
		return entryPoint;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
