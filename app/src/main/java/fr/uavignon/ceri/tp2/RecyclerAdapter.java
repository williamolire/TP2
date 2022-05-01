package fr.uavignon.ceri.tp2;


import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import fr.uavignon.ceri.tp2.data.Book;

public class RecyclerAdapter extends RecyclerView.Adapter<fr.uavignon.ceri.tp2.RecyclerAdapter.ViewHolder> {

    private static final String TAG = fr.uavignon.ceri.tp2.RecyclerAdapter.class.getSimpleName();
    private int bookItemLayout;
    private List<Book> bookList;
    private ListViewModel listViewModel;

    public RecyclerAdapter(int layoutId) {
        bookItemLayout = layoutId;
    }

    public RecyclerAdapter() {

    }


    public void setBookList(List<Book> books) {
        bookList = books;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_layout, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.itemTitle.setText(Book.books[i].getTitle());
        viewHolder.itemDetail.setText(Book.books[i].getAuthors());

    }

    @Override
    public int getItemCount() {
        return Book.books.length;
    }

    public void setListViewModel(ListViewModel viewModel) {
        listViewModel = viewModel;
    }
    private void deleteItem(long id) {
        if (listViewModel != null)
            listViewModel.deleteBook(id);
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemTitle;
        TextView itemDetail;

        ActionMode actionMode;
        long idSelectedLongClick;

        ViewHolder(View itemView) {
            super(itemView);
            itemTitle = itemView.findViewById(R.id.item_title);
            itemDetail = itemView.findViewById(R.id.item_detail);


            ActionMode.Callback actionModeCallback = new ActionMode.Callback() {

                // Called when the action mode is created; startActionMode() was called
                @Override
                public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                    // Inflate a menu resource providing context menu items
                    MenuInflater inflater = mode.getMenuInflater();
                    inflater.inflate(R.menu.contexte_menu, menu);
                    return true;
                }

                // Called each time the action mode is shown. Always called after onCreateActionMode, but
                // may be called multiple times if the mode is invalidated.
                @Override
                public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                    return false; // Return false if nothing is done
                }

                public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.menu_delete:
                            fr.uavignon.ceri.tp2.RecyclerAdapter.this.deleteItem(idSelectedLongClick);
                            Snackbar.make(itemView, "Book supprimée !",
                                    Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                            mode.finish(); // Action picked, so close the CAB
                            return true;
                        default:
                            return false;
                    }
                }

                // Called when the user exits the action mode
                @Override
                public void onDestroyActionMode(ActionMode mode) {
                    actionMode = null;
                }
            };



            itemView.setOnClickListener(new View.OnClickListener() {//on definie l'action de click que l'element de notre vue pour acceder a la second vue
                @Override public void onClick(View v) {
                    int position = getAdapterPosition();

                    ListFragmentDirections.ActionFirstFragmentToSecondFragment action = ListFragmentDirections.actionFirstFragmentToSecondFragment();
                    action.setBookNum(position);
                    Navigation.findNavController(v).navigate(action);
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    idSelectedLongClick = RecyclerAdapter.this.bookList.get((int)getAdapterPosition()).getId();
                    if (actionMode != null) {
                        return false;
                    }
                    Context context = v.getContext();
                    // Start the CAB using the ActionMode.Callback defined above
                    actionMode = ((Activity)context).startActionMode(actionModeCallback);
                    v.setSelected(true);
                    return true;
                }
            });

        }
    }


}