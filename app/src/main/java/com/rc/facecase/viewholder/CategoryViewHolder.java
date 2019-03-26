package com.rc.facecase.viewholder;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.rc.facecase.R;
import com.rc.facecase.activity.MusicCategoryActivity;
import com.rc.facecase.activity.PictureCategoryActivity;
import com.rc.facecase.model.Category;
import com.rc.facecase.util.AllConstants;
import com.rc.facecase.util.AppUtil;

import org.parceler.Parcels;

import static com.rc.facecase.util.AllConstants.CATEGORY_MUSIC;
import static com.rc.facecase.util.AllConstants.CATEGORY_PICTURE;


/**
 * @author Md. Rashadul Alam
 * Email: rashed.droid@gmail.com
 */
public class CategoryViewHolder extends BaseViewHolder<Category> {

    ImageView ivCategory;

    public CategoryViewHolder(ViewGroup parent) {
        super(parent, R.layout.row_category_item);

        ivCategory = (ImageView) $(R.id.iv_category);
    }

    @Override
    public void setData(final Category data) {

        if (data.getCategory_name().trim().equalsIgnoreCase(CATEGORY_PICTURE)){
            ivCategory.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_pictures));
         //   AppUtil.loadImage(getContext(), ivCategory, getContext().getResources().getDrawable(R.drawable.ic_pictures), false, false, false);
        } else if (data.getCategory_name().trim().equalsIgnoreCase(CATEGORY_MUSIC)){
//            AppUtil.loadImage(getContext(), ivCategory, getContext().getResources().getDrawable(R.drawable.ic_music), false, false, false);
            ivCategory.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_music));

        }
        Log.e("Categorydata>>>>",data.toString()+"");

        ivCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (data.getCategory_name().trim().equalsIgnoreCase(CATEGORY_PICTURE)){
                    Intent iFacePlay = new Intent(getContext(), PictureCategoryActivity.class);
                    iFacePlay.putExtra(AllConstants.SESSION_KEY_PICTURE_CATEGORY, Parcels.wrap(data));
                    getContext().startActivity(iFacePlay);
                }
//                else if (data.getCategory_name().trim().equalsIgnoreCase(CATEGORY_MUSIC)){
//                    Intent iFaceMusicPlay = new Intent(getContext(), MusicCategoryActivity.class);
//                    iFaceMusicPlay.putExtra(AllConstants.SESSION_KEY_MUSIC_CATEGORY, Parcels.wrap(data));
//                    getContext().startActivity(iFaceMusicPlay);
//                }
                Log.e("data>>>>",data.toString()+"");

            }
        });
    }
}