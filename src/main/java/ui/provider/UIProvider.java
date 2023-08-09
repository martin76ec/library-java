/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui.provider;

import javax.swing.JFrame;
import ui.components.ClientWindow;
import ui.components.LibrarianWindow;
import ui.components.LoginWindow;

/**
 *
 * @author martin
 */
public class UIProvider {
    
    public JFrame getMainWindow() {
        LibrarianWindow librarianWindow = new LibrarianWindow();
        ClientWindow clientWindow = new ClientWindow();
        return new LoginWindow(librarianWindow, clientWindow);
    }
}
