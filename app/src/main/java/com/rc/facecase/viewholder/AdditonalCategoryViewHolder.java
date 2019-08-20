package com.rc.facecase.viewholder;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.rc.facecase.R;
import com.rc.facecase.activity.MusicSubcategoryActivity;
import com.rc.facecase.activity.PictureSubcategoryActivity;
import com.rc.facecase.model.Category;
import com.rc.facecase.util.AllConstants;

import org.parceler.Parcels;

import static com.rc.facecase.util.AllConstants.CATEGORY_MUSIC;
import static com.rc.facecase.util.AllConstants.CATEGORY_PICTURE;


/**
 * @author Md. Rashadul Alam
 * Email: rashed.droid@gmail.com
 */
public class AdditonalCategoryViewHolder extends BaseViewHolder<Category> {

    private TextView tvCategory;
    private LinearLayout llCategory;

    public AdditonalCategoryViewHolder(ViewGroup parent) {
        super(parent, R.layout.row_sub_category_item);
        tvCategory = (TextView) $(R.id.tv_subcategory);
        llCategory = (LinearLayout) $(R.id.ll_subcategory);
    }

    @Override
    public void setData(final Category data) {


        tvCategory.setText(data.getCategory_name());
        Log.e("Categorydata>>>>", data.toString() + "");

        llCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                    Intent iFacePicturePlay = new Intent(getContext(), PictureSubcategoryActivity.class);
                    iFacePicturePlay.putExtra(AllConstants.SESSION_KEY_PICTURE_CATEGORY, Parcels.wrap(data));
                    iFacePicturePlay.putExtra(AllConstants.CATEGORY_NAME, data.getCategory_name()+ " Categories");
                    getContext().startActivity(iFacePicturePlay);

                Log.e("data>>>>", data.toString() + "");

            }
        });
    }
}