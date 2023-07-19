package ratings;

public class Reviewer {
    private String reviewerID;

    public Reviewer(String id){
        this.reviewerID=id;
    }
    public String getReviewerID(){
        return this.reviewerID;
    }
    public void setReviewerID(String id){
        this.reviewerID=id;
    }
    public Rating rateSong(int rating){
        return new Rating(this.reviewerID,rating);
    }
}
