package fr.uavignon.ceri.tp2.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
import fr.uavignon.ceri.tp2.data.Book;

@Dao
public interface BookDao {

    @Update(onConflict = OnConflictStrategy.IGNORE)
    int update(Book book);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insertBook(Book book);

    @Query("SELECT * FROM books WHERE id = :id")
    Book getBook(long id);


    @Query("DELETE FROM books WHERE id = :id")
    void deleteBook(double id);

    @Query("SELECT * from books ORDER BY title ASC")
    LiveData<List<Book>> getAllBooks();

    @Query("SELECT * FROM books WHERE title = :title")
    List<Book> findBook(String title);

    @Query("SELECT * FROM books WHERE id = :id")
    Book getBookById(double id);

}

