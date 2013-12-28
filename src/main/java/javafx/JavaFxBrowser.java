package javafx;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import javafx.embed.swt.FXCanvas;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class JavaFxBrowser extends FXCanvas{

	public JavaFxBrowser(Composite parent) {
		super(parent, SWT.NONE);
		WebView browser = new WebView();
		this.setScene(new Scene(browser));
		WebEngine webEngine = browser.getEngine();
		webEngine.load("http://www.oracle.com/products/index.html");	
	}
}
