package io.snyk.eclipse.plugin.views.snyktoolview.handlers;

import org.eclipse.ui.commands.IElementUpdater;

import io.snyk.eclipse.plugin.properties.preferences.Preferences;
import io.snyk.eclipse.plugin.utils.SnykIcons;

public class EnableOssProductHandler extends BaseHandler implements IElementUpdater {

	public EnableOssProductHandler() {
		super();

		iconEnabled = SnykIcons.OSS;
		iconDisabled = SnykIcons.OSS_DISABLED;
		preferenceKey = Preferences.ACTIVATE_SNYK_OPEN_SOURCE;
	}

}