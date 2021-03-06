package com.team.androidpos;

import android.content.Context;

import androidx.room.Room;

import com.team.androidpos.model.PosDatabase;
import com.team.androidpos.model.repo.CategoryRepo;
import com.team.androidpos.model.repo.ProductRepo;
import com.team.androidpos.model.repo.SaleRepo;

public abstract class ServiceLocator {

    private static ServiceLocator instance;

    public abstract CategoryRepo categoryRepo();
    public abstract ProductRepo productRepo();

    public abstract SaleRepo saleRepo();

    public static ServiceLocator getInstance(Context context) {
        if (instance == null) {
            instance = new DefaultServiceLocator(context);
        }
        return instance;
    }

    static class DefaultServiceLocator extends ServiceLocator {

        private PosDatabase database;
        private CategoryRepo categoryRepo;
        private ProductRepo productRepo;
        private SaleRepo saleRepo;

        DefaultServiceLocator(Context context) {
            database = Room.databaseBuilder(context, PosDatabase.class, "android-pos-office").build();
        }

        @Override
        public CategoryRepo categoryRepo() {
            if (categoryRepo == null) {
                categoryRepo = new CategoryRepo(database.categoryDao());
            }
            return categoryRepo;
        }

        @Override
        public ProductRepo productRepo() {
            if (productRepo == null) {
                productRepo = new ProductRepo(database.productDao());
            }
            return productRepo;
        }

        @Override
        public SaleRepo saleRepo() {
            if(saleRepo == null) {
                saleRepo = new SaleRepo(database.saleDao());
            }
            return saleRepo;
        }

    }

}
