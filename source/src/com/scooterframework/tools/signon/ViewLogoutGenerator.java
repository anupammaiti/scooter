/*
 *   This software is distributed under the terms of the FSF
 *   Gnu Lesser General Public License (see lgpl.txt).
 *
 *   This program is distributed WITHOUT ANY WARRANTY. See the
 *   GNU General Public License for more details.
 */
package com.scooterframework.tools.signon;

import java.util.Properties;

import com.scooterframework.tools.common.AbstractGenerator;

/**
 * This class generates logout view code.
 *
 * @author (Fei) John Chen
 */
public class ViewLogoutGenerator extends AbstractGenerator {
	private String controller;
	private String action;
	private String relativePathToView;
	private String viewFileName;

	public ViewLogoutGenerator(String templateFilePath, Properties props, String controller, String action) {
		super(templateFilePath, props);
		
		this.controller = controller.toLowerCase();
		this.action = action.toLowerCase();

		String viewExtension = wc.getViewExtension();
		if (viewExtension != null && !viewExtension.startsWith(".")) viewExtension = "." + viewExtension;

		String webpageDirectoryName = wc.getWebPageDirectoryName();
		if (webpageDirectoryName.startsWith("/") || webpageDirectoryName.startsWith("\\"))
			webpageDirectoryName = webpageDirectoryName.substring(1);

		relativePathToView = webpageDirectoryName + "/" + controller.toLowerCase();
		
		viewFileName = isEmpty(viewExtension)?action:(action + viewExtension);
	}

	protected Properties getTemplateProperties() {
		Properties templateProps = new Properties();
		templateProps.setProperty("controller", controller);
		templateProps.setProperty("action", action);
		templateProps.setProperty("app_name", getProperty("app.name"));
		return templateProps;
	}
	
	protected String getRootPath() {
		return getProperty("app.path");
	}

	protected String getRelativePathToOutputFile() {
		return relativePathToView;
	}

	protected String getOutputFileName() {
		return viewFileName;
	}
}