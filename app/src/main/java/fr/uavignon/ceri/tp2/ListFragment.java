package fr.uavignon.ceri.tp2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;
import java.util.Locale;

import fr.uavignon.ceri.tp2.data.Book;

public class ListFragment extends Fragment {

    private RecyclerAdapter adapter;
    private ListViewModel viewModel;

    private ProgressBar progress;

    private TextView item_title;
    private TextView item_detail;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    private void clearFields() {
        item_title.setText("");
        item_detail.setText("");
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(ListViewModel.class);

        listenerSetup();
        observerSetup();
        recyclerSetup();

    }

    private void listenerSetup() {
        recyclerView = getView().findViewById(R.id.recyclerView);
      /*  layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setListViewModel(viewModel);
        progress = getView().findViewById(R.id.progress_circular);*/

        FloatingActionButton fab = getView().findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ListFragmentDirections.ActionFirstFragmentToSecondFragment action = ListFragmentDirections.actionFirstFragmentToSecondFragment();
                action.setBookNum(Book.ADD_ID);
                Navigation.findNavController(view).navigate(action);

            }
        });
    }

    private void observerSetup() {
        viewModel.getAllBooks().observe(getViewLifecycleOwner(),
                new Observer<List<Book>>() {
                    @Override
                    public void onChanged(@Nullable final List<Book> books) {
                        adapter.setBookList(books);
                    }
                });
    }



    private void recyclerSetup() {
        RecyclerView recyclerView;
        adapter = new RecyclerAdapter(R.layout.fragment_list);
       // adapter = new RecyclerAdapter();
        recyclerView = getView().findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }
}