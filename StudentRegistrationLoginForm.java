package com.students_registration_login_form;


import java.sql.*;
import java.util.*;

public class StudentRegistrationLoginForm {

	public static void main(String[] args) 
	{ 
		Scanner sc =new Scanner(System.in);
		try 
		{
			System.out.println("Enter 1) for Register :");
			System.out.println(" Enter 2) for Loging :");
			System.out.print("Enter your Option :");
			int option =Integer.parseInt(sc.nextLine());
			
	     Connection con =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system","1234");
	     PreparedStatement stm=null;
	     
	     switch(option) 
	     { 
	     
	     //student registration
	     case 1:
	       System.out.print("How many row you want :");
	       int row=Integer.parseInt(sc.nextLine());
	       for(int i=1; i<=row; i++) 
	       {
	    	   System.out.print("Enter the RollNo :");
			     int RollNo=Integer.parseInt(sc.nextLine());
			     
			     System.out.print("Enter the Name :");
			     String Name=sc.nextLine();
			     
			     System.out.print("Enter the Percentage :");
			     int Percentage =Integer.parseInt(sc.nextLine());
			     
			     System.out.print("Enter the FName :");
			     String FName =sc.nextLine();
			     
			     System.out.print("Enter the LName :");
			     String LName =sc.nextLine();
				  
			     System.out.print("Enter the MailId :");
			     String MailId =sc.nextLine();
			     
			     System.out.print("Enter the PhoNo :");
			     
			     long PhoNo =Long.parseLong(sc.nextLine());
			      stm=con.prepareStatement("insert into STUDENTLOGIN values(?,?,?,?,?,?,?)");
			     stm.setInt(1,RollNo);
			     stm.setString(2, Name);
			     stm.setInt(3,Percentage);
			     stm.setString(4, FName);
			     stm.setString(5,LName);
			     stm.setString(6, MailId);
			     stm.setLong(7,PhoNo);
			     int records =stm.executeUpdate();
			     if(records>0)
			     {
			    	System.out.println("Register Successfully :"); 
			     }
			     else {
			    	 System.out.println("Register  Not Successfully :");
			     }
	       }
	       break;

              // Show all Student details
//	     case 2:
//	    	 ResultSet rSet = stm.executeQuery("select * from STUDENTLOGIN ");
//		     while(rSet.next()) {
//		    	 System.out.println("RollNo: "+rSet.getString(1)+"\tName :"+rSet.getString(2)+"\tPercentage :"+rSet.getString(3)+"\tFName :"+rSet.getString(4)+"\tLName :"+rSet.getString(5)+"\tMailId :"+rSet.getString(6)+"\tPhoNo :"+rSet.getString(7)+": ");
//		     }
//		     con.close();
//				sc.close();
//				rSet.close();
//				break;
				
	       //show student deails with name and roll number 
	     case 2:
	    	 System.out.print("Enter the RollNo :");
		     int RollNo=Integer.parseInt(sc.nextLine());
		     
		     System.out.print("Enter the Name :");
		     String Name=sc.nextLine();
		     
		     
	    	  
	    	 stm=con.prepareStatement("select * from  STUDENTLOGIN where rollno=? and name=? ");
	    	
	    	 stm.setInt(1,RollNo);
		     stm.setString(2, Name);
	    	 ResultSet r = stm.executeQuery();
		     if(r.next()) 
		     {
		    	 
		    	 System.out.println("login successfully :");
		    	 System.out.println("If Login and Register successfully :");
		    	 System.out.println(" 1)Show Students whose percentage>60% :");
		    	 System.out.println(" 2)Update MailID & PhoneNo based one RollNo :");
		    	 System.out.println(" 3)Delete Student whose Percentage between 30% to 60% :");
		    	 System.out.println(" 4)Find how many student got more than 80% :");
		    	 
		    	 
		    	 // show the result by select student name....
		    	 System.out.print("Enter your option :");
		    	  option=Integer.parseInt(sc.nextLine());
		    	 switch(option) 
		    	 {
		    	 
		    	 //show students which have scored 60%
		    	 case 1:
		    		 stm=con.prepareStatement("select * from STUDENTLOGIN where percentage>60");
		    		 r = stm.executeQuery();
		    		 while(r.next()) {
		    			 System.out.println("RollNo: "+r.getString(1)+"\tName :"+r.getString(2)+"\tPercentage :"+r.getString(3)+"\tFName :"+r.getString(4)+"\tLName :"+r.getString(5)+"\tMailId :"+r.getString(6)+"\tPhoNo :"+r.getString(7)+": ");
		    		 }
		    		break;
		 	    	
		    		// update student details 
		    	 case 2:
		    		 
		    		 System.out.print("Enter the MailId :");
				     String MailId =sc.nextLine();
		    		 
				     System.out.print("Enter the PhoNo :");
				     int PhoNo =Integer.parseInt(sc.nextLine());
				     
		    		 System.out.print("Enter the RollNo :");
				     RollNo=Integer.parseInt(sc.nextLine());
		    		 stm=con.prepareStatement("update  STUDENTLOGIN set mailid=?, phno=? where rollno=?");
		    		 
		    		 stm.setString(1, MailId);
				     stm.setInt(2,PhoNo);
				     stm.setInt(3,RollNo);
				     stm.executeUpdate();
				     
				     break;
				     
				     // delete student data
		    	 case 3:
		    		 stm=con.prepareStatement("delete from STUDENTLOGIN where percentage between 30 and 60 ");
		    		  stm.executeUpdate();
		    		 while(r.next()) {
		    			 System.out.println("RollNo: "+r.getString(1)+"\tName :"+r.getString(2)+"\tPercentage :"+r.getString(3)+"\tFName :"+r.getString(4)+"\tLName :"+r.getString(5)+"\tMailId :"+r.getString(6)+"\tPhoNo :"+r.getString(7)+": ");
		    		 }
		    		break;
		    	 
		    		
		    		// show student data above 80% on display.
		    	 case 4:
		    		 stm =con.prepareStatement("select * from STUDENTLOGIN where percentage>80");
		    		 r=stm.executeQuery();
		    		 while(r.next()) {
		    			 System.out.println("RollNo: "+r.getString(1)+"\tName :"+r.getString(2)+"\tPercentage :"+r.getString(3)+"\tFName :"+r.getString(4)+"\tLName :"+r.getString(5)+"\tMailId :"+r.getString(6)+"\tPhoNo :"+r.getString(7)+": ");
		    		 }
		    		break;
		    	 } 
		     }
		     else {
		    	 System.out.println("login un succefully");
		     }
	    	
		     con.close();
			sc.close();
				break;
	    	 
		 }
		}
		catch(Exception e) 
		{
			System.out.println(e);
		}
		finally {
			System.out.println("Operation ended successfully");
		}
	}
}
