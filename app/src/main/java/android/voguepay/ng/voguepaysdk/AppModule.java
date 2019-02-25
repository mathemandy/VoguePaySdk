package android.voguepay.ng.voguepaysdk;

import android.content.Context;
import android.voguepay.ng.voguepaysdk.di.ViewModelModule;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * A generic class to Provide Our Databse instances and Dao instances
 */



@Module(includes = {ViewModelModule.class})
public class AppModule {
    AppExecutors appExecutors;

    public AppModule() {
        appExecutors = new AppExecutors();
    }


     @Singleton @Provides
     AppExecutors providesExecutors () {
         return appExecutors;
     }


     @Singleton
     @Provides
     static Context providesContext() {
         return AppController.getContextInstance();
     }


//    /**
//   * provide the database to Use
//   * */
//    @Provides
//    @Singleton
//    NewsDatabase provideDb(Context context){
//        return Room.databaseBuilder(
//                context,
//                NewsDatabase.class,
//                "newsDatabase")
//                .addMigrations(MIGRATION_1_2)
//                .build();
//    }

    //class to get access to DAO
//    @Singleton  @Provides
//    NewsDao provideNewsDao(NewsDatabase newsDatabase){
//        return newsDatabase.newsDao();
//    }
//    //provide the Favorite Dao
//    @Singleton @Provides
//    FavoriteDao provideFavoriteDao (NewsDatabase newsDatabase){
//        return newsDatabase.favoriteDao();
//    }
//
//
//    static  final Migration MIGRATION_1_2 =
//            new Migration(1, 2) {
//                @Override
//                public void migrate(@NonNull SupportSQLiteDatabase database) {
//                database.execSQL(" ALTER TABLE news " + " ADD COLUMN favorite INTEGER NOT NULL DEFAULT 0 ");
//                }
//            };
}



