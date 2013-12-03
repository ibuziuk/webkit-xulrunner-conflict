package javafx.xulrunner.conflict;

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

public class JavaFxWebViewVsXulrunner {
	public static void main(String[] args) {
		Display display = new Display();
		final Shell shell = new Shell(display);
		final StackLayout stackLayout = new StackLayout();
		shell.setLayout(stackLayout);
		shell.setText("JavaFx WebView / XULRUNNER Conflict ");

		final JavaFxBrowser javaFxBrowser = new JavaFxBrowser(shell);

		final Browser xulrunnerBrowser;
		try {
			xulrunnerBrowser = new Browser(shell, SWT.MOZILLA);
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

		MenuItem xulrunnerItem = new MenuItem(submenu, SWT.RADIO);
		xulrunnerItem.setText("Use Xulrunner");

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

		xulrunnerItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				MenuItem item = (MenuItem) event.widget;
				if (item.getSelection()) {
					stackLayout.topControl = xulrunnerBrowser;
					shell.layout();
				}
			}
		});
		
		stackLayout.topControl = javaFxBrowser;
		shell.open();
		xulrunnerBrowser.setUrl("http://en.wikipedia.org/wiki/XULRunner");
		
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

}
