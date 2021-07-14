package com.example.assignment2.utils;

import com.example.assignment2.R;

public class IconPicker {

    private static int[] icons ={R.drawable.ic_bag_icon,R.drawable.ic_calculator_icon,R.drawable.ic_graduation_icon,R.drawable.ic_letter_icon,R.drawable.ic_line_read_icon,R.drawable.ic_presentation_icon,R.drawable.ic_pencil_icon};
    private static int currentIcon = 0;

    public static int  getIcon() {
        currentIcon = (currentIcon + 1) % icons.length;
        return icons[currentIcon];
    }
}
