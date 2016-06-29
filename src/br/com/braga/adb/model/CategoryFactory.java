package br.com.braga.adb.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ericbraga on 01/03/16.
 */
public class CategoryFactory {
    private List<String> categories;

    public CategoryFactory() {
        categories = new ArrayList<>();

        categories.add("android.intent.category.DEFAULT");
        categories.add("android.intent.category.BROWSABLE");
        categories.add("android.intent.category.TAB");
        categories.add("android.intent.category.ALTERNATIVE");
        categories.add("android.intent.category.LAUNCHER");
        categories.add("android.intent.category.INFO");
        categories.add("android.intent.category.HOME");
        categories.add("android.intent.category.PREFERENCE");
        categories.add("android.intent.category.TEST");
        categories.add("android.intent.category.CAR_DOCK");
        categories.add("android.intent.category.DESK_DOCK");
        categories.add("android.intent.category.HE_DESK_DOCK");
        categories.add("android.intent.category.CAR_MODE");
        categories.add("android.intent.category.APP_MARKET");
    }

    public List<String> getList() {
        return new ArrayList<>(categories);
    }
}
