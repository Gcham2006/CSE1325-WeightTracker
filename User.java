public class Profile{
    private String ProfileName;
    private String Password;
    private String VerificationQuestion;
    private String VerificationAnswer;

    public Profile(String ProfileName, String Password, String VerificationQuestion, String VerificationAnswer){
        this.ProfileName = ProfileName;
        this.Password = Password;
        this.VerificationQuestion = VerificationQuestion;
        this.VerificationAnswer = VerificationAnswer;
    }
    public String getProfileName(){
        return this.ProfileName;
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
    public void setProfileName(String ProfileName){
        this.ProfileName = ProfileName;
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