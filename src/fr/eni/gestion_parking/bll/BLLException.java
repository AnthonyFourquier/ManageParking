package fr.eni.gestion_parking.bll;



public class BLLException extends Exception {



public BLLException() {
// TODO Auto-generated constructor stub
}



public BLLException(String message) {
super(message);
// TODO Auto-generated constructor stub
}



public BLLException(String message, Throwable exc) {
super(message, exc);
// TODO Auto-generated constructor stub
}

@Override
public String getMessage() {
	return "BLLException " + super.getMessage();
}



}