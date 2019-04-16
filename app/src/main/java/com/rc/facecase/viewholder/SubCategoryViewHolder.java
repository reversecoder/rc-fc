package com.rc.facecase.viewholder;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.rc.facecase.R;
import com.rc.facecase.activity.PictureGamePlayActivity;
import com.rc.facecase.model.Items;
import com.rc.facecase.model.SubCategory;
import com.rc.facecase.util.AllConstants;

import org.parceler.Parcels;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

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

    private Context context;
    private TextView tvSubcategory;
    private LinearLayout llSubcategory;
    private static List<Items> itemsPrimarySports = new ArrayList<>();
    private static List<Items> itemsAllOtherSports= new ArrayList<>();
    private static List<Items> itemsFamousUSPlaces= new ArrayList<>();
    private static List<Items> itemsFamousWorldPlaces= new ArrayList<>();
    private static List<Items> itemsEntertainers = new ArrayList<>();
    private static List<Items> itemsFamousPlaces = new ArrayList<>();
    private String subCategoryName = "";

    public SubCategoryViewHolder(ViewGroup parent) {
        super(parent, R.layout.row_sub_category_item);

        tvSubcategory = (TextView) $(R.id.tv_subcategory);
        llSubcategory = (LinearLayout) $(R.id.ll_subcategory);
    }

    @Override
    public void setData(final SubCategory data) {
        subCategoryName = data.getSub_category_name().trim();
        AllConstants.isShown = false;

        tvSubcategory.setText(subCategoryName);

//        if (subCategoryName.equalsIgnoreCase(SUB_CATEGORY_PRIMARY_SPORTS)){
//            ivCategory.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_primarysports));
//        } else if (subCategoryName.equalsIgnoreCase(SUB_CATEGORY_ALL_OTHER_SPORTS)){
//            ivCategory.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_allothersports));
//        } else if (subCategoryName.equalsIgnoreCase(SUB_CATEGORY_FAMOUS_PLACES)){
//            ivCategory.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_famoususplaces));
//        } else if (subCategoryName.equalsIgnoreCase(SUB_CATEGORY_FAMOUS_WORLD_PLACES)){
//            ivCategory.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_famousworldplaces));
//        } else if (subCategoryName.equalsIgnoreCase(SUB_CATEGORY_ENTERTAINERS)){
//            ivCategory.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_entertainers));
//        } else if (subCategoryName.equalsIgnoreCase(SUB_CATEGORY_FAMOUS_PEOPLE)){
//            ivCategory.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_famouspeople));
//        }

        Log.e("getSub_name>>>>",data.getSub_category_name()+"");
        Log.e("subCategory>>>>",data.toString()+"");


        llSubcategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (subCategoryName.equalsIgnoreCase(SUB_CATEGORY_PRIMARY_SPORTS)) {
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
                } else if (subCategoryName.equalsIgnoreCase(SUB_CATEGORY_ALL_OTHER_SPORTS)) {
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
                }else if (subCategoryName.equalsIgnoreCase(SUB_CATEGORY_FAMOUS_PLACES)) {
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
                }else if (subCategoryName.equalsIgnoreCase(SUB_CATEGORY_FAMOUS_WORLD_PLACES)) {
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
                }else if (subCategoryName.equalsIgnoreCase(SUB_CATEGORY_ENTERTAINERS)) {
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
                }else if (subCategoryName.equalsIgnoreCase(SUB_CATEGORY_FAMOUS_PEOPLE)) {
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
        Intent iFacePlay = new Intent(getContext(), PictureGamePlayActivity.class);
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
        Intent iFacePlay = new Intent(getContext(), PictureGamePlayActivity.class);

        if (subCategoryName.equalsIgnoreCase(SUB_CATEGORY_PRIMARY_SPORTS)) {
            itemsPrimarySports.add(firstitem);
            iFacePlay.putExtra(AllConstants.SUB_CATEGORY_NAME, subCategoryName);
            iFacePlay.putExtra(AllConstants.INTENT_KEY_ITEM, Parcels.wrap(itemsPrimarySports.get(0)));

        }else if (subCategoryName.equalsIgnoreCase(SUB_CATEGORY_ALL_OTHER_SPORTS)) {
            itemsAllOtherSports.add(firstitem);
            iFacePlay.putExtra(AllConstants.SUB_CATEGORY_NAME, subCategoryName);
            iFacePlay.putExtra(AllConstants.INTENT_KEY_ITEM, Parcels.wrap(itemsAllOtherSports.get(0)));

        }else if (subCategoryName.equalsIgnoreCase(SUB_CATEGORY_FAMOUS_PLACES)) {
            itemsFamousUSPlaces.add(firstitem);
            iFacePlay.putExtra(AllConstants.SUB_CATEGORY_NAME, subCategoryName);
            iFacePlay.putExtra(AllConstants.INTENT_KEY_ITEM, Parcels.wrap(itemsFamousUSPlaces.get(0)));

        }else if (subCategoryName.equalsIgnoreCase(SUB_CATEGORY_FAMOUS_WORLD_PLACES)) {
            itemsFamousWorldPlaces.add(firstitem);
            iFacePlay.putExtra(AllConstants.SUB_CATEGORY_NAME, subCategoryName);
            iFacePlay.putExtra(AllConstants.INTENT_KEY_ITEM, Parcels.wrap(itemsFamousWorldPlaces.get(0)));

        }else if (subCategoryName.equalsIgnoreCase(SUB_CATEGORY_ENTERTAINERS)) {
            itemsEntertainers.add(firstitem);
            iFacePlay.putExtra(AllConstants.SUB_CATEGORY_NAME, subCategoryName);
            iFacePlay.putExtra(AllConstants.INTENT_KEY_ITEM, Parcels.wrap(itemsEntertainers.get(0)));

        }else if (subCategoryName.equalsIgnoreCase(SUB_CATEGORY_FAMOUS_PEOPLE)) {
            itemsFamousPlaces.add(firstitem);
            iFacePlay.putExtra(AllConstants.SUB_CATEGORY_NAME, subCategoryName);
            iFacePlay.putExtra(AllConstants.INTENT_KEY_ITEM, Parcels.wrap(itemsFamousPlaces.get(0)));

        }
        getContext().startActivity(iFacePlay);
    }
}