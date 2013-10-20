package com.teaminabox.eclipse.sortit;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

public final class SortItPlugin extends AbstractUIPlugin {

  public static final String PLUGIN_ID = "com.teaminabox.eclipse.sortit";
  private static final String RESOURCE_BUNDLE = "com.teaminabox.eclipse.sortit.SortItPluginResources";

  private static SortItPlugin plugin;

  private ResourceBundle resourceBundle;

  public SortItPlugin() {
    plugin = this;
  }

  /**
   * This method is called upon plug-in activation
   */
  public void start(BundleContext context) throws Exception {
    super.start(context);
    try {
      resourceBundle = ResourceBundle.getBundle(SortItPlugin.RESOURCE_BUNDLE);
    } catch (MissingResourceException x) {
      resourceBundle = null;
    }
  }

  /**
   * This method is called when the plug-in is stopped
   */
  public void stop(BundleContext context) throws Exception {
    super.stop(context);
    plugin = null;
    resourceBundle = null;
  }

  public static SortItPlugin getDefault() {
    return plugin;
  }

  public static IWorkspace getWorkspace() {
    return ResourcesPlugin.getWorkspace();
  }

  public static String getResourceString(String key) {
    ResourceBundle bundle = SortItPlugin.getDefault().getResourceBundle();
    try {
      return bundle.getString(key);
    } catch (MissingResourceException e) {
      return key;
    }
  }

  public ResourceBundle getResourceBundle() {
    return resourceBundle;
  }
}
