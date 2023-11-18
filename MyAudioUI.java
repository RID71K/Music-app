
//NAME: MD RIDWAN UL KABIR
//ID: 501147339

import java.lang.invoke.TypeDescriptor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.StringTokenizer;

// Simulation of a Simple Text-based Music App (like Apple Music)

public class MyAudioUI
{
	public static void main(String[] args)
	{
		// Simulation of audio content in an online store
		// The songs, podcasts, audiobooks in the store can be downloaded to your mylibrary
		AudioContentStore store = new AudioContentStore();
		
		// Create my music mylibrary
		Library mylibrary = new Library();

		Scanner scanner = new Scanner(System.in);
		System.out.print(">");

		// Process keyboard actions
		while (scanner.hasNextLine())
		{
			String action = scanner.nextLine();

			if (action == null || action.equals("")) 
			{
				System.out.print("\n>");
				continue;
			}
			else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
				return;
			
			else if (action.equalsIgnoreCase("STORE"))	// List all songs
			{
				store.listAll(); 
			}
			else if (action.equalsIgnoreCase("SONGS"))	// List all songs
			{
				mylibrary.listAllSongs(); 
			}
			else if (action.equalsIgnoreCase("BOOKS"))	// List all songs
			{
				mylibrary.listAllAudioBooks(); 
			}
			else if (action.equalsIgnoreCase("PODCASTS"))	// List all songs
			{
				mylibrary.listAllPodcasts(); 
			}
			else if (action.equalsIgnoreCase("ARTISTS"))	// List all songs
			{
				mylibrary.listAllArtists(); 
			}
			else if (action.equalsIgnoreCase("PLAYLISTS"))	// List all play lists
			{
				mylibrary.listAllPlaylists(); 
			}
			// Download audiocontent (song/audiobook/podcast) from the store 
			// Specify the index of the content
			else if (action.equalsIgnoreCase("DOWNLOAD")) 
			{
				int index = 0;
				int toIndex=0;
				
				System.out.print("From Store Content #: ");
				try
				{
					index = scanner.nextInt(); //user enters the starting index
					scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
					System.out.print("To Store Content #: ");
					toIndex=scanner.nextInt(); // user enters the ending the index
					scanner.nextLine();
					ArrayList<AudioContent> contents = store.getContent(index,toIndex); //using  getContent method of store class to get ArrayList of AudioContent objects
				for(int i=0;i<contents.size();i++) //looping through the contents arraylist and using download method
				{
					mylibrary.download(contents.get(i));
					
				}
				
				}catch(InputMismatchException e){
					//If we have a mismatched input we catch it here and print out message
					System.out.println("Invalid input");
				}
				catch(NullPointerException e){
					//since we have a arraylist  we can have null pointer exception so we catch that exception and we print out the message
					System.out.println("No contents");
				}

				
				
			}
			// Get the *library* index (index of a song based on the songs list)
			// of a song from the keyboard and play the song 

			else if (action.equalsIgnoreCase("PLAYSONG")) // plays Song 
			{
				int indexSong=0;
				System.out.print("Song Number: ");
				try{
					indexSong=scanner.nextInt(); //user inputs index of Song
					scanner.nextLine();
					mylibrary.playSong(indexSong);


				}
				catch(SongNotFoundException e){
					//playSong throws SongNotFoundException we catch it here and print message
					System.out.println(e.getLocalizedMessage());
				}
				catch(InputMismatchException e){
						//If we have a mismatched input we catch it here and print out message

					System.out.println("Invalid input");
				}
			}
					

			



			// Print error message if the song doesn't exist in the library
			
			// Print the table of contents (TOC) of an audiobook that
			// has been downloaded to the library. Get the desired book index
			// from the keyboard - the index is based on the list of books in the library
			else if (action.equalsIgnoreCase("BOOKTOC")) 
			{
				int index=0;
				System.out.print("Audio Book Number: ");

					


				try{
					index=scanner.nextInt(); //user inputs the audiobook number
					scanner.nextLine();
					mylibrary.printAudioBookTOC(index);
				}
				catch(AudioBookNotFoundException e){
					//printAudioBookTOC throws AudioBookNotFoundException, we catch the exception here and print message 
					System.out.println(e.getLocalizedMessage());
				}
				catch(InputMismatchException e){
					//If we have a mismatched input we catch it here and print out message

					System.out.println("Invalid Input");
				}

			// Print error message if the book doesn't exist in the library
			}
			// Similar to playsong above except for audio book
			// In addition to the book index, read the chapter 
			// number from the keyboard - see class Library
			else if (action.equalsIgnoreCase("PLAYBOOK")) //plays books
			{
				int index=0;
				int chapter=0;
				
				try{
					System.out.print("Audio Book Number: ");
					index=scanner.nextInt();// user enters audiobook number
					scanner.nextLine();
					System.out.print("Chapter: ");
					chapter=scanner.nextInt(); //user enters chapter number
					scanner.nextLine();
					mylibrary.playAudioBook(index, chapter);
				}
				catch(AudioBookNotFoundException e){
					//playAudioBook method throws a AudioBookNotFoundException we catch it here and print message
					System.out.println(e.getLocalizedMessage());
				}
				catch(InputMismatchException e){
				//If we have a mismatched input we catch it here and print out message

					System.out.println("Invalid input");
				}
				
			}
			// Print the episode titles for the given season of the given podcast
			// In addition to the podcast index from the list of podcasts, 
			// read the season number from the keyboard
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("PODTOC")) 
			{
				
			}
			// Similar to playsong above except for podcast
			// In addition to the podcast index from the list of podcasts, 
			// read the season number and the episode number from the keyboard
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("PLAYPOD")) 
			{
				
			}
			// Specify a playlist title (string) 
			// Play all the audio content (songs, audiobooks, podcasts) of the playlist 
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("PLAYALLPL")) 
			{
				String title="";
				System.out.print("Playlist Title: ");


				try{
					title=scanner.next(); //user enters playlist title
					scanner.nextLine();
					mylibrary.playPlaylist(title);
				}
				catch(PlaylistNotFoundException e){
					//playPlaylist throws PlaylistNotFoundException we catch here it and print message
					System.out.println(e.getLocalizedMessage());
				}


			}
			// Specify a playlist title (string) 
			// Read the index of a song/audiobook/podcast in the playist from the keyboard 
			// Play all the audio content 
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("PLAYPL")) 
			{
				String title="";
				int index=0;


				try{
					System.out.print("Playlist Title: ");
					title=scanner.next(); //user enters playlist title
					scanner.nextLine();
					System.out.print("Content Number: ");
					index=scanner.nextInt(); // user enters content number
					scanner.nextLine();



					mylibrary.playPlaylist(title, index);

				}
				catch(PlaylistNotFoundException e){
					//playPlaylist throws PlaylistNotFoundException we catch here it and print message

					System.out.println(e.getLocalizedMessage());
				}
				catch(InputMismatchException e){
					//If we have a mismatched input we catch it here and print out message

					System.out.println("Invalid input");
				}

			}
			// Delete a song from the list of songs in mylibrary and any play lists it belongs to
			// Read a song index from the keyboard
			// see class Library for the method to call
			
			else if (action.equalsIgnoreCase("DELSONG")) 
			{
				int songIndex=0;



				try{
					System.out.print("Library Song #: ");
					songIndex=scanner.nextInt(); // user enters song index
					scanner.nextLine();

					mylibrary.deleteSong(songIndex);
				}
				catch(SongNotFoundException e){
					// deleteSong() method throws SongNotFoundException we catch it here and print message
					System.out.println(e.getLocalizedMessage());
				}
				catch(InputMismatchException e){
					//If we have a mismatched input we catch it here and print out message

					
					System.out.println("Invalid input");
				}


			}
			// Read a title string from the keyboard and make a playlist
	 	 	// see class Library for the method to call
			else if (action.equalsIgnoreCase("MAKEPL")) 
			{
				String title="";

				try{
					System.out.print("Playlist Title: ");
					title=scanner.next(); //user enters playlist title
					scanner.nextLine();

					mylibrary.makePlaylist(title);
				}
				catch(PlaylistNotFoundException e){
					//makePlaylist throws PlaylistNotFoundException we catch it here and print message
					System.out.println(e.getLocalizedMessage());
				}


			}
			// Print the content information (songs, audiobooks, podcasts) in the playlist
		 	// Read a playlist title string from the keyboard
		   // see class Library for the method to call
			else if (action.equalsIgnoreCase("PRINTPL"))	// print playlist content information
			{
				String title="";


				try{
					System.out.print("Playlist Title: ");
					title=scanner.next(); //user enters playlist title
					scanner.nextLine();

					mylibrary.printPlaylist(title);
				}
				catch(PlaylistNotFoundException e){
					//printPlaylist method throws PlaylistNotFoundException we catch it here and print message
					System.out.println(e.getLocalizedMessage());
				}

				
			}
			// Add content (song, audiobook, podcast) from mylibrary (via index) to a playlist
			// Read the playlist title, the type of content ("song" "audiobook" "podcast")
			// and the index of the content (based on song list, audiobook list etc) from the keyboard
		    // see class Library for the method to call
			else if (action.equalsIgnoreCase("ADDTOPL")) 
			{
				String title="";
				String type="";
				int index=0;



				try{
					System.out.print("Playlist Title: ");
					title=scanner.next(); //user enters playlist title
					scanner.nextLine();
					System.out.print("Content Type [SONG, PODCAST, AUDIOBOOK]: ");
					type=scanner.next().toUpperCase(); //user enters content type
					scanner.nextLine();
					System.out.print("Library Content #: ");
					index=scanner.nextInt(); //user enters content number
					scanner.nextLine();




					mylibrary.addContentToPlaylist(type, index, title);
				}
				catch(SongNotFoundException e ){
					//addContentToPlaylist throws SongNotFoundException we catch it here and print message

					System.out.println(e.getLocalizedMessage());
				}
				catch(PlaylistNotFoundException e_1){
					//addContentToPlaylist throws PlaylistNotFoundException we catch it here and print message

					System.out.println(e_1.getLocalizedMessage());
				}
				catch(AudioBookNotFoundException e_2){
					//addContentToPlaylist throws AudioBookNotFoundException we catch it here and print message

					System.out.println(e_2.getLocalizedMessage());
				}
				catch(AudioContentNotFoundException e_3){
					//addContentToPlaylist throws AudioContentNotFoundException we catch it here and print message

					System.out.println(e_3.getLocalizedMessage());
				}
				catch(InputMismatchException e_4){
					//If we have a mismatched input we catch it here and print out message

					System.out.println("Invalid input");

				}


			}
			//Delete content from play list based on index from the playlist
			// Read the playlist title string and the playlist index
		  	// see class Library for the method to call
			else if (action.equalsIgnoreCase("DELFROMPL")) 
			{
				String title="";
				int index=0;

				try{
					System.out.print("Playlist Title: ");

					title=scanner.next(); //user enters playlist title
					scanner.nextLine();
					System.out.print("Playlist Content #: ");
					index=scanner.nextInt(); //user enters playlist content number
					scanner.nextLine();
					mylibrary.delContentFromPlaylist(index, title);


				}
				catch(PlaylistNotFoundException e){
					//delContentFromPlaylist throws PlaylistNotFoundException we catch it here and print message
					System.out.println(e.getLocalizedMessage());
				}
				catch(InputMismatchException e){
					//If we have a mismatched input we catch it here and print out message
					
					System.out.println("Invalid Input");
				}

			}
			
			else if (action.equalsIgnoreCase("SORTBYYEAR")) // sort songs by year
			{
				mylibrary.sortSongsByYear();
			}
			else if (action.equalsIgnoreCase("SORTBYNAME")) // sort songs by name (alphabetic)
			{
				mylibrary.sortSongsByName();
			}
			else if (action.equalsIgnoreCase("SORTBYLENGTH")) // sort songs by length
			{
				mylibrary.sortSongsByLength();
			}
			else if (action.equalsIgnoreCase("SEARCH")){ //searches store for audiocontent with specified title
				String title="";




				try{
					System.out.print("Title: ");
					title=scanner.nextLine(); // user enters title
					store.search(title);



				}
				catch(AudioContentNotFoundException e){
					//search method throw AudioContentNotFoundException we catch it and print message

					System.out.println(e.getLocalizedMessage());
				}

			}
			else if(action.equalsIgnoreCase("SEARCHA")){ //searches store according to the inputed artist or author string
				String creator="";


				try{
					System.out.print("Artist: ");
					creator=scanner.nextLine(); //user enters name
					store.searchA(creator);




				}
				catch(AudioContentNotFoundException e){
						//searchA method throw AudioContentNotFoundException we catch it and print message
					System.out.println(e.getLocalizedMessage());
				}
			}
			else if(action.equalsIgnoreCase("SEARCHG")){ //searches store for song according to genre
				String genre="";

				try{
					System.out.print("Genre [POP, ROCK, JAZZ, HIPHOP, RAP, CLASSICAL]: ");
					genre=scanner.next().toUpperCase(); //user enters Genre
					scanner.nextLine();


				store.searchG(genre);
			}
			catch(AudioContentNotFoundException e){
					//searchG method throw AudioContentNotFoundException we catch it and print message
				System.out.println(e.getLocalizedMessage());
			}

			
			}
			else if(action.equalsIgnoreCase("DOWNLOADA")){ //downloads songs or audiobook according to the name of artist or author
				String name="";

				try{
					System.out.print("Artist Name: ");
					name=scanner.nextLine(); //user enters name
					ArrayList<AudioContent>objects=store.downloadA(name); //get ArrayList of AudioContent objects from downloadA
				for(int i=0;i<objects.size();i++){
					mylibrary.download(objects.get(i));
				}



				}catch(InputMismatchException e){
					//If we have a mismatched input we catch it here and print out message
					System.out.println("Invalid input");
				}
				catch(NullPointerException e){
					//since we have a arraylist  we can have null pointer exception so we catch that exception and we print out the message
					System.out.println("No contents");
				}

			




				


			}
			else if(action.equalsIgnoreCase("DOWNLOADG")){//download songs according to genre
				String genre="";

				
				try{
					System.out.print("Genre: ");
					genre=scanner.next().toUpperCase();//user enters genre
					scanner.nextLine();
					ArrayList<AudioContent>objects=store.downloadG(genre); //we arraylist of AudioContent of objects from downloadG method
					for(int i=0;i<objects.size();i++){
					mylibrary.download(objects.get(i));
				}


				}catch(InputMismatchException e){
					//If we have a mismatched input we catch it here and print out message
					System.out.println("Invalid input");
				}
				catch(NullPointerException e){
					//since we have a arraylist  we can have null pointer exception so we catch that exception and we print out the message
					System.out.println("No contents");
				}

			}
			else if(action.equalsIgnoreCase("SEARCHP")){ //searches all audio content that partially matches a target string
				String input="";

				try{
					System.out.println("Enter input");
					input=scanner.nextLine(); //user enter the input
					store.searchHP(input);

					

				}
				catch(AudioContentNotFoundException e){
					//searcHP method throws AudioContentNotFoundException we catch it here and print message
					System.out.println(e.getLocalizedMessage());
				}
			}


			System.out.print("\n>");
		}
		
	}
}
