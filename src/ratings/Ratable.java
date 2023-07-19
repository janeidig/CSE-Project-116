package ratings;

import ratings.datastructures.LinkedListNode;

public class Ratable {
    private String title;
    private LinkedListNode<Rating> ratings;

    public void Ratable(){
    }

    //methods from songs for part 5
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void addRating(Rating r) {
        if (this.ratings == null) {
            this.ratings = new LinkedListNode<Rating>(r, null);
        } else {
            if (didReviewerRateSong(r.getReviewerID())) {
                //add nothing
            } else {
                this.ratings.append(r);
            }
        }
    }

    public LinkedListNode<Rating> getRatings() {
        if (this.ratings == null) {
            return null;
        } else {
            return this.ratings;
        }
    }

    public double averageRating() {
        if (this.ratings == null) {
            return 0.0;
        } else {
            int sum = 0;
            int counter = 0;
            LinkedListNode<Rating> LLN = this.ratings;
            while (LLN != null) {
                sum += LLN.getValue().getRating();
                counter++;
                LLN = LLN.getNext();
            }
            return (double)sum / counter;
        }
    }

    public boolean didReviewerRateSong(String id) {
        LinkedListNode<Rating> head = this.ratings;
        while (head != null) {
            if (id.equals(head.getValue().getReviewerID())) {
                return true;
            }
            head = head.getNext();
        }
        return false;
    }

    public void removeRatingByReviewer(Reviewer reviewer) {
        //setting up two variables for previous and current node
        LinkedListNode<Rating> currentNode = this.ratings;
        LinkedListNode<Rating> previousNode = currentNode;
        while (currentNode != null) {
            if (currentNode.getValue().getReviewerID().equals(reviewer.getReviewerID())) {
                if (currentNode == this.ratings) {//if reviewer is in head node
                    this.ratings=this.ratings.getNext();
                } else {//if reviewer is not the head node
                    previousNode.setNext(currentNode.getNext());
                    currentNode = currentNode.getNext();
                }
            } else {//update prev and curr nodes to go through linked list
                previousNode = currentNode;
                currentNode = currentNode.getNext();
            }
        }
    }
        public int numberOfRatings(){
        if (this.ratings==null){
            return 0;
        }else{
            LinkedListNode<Rating> temp = this.ratings.getNext();
            int count=1;
            while (temp!=null){
                count++;
                temp=temp.getNext();
            }
            return count;
        }
    }

    public double bayesianAverageRating(int extraRatings, int ratingValue){
        if(extraRatings!=0){
            double numerator = (this.averageRating()*this.numberOfRatings())+(ratingValue*extraRatings);
            double bayAvg = numerator/(this.numberOfRatings()+extraRatings);
            return bayAvg;}
        else{
            return this.averageRating();
        }
    }
}
