package com.thechicks.conditionform.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by opklnm102 on 2016-05-02.
 */
public class Pill implements Serializable {

    private int id;

    @SerializedName("ko_name")
    private String koName;  // 한글약이름

    @SerializedName("en_name")
    private String enName;  // 영문약이름

    @SerializedName("image_url")
    private String imageUrl;  // 이미지URL

    @SerializedName("ingredient")
    private String ingredient;  // 성분명

    @SerializedName("assortment")
    private String assortment;  //전문/일반 //구분

    @SerializedName("unitariness_or_complexness")
    private String unitarinessOrComplexness;  // 단일/복합

    @SerializedName("manufacture_assortment")
    private String manufactureAssortment;  // 제조/수입사

    @SerializedName("seller")
    private String seller;  //판매사

    @SerializedName("formulation")
    private String formulation;  //제형

    @SerializedName("taking_route")
    private String takingRoute;  // 투여경로

    @SerializedName("korea_food_and_drug_administration_category")
    private String koreaFoodAndDrugAdministrationCategory;  // 식약처 분류

    @SerializedName("insurance_code")
    private String insuranceCode;  //보험코드


    //의약품 안전성 정보(DUR)
    @SerializedName("combination_taboo")
    private String combinationTaboo; //병용금기

    @SerializedName("age_taboo")
    private String ageTaboo;  //연령금기

    @SerializedName("pregnant_taboo")
    private String pregnantTaboo;  //임부금기

    @SerializedName("old_man_caution")
    private String oldManCaution;  //노인주의

    @SerializedName("volume_and_treatment_period_caution")
    private String volumeAndTreatmentPeriodCaution;  //용량/투여기간주의

    @SerializedName("division_caution")
    private String divisionCaution;  //분할주의

    @SerializedName("blood_donation_prohibition")
    private String bloodDonationProhibition;  //헌혈금지


    @SerializedName("shape_info")
    private String shapeInfo;  //성상

    @SerializedName("packing_unit")
    private String packingUnit;  //포장단위

    @SerializedName("storagint_method")
    private String storagintMethod;  //저장방법

    @SerializedName("efficacy")
    private String efficacy;  //효능효과

    @SerializedName("dosage")
    private String dosage;  //용법용량

    @SerializedName("precaution")
    private String precaution;  //사용상 주의사항

    @SerializedName("medication_guide")
    private String medicationGuide;  //복약지도

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKoName() {
        return koName;
    }

    public void setKoName(String koName) {
        this.koName = koName;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getAssortment() {
        return assortment;
    }

    public void setAssortment(String assortment) {
        this.assortment = assortment;
    }

    public String getUnitarinessOrComplexness() {
        return unitarinessOrComplexness;
    }

    public void setUnitarinessOrComplexness(String unitarinessOrComplexness) {
        this.unitarinessOrComplexness = unitarinessOrComplexness;
    }

    public String getManufactureAssortment() {
        return manufactureAssortment;
    }

    public void setManufactureAssortment(String manufactureAssortment) {
        this.manufactureAssortment = manufactureAssortment;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getFormulation() {
        return formulation;
    }

    public void setFormulation(String formulation) {
        this.formulation = formulation;
    }

    public String getTakingRoute() {
        return takingRoute;
    }

    public void setTakingRoute(String takingRoute) {
        this.takingRoute = takingRoute;
    }

    public String getKoreaFoodAndDrugAdministrationCategory() {
        return koreaFoodAndDrugAdministrationCategory;
    }

    public void setKoreaFoodAndDrugAdministrationCategory(String koreaFoodAndDrugAdministrationCategory) {
        this.koreaFoodAndDrugAdministrationCategory = koreaFoodAndDrugAdministrationCategory;
    }

    public String getInsuranceCode() {
        return insuranceCode;
    }

    public void setInsuranceCode(String insuranceCode) {
        this.insuranceCode = insuranceCode;
    }

    public String getCombinationTaboo() {
        return combinationTaboo;
    }

    public void setCombinationTaboo(String combinationTaboo) {
        this.combinationTaboo = combinationTaboo;
    }

    public String getAgeTaboo() {
        return ageTaboo;
    }

    public void setAgeTaboo(String ageTaboo) {
        this.ageTaboo = ageTaboo;
    }

    public String getPregnantTaboo() {
        return pregnantTaboo;
    }

    public void setPregnantTaboo(String pregnantTaboo) {
        this.pregnantTaboo = pregnantTaboo;
    }

    public String getOldManCaution() {
        return oldManCaution;
    }

    public void setOldManCaution(String oldManCaution) {
        this.oldManCaution = oldManCaution;
    }

    public String getVolumeAndTreatmentPeriodCaution() {
        return volumeAndTreatmentPeriodCaution;
    }

    public void setVolumeAndTreatmentPeriodCaution(String volumeAndTreatmentPeriodCaution) {
        this.volumeAndTreatmentPeriodCaution = volumeAndTreatmentPeriodCaution;
    }

    public String getDivisionCaution() {
        return divisionCaution;
    }

    public void setDivisionCaution(String divisionCaution) {
        this.divisionCaution = divisionCaution;
    }

    public String getBloodDonationProhibition() {
        return bloodDonationProhibition;
    }

    public void setBloodDonationProhibition(String bloodDonationProhibition) {
        this.bloodDonationProhibition = bloodDonationProhibition;
    }

    public String getShapeInfo() {
        return shapeInfo;
    }

    public void setShapeInfo(String shapeInfo) {
        this.shapeInfo = shapeInfo;
    }

    public String getPackingUnit() {
        return packingUnit;
    }

    public void setPackingUnit(String packingUnit) {
        this.packingUnit = packingUnit;
    }

    public String getStoragintMethod() {
        return storagintMethod;
    }

    public void setStoragintMethod(String storagintMethod) {
        this.storagintMethod = storagintMethod;
    }

    public String getEfficacy() {
        return efficacy;
    }

    public void setEfficacy(String efficacy) {
        this.efficacy = efficacy;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getPrecaution() {
        return precaution;
    }

    public void setPrecaution(String precaution) {
        this.precaution = precaution;
    }

    public String getMedicationGuide() {
        return medicationGuide;
    }

    public void setMedicationGuide(String medicationGuide) {
        this.medicationGuide = medicationGuide;
    }
}
