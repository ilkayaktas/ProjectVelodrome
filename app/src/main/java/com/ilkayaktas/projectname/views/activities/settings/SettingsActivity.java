package com.ilkayaktas.projectname.views.activities.settings;

import android.os.Bundle;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.ilkayaktas.projectname.R;
import com.ilkayaktas.projectname.views.activities.base.BaseActivity;

/**
 * Created by ilkayaktas on 28.02.2018 at 13:17.
 */

public class SettingsActivity extends BaseActivity {

    @BindView(R.id.toolbar_title) TextView toolbarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setUnBinder(ButterKnife.bind(this));

        initUI();

        // Display the fragment as the main content.
        getFragmentManager().beginTransaction()
                .replace(R.id.cl_settings_content, new SettingsFragment())
                .commit();

    }

    @Override
    protected int getActivityLayout() {
        return R.layout.activity_settings;
    }

    @Override
    protected void initUI() {
        setFont();
    }

    private void setFont(){
        toolbarTitle.setTypeface(fontGothic);
    }
}
