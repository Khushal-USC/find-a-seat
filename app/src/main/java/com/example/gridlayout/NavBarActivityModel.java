package com.example.gridlayout;

import androidx.lifecycle.ViewModel;

public class NavBarActivityModel extends ViewModel {
    private int selectedItemPosition;

    public int getSelectedItemPosition() {
        return selectedItemPosition;
    }

    public void setSelectedItemPosition(int position) {
        selectedItemPosition = position;
    }
}

