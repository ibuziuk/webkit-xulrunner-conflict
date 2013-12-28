package javafx.swt.webkit.conflict;

import javafx.JavaFxBrowser;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

public class JavaFxWebViewVsSwtWebKit {
	public static void main(String[] args) {
		Display display = new Display();
		final Shell shell = new Shell(display);
		final StackLayout stackLayout = new StackLayout();
		shell.setLayout(stackLayout);
		shell.setText("JavaFx WebView / SWT WebKit ");

		final JavaFxBrowser javaFxBrowser = new JavaFxBrowser(shell);

		final Browser swtWebKitBrowser;
		try {
			swtWebKitBrowser = new Browser(shell, SWT.WEBKIT);
		} catch (SWTError e) {
			System.out.println("Could not instantiate Browser: " + e.getMessage());
			display.dispose();
			return;
		}

		Menu menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);
		MenuItem ConflictItem = new MenuItem(menu, SWT.CASCADE);
		ConflictItem.setText("&TestConflict");
		Menu submenu = new Menu(shell, SWT.DROP_DOWN);
		ConflictItem.setMenu(submenu);

		MenuItem javaFxWebViewItem = new MenuItem(submenu, SWT.RADIO);
		javaFxWebViewItem.setText("Use javaFx WebView");
		javaFxWebViewItem.setSelection(true);

		MenuItem swtWebKitItem = new MenuItem(submenu, SWT.RADIO);
		swtWebKitItem.setText("Use swt WebKit");

		javaFxWebViewItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				MenuItem item = (MenuItem) event.widget;
				if (item.getSelection()) {
					stackLayout.topControl = javaFxBrowser;
					shell.layout();
				}
			}
		});

		swtWebKitItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				MenuItem item = (MenuItem) event.widget;
				if (item.getSelection()) {
					stackLayout.topControl = swtWebKitBrowser;
					shell.layout();
				}
			}
		});
		
		stackLayout.topControl = javaFxBrowser;
		shell.open();
		swtWebKitBrowser.setUrl("http://www.eclipse.org/swt/faq.php");
		
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
}
