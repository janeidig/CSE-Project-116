package ratings;

import ratings.datastructures.BinaryTreeNode;
import ratings.datastructures.Comparator;
import ratings.datastructures.LinkedListNode;

public class Playlist{
    private Comparator<Song> comparator;
    private BinaryTreeNode<Song> playlist;

    public Playlist(Comparator<Song> songs){
        this.comparator=songs;
    }

    public void addSong(Song song){
        if(this.playlist == null){
            this.playlist=new BinaryTreeNode<>(song,null,null);
        }else {
            this.addSongHelper(this.playlist,song);
        }
    }

    public void addSongHelper(BinaryTreeNode<Song> node, Song toAdd) {
        if (this.comparator.compare(toAdd, node.getValue())) {
            if (node.getLeft() == null) {
                node.setLeft(new BinaryTreeNode<>(toAdd, null, null));
            } else {
                addSongHelper(node.getLeft(), toAdd);
            }
        } else {
            if (node.getRight() == null) {
                node.setRight(new BinaryTreeNode<>(toAdd, null, null));
            } else {
                addSongHelper(node.getRight(), toAdd);
            }
        }
    }

    public BinaryTreeNode<Song> getSongTree(){
        return this.playlist;
    }

    public LinkedListNode<Song> getSongList(BinaryTreeNode<Song> playlist) {
        LinkedListNode<Song> head = null;
        LinkedListNode<Song> end = null;
        if (playlist != null){
            LinkedListNode<Song> leftSide = getSongList(playlist.getLeft()); //traversing left side recursively
            LinkedListNode<Song> thisNode = new LinkedListNode<>(playlist.getValue(),null);
            if (leftSide != null){
                end = leftSide;
                while(end.getNext() != null){
                    end = end.getNext();
                }
                end.setNext(thisNode);
                head = leftSide;
            }else{
                head = thisNode;//setting node as head of list
            }
            LinkedListNode<Song> rightSide = getSongList(playlist.getRight()); //traversing right side
            if (rightSide != null){
                thisNode.setNext(rightSide);//add right side to current node
                end = rightSide;
                while (end.getNext() != null){
                    end = end.getNext();
                }
            }else{
                end = thisNode;//making current node the end of list
            }
        }
        return head;
    }

    public LinkedListNode<Song> getSongList(){
        return getSongList(this.playlist);
    }

    public Comparator<Song> getComparator(){
        return this.comparator;
    }
}
