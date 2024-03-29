﻿using System;
using System.ComponentModel;
using System.Windows.Input;

namespace web_shop_frontend.view_model;

public class ViewModelBase : INotifyPropertyChanged
{
    private bool _isDialogOpen;
    private string _dialogText;
    public event PropertyChangedEventHandler PropertyChanged;

    public event EventHandler<ViewModelBase> ViewModelRequested;

    public ViewModelBase()
    {
        ShowDialogCommand = new RelayCommand<object>(OnShowDialog);
    }
    
    public string DialogText
    {
        get => _dialogText;
        set
        {
            _dialogText = value;
            OnPropertyChange(nameof(DialogText));
        }
    }

    public ICommand ShowDialogCommand { get; }

    public bool IsDialogOpen
    {
        get => _isDialogOpen;
        set
        {
            _isDialogOpen = value;
            OnPropertyChange(nameof(IsDialogOpen));
        }
    }

    protected virtual void OnPropertyChange(string propertyName = null)
    {
        PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(propertyName));
    }

    protected void RaiseViewModelRequested(ViewModelBase viewModel)
    {
        ViewModelRequested?.Invoke(this, viewModel);
    }

    protected virtual void Dispose()
    {
    }

    protected void OnShowDialog(object param)
    {
        IsDialogOpen = true;
    }
}