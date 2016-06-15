package br.com.braga.adb.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ericbraga on 01/03/16.
 */
public class CategoryFactory {
    private List<FilterModel> categories;

    public CategoryFactory() {
        categories = new ArrayList<>();

        categories.add(new FilterModel("android.intent.category.DEFAULT", "Intent.CATEGORY_DEFAULT"));
        categories.add(new FilterModel("android.intent.category.BROWSABLE", "Intent.CATEGORY_BROWSABLE"));
        categories.add(new FilterModel("android.intent.category.TAB", "Intent.CATEGORY_TAB"));
        categories.add(new FilterModel("android.intent.category.ALTERNATIVE", "Intent.CATEGORY_ALTERNATIVE"));
        categories.add(new FilterModel("android.intent.category.LAUNCHER", "Intent.CATEGORY_LAUNCHER"));
        categories.add(new FilterModel("android.intent.category.INFO", "Intent.CATEGORY_INFO"));
        categories.add(new FilterModel("android.intent.category.HOME", "Intent.CATEGORY_HOME"));
        categories.add(new FilterModel("android.intent.category.PREFERENCE", "Intent.CATEGORY_PREFERENCE"));
        categories.add(new FilterModel("android.intent.category.TEST", "Intent.CATEGORY_TEST"));
        categories.add(new FilterModel("android.intent.category.CAR_DOCK", "Intent.CATEGORY_CAR_DOCK"));
        categories.add(new FilterModel("android.intent.category.DESK_DOCK", "Intent.CATEGORY_DESK_DOCK"));
        categories.add(new FilterModel("android.intent.category.HE_DESK_DOCK", "Intent.CATEGORY_HE_DESK_DOCK"));
        categories.add(new FilterModel("android.intent.category.CAR_MODE", "Intent.CATEGORY_CAR_MODE"));
        categories.add(new FilterModel("android.intent.category.APP_MARKET", "Intent.CATEGORY_APP_MARKET"));
    }

    public List<FilterModel> getList() {
        return new ArrayList<>(categories);
    }
}
