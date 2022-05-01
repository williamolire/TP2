package fr.uavignon.ceri.tp2.data;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "books", indices = {@Index(value = {"title", "authors"}, unique = true)})
public class Book{

    public static final long ADD_ID = -1;
    //public static final String TAG = Book.class.getSimpleName();

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name="id")
    private int id;

    @NonNull
    @ColumnInfo(name="title")
    private String title;

    @NonNull
    @ColumnInfo(name="authors")
    private String authors;

    @ColumnInfo(name="year")
    private String year; // publication year

    @ColumnInfo(name="genres")
    private String genres;

    @ColumnInfo(name="publish")
    private String publisher;

    public Book(String title, String authors, String year, String genres, String publisher) {
        this.title = title;
        this.authors = authors;
        this.year = year;
        this.genres = genres;
        this.publisher = publisher;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() { return title; }

    public int update(Book book) {
        return 0;
    }

    public long insert(Book books) {
        return 0;
    }

    public Book getBook(long id){ return this; }

    public LiveData<List<Book>> getAllBooks() {
        return null;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getGenres() { return genres;}

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return this.title+"("+this.authors+")";
    }



    public static Book[] books = {new Book("Rouge Brésil", "J.-C. Rufin", "2003", "roman d'aventure, roman historique", "Gallimard"),
            new Book("Guerre et paix", "L. Tolstoï", "1865-1869", "roman historique", "Gallimard"),
            new Book("Fondation", "I. Asimov", "1957", "roman de science-fiction", "Hachette"),
            new Book("Du côté de chez Swan", "M. Proust", "1913", "roman", "Gallimard"),
            new Book("Le Comte de Monte-Cristo", "A. Dumas", "1844-1846", "roman-feuilleton", "Flammarion"),
            new Book("L'Iliade", "Homère", "8e siècle av. J.-C.", "roman classique", "L'École des loisirs"),
            new Book("Histoire de Babar, le petit éléphant", "J. de Brunhoff", "1931", "livre pour enfant", "Éditions du Jardin des modes"),
            new Book("Le Grand Pouvoir du Chninkel", "J. Van Hamme et G. Rosiński", "1988", "BD fantasy", "Casterman"),
            new Book("Astérix chez les Bretons", "R. Goscinny et A. Uderzo", "1967", "BD aventure", "Hachette"),
            new Book("Monster", "N. Urasawa", "1994-2001", "manga policier", "Kana Eds"),
            new Book("V pour Vendetta", "A. Moore et D. Lloyd", "1982-1990", "comics", "Hachette")};

}
