package fr.uavignon.ceri.tp2.data;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static fr.uavignon.ceri.tp2.data.BookRoomDatabase.databaseWriteExecutor;

public class BookRepository {

    private static final String TAG = BookRepository.class.getSimpleName();
    private MutableLiveData<List<Book>> searchResults = new MutableLiveData<>();
    private LiveData<List<Book>>allBooks;
    private MutableLiveData<Book> selectedBook;


    private BookDao bookDao;

    private static volatile BookRepository INSTANCE;

    public BookRepository(Application application) {
        BookRoomDatabase db = BookRoomDatabase.getDatabase(application);
        bookDao = db.BookDao();
        allBooks = bookDao.getAllBooks();
        selectedBook = new MutableLiveData<>();
    }

    public synchronized static BookRepository get(Application application) {
        if (INSTANCE == null) {
            INSTANCE = new BookRepository(application);
        }

        return INSTANCE;
    }

  /*  public BookRepository(Application application) {
        BookRoomDatabase db = BookRoomDatabase.getDatabase(application);
        bookDao = db.BookDao();
        allBooks = bookDao.getAllBooks();
    }*/

    public LiveData<List<Book>> getAllBooks() {
        return allBooks;
    }

    public MutableLiveData<List<Book>>getSearchResults() {
        return searchResults;
    }

    public MutableLiveData<Book> getSelectedBook() {
        return selectedBook;
    }



    public void deleteBook(double id) {
        databaseWriteExecutor.execute(() -> {
            bookDao.deleteBook(id);
            });
        }

    public int updateBook(Book book) {
        Future<Integer> fint = databaseWriteExecutor.submit(() -> {
            return bookDao.update(book);
        });
        int res = -1;
        try {
            res = fint.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (res != -1)
            selectedBook.setValue(book);
        return res;
    }
    public long insertBook(Book book) {
        Future<Long> flong = databaseWriteExecutor.submit(() -> {
            return bookDao.insertBook(book);
        });
        long res = -1;
        try {
            res = flong.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (res != -1)
            selectedBook.setValue(book);
        return res;
    }

    public void getBook(double id)  {
        Future<Book> fbook = databaseWriteExecutor.submit(() -> {
            Log.d(TAG,"selected id="+id);
            return bookDao.getBookById(id);
        });
        try {
            selectedBook.setValue(fbook.get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void findBook(String name) {
        Future<List<Book>> fbooks = databaseWriteExecutor.submit(() -> {
            return bookDao.findBook(name);
            });
        try {
            searchResults.setValue(fbooks.get());
            } catch (ExecutionException e) {
            e.printStackTrace();
            } catch (InterruptedException e) {
            e.printStackTrace();
            }
        }

        
}


