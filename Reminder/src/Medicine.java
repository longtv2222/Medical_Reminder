
//Need to use sqlite to store the database.
public class Medicine {
	private String medName;

	public Medicine(String medName) {
		this.setMedName(medName);
	}

	public void setMedName(String medName) {
		this.medName = medName;
	}

	@Override
	public int hashCode() {
		return medName.hashCode();
	}

}
