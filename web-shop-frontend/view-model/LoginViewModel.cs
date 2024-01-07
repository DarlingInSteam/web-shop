using System;
using System.Windows.Input;
using web_shop_frontend.data.api_request;
using web_shop_frontend.data.secure;
using AuthenticationRequest = web_shop_frontend.model.entity.AuthenticationRequest;

namespace web_shop_frontend.view_model;

public class LoginViewModel : ViewModelBase
{
    private string _username;
    private string _password;
    private SecureDataStorage _secureDataStorage;
    
    public ICommand LogInCommand { get; }
    
    public LoginViewModel(SecureDataStorage secureDataStorage)
    {
        LogInCommand = new RelayCommand<object>(LogInExecute, LogInCanExecute);
        _secureDataStorage = secureDataStorage;
    }
    
    public string Username
    {
        get => _username;
        set
        {
            _username = value;
            OnPropertyChange(nameof(Username));
        }
    }

    public string Password
    {
        get => _password;
        set
        {
            _password = value;
            OnPropertyChange(nameof(Password));
        }
    }
    
    private async void LogInExecute(object parameter)
    {
        
        try
        {
            web_shop_frontend.model.entity.AuthenticationRequest request = new web_shop_frontend.model.entity.AuthenticationRequest(Username, Password);
            var req = new web_shop_frontend.data.api_request.AuthenticationRequest(_secureDataStorage);
            var response = await req.LoginUser(request);
            OpenMainWindowRequest();
            OnLoginSuccess?.Invoke();
        }
        catch (Exception e)
        {
            DialogText = "Не удалось авторизоваться. Обратитесь в поддержку."; 
            OnShowDialog(this);   
        }
    }
    
    private bool LogInCanExecute(object parameter)
    {
        return !String.IsNullOrEmpty(Username) && !String.IsNullOrEmpty(Password);
    }
    
    private void OpenMainWindow()
    {
        OpenMainWindowRequest?.Invoke();    
    }
    public Action OpenMainWindowRequest { get; set; }

    public Action OnLoginSuccess { get; set; }  
}