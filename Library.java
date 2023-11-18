//NAME: MD RIDWAN UL KABIR
//ID: 501147339

//if there's problems check playlists

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
 * This class manages, stores, and plays audio content such as songs, podcasts and audiobooks. 
 */
public class Library
{
	private ArrayList<Song> 			songs; 
	private ArrayList<AudioBook> 	audiobooks;
	private ArrayList<Playlist> 	playlists; 

	
  //private ArrayList<Podcast> 	podcasts;
	String errorMsg = "";
	
	public String getErrorMessage()
	{
		return errorMsg;
	}

	public Library()
	{
		songs 			= new ArrayList<Song>(); 
		audiobooks 	= new ArrayList<AudioBook>(); ;
		playlists   = new ArrayList<Playlist>();

	}
	/*
	 * Download audio content from the store. Since we have decided (design decision) to keep 3 separate lists in our library
	 * to store our songs, podcasts and audiobooks (we could have used one list) then we need to look at the type of
	 * audio content (hint: use the getType() method and compare to Song.TYPENAME or AudioBook.TYPENAME etc)
	 * to determine which list it belongs to above
	 * 
	 * Make sure you do not add song/podcast/audiobook to a list if it is already there. Hint: use the equals() method
	 * If it is already in a list, set the errorMsg string and return false. Otherwise add it to the list and return true
	 * See the video
	 */


	public void download(AudioContent content){


	
		String type=content.getType();
		if(type.equals("SONG")){
			Song songObject=(Song)content;

			try{
				if(!songs.contains(songObject)){
					songs.add(songObject);
					System.out.println(songObject.getType()+" "+songObject.getTitle()+" Added to library");


				}
				else {
					//if song is in the songs arraylist throw Exception
					throw new AudioContentNotFoundException(songObject.getType()+" "+songObject.getTitle()+" already downloaded");
					
				}

			}
			catch(AudioContentNotFoundException e){
				//we catch the exception and print the message
				System.out.println(e.getLocalizedMessage());
			}
		}
		else if(type.equals("AUDIOBOOK")){
			AudioBook book = (AudioBook)content;

			try{
				if(!audiobooks.contains(book)){
					audiobooks.add(book);
					System.out.println(book.getType()+ " "+book.getTitle()+" Added to library");
				}
				else{
					//if audiobook is in the audiooks arraylist throw Exception
					throw new AudioContentNotFoundException(book.getType()+" "+book.getTitle()+" already downloaded");
				}
			}
			catch(AudioContentNotFoundException e){
				//we catch the exception and print message
				System.out.println(e.getLocalizedMessage());
			}
		}
		
	}
	
	// Print Information (printInfo()) about all songs in the array list
	public void listAllSongs()
	{
		for (int i = 0; i < songs.size(); i++)
		{
			int index = i + 1;
			System.out.print("" + index + ". ");
			songs.get(i).printInfo();
			System.out.println();	
		}
	}
	
	// Print Information (printInfo()) about all audiobooks in the array list
	public void listAllAudioBooks()
	{
		for(int i=0;i<audiobooks.size();i++){ //going through audiobooks arraylist and printing each AudioBook object's index and using printInfo() on each AudioBook object 
			int audioBookIndex=i+1;
			System.out.print("" +audioBookIndex+". ");
			audiobooks.get(i).printInfo();
			System.out.println();

		}	
	}
	
  // Print Information (printInfo()) about all podcasts in the array list

	public void listAllPodcasts()
	{
	}
	
  	// Print the name of all playlists in the playlists array list
	// First print the index number as in listAllSongs() above
	
	public void listAllPlaylists()
	{    //going through playlists arraylist and printing the index and title of each playlist object
		for(int i=0;i<playlists.size();i++){
			int playlistIndex=i+1;
			System.out.print(""+ playlistIndex+". ");
			System.out.print(playlists.get(i).getTitle());
			System.out.println();
		}
	}
	
  // Print the name of all artists. 
	public void listAllArtists()
	{
		// First create a new (empty) array list of string 
		// Go through the songs array list and add the artist name to the new arraylist only if it is
		// not already there. Once the artist arraylist is complete, print the artists names
		ArrayList<String>artistArray=new ArrayList<>();
		for(int i=0;i<songs.size();i++){
			Song songObject=songs.get(i);
			String songArtist=songObject.getArtist(); 

			if(artistArray.contains(songArtist)==false){ //checking to see if songArtist is within the arrtistArray if it's not in then add it
				artistArray.add(songArtist);
			}
		}
		for(int i=0;i<artistArray.size();i++){ //going through artistArray and printing each artists index and their names
			System.out.println(i+1+". "+artistArray.get(i));
		}
		
	}

	// Delete a song from the library (i.e. the songs list) - 
	// also go through all playlists and remove it from any playlist as well if it is part of the playlist


	public void deleteSong(int index)
	{
		if(index<1||index>songs.size()){ //if index is less than 1 or greater than size of songs arraylist throw SongNotFoundException 
		throw new SongNotFoundException("Index is Invalid");

	}
	else if (playlists.size()==0){ //if there's no playlists or playlists arraylist size is 0 then remove the song only from songs arraylist according to it's index 
		songs.remove(index-1);
	}
	else{
		boolean check=false;
	
		Song removedSong=songs.get(index-1); //get the Song object to be removed according to index and store it into removedSong variable
		songs.remove(index-1); //remove song according to index

		for(int i=0;i<playlists.size();i++){
			if(playlists.get(i).getContent().contains(removedSong)){
				//loop through playlists arraylist and check if each playlist contains the removedSong or not
				int indexSong=playlists.get(i).getContent().indexOf(removedSong); //if it has the removedSong get it's index 
				playlists.get(i).getContent().remove(indexSong);  // remove song from playlist object according to it's index
				check=true;

			}

		} 
		if(check==false){
			//if we didn't find the song according to the given index we throw SongNotFoundException
			throw new SongNotFoundException("Song not found in playlists");
		}

	}
















	}
	
  //Sort songs in library by year
	public void sortSongsByYear()
	{
		// Use Collections.sort() 
		Collections.sort(songs,new SongYearComparator());
	
	}
  // Write a class SongYearComparator that implements
	// the Comparator interface and compare two songs based on year
	private class SongYearComparator implements Comparator<Song> 
	{
		public int compare(Song s1, Song s2) //compares two Song objects based on year
		{ Song song1=(Song)s1; 
			Song song2=(Song)s2;
			if(song1.getYear()>song2.getYear()){
			return 1;
		}
		else if(song1.getYear()<song2.getYear()){
			return -1;
		}
		else{
			return 0;
		}
		}
	}

	// Sort songs by length
	public void sortSongsByLength()
	{	
		Collections.sort(songs,new SongLengthComparator());
	 // Use Collections.sort() 
	}
  // Write a class SongLengthComparator that implements
	// the Comparator interface and compare two songs based on length
	private class SongLengthComparator 	implements Comparator<Song>
	{
		public int compare(Song s1, Song s2){ //compares two Song objects based on their length
			if(s1.getLength()>s2.getLength()){
				return 1;
			}
			else if(s1.getLength()<s2.getLength()){
				return -1;
			}
			else{
				return s1.getTitle().compareTo(s2.getTitle()); //if the length of two Song objects are equal then compare them to their titles
			}
		}
	}

	// Sort songs by title 
	public void sortSongsByName()
	{
	  // Use Collections.sort()
		// class Song should implement the Comparable interface
		// see class Song code
		Collections.sort(songs);
	}

	
	
	










	public void playSong(int index)
	{
		if (index < 1 || index > songs.size())
		{ //if index is less than 1 or greater than songs arraylist  throw SongNotFoundException
			throw new SongNotFoundException("Song Not Found");
		}
		songs.get(index-1).play(); //getting song from songs arraylist and playing that song
	}
	
	// Play podcast from list (specify season and episode)
	// Bonus
	
	public boolean playPodcast(int index, int season, int episode)
	{
		return false;
	}
	
	// Print the episode titles of a specified season
	// Bonus 


	public boolean printPodcastEpisodes(int index, int season)
	{
		return false;
	}

	// Play a chapter of an audio book from list of audiobooks



	public void playAudioBook(int index, int chapter)
	{
		if(index<1||index>audiobooks.size()){ //if index less than 1 or index greater than the size of audiobooks throw AudioBookNotFoundException
			throw new AudioBookNotFoundException("AudioBook not found");

		}
		else if(chapter<1||chapter>audiobooks.get(index-1).getChapters().size()){ //if chapter less than 1 or  chapter greater than the size of chapters arraylist of audiobook object throw AudioBookNotFoundException
			throw new AudioBookNotFoundException("Chapter invalid");
		}
		else{
			AudioBook audiobookObject=audiobooks.get(index-1); //get audioBook object from audiobooks
			audiobookObject.selectChapter(chapter); //select specific chapter to play
			audiobookObject.play(); // play audiboook

		}
	}
















	
	// Print the chapter titles (Table Of Contents) of an audiobook
	// see class AudioBook
	public void  printAudioBookTOC(int index)
	{
		if(index<1||index>audiobooks.size()){ //if index is less than 1 or index greater than the size of audiobooks throw AudioBookNotFoundException
			throw new AudioBookNotFoundException(" Index is invalid");
		}
		else{
			AudioBook audiobookObject=audiobooks.get(index-1); //get the audiobook object from audiobooks
			audiobookObject.printTOC(); //printing the table of contents of the audibook object

		}
	}








	
  /*
   * Playlist Related Methods
   */
	
	// Make a new playlist and add to playlists array list
	// Make sure a playlist with the same title doesn't already exist


	// check this part again
	public void  makePlaylist(String title)
	{
		Playlist playlistObject=new Playlist(title);
		if(playlists.contains(playlistObject)){ //if playlists contains the throw Exception

			throw new PlaylistNotFoundException("Playlist "+ title +" Already Exists");
		}
		else{ //if playlists doesn't have playlistObject then add it to playlists 
			playlists.add(playlistObject);

		}
	}










	
	// Print list of content information (songs, audiobooks etc) in playlist named title from list of playlists
	public void printPlaylist(String title)
	{ 
		if(playlists.size()==0){ //if size of playlists is 0 then throw PlaylistNotFoundException
			throw new PlaylistNotFoundException("No playlists");
		}
		else{
			boolean check=false;
			for(int i=0;i<playlists.size();i++){ 
				if(playlists.get(i).getTitle().equals(title)){ 
					//loop through playlists and check if each playlist object's title matches the string title
					playlists.get(i).printContents(); //get the playlist object from playlists then it print it's contents
					check=true;
				}
			}
			if(check==false){
				// if we didn't find the playlist from playlists arraylist according to the title throw PlaylistNotFoundException
				throw new PlaylistNotFoundException("Playlist Not found");
			}

		}
	}
	












	// Play all content in a playlist
	public void  playPlaylist(String playlistTitle)
	{
		if(playlists.size()==0){ //if playlists size is 0 then set error message and return false
			throw new PlaylistNotFoundException("No Playlists");
		}
		else{
			boolean check=false;
			for(int i=0;i<playlists.size();i++){
				if(playlists.get(i).getTitle().equalsIgnoreCase(playlistTitle)){ 
					//loop through playlists and check if each playlist object's title is equal to playlistTitle
					playlists.get(i).playAll(); //get the playlist object from playlists and use playAll() method
					System.out.println();
					check=true;
				}
			}
			if(check==false){

				throw new PlaylistNotFoundException("Playlist Not Found");

			}

		}
	}










	
	// Play a specific song/audiobook in a playlist
	public void  playPlaylist(String playlistTitle, int indexInPL)
	{
		if(indexInPL<1||playlists.size()==0){ //if indexInPL is less than 1 or playlists size is 0 then set error message and return false
			throw new PlaylistNotFoundException(" Playlist Not Found");

		}
		else{
			boolean check=false;
			for(int i=0;i<playlists.size();i++){
				if(playlists.get(i).getTitle().equalsIgnoreCase(playlistTitle)){
					//loop through playlists and check if each playlist object's title is equal to playlistTitle 
					playlists.get(i).play(indexInPL); //get the playlist object from playlists and  use the play() method with indexInPL as it's parameter
					check=true;

				}
			}
			if(check==false){
				throw new PlaylistNotFoundException("Playlist Not Found");
			}

		}
		

	}














	
	// Add a song/audiobook/podcast from library lists at top to a playlist
	// Use the type parameter and compare to Song.TYPENAME etc
	// to determine which array list it comes from then use the given index
	// for that list
	public void addContentToPlaylist(String type, int index, String playlistTitle)
	{
		if(type.equals(Song.TYPENAME)){ //check to see if type is equal to Song.TYPENAME
			if(index<1||index>songs.size()){// if index is less than 1 or index greater than size of songs set error message and return false
				throw new SongNotFoundException("Song Not Found");
			}
			else{
				boolean check=false;
				for(int i=0;i<playlists.size();i++){
					if(playlists.get(i).getTitle().equals(playlistTitle)){
						//loop through playlists and check if each playlist object's title is equal to playlistTitle
						playlists.get(i).addContent(songs.get(index-1)); //get the playlist object from playlists and add song by using addContent method of playlist
						check=true;
					}
				}

				if(check==false){
					throw new PlaylistNotFoundException("Playlist Not Found");
				}
			}
		}
		else if(type.equals(AudioBook.TYPENAME)){ //check to see if type is equal to Audibook.TYPENAME
			if(index<1||index>audiobooks.size()){ //if index is less than 1 or greater than size of audiobooks throw AudioBookNotFoundException
				throw new AudioBookNotFoundException("AudioBook Not Found");
			}
			else{
				boolean check=false;
				for(int i=0;i<playlists.size();i++){
					if(playlists.get(i).getTitle().equals(playlistTitle)){
						//loop through playlists and check if each playlist object's title is equal to playlistTitle
						playlists.get(i).addContent(audiobooks.get(index-1)); //get playlist object from playlists and add audiobook using addContent method of playlist
						check=true;
					}
				}
				if(check==false){
					//if we didn't find the playlist that we're looking throw PlaylistNotFoundException
					throw new PlaylistNotFoundException("Playlist Not Found");
				}
			}
		}
		
		else{
			//if type is invalid throw AudioContentNotFoundException
			throw new AudioContentNotFoundException("AudioContent Not found");
		}
	}


























  // Delete a song/audiobook/podcast from a playlist with the given title
	// Make sure the given index of the song/audiobook/podcast in the playlist is valid 
	public void delContentFromPlaylist(int index, String title)
	{
		if(index<1|| playlists.size()==0){ //if index is less than 1 or size of playlists is equal to 0 throw PlaylistNotFoundException

			throw new PlaylistNotFoundException("Playlist Not Found");
		}
		else{
			boolean check=false;
			for(int i=0;i<playlists.size();i++){
				if(playlists.get(i).getTitle().equalsIgnoreCase(title)){
					//loop through playlists and check if each playlist object's title is equal to title
					playlists.get(i).deleteContent(index); // get the playlist object from playlists and deleteContent according to index 
					check=true;
				}
			}


			if(check==false){
			//if we didn't find the playlist that we're looking throw PlaylistNotFoundException

				throw new PlaylistNotFoundException("Playlist Not Found");
			}

	}
	
}





}



//Custom exception called AudioContentNotFoundException which extends RuntimeException
class AudioContentNotFoundException extends RuntimeException{ 
	public AudioContentNotFoundException(){}
	public AudioContentNotFoundException(String message){
		super(message);
	}
}
//Custom exception called AudioBookNotFoundException which extends RuntimeException

class AudioBookNotFoundException extends RuntimeException{
	public AudioBookNotFoundException(){}
	public AudioBookNotFoundException(String message){
		super(message);
	}
}

//Custom exception called SongNotFoundException which extends RuntimeException


class SongNotFoundException extends RuntimeException{
	public SongNotFoundException(){}
	public SongNotFoundException(String message){
		super(message);
	}
}
//Custom exception called PlaylistNotFoundException which extends RuntimeException

class PlaylistNotFoundException extends RuntimeException{
	public PlaylistNotFoundException(){}
	public PlaylistNotFoundException(String message){
		super(message);
	}
}




