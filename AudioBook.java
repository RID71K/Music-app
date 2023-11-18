//NAME: MD RIDWAN UL KABIR
//ID: 501147339
import java.util.ArrayList;

/*
 * An AudioBook is a type of AudioContent.
 * It is a recording made available on the internet of a book being read aloud by a narrator
 * 
 */
public class AudioBook extends AudioContent
{
	public static final String TYPENAME =	"AUDIOBOOK";
	
	private String author; 
	private String narrator;
	private ArrayList<String> chapterTitles;
	private ArrayList<String> chapters;
	private int currentChapter = 0;

	
	public AudioBook(String title, int year, String id, String type, String audioFile, int length,
									String author, String narrator, ArrayList<String> chapterTitles, ArrayList<String> chapters)
	{
		// Make use of the constructor in the super class AudioContent. 
		// Initialize additional AudioBook instance variables. 
		super(title, year, id, type, audioFile, length); // calling parent's constructor to initalize instance variables
		this.author=author;
		this.narrator=narrator;
		this.chapterTitles=chapterTitles;
		this.chapters=chapters;
	}
	
	public String getType()
	{
		return TYPENAME;
	}

  	// Print information about the audiobook. First print the basic information of the AudioContent 
	// by making use of the printInfo() method in superclass AudioContent and then print author and narrator
	// see the video


	public void printInfo() 
	{
		super.printInfo(); // using parent's printInfo() method
		System.out.println("Author: "+ this.author+" Narrated by: "+this.narrator); //printing out Author and narrator name
	}
	
  	// Play the audiobook by setting the audioFile to the current chapter title (from chapterTitles array list) 
	// followed by the current chapter (from chapters array list)
	// Then make use of the the play() method of the superclass
	public void play()
	{   String currenTitle= this.chapterTitles.get(currentChapter); //from chapterTitles arraylist get current chapter title name by using value of currentChapter as an index
		String Chapter=this.chapters.get(currentChapter);    //from chapters arraylist get current chapter name by using the value of currentChapter as an index
		String input=currenTitle+"\n"+Chapter;       
		super.setAudioFile(input);     // seting  AudioFile with input
		super.play(); //using parent's play() method
	}
	
	// Print the table of contents of the book - i.e. the list of chapter titles
	
	public void printTOC()
	{   
		for(int i=0;i<chapterTitles.size();i++){
			System.out.println("Chapter "+(i+1)+". "+chapterTitles.get(i)); //looping through chapterTitles arraylist and printing each chapter number and their title
			System.out.println();
		}	
		
	}

	// Select a specific chapter to play - nothing to do here
	public void selectChapter(int chapter)
	{
		if (chapter >= 1 && chapter <= chapters.size())
		{
			currentChapter = chapter - 1;
		}
	}
	
	//Two AudioBooks are equal if their AudioContent information is equal and both the author and narrators are equal
	public boolean equals(Object other)
	{ AudioBook otherBook=(AudioBook)other; //Creating AudioBook object called otherBook by casting Object other to AudioBook
		if(super.equals(otherBook)&&(this.author.equals(otherBook.author)&&(this.narrator.equals(otherBook.narrator)))){
		return true;
	}
		return false;
	}
	
	public int getNumberOfChapters()
	{
		return chapters.size();
	}

	public String getAuthor()
	{
		return author;
	}

	public void setAuthor(String author)
	{
		this.author = author;
	}

	public String getNarrator()
	{
		return narrator;
	}

	public void setNarrator(String narrator)
	{
		this.narrator = narrator;
	}

	public ArrayList<String> getChapterTitles()
	{
		return chapterTitles;
	}

	public void setChapterTitles(ArrayList<String> chapterTitles)
	{
		this.chapterTitles = chapterTitles;
	}

	public ArrayList<String> getChapters()
	{
		return chapters;
	}

	public void setChapters(ArrayList<String> chapters)
	{
		this.chapters = chapters;
	}
	public boolean checkTitle(String name){
		//this method checks if a given name is valid title or not

		for(int i=0;i<chapterTitles.size();i++){

			if(chapterTitles.get(i).trim().equalsIgnoreCase(name)){
				return true;

			}
			
		}
		return false;

	}
	public boolean checkChapters(String name){
		//this method checks if if a given name is a valid  chapter or not
		for(int i=0;i<chapters.size();i++){
			if(chapters.get(i).trim().contains(name)){
				return true;
			}

		}
		return false;
	}

}
