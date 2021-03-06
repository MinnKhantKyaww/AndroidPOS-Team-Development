package com.example.andoridpos.ui.product;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.andoridpos.R;
import com.example.andoridpos.ui.ListFragment;
import com.example.andoridpos.ui.category.CategorieEditFragment;
import com.example.andoridpos.ui.category.CategoriesViewMode;
import com.example.andoridpos.ui.category.CategoryAndProductAdapter;

public class ProductsFragment extends ListFragment {

    private ProductAndCategoryAdapter adapter;
    private ProductsViewModel viewModel;

    @Override
    protected RecyclerView.Adapter<? extends RecyclerView.ViewHolder> adapter() {
        if(adapter == null) {
            adapter = new ProductAndCategoryAdapter();
            adapter.setAdapterItemClickListener(vo -> {
                if (getView() == null) return;
                Bundle args = new Bundle();
                args.putInt(ProductEditFragment.KEY_PRODUCT_ID, vo.getId());
                Navigation.findNavController(getView()).navigate(R.id.action_productsFragment2_to_productEditFragment, args);
            });
        }
        return adapter;
    }

    @Override
    protected boolean listenSwipeDelete() {
        return true;
    }

    @Override
    protected void deleteItemAt(int position) {
        viewModel.delete(adapter.getItemAt(position).getId());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(ProductsViewModel.class);
        viewModel.getProducts().observe(this, list -> {
            adapter.submitList(list);
        });
    }

    @Override
    protected void onFabClick(View view) {

        Navigation.findNavController(view).navigate(R.id.action_productsFragment2_to_productEditFragment);
    }
}
