package io.snyk.eclipse.plugin.wizards;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

import io.snyk.languageserver.LsConfigurationUpdater;
import io.snyk.languageserver.protocolextension.SnykExtendedLanguageClient;

public class SnykWizard extends Wizard implements INewWizard {
  protected SnykWizardConfigureAPIPage configureAPIPage;
  protected SnykWizardAuthenticatePage authenticatePage;

  protected SnykWizardModel model;

  protected IWorkbench workbench;
  protected IStructuredSelection selection;

  public SnykWizard() {
    super();
    model = new SnykWizardModel();
    setNeedsProgressMonitor(true);
  }

  @Override
  public String getWindowTitle() {
    return "Snyk Wizard";
  }

  @Override
  public void addPages() {
    configureAPIPage = new SnykWizardConfigureAPIPage();
    addPage(configureAPIPage);

    authenticatePage = new SnykWizardAuthenticatePage();
    addPage(authenticatePage);
  }

  public void init(IWorkbench workbench, IStructuredSelection selection) {
    this.workbench = workbench;
    this.selection = selection;
  }

  public boolean canFinish() {
    if (this.getContainer().getCurrentPage() == authenticatePage) {
      return true;
    }
    return false;
  }

  public boolean performCancel() {
    model.resetPreferences();
    return true;
  }

  public boolean performFinish() {
    new LsConfigurationUpdater().configurationChanged();
    SnykExtendedLanguageClient.getInstance().triggerAuthentication();
    SnykExtendedLanguageClient.getInstance().trustWorkspaceFolders();
    return true;
  }
}
