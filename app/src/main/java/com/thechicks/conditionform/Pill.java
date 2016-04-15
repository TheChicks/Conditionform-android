package com.thechicks.conditionform;

/**
 * Created by Dong on 2016-04-09.
 */
public class Pill {

    String koName;
    String enName;
    String image_url;

    String categoryWelfare;  //복지부 분류
    String classification; //구분
    String manufactureAssort;  //제조,수입 구분
    String assortment;  //제조사
    Integer insuranceCode;  //약품 보험 코드

    //외형정보
    String shapeInfoAppearance;  //성상
    String shapeInfoFormulation;  //제형
    String shapeInfoShape;  //모양
    String shapeInfoColor;  //색상
    String shapeInfoIdMark;  //식별 표기

    String ingredientInfo;  //성분정보

    String storagintMethod;  //저장방법

    String efficacy;  //효능효과

    String dosage;  //용법용량

    String precaution; //사용상 주의사항

    String pregnantRating;  //임산부금기등급

    String ageProhibit;  //연령금지

    String prescriptionNo;

    public Pill() {
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

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getCategoryWelfare() {
        return categoryWelfare;
    }

    public void setCategoryWelfare(String categoryWelfare) {
        this.categoryWelfare = categoryWelfare;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }
    public String getClassification() {
        return classification;
    }
    public String getManufactureAssort() {
        return manufactureAssort;
    }

    public void setManufactureAssort(String manufactureAssort) {
        this.manufactureAssort = manufactureAssort;
    }

    public String getAssortment() {
        return assortment;
    }

    public void setAssortment(String assortment) {
        this.assortment = assortment;
    }

    public Integer getInsuranceCode() {
        return insuranceCode;
    }

    public void setInsuranceCode(Integer insuranceCode) {
        this.insuranceCode = insuranceCode;
    }

    public String getShapeInfoAppearance() {
        return shapeInfoAppearance;
    }

    public void setShapeInfoAppearance(String shapeInfoAppearance) {
        this.shapeInfoAppearance = shapeInfoAppearance;
    }

    public String getShapeInfoFormulation() {
        return shapeInfoFormulation;
    }

    public void setShapeInfoFormulation(String shapeInfoFormulation) {
        this.shapeInfoFormulation = shapeInfoFormulation;
    }

    public String getShapeInfoShape() {
        return shapeInfoShape;
    }

    public void setShapeInfoShape(String shapeInfoShape) {
        this.shapeInfoShape = shapeInfoShape;
    }

    public String getShapeInfoColor() {
        return shapeInfoColor;
    }

    public void setShapeInfoColor(String shapeInfoColor) {
        this.shapeInfoColor = shapeInfoColor;
    }

    public String getShapeInfoIdMark() {
        return shapeInfoIdMark;
    }

    public void setShapeInfoIdMark(String shapeInfoIdMark) {
        this.shapeInfoIdMark = shapeInfoIdMark;
    }

    public String getIngredientInfo() {
        return ingredientInfo;
    }

    public void setIngredientInfo(String ingredientInfo) {
        this.ingredientInfo = ingredientInfo;
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

    public String getPregnantRating() {
        return pregnantRating;
    }

    public void setPregnantRating(String pregnantRating) {
        this.pregnantRating = pregnantRating;
    }

    public String getAgeProhibit() {
        return ageProhibit;
    }

    public void setAgeProhibit(String ageProhibit) {
        this.ageProhibit = ageProhibit;
    }

    public String getPrescriptionNo() {
        return prescriptionNo;
    }

    public void setPrescriptionNo(String prescriptionNo) {
        this.prescriptionNo = prescriptionNo;
    }
}
