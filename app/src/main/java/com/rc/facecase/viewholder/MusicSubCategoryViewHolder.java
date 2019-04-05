package com.rc.facecase.viewholder;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.rc.facecase.R;
import com.rc.facecase.activity.GamePlayActivity;
import com.rc.facecase.activity.MusicGamePlayActivity;
import com.rc.facecase.model.Items;
import com.rc.facecase.model.SubCategory;
import com.rc.facecase.util.AllConstants;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import static com.rc.facecase.util.AllConstants.SUB_CATEGORY_ALL_OTHER_SPORTS;
import static com.rc.facecase.util.AllConstants.SUB_CATEGORY_CHILDREN_SOUNDS;
import static com.rc.facecase.util.AllConstants.SUB_CATEGORY_ENTERTAINERS;
import static com.rc.facecase.util.AllConstants.SUB_CATEGORY_FAMOUS_ACRONYMES;
import static com.rc.facecase.util.AllConstants.SUB_CATEGORY_FAMOUS_COMMERCIALS;
import static com.rc.facecase.util.AllConstants.SUB_CATEGORY_FAMOUS_PEOPLE;
import static com.rc.facecase.util.AllConstants.SUB_CATEGORY_FAMOUS_PLACES;
import static com.rc.facecase.util.AllConstants.SUB_CATEGORY_FAMOUS_WORLD_PLACES;
import static com.rc.facecase.util.AllConstants.SUB_CATEGORY_HOLIDAY;
import static com.rc.facecase.util.AllConstants.SUB_CATEGORY_OLD_TIME_FAVOURITES;
import static com.rc.facecase.util.AllConstants.SUB_CATEGORY_PRIMARY_SPORTS;
import static com.rc.facecase.util.AllConstants.SUB_CATEGORY_SOUNDS;


/**
 * @author Md. Rashadul Alam
 * Email: rashed.droid@gmail.com
 */
public class MusicSubCategoryViewHolder extends BaseViewHolder<SubCategory> {
    private Context context;
    private ImageView ivCategory;
    private static List<Items> itemsPrimarySports = new ArrayList<>();
    private static List<Items> itemsAllOtherSports= new ArrayList<>();
    private static List<Items> itemsFamousUSPlaces= new ArrayList<>();
    private static List<Items> itemsFamousWorldPlaces= new ArrayList<>();
    private static List<Items> itemsEntertainers = new ArrayList<>();
    private static List<Items> itemsFamousPlaces = new ArrayList<>();
    private String subCategoryName = "";

    public MusicSubCategoryViewHolder(ViewGroup parent) {
        super(parent, R.layout.row_sub_category_item);

        ivCategory = (ImageView) $(R.id.iv_category);
    }

    @Override
    public void setData(final SubCategory data) {
        subCategoryName = data.getSub_category_name().trim();
        AllConstants.isShown = false;

        if (subCategoryName.equalsIgnoreCase(SUB_CATEGORY_FAMOUS_COMMERCIALS)){
            ivCategory.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_famouscommercials));
        } else if (subCategoryName.equalsIgnoreCase(SUB_CATEGORY_FAMOUS_ACRONYMES)){
            ivCategory.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_famousacronyms));
        } else if (subCategoryName.equalsIgnoreCase(SUB_CATEGORY_SOUNDS)){
           ivCategory.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_sounds));
        } else if (subCategoryName.equalsIgnoreCase(SUB_CATEGORY_CHILDREN_SOUNDS)){
            ivCategory.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_childrens));
        } else if (subCategoryName.equalsIgnoreCase(SUB_CATEGORY_OLD_TIME_FAVOURITES)){
            ivCategory.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_oldtimefavorites));
        } else if (subCategoryName.equalsIgnoreCase(SUB_CATEGORY_HOLIDAY)){
            ivCategory.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_holidaypatrioticmusic));
        }


        Log.e("getSub_name>>>>",data.getSub_category_name()+"");
        Log.e("subCategory>>>>",data.toString()+"");


        ivCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (subCategoryName.equalsIgnoreCase(SUB_CATEGORY_FAMOUS_COMMERCIALS)) {
                    if (!AllConstants.isShown) {
                        if (data.getItems().size()>0) {
                            itemsPrimarySports = data.getItems();
                            switchActivity(data.getItems().get(0));
                        }else {
                            Toast.makeText(getContext(), getContext().getResources().getString(R.string.toast_no_info_found), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        reverseList(itemsPrimarySports,subCategoryName);
                        Log.e("itemsPrimarySports>>>>", itemsPrimarySports.toString() + "");
                    }
                } else if (subCategoryName.equalsIgnoreCase(SUB_CATEGORY_FAMOUS_ACRONYMES)) {
                    if (!AllConstants.isShown) {
                        if (data.getItems().size()>0) {
                            itemsAllOtherSports = data.getItems();
                            switchActivity(data.getItems().get(0));
                        }else {
                            Toast.makeText(getContext(), getContext().getResources().getString(R.string.toast_no_info_found), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        reverseList(itemsAllOtherSports,subCategoryName);
                        Log.e("itemsAllOtherSports>>>>", itemsPrimarySports.toString() + "");
                    }
                }else if (subCategoryName.equalsIgnoreCase(SUB_CATEGORY_SOUNDS)) {
                    if (!AllConstants.isShown) {
                        if (data.getItems().size()>0) {
                            itemsFamousUSPlaces = data.getItems();
                            switchActivity(data.getItems().get(0));
                        }else {
                            Toast.makeText(getContext(), getContext().getResources().getString(R.string.toast_no_info_found), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        reverseList(itemsFamousUSPlaces,subCategoryName);
                        Log.e("itemsPrimarySports>>>>", itemsPrimarySports.toString() + "");
                    }
                }else if ( subCategoryName.equalsIgnoreCase(SUB_CATEGORY_CHILDREN_SOUNDS)) {
                    if (!AllConstants.isShown) {
                        if (data.getItems().size()>0) {
                            itemsFamousWorldPlaces = data.getItems();
                            switchActivity(data.getItems().get(0));
                        } else {
                            Toast.makeText(getContext(), getContext().getResources().getString(R.string.toast_no_info_found), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        reverseList(itemsPrimarySports,subCategoryName);
                        Log.e("itemsPrimarySports>>>>", itemsPrimarySports.toString() + "");
                    }
                }else if ( subCategoryName.equalsIgnoreCase(SUB_CATEGORY_OLD_TIME_FAVOURITES)) {
                    if (!AllConstants.isShown) {
                        if (data.getItems().size()>0) {
                            itemsEntertainers = data.getItems();
                            switchActivity(data.getItems().get(0));
                        }else {
                            Toast.makeText(getContext(), getContext().getResources().getString(R.string.toast_no_info_found), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        reverseList(itemsEntertainers,subCategoryName);
                        Log.e("itemsPrimarySports>>>>", itemsEntertainers.toString() + "");
                    }
                }else if ( subCategoryName.equalsIgnoreCase(SUB_CATEGORY_HOLIDAY)) {
                    if (!AllConstants.isShown) {
                        if (data.getItems().size()>0) {
                            itemsFamousPlaces = data.getItems();
                            switchActivity(data.getItems().get(0));
                        }else {
                            Toast.makeText(getContext(), getContext().getResources().getString(R.string.toast_no_info_found), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        reverseList(itemsFamousPlaces,subCategoryName);
                        Log.e("itemsPrimarySports>>>>", itemsPrimarySports.toString() + "");
                    }
                }
                    Log.e("data>>>>",data.toString()+"");
            }
        });
    }

    private  void switchActivity(Items item){
        AllConstants.isShown = true;
        Intent iFacePlay = new Intent(getContext(), MusicGamePlayActivity.class);
        iFacePlay.putExtra(AllConstants.SUB_CATEGORY_NAME, subCategoryName);
        iFacePlay.putExtra(AllConstants.INTENT_KEY_ITEM, Parcels.wrap(item));

        getContext().startActivity(iFacePlay);
    }

    private  void reverseList( List<Items> itemsList ,String subCategoryName) {
        Items firstitem = null;
        if ( itemsList.size() == 0 ) {
            Toast.makeText(getContext(), getContext().getResources().getString(R.string.toast_no_info_found), Toast.LENGTH_SHORT).show();
            return;
        } else {
            firstitem = itemsList.get(0);
            itemsList.remove(0);
        }
       // reverseList( itemsList );
        AllConstants.isShown = false;
        Intent iFacePlay = new Intent(getContext(), MusicGamePlayActivity.class);
        if ( subCategoryName.equalsIgnoreCase(SUB_CATEGORY_FAMOUS_COMMERCIALS)) {
            itemsPrimarySports.add(firstitem);
            iFacePlay.putExtra(AllConstants.SUB_CATEGORY_NAME, subCategoryName);
            iFacePlay.putExtra(AllConstants.INTENT_KEY_ITEM, Parcels.wrap(itemsPrimarySports.get(0)));
        }else if ( subCategoryName.equalsIgnoreCase(SUB_CATEGORY_FAMOUS_ACRONYMES)) {
            itemsAllOtherSports.add(firstitem);
            iFacePlay.putExtra(AllConstants.SUB_CATEGORY_NAME, subCategoryName);
            iFacePlay.putExtra(AllConstants.INTENT_KEY_ITEM, Parcels.wrap(itemsAllOtherSports.get(0)));
        }else if ( subCategoryName.equalsIgnoreCase(SUB_CATEGORY_SOUNDS)) {
            itemsFamousUSPlaces.add(firstitem);
            iFacePlay.putExtra(AllConstants.SUB_CATEGORY_NAME, subCategoryName);
            iFacePlay.putExtra(AllConstants.INTENT_KEY_ITEM, Parcels.wrap(itemsFamousUSPlaces.get(0)));
        }else if ( subCategoryName.equalsIgnoreCase(SUB_CATEGORY_CHILDREN_SOUNDS)) {
            itemsFamousWorldPlaces.add(firstitem);
            iFacePlay.putExtra(AllConstants.SUB_CATEGORY_NAME, subCategoryName);
            iFacePlay.putExtra(AllConstants.INTENT_KEY_ITEM, Parcels.wrap(itemsFamousWorldPlaces.get(0)));
        }else if ( subCategoryName.equalsIgnoreCase(SUB_CATEGORY_OLD_TIME_FAVOURITES)) {
            itemsEntertainers.add(firstitem);
            iFacePlay.putExtra(AllConstants.SUB_CATEGORY_NAME, subCategoryName);
            iFacePlay.putExtra(AllConstants.INTENT_KEY_ITEM, Parcels.wrap(itemsEntertainers.get(0)));
        }else if ( subCategoryName.equalsIgnoreCase(SUB_CATEGORY_HOLIDAY)) {
            itemsFamousPlaces.add(firstitem);
            iFacePlay.putExtra(AllConstants.SUB_CATEGORY_NAME, subCategoryName);
            iFacePlay.putExtra(AllConstants.INTENT_KEY_ITEM, Parcels.wrap(itemsFamousPlaces.get(0)));
        }
        getContext().startActivity(iFacePlay);
    }
}