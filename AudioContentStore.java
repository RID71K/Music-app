
//NAME: MD RIDWAN UL KABIR
//ID: 501147339
import java.io.FileNotFoundException;
import java.io.*;
import java.io.IOException;
import java.util.*;

import javax.swing.text.AbstractDocument.Content;


// Simulation of audio content in an online store
// The songs, podcasts, audiobooks listed here can be "downloaded" to your library

public class AudioContentStore
{
		private ArrayList<AudioContent> contents; 
		private Map<String, Integer> titleMap;
		//map for titles which has string as key and integer as value
		private Map<String, ArrayList<Integer>> creatorMap;
		//map for creators where string is key and integer arraylist is value
		private Map<String, ArrayList<Integer>> genreMap;
		//map for generes where string is key and integer arraylist is value
		private ArrayList<AudioContent> storeContent;
		private ArrayList<AudioContent> objects;
		private ArrayList<AudioContent>genreObjects;
		private Map<String, ArrayList<Integer>> contentMap;
		//map for contents where string is key and integer arraylist is value



		
		
		public AudioContentStore()
		{
			contents = new ArrayList<AudioContent>();
			titleMap=new HashMap<>();
			creatorMap=new HashMap<>();
			genreMap=new HashMap<>();
			try{

				contents=readFile("store.txt");
				//contents will have arraylist that readFile method returns


			}
			catch(IOException e){
				//readFile method throws IOException we catch it and print message and exit system
				System.out.println("IOExeception occured "+ e.getMessage());
				System.exit(1);
			}
			for(int i=0;i<contents.size();i++){ //go through contents arraylist and put titles to title map and putting artists and authors to creatorMap
				titleMap.put(contents.get(i).getTitle(),i);
				if(contents.get(i).getType().equals("SONG")){
					Song songObject= (Song) contents.get(i);
					String artists=songObject.getArtist();
					if(!creatorMap.containsKey(artists)){
						ArrayList<Integer>indexes=new ArrayList<>();
						indexes.add(i); //if creatorMap doesn't have artist put the artist as key and and add it's index to indexes arraylist put it in creatorMap
						creatorMap.put(songObject.getArtist(),indexes);

					}
					else{
						//if creatorMap has artist as key add  index to indexes arraylist and put both of them into creatorMap
						ArrayList<Integer> indexes= creatorMap.get(artists);
						indexes.add(i);
						creatorMap.put(artists, indexes);
				


					}
				}
				else if(contents.get(i).getType().equals("AUDIOBOOK")){
					AudioBook book= (AudioBook) contents.get(i);
					String author=book.getAuthor();
					if(!creatorMap.containsKey(author)){ //if creatorMap doesn't have author as key put index into indexes arraylist and put both of them into creatorMap
						ArrayList<Integer> indexes=new ArrayList<>();
						indexes.add(i);
						creatorMap.put(author,indexes);
					}
					else{
						//if creatorMap has author as key add  index to indexes arraylist and put both of them into creatorMap
						ArrayList<Integer> indexes= creatorMap.get(author);
						indexes.add(i);
						creatorMap.put(author, indexes);
					}
				}

			}
			for(int i=0;i<contents.size();i++){ //going through contents arraylist putting genere of songs into genreMap
				if(contents.get(i).getType().equals("SONG")){
					Song song=(Song) contents.get(i);
					String genre=song.getGenre().name();
					if(!genreMap.containsKey(genre)){ //if genreMap doesn't have genre as key put index into indexes arraylist and put both of them into genreMap
						ArrayList<Integer>indexes=new ArrayList<>();
						indexes.add(i);
						genreMap.put(genre, indexes);
					}
					else{
						//if genreMap has genre as key add  index to indexes arraylist and put both of them into genreMap
						ArrayList<Integer>indexes=genreMap.get(genre);
						indexes.add(i);
						genreMap.put(genre, indexes);

					}
				}

			}






		}
		public ArrayList<AudioContent> downloadA(String artist){ //downloadA method takes string artist or author name and returns Arraylist of indexes assosicated with that author or artist
			try{objects=new ArrayList<>();
			ArrayList<Integer>indexes=creatorMap.get(artist); //getting indexes arraylist from creatorMap
			for(int i=0;i<indexes.size();i++){
				int index=indexes.get(i);
				objects.add(contents.get(index));


			}
			}
			catch(NullPointerException e){
				//since we have arrayList we might have NullPointerException we catch the exception and print out message
				System.out.println("No matches for "+artist );
			}
			return objects;
		}
		public ArrayList<AudioContent> downloadG(String genre){ //downloadG takes genre string as parameters and returns arraylist of AudioContent objects assoicated with that genre
			try{
				genreObjects=new ArrayList<>();
				ArrayList<Integer>indexes=genreMap.get(genre);
				for(int i=0;i<indexes.size();i++){
				int index=indexes.get(i);
				genreObjects.add(contents.get(index));
			}
			}
			catch(NullPointerException e){
				//since we have arrayList we might have NullPointerException we catch the exception and print out message
				System.out.println("Invalid Genre");
			}
			return genreObjects;

		}





		public void search(String title){ //search takes title string as parameter and prints info about the object associated with that title
			if(titleMap.containsKey(title)){
				int index=titleMap.get(title);
				System.out.print(index+1+". ");
				contents.get(index).printInfo();

			}
			else{
				//if we don't find the AudioContent object associated with  title string we throw AudioContentNotFound exception
				throw new AudioContentNotFoundException("No matches for "+title);
			}

		}
		public void searchA(String creator){ //searchA takes string creator as parameter and prints info about objects associated with that creator
			if(creatorMap.containsKey(creator)){
				ArrayList<Integer> indexes=creatorMap.get(creator);
				for(int i=0;i<indexes.size();i++){
					int index=indexes.get(i);
					System.out.print(index+1+". ");
					contents.get(index).printInfo();
					System.out.println();
				}

			}
			else{
				//if we don't find the AudioContent object associated with creator string we throw AudioContentNotFound exception
				throw new AudioContentNotFoundException("No matches for "+creator);
			}



		}
		public void searchG(String genre){ //searchA takes string genre as parameter and prints info about objects associated with that genre
			if(genreMap.containsKey(genre)){
				ArrayList<Integer> indexes=genreMap.get(genre);
				for(int i=0;i<indexes.size();i++){
					int index=indexes.get(i);
					System.out.print(index+1+". ");
					contents.get(index).printInfo();
					System.out.println();
				}

			}
			else{
				//if we don't find the AudioContent object associated with genre string we throw AudioContentNotFound exception
				throw new AudioContentNotFoundException("Invalid Genre");
			}
		}
		public void searchHP(String name){  //searchA takes string name as parameter and prints info about objects associated with that name
			contentMap=new HashMap<>();
			for(int i=0;i<contents.size();i++){
				String type=contents.get(i).getType();
				if(type.equals("SONG")){
					Song song=(Song) contents.get(i);
					String genre=song.getGenre().name();
					if(song.getArtist().toLowerCase().contains(name.toLowerCase())||song.getAudioFile().contains(name)||song.getComposer().contains(name)||song.getTitle().toLowerCase().contains(name)||song.getId().contains(name)||song.getLyrics().contains(name)||genre.contains(name)){
						if(!contentMap.containsKey(name)){
							ArrayList<Integer>indexes=new ArrayList<>();
							indexes.add(i);
							contentMap.put(name, indexes);

						}
						else{

							ArrayList<Integer>indexes=contentMap.get(name);
							if(!indexes.contains(i)){
								indexes.add(i);
								contentMap.put(name, indexes);


							}
						}



						
					}

				}
				else if(type.equals("AUDIOBOOK")){
					AudioBook book=(AudioBook) contents.get(i);


					if(book.getTitle().toLowerCase().contains(name.toLowerCase())||book.getId().contains(name)||book.getAudioFile().contains(name)||book.getAuthor().contains(name)||book.getNarrator().contains(name)||book.checkTitle(name)|| book.checkChapters(name)){

						
						if(!contentMap.containsKey(name)){
							ArrayList<Integer>indexes=new ArrayList<>();
							indexes.add(i);
							contentMap.put(name,indexes);
						}
						else{
							ArrayList<Integer>indexes=contentMap.get(name);
							if(!indexes.contains(i)){
								indexes.add(i);
								contentMap.put(name, indexes);
							}

						}
						

					}
				}








			}
			try{
				ArrayList<Integer>indexes=contentMap.get(name);
				boolean check=false;
				for(int i=0;i<indexes.size();i++){
					int index=indexes.get(i);
					contents.get(index).printInfo();
					check=true;
				}
				if(check==false){
					//if we don't the name within the objects we throw AudioContentNotFoundException
					throw new AudioContentNotFoundException("AudioContent not Found");
				}

			}
			catch(NullPointerException e){
				//since we have arrayList we might have NullPointerException we catch the exception and print out message
				System.out.println("No contents");
			}

		}


		private ArrayList<AudioContent> readFile(String fileName) throws IOException{ 
			//readFile method goes through a file and creates creates song and audiobook objects and adds to the contents arraylist and returns that arraylist
			this.contents=new ArrayList<>();
	
			File file= new File(fileName);
			Scanner scan=new Scanner(file);
			while(scan.hasNextLine()){
				String type=scan.nextLine().trim();
				if(type.equals("SONG")){
					
					String id=scan.nextLine().trim();
					String title=scan.nextLine().trim();
					int year= Integer.parseInt(scan.nextLine().trim());
					int length=Integer.parseInt(scan.nextLine().trim());
					String artist=scan.nextLine().trim();
					String composer=scan.nextLine().trim();
					
					String genre=scan.nextLine().trim();

					int lyricsLine=Integer.parseInt(scan.nextLine());
					String lyrics="";
					for(int i=0;i<lyricsLine;i++){
						lyrics+=scan.nextLine().trim()+"\n";
					}

					Song songObject=new Song(title, year, id, type,lyrics,length, artist, composer, Song.Genre.valueOf(genre),lyrics);
					System.out.println("Loading SONG");
					contents.add(songObject);

				}
				else if(type.equals("AUDIOBOOK")){
					String id=scan.nextLine().trim();
					String bookTitle=scan.nextLine().trim();
					int bookYear=Integer.parseInt(scan.nextLine().trim());
					int length=Integer.parseInt(scan.nextLine().trim());
					String author=scan.nextLine().trim();
					String narrator=scan.nextLine().trim();
					int numberOfTitles=Integer.parseInt(scan.nextLine().trim());
					ArrayList<String>chapterTitles=new ArrayList<>();
					ArrayList<String> chapters=new ArrayList<>();

					for(int i=0;i<numberOfTitles;i++){
						chapterTitles.add(scan.nextLine().trim()+"\n");


					}
					for(int i=0;i<numberOfTitles;i++){
						String lines="";
						int chapterLines=Integer.parseInt(scan.nextLine().trim());
						for(int j=0;j<chapterLines;j++){
							lines+=scan.nextLine()+"\n";

						}
						chapters.add(lines);

					}


					AudioBook book =new AudioBook(bookTitle, bookYear, id, type, "", length, author, narrator, chapterTitles, chapters);
					System.out.println("Loading AUDIOBOOK");
					contents.add(book);





					
				}


			}
			return contents;
		}
			






			
		  
			public ArrayList<AudioContent> getContent(int startIndex, int endIndex){ 
				//getContent method takes two parameters startIndex and endIndex. It will add objects into storeContent arraylist according to those indexes and return that list.
				storeContent=new ArrayList<>();
				if(startIndex<1 ||startIndex>contents.size()||endIndex<1||endIndex>contents.size()){
					//if index invalid return null
					return null;
				}
				else{
					for(int i=startIndex-1;i<endIndex;i++){
						storeContent.add(contents.get(i));


					}
					return storeContent;

				}
			}
		
		
		public AudioContent getContent(int index)
		{
			if (index < 1 || index > contents.size())
			{
				return null;
			}
			return contents.get(index-1);
		}
		
		public void listAll()
		{
			for (int i = 0; i < contents.size(); i++)
			{
				int index = i + 1;
				System.out.print("" + index + ". ");
				contents.get(i).printInfo();
				System.out.println();
			}
		}
		
		
		
		
}
