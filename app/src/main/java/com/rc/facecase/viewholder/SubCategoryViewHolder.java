package com.rc.facecase.viewholder;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.rc.facecase.R;
import com.rc.facecase.activity.GamePlayActivity;
import com.rc.facecase.activity.MusicCategoryActivity;
import com.rc.facecase.activity.PictureCategoryActivity;
import com.rc.facecase.model.Category;
import com.rc.facecase.model.Items;
import com.rc.facecase.model.SubCategory;
import com.rc.facecase.util.AllConstants;
import com.rc.facecase.util.AppUtil;

import org.parceler.Parcels;

import java.util.List;

import static com.rc.facecase.util.AllConstants.CATEGORY_MUSIC;
import static com.rc.facecase.util.AllConstants.CATEGORY_PICTURE;
import static com.rc.facecase.util.AllConstants.SUB_CATEGORY_ALL_OTHER_SPORTS;
import static com.rc.facecase.util.AllConstants.SUB_CATEGORY_ENTERTAINERS;
import static com.rc.facecase.util.AllConstants.SUB_CATEGORY_FAMOUS_PEOPLE;
import static com.rc.facecase.util.AllConstants.SUB_CATEGORY_FAMOUS_PLACES;
import static com.rc.facecase.util.AllConstants.SUB_CATEGORY_FAMOUS_WORLD_PLACES;
import static com.rc.facecase.util.AllConstants.SUB_CATEGORY_PRIMARY_SPORTS;


/**
 * @author Md. Rashadul Alam
 * Email: rashed.droid@gmail.com
 */
public class SubCategoryViewHolder extends BaseViewHolder<SubCategory> {

    ImageView ivCategory;
    List<Items> itemsPrimarySports;
    Boolean isShown=false;
    public SubCategoryViewHolder(ViewGroup parent) {
        super(parent, R.layout.row_sub_category_item);
        ivCategory = (ImageView) $(R.id.iv_category);
    }

    @Override
    public void setData(final SubCategory data) {

        if (data.getSub_category_name().trim().equalsIgnoreCase(SUB_CATEGORY_PRIMARY_SPORTS)){
           // AppUtil.loadImage(getContext(), ivCategory, getContext().getResources().getDrawable(R.drawable.ic_primarysports), false, false, false);
            ivCategory.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_primarysports));

            itemsPrimarySports = data.getItems();
            isShown=false;
        } else if (data.getSub_category_name().trim().equalsIgnoreCase(SUB_CATEGORY_ALL_OTHER_SPORTS)){
         //   AppUtil.loadImage(getContext(), ivCategory, getContext().getResources().getDrawable(R.drawable.ic_allothersports), false, false, false);
            ivCategory.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_allothersports));

        } else if (data.getSub_category_name().trim().equalsIgnoreCase(SUB_CATEGORY_FAMOUS_PLACES)){
          //  AppUtil.loadImage(getContext(), ivCategory, getContext().getResources().getDrawable(R.drawable.ic_famoususplaces), false, false, false);
            ivCategory.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_famoususplaces));

        } else if (data.getSub_category_name().trim().equalsIgnoreCase(SUB_CATEGORY_FAMOUS_WORLD_PLACES)){
         //   AppUtil.loadImage(getContext(), ivCategory, getContext().getResources().getDrawable(R.drawable.ic_famousworldplaces), false, false, false);
            ivCategory.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_famousworldplaces));

        } else if (data.getSub_category_name().trim().equalsIgnoreCase(SUB_CATEGORY_ENTERTAINERS)){
        //    AppUtil.loadImage(getContext(), ivCategory, getContext().getResources().getDrawable(R.drawable.ic_entertainers), false, false, false);
            ivCategory.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_entertainers));

        } else if (data.getSub_category_name().trim().equalsIgnoreCase(SUB_CATEGORY_FAMOUS_PEOPLE)){
         //   AppUtil.loadImage(getContext(), ivCategory, getContext().getResources().getDrawable(R.drawable.ic_famouspeople), false, false, false);
            ivCategory.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_famouspeople));

        }

        ivCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                isShown = true;
                if (isShown) {
                    Intent iFacePlay = new Intent(getContext(), GamePlayActivity.class);
                    iFacePlay.putExtra(AllConstants.INTENT_KEY_ITEM, Parcels.wrap(data.getItems()));
                    getContext().startActivity(iFacePlay);
                } else {

                }
                    Log.e("data>>>>",data.toString()+"");
            }
        });
    }
}