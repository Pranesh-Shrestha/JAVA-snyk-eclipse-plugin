package io.snyk.eclipse.plugin.views.snyktoolview.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import io.snyk.eclipse.plugin.SnykStartup;

public class CollapseTreeHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		String commandId = event.getCommand().getId();

		switch (commandId) {
		case "io.snyk.eclipse.plugin.commands.TreeCollapse":
			SnykStartup.getView().getTreeViewer().collapseAll();
			break;
		case "io.snyk.eclipse.plugin.commands.TreeExpand":
			SnykStartup.getView().getTreeViewer().expandAll();
			break;
		}

		return null;
	}
}