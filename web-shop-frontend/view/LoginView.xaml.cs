using System;
using System.Windows;
using System.Windows.Controls;
using web_shop_frontend.data.secure;
using web_shop_frontend.view_model;

namespace web_shop_frontend.view;

public partial class LoginView : Window
{
    private SecureDataStorage _secureDataStorage;
    public event EventHandler<Window> WindowRequested;

    
    public LoginView()
    {
        InitializeComponent();
    }

    public LoginView(LoginViewModel loginViewModel, SecureDataStorage secureDataStorage)
    {
        _secureDataStorage = secureDataStorage;
        LoginViewModel vm = loginViewModel;
        vm.OpenMainWindowRequest += OpenMainWindowExecute;
        this.DataContext = loginViewModel;
        InitializeComponent();
    }
    
    private void PasswordBox_OnPasswordChanged(object sender, RoutedEventArgs e)
    {
        if (this.DataContext != null)
        {
            ((dynamic)this.DataContext).Password = ((PasswordBox)sender).Password; 
        }
    }
    
    private void OpenMainWindowExecute()
    {
        WindowRequested?.Invoke(this, new MainWindow(_secureDataStorage));
        Close();
    }
}