package com.gtv.hanhee.testlayout.ui.adapter;

import android.app.Activity;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gtv.hanhee.testlayout.R;
import com.gtv.hanhee.testlayout.base.OnItemRvClickListener;
import com.gtv.hanhee.testlayout.model.GroupDetails;
import com.gtv.hanhee.testlayout.model.User;
import com.gtv.hanhee.testlayout.utils.ImageUtils;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileUserJoinGroupAdapter extends BaseQuickAdapter<GroupDetails, BaseViewHolder> {

    public List<GroupDetails> list;
    private Activity activity;
    private OnItemRvClickListener onItemRvClickListener;


    public ProfileUserJoinGroupAdapter(Activity activity, List<GroupDetails> data, OnItemRvClickListener onItemRvClickListener) {
        super(R.layout.item_join_group_user, data);
        this. onItemRvClickListener = onItemRvClickListener;
        this.list = data;
        this.activity = activity;
    }

    CircleImageView imgGroup;
    @Override
    protected void convert(BaseViewHolder holder, GroupDetails item) {
        imgGroup = holder.getView(R.id.imgGroup);
        ImageUtils.loadCircleImageByGlide(activity, item.getImage(), imgGroup);
        imgGroup.setOnClickListener(v -> onItemRvClickListener.onItemRvClick(v, item, holder.getAdapterPosition()));
    }
}
