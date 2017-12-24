package br.com.julianograciano.testng;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

import org.testng.IAlterSuiteListener;
import org.testng.IAnnotationTransformer2;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener2;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.IConfigurationAnnotation;
import org.testng.annotations.IDataProviderAnnotation;
import org.testng.annotations.IFactoryAnnotation;
import org.testng.annotations.ITestAnnotation;
import org.testng.xml.XmlSuite;

/**
 * 
 * Interfaces de listeners do TestNG.
 * 
 * @author Juliano Graciano Pereira Costa
 *
 */
abstract class AbstractTestNGListener implements IAnnotationTransformer2, IInvokedMethodListener2, IAlterSuiteListener  {

	@Override
	public void transform(ITestAnnotation annotation, @SuppressWarnings("rawtypes") Class testClass,
			@SuppressWarnings("rawtypes") Constructor testConstructor, Method testMethod) {

	}

	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {

	}

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {

	}

	@Override
	public void alter(List<XmlSuite> suites) {

	}

	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context) {

	}

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context) {

	}

	@Override
	public void transform(IConfigurationAnnotation annotation, @SuppressWarnings("rawtypes") Class testClass,
			@SuppressWarnings("rawtypes") Constructor testConstructor, Method testMethod) {

	}

	@Override
	public void transform(IDataProviderAnnotation annotation, Method method) {

	}

	@Override
	public void transform(IFactoryAnnotation annotation, Method method) {

	}

}
