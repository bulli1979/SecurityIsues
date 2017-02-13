package tests;

import org.junit.Test;
import static org.junit.Assert.*;

import java.sql.SQLException;

import sql.SQLInjection;

public class SQLInject {
	
	@Test
	public void idmanipulate() throws ClassNotFoundException, SQLException{
		SQLInjection injector = new SQLInjection();
		String request = injector.doBadThings("2 or 1=1");
		System.out.println("test: " + request);
		
		assertTrue(request.length()>0);
	}
	
	
	//@Test
	public void idmanipulate2() throws ClassNotFoundException, SQLException{
		SQLInjection injector = new SQLInjection();
		int request = injector.doBadThings2("2; UPDATE test set salar = 7000 where id=3;","new description");
		System.out.println("test: " + request);
		
		assertTrue(request>0);
	}
	
}
