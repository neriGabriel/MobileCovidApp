package com.example.mobilecovidinfo.view;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import com.example.mobilecovidinfo.R;
import com.example.mobilecovidinfo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding activityMainBinding;
    ActionBar actionBar;

    private NavController navController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        this.activityMainBinding.getRoot();

        this.navController = Navigation.findNavController(this, R.id.navHost);
        NavigationUI.setupActionBarWithNavController(this, this.navController);

        this.actionBar = this.getSupportActionBar();
        this.actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorGreen)));
        this.getWindow().setStatusBarColor(getResources().getColor(R.color.colorGreen));
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(this.navController, (DrawerLayout) null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
}
