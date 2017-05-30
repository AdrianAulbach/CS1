package ch.bfh.bti7081.s2017.red.mhc_pms.util;

import java.io.File;

import org.apache.log4j.PropertyConfigurator;
import org.junit.Before;
import org.junit.Test;

public class TestPathParams
{
	@Before
	public void setUp() throws Exception 
	{
		// Init log4j properties
		PropertyConfigurator.configure(FileTools.getApplicationPath()+File.separator+"log4j.properties");
	}

	@Test
	public void testCreate()
	{
		PathParams pp = new PathParams();
		pp.addParam("param1", "value1");
		pp.addParam("param2", "value2");
		assert(pp.getParamString().equals("?param1=value1&param2=value2"));
	}
	
	@Test
	public void testParseFullUrl()
	{
		PathParams pp = new PathParams("http://www.myurl.com/index.html?param1=value1&param2=value2");
		assert(pp.getParam("param1").equals("value1"));
		assert(pp.getParam("param2").equals("value2"));
	}
	
	@Test
	public void testParseReducedUrl()
	{
		PathParams pp = new PathParams("main/patients?param1=value1&param2=value2");
		assert(pp.getParam("param1").equals("value1"));
		assert(pp.getParam("param2").equals("value2"));
	}
	
	@Test
	public void testParamNotFound()
	{
		PathParams pp = new PathParams("main/patients?param1=value1&param2=value2");
		assert(pp.getParam("param3") == null);
	}
	
	@Test
	public void testUndefinedParam()
	{
		PathParams pp = new PathParams("http://www.myurl.com/index.html?param1&param2=value2");
		assert(pp.getParam("param1") == null);
		assert(pp.getParam("param2").equals("value2"));
	}
	


}
