package speedhome.interview.boot.Main.Entity;

public class CreateMemberResponse {
    private String message;
    private Member member;

    public CreateMemberResponse(String message) {
        this.message = message;
    }

    public CreateMemberResponse(String message, Member member) {
        this.message = message;
        this.member = member;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}