package fr.uavignon.ceri.tp2;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import fr.uavignon.ceri.tp2.data.Book;
import fr.uavignon.ceri.tp2.data.BookRepository;

public class ListViewModel extends AndroidViewModel {
    private BookRepository repository;
    private LiveData<List<Book>> allBooks;
    private MutableLiveData<List<Book>> searchResults;


    public ListViewModel (Application application) {
        super(application);
        repository = new BookRepository(application);
        allBooks = repository.getAllBooks();
        searchResults = repository.getSearchResults();
    }

    MutableLiveData<List<Book>> getSearchResults() {
        return searchResults;
    }

    LiveData<List<Book>> getAllBooks() {
        return allBooks;
    }

    /*public void insertBook(Book book) {
        repository.insertBook(book);
    }*/

    public void findBooks(String name) {
        repository.findBook(name);
    }

    public void deleteBook(double id) {
        repository.deleteBook(id);
    }

}
