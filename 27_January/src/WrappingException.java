//Wrapping a low level exception in a bigger business exception

class DatabaseException extends Exception{
    public DatabaseException(String msg, Throwable cause){
        super(msg, cause);
    }

    public class WrappingException{
        public static void main(String[] args){
            try{
                int x = 10/0;
            }catch (ArithmeticException e){
                throw new RuntimeException(
                        new DatabaseException("DB failure", e)
                );
            }
        }
    }
}