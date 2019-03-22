package com.rc.facecase.util;

import com.rc.facecase.model.Category;
import com.rc.facecase.model.Items;
import com.rc.facecase.model.SubCategory;

import java.util.ArrayList;
import java.util.List;

public final class DataUtils {

    public static List<Items> getItemsList(Category category, String SubCategoryNames) {
        {
            //List<Items> itemsList = new ArrayList<>();
            if (category!=null) {

                if (category.getSub_categories().size()>0) {
                    for (int i = 0; i < category.getSub_categories().size(); i++) {
                        String subCategoryName = category.getSub_categories().get(i).getSub_category_name();
                        if (subCategoryName.equalsIgnoreCase(SubCategoryNames)) {
                            //searchMatches.
                            return category.getSub_categories().get(i).getItems();
                        } else if (subCategoryName.equalsIgnoreCase(SubCategoryNames)) {
                            return category.getSub_categories().get(i).getItems();
                        } else if (subCategoryName.equalsIgnoreCase(SubCategoryNames)) {
                            return category.getSub_categories().get(i).getItems();
                        } else if (subCategoryName.equalsIgnoreCase(SubCategoryNames)) {
                            return category.getSub_categories().get(i).getItems();
                        }
                    }
                }

            }
            return null;
        }
    }
}
