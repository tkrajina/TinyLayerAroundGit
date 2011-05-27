package tinylayeraroundgit.wizard;

import org.eclipse.jface.wizard.Wizard;


public class TestWizard extends Wizard {

	public TestWizard() {
		super();
	}
	
	public void addPages() {
//		personalInfoPage = new PersonalInformationPage( "Personal Information Page" );
//		addPage( personalInfoPage );
		AddressInformationPage addressInfoPage = new AddressInformationPage( "Address Information" );
		addPage( addressInfoPage );
	}

	@Override
	public boolean performFinish() {
		return false;
	}

}
