package com.example.sabbir_2207102_cvbuilder.controllers;

import com.example.sabbir_2207102_cvbuilder.models.CVFormModel;

public class CVPreviewController {
    CVFormModel cvFormModel;
    public void setData(CVFormModel cvFormModel) {
        this.cvFormModel = cvFormModel;
    }
    public CVFormModel getCVFormInstance(){
        return cvFormModel;
    }
}