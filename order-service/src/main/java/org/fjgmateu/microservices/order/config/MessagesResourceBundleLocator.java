package org.fjgmateu.microservices.order.config;

import org.hibernate.validator.spi.resourceloading.ResourceBundleLocator;

import java.util.Locale;
import java.util.ResourceBundle;
/**
 * Created by FJGMATEU
 */
public class MessagesResourceBundleLocator implements ResourceBundleLocator {

	@Override
	public ResourceBundle getResourceBundle(Locale locale) {
		ResourceBundle.clearCache(Thread.currentThread().getContextClassLoader());
		return ResourceBundle.getBundle("validation_message");
	}
}