using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data;
using System.Linq;
using System.Threading.Tasks;
using System.Windows;
using web_shop_frontend.data.secure;
using web_shop_frontend.view;
using web_shop_frontend.view_model;

namespace web_shop_frontend
{
    /// <summary>
    /// Interaction logic for App.xaml
    /// </summary>
    public partial class App : Application
    {
        private SecureDataStorage _secureDataStorage;

        protected override void OnStartup(StartupEventArgs e)
        {
            _secureDataStorage = new SecureDataStorage();
            LoginView loginView = new LoginView(new LoginViewModel(_secureDataStorage), _secureDataStorage);
            loginView.WindowRequested += OnWindowRequested;
            MainWindow = loginView;
            MainWindow.Show();
            base.OnStartup(e);
        }
        
        private void OnWindowRequested(object? sender, Window window)
        {
            MainWindow = window;
            MainWindow.Show();
        }
    }
}