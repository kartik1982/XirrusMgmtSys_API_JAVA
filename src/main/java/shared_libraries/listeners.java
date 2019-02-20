package shared_libraries;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class listeners implements ITestListener, ISuiteListener{
	String testResult;

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		testResult=null;

		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		testResult="PASS";		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		testResult="FAIL";
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		testResult="SKIP";
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ITestContext context) {
		
	}

	@Override
	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ISuite suite) {
		// TODO Auto-generated method stub

		
	}

}
