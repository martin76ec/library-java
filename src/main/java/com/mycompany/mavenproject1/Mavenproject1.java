/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mavenproject1;

import javax.swing.JFrame;
import ui.provider.UIProvider;

/**
 *
 * @author martin
 */
public class Mavenproject1 {

    public static void main(String[] args) {
        UIProvider uiProvider = new UIProvider();
        JFrame main = uiProvider.getMainWindow();
        main.show();
    } 
}
