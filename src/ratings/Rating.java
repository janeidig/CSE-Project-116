package ratings;

public class Rating {
    private String reviewerID;
    private int rating;

    public Rating(String reviewerID, int rating){
        this.reviewerID=reviewerID;
        this.rating=rating;
        setRating(rating);
        setReviewerID(reviewerID);
    }

    public String getReviewerID(){
        return this.reviewerID;}
    public void setReviewerID(String id){
        this.reviewerID=id;}
    public int getRating(){
        return this.rating;}
    public void setRating(int rate){
        if (rate >=1 && rate<=5){
            this.rating=rate;}
        else{
            this.rating=-1;
        }}
}
