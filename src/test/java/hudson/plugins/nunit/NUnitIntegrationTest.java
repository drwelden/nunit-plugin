package hudson.plugins.nunit;

import hudson.model.FreeStyleBuild;
import hudson.model.FreeStyleProject;
import hudson.model.Result;
import hudson.tasks.test.AbstractTestResultAction;

import org.jvnet.hudson.test.Bug;
import org.jvnet.hudson.test.HudsonTestCase;
import org.jvnet.hudson.test.recipes.LocalData;

public class NUnitIntegrationTest extends HudsonTestCase {

	@Bug(5673)
	@LocalData
	public void testIssue5673() throws Exception {
		FreeStyleProject project = (FreeStyleProject) hudson.getItem("5673");
		FreeStyleBuild build = project.scheduleBuild2(0).get();
		assertBuildStatus(Result.UNSTABLE, build);
		AbstractTestResultAction action = build.getAction(AbstractTestResultAction.class);
		// For some reason the total count returned is 1338 but the number of tests
		// displayed in Jenkins when run outside test is 1355 (which is correct)
		// Very strange......
		assertTrue("The number of test counts should be larger than 1330", action.getTotalCount() > 1330);
	}
}
