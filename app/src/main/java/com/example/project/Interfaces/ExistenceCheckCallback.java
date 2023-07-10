package com.example.project.Interfaces;

import com.example.project.Models.Survivor;

public interface ExistenceCheckCallback {
    void onExistenceChecked(boolean exists, Survivor survivor);
}
