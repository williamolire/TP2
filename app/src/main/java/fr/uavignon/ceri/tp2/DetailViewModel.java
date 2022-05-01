package fr.uavignon.ceri.tp2;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import fr.uavignon.ceri.tp2.data.Book;
import fr.uavignon.ceri.tp2.data.BookRepository;

public class DetailViewModel extends AndroidViewModel {
    public static final String TAG = DetailViewModel.class.getSimpleName();

    private BookRepository repository;
    private MutableLiveData<Book> book;
    private LiveData<List<Book>> allBooks;
    private MutableLiveData<List<Book>> searchResults;


    public DetailViewModel(Application application) {
        super(application);
        repository = new BookRepository(application);
        allBooks = repository.getAllBooks();
        searchResults = repository.getSearchResults();
    }

    public void setCity(long id) {
        repository.getBook(id);
        book = repository.getSelectedBook();
    }

    public void updateBook(Book book) {
        repository.updateBook(book);
    }

}

