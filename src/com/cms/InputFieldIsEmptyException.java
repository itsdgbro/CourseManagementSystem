package com.cms;
@SuppressWarnings("serial")
class CustomException extends Exception{
	public CustomException(String str) {
		super(str);
	}
}

public class InputFieldIsEmptyException{
	   // method to check the age  
    static void checkInput (String str) throws CustomException{    
       if(str == null || str.isEmpty() || str.isBlank()){  
  
        // throw an object of user defined exception  
        throw new CustomException("Text field is empty, can not input null value.");    
       }   
     } 
}  


