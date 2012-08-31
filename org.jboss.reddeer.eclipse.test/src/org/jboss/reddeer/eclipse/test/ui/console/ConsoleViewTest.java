package org.jboss.reddeer.eclipse.test.ui.console;

import static org.junit.Assert.assertThat;

import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNull;
import org.jboss.reddeer.eclipse.jdt.ui.ide.NewJavaProjectWizardDialog;
import org.jboss.reddeer.eclipse.jdt.ui.ide.NewJavaProjectWizardPage;
import org.jboss.reddeer.eclipse.jdt.ui.packageexplorer.PackageExplorer;
import org.jboss.reddeer.eclipse.ui.console.ConsoleView;
import org.jboss.reddeer.eclipse.ui.ide.NewJavaClassDialog;
import org.jboss.reddeer.swt.impl.menu.ShellMenu;
import org.jboss.reddeer.swt.matcher.RegexMatchers;
import org.jboss.reddeer.swt.util.Bot;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

public class ConsoleViewTest {

	private ConsoleView consoleView;
	
	private static final String TEST_PROJECT_NAME = "Project";
	
	@Before
	public void setup() {
		consoleView = new ConsoleView();
		consoleView.open();
		runTestProject();
	}
	
	@AfterClass
	public static void tearDown() {
		new PackageExplorer().getProject(TEST_PROJECT_NAME).delete(true);
	}
	
	@Test
	public void getConsoleText() {
		consoleView = new ConsoleView();
		String text = consoleView.getConsoleText();
		assertThat(text, IsNull.notNullValue());
		assertThat(text, IsEqual.equalTo("Hello World"));
	}
	
	@Test
	public void clearConsole() {
		consoleView = new ConsoleView();
		consoleView.clearConsole();
		String text = consoleView.getConsoleText();
		assertThat(text, IsEqual.equalTo(""));
	}
	
	private void runTestProject() {
		createTestProject();
		RegexMatchers m = new RegexMatchers("Run.*", "Run As.*", ".*Java Application.*");
		new ShellMenu(m.getMatchers()).select();
	}
	
	private void createTestProject() {
		PackageExplorer packageExplorer = new PackageExplorer();
		if (!packageExplorer.containsProject(TEST_PROJECT_NAME)) {
			createJavaProject();
			createJavaClass();
		}
		packageExplorer.selectProject(TEST_PROJECT_NAME);
	}
	
	private void createJavaProject() {
		NewJavaProjectWizardDialog javaProject = new NewJavaProjectWizardDialog();
		javaProject.open();
		
		NewJavaProjectWizardPage javaWizardPage = new NewJavaProjectWizardPage(javaProject);
		javaWizardPage.setProjectName(TEST_PROJECT_NAME);
		
		javaProject.finish(false);
	}
	
	private void createJavaClass() {
		NewJavaClassDialog javaClassDialog = new NewJavaClassDialog();
		javaClassDialog.open();
		javaClassDialog.setName("TestClass");
		javaClassDialog.setPackage("test");
		javaClassDialog.setStaticMainMethod(true);
		javaClassDialog.finish();
		
		Bot.get().activeEditor().toTextEditor().
			insertText(9, 0, "System.out.print(\"Hello World\");");
		Bot.get().activeEditor().toTextEditor().save();
	}
}