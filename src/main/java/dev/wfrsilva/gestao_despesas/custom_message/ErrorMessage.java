package dev.wfrsilva.gestao_despesas.custom_message;

public class ErrorMessage {
    

    private String message;
    private String type;


    public ErrorMessage(String message, String type)
    {
        this.message = message;
        this.type = type;
    }//cronstructor ALLargs

    
    public String getMessage()
    {
        return message;
    }//getMessage

    public void setMessage(String message)
    {
        this.message = message;
    }//setMessage

    public String getType()
    {
        return type;
    }//getType

    public void setType(String type)
    {
        this.type = type;
    }//setType


}//ErrorMessage
