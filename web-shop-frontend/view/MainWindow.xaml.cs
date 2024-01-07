using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;
using web_shop_frontend.data.secure;
using web_shop_frontend.view_model;

namespace web_shop_frontend
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        private SecureDataStorage _secureDataStorage;
        public event EventHandler<Window> WindowRequested;
        
        public MainWindow(SecureDataStorage secureDataStorage)
        {
            _secureDataStorage = secureDataStorage;
            MainWindowViewModel vm = new MainWindowViewModel(_secureDataStorage);
            DataContext = vm;
            InitializeComponent();
        }
    }
}