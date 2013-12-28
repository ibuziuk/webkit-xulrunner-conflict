webkit-xulrunner-conflict
=========================
*[WebKit from SWT may conflict with XULRunner on linux](https://issues.jboss.org/browse/JBIDE-9144?focusedCommentId=12609748&page=com.atlassian.jira.plugin.system.issuetabpanels:comment-tabpanel#comment-12609748).*

Where is XULRunner?
------------------------------------
*For this experiment xulrunner was taken from [jbosstools-xulrunner](https://github.com/jbosstools/jbosstools-xulrunner/tree/master/plugins).* 
*[How to install XULRunner for Eclipse](http://stackoverflow.com/questions/17994805/how-to-install-xulrunner-for-eclipse).*

How to test the conflict?
------------------------------------

1. Add jfxrt.jar to the project (\Java\jdk1.7.0_07\jre\lib\jfxrt.jar)
2. *Clone [jbosstools-xulrunner](https://github.com/jbosstools/jbosstools-xulrunner).* This repo contains xulrunner for all platforms
3. Edit your app's Run Configuration->Arguments->VM Arguments, add following argument:

   **-Dorg.eclipse.swt.browser.XULRunnerPath=path/to/xulrunner**
4. Run XulrunnerVsSwtWebKit.java - on Fedora 18 x64 / Ubuntu 12 x64 after several clicks the app will crash with native error. This is reproduction of SWT WebKit and Xulrunner conflict
5. Run JavaFxWebViewVsXulrunner.java - the app will not crash. Assuming that, there is no conflict between javaFx WebView and Xulrunner on Linux

--------------------------------------------------
**JavaFx Webkit vs. SWT.Webkit**
 - for testing potential conflict add jfxrt.jar to the project and run JavaFxWebViewVsSwtWebKit.java


