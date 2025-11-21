public class User{
    private String UserName;
    private String Password;
    private String VerificationQuestion;
    private String VerificationAnswer;

    public User(String UserName, String Password, String VerificationQuestion, String VerificationAnswer){
        this.UserName = UserName;
        this.Password = Password;
        this.VerificationQuestion = VerificationQuestion;
        this.VerificationAnswer = VerificationAnswer;
    }
    public String getUserName(){
        return this.UserName;
    }
    public String getPassword(){
        return this.Password;
    }
    public String getVerificationQuestion(){
        return this.VerificationQuestion;
    }
    public String getVerificationAnswer(){
        return this.VerificationAnswer;
    }
    public void setUserName(String UserName){
        this.UserName = UserName;
    }
    public void setPassword(String Password){
        this.Password = Password;
    }
    public void setVerificationQuestion(String VerificationQuestion){
        this.VerificationQuestion = VerificationQuestion;
    }
    public void setVerificationAnswer(String VerificationAnswer){
        this.VerificationAnswer = VerificationAnswer;
    }
}