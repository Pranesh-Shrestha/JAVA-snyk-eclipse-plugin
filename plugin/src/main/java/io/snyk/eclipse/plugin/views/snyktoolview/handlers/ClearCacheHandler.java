package io.snyk.eclipse.plugin.views.snyktoolview.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import io.snyk.eclipse.plugin.properties.preferences.Preferences;
import io.snyk.eclipse.plugin.utils.SnykLogger;
import io.snyk.eclipse.plugin.utils.SnykMessageDialog;
import io.snyk.eclipse.plugin.views.snyktoolview.ISnykToolView;
import io.snyk.eclipse.plugin.views.snyktoolview.SnykToolView;
import io.snyk.languageserver.ScanState;
import io.snyk.languageserver.SnykIssueCache;

public class ClearCacheHandler extends AbstractHandler {

	private ISnykToolView toolView;

	public ClearCacheHandler() {
		super();

	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		SnykIssueCache.getInstance().clearAll();
		ScanState.getInstance().clearAllScanStates();

		IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		try {
			toolView = (ISnykToolView) activePage.showView(SnykToolView.ID);
			toolView.clearTree();
			toolView.refreshTree();
		} catch (PartInitException e) {
			SnykLogger.logError(e);
			return null;
		}

		return null;
	}

}