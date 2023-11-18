
//NAME: MD RIDWAN UL KABIR
//ID: 501147339
import java.util.ArrayList;

/*
 * A Playlist contains an array list of AudioContent (i.e. Song, AudioBooks, Podcasts) from the library
 */
public class Playlist
{
	private String title;
	private ArrayList<AudioContent> contents; // songs, books, or podcasts or even mixture
	
	public Playlist(String title)
	{
		this.title = title;
		contents = new ArrayList<AudioContent>();
	}
	
	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public void addContent(AudioContent content)
	{
		contents.add(content);
	}
	
	public ArrayList<AudioContent> getContent()
	{
		return contents;
	}

	public void setContent(ArrayList<AudioContent> contents)
	{
		this.contents = contents;
	}
	
	/*
	 * Print the information of each audio content object (song, audiobook, podcast)
	 * in the contents array list. Print the index of the audio content object first
	 * followed by ". " then make use of the printInfo() method of each audio content object
	 * Make sure the index starts at 1
	 */
	public void printContents()
	{ int index=1; // first index will be 1
		for(int i=0;i<contents.size();i++){ // going through the contents arraylist
			System.out.print(index+". "); // printing the index
			contents.get(i).printInfo();  //printing info of each AudioContent object
			System.out.println(); // prints a new empty line
			index++;
		}
	}

	// Play all the AudioContent in the contents list
	public void playAll()
	{

		for(int i=0;i<contents.size();i++){
			contents.get(i).play(); //going through the contents arraylist and getting each AudioContent object and using the play method
			System.out.println(); //prints a new empty line
		}
	}
	
	// Play the specific AudioContent from the contents array list.
	// First make sure the index is in the correct range. 
	public void play(int index)
	{
		if(contains(index)){ //using contains method to check whether index is valid or not
			contents.get(index-1).play(); //if index is valid, use the index to get the AudioContent object from the contents arraylist then using play method
		}
	}
	
	public boolean contains(int index)
	{
		return index >= 1 && index <= contents.size();
	}
	
	// Two Playlists are equal if their titles are equal
	public boolean equals(Object other){
		Playlist otherObject=(Playlist)(other); // Creating a Playlist object called otherObject by casting Object other to Playlist
		if(otherObject==null){ // if otherObject is null return false
			return false;
		}
		else{
			if(this.title.equals(otherObject.title)){ // return true if the titles of two playlist objects are equal
				return true;
			}
			else{
				return false;
			}
		}
	}
	
	// Given an index of an audio content object in contents array list,
	// remove the audio content object from the array list
	// Hint: use the contains() method above to check if the index is valid
	// The given index is 1-indexed so convert to 0-indexing before removing
	public void deleteContent(int index)
	{
		
		if(contains(index)){ //using contains method to check whether index is valid or not
			contents.remove(index-1); // if index is valid, use that index to remove the object assoicated with that index
	}

	}
	
	
}
