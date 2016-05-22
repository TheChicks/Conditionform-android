package com.thechicks.conditionform.util;

import android.provider.BaseColumns;

import com.thechicks.conditionform.BuildConfig;

/**
 * Created by opklnm102 on 2016-04-29.
 */
public class Constants {

    // geneymotion localhost
    public static final String ENDPOINT = BuildConfig.LOCAL_HOST_IP;

    // api server
//    public static final String ENDPOINT = BuildConfig.API_SERVER_IP;

    //SharedPreference
    public static final String PREF_NAME = "conditionform_settings";
    public static final String PREF_VIBRATE = "pref_vibrate";
    public static final String PREF_SOUND = "perf_sound";
    public static final String PREF_TIME_WAKEUP = "perf_time_wakeup";
    public static final String PREF_TIME_MORNING = "perf_time_morning";
    public static final String PREF_TIME_LUNCH = "perf_time_lunch";
    public static final String PREF_TIME_EVENING = "perf_time_evening";
    public static final String PREF_TIME_SLEEP = "perf_time_sleep";

    // dosage type
    public static final int DOSAGE_TYPE_EVERYDAY = 5000;  //매일
    public static final int DOSAGE_TYPE_TWODAY = 5001;  //2일
    public static final int DOSAGE_TYPE_THREEDAY = 5002;  //3일
    public static final int DOSAGE_TYPE_EVERYHOUR = 5003;  //시간마다


    //SQLite
    //병
    public static final class DiseaseEntray implements BaseColumns {

        public static final String TABLE_NAME = "disease";

        public static final String COLUMN_DISEASE_NAME = "disease_name";  //TEXT, 이름
        public static final String COLUMN_DISEASE_IMAGE = "disease_image";  //INTEGER, 이미지ID
        public static final String COLUMN_DISEASE_LABEL_COLOR = "disease_label_color";  //TEXT, 레이블 색
        public static final String COLUMN_DISEASE_DATE_START = "disease_date_start";  //NUMERIC(datetime), 시작 날짜
        public static final String COLUMN_DISEASE_DATE_END = "disease_date_end";  //NUMERIC(datetime), 끝 날짜
        public static final String COLUMN_DISEASE_DOSAGE_TYPE = "disease_dosage_type";  //INTEGER, 복용 타입
        public static final String COLUMN_DISEASE_DOSAGE_ONE_TIME = "disease_dosage_one_time";  //INTEGER, 1회 투여량
        public static final String COLUMN_DISEASE_DOSAGE_TOTAL = "disease_dosage_total";  //INTEGER, 총 투여 횟수
        public static final String COLUMN_DISEASE_DOSAGE_TOTAL_DAYS = "disease_dosage_total_days";  //INTEGER, 투약 일수

        public static final String COLUMN_DISEASE_ENABLED_WAKEUP = "disease_enabled_wakeup";  //NUMERIC(boolean), 일어나서 활성여부
        public static final String COLUMN_DISEASE_ENABLED_MORNING = "disease_enabled_morning";  //NUMERIC(boolean), 아침 활성여부
        public static final String COLUMN_DISEASE_ENABLED_LUNCH = "disease_enabled_lunch";  //NUMERIC(boolean), 점심 활성여부
        public static final String COLUMN_DISEASE_ENABLED_EVENING = "disease_enabled_evening";  //NUMERIC(boolean), 저녁 활성여부
        public static final String COLUMN_DISEASE_ENABLED_SLEEP = "disease_enabled_sleep";  //NUMERIC(boolean), 잠자기전 활성여부

        public static final String COLUMN_DISEASE_TIME_START_HOUR = "disease_time_start_hour";  //INTEGER, 울릴 시작 시간(시)
        public static final String COLUMN_DISEASE_TIME_START_MINUTE = "disease_time_start_minute";  //INTEGER, 울릴 시작 시간(분)
        public static final String COLUMN_DISEASE_TIME_INTERVAL = "disease_time_interval";  //INTEGER, 울릴 시간 간격

//        public static final String COLUMN_DISEASE_FK_HISTORY_ID = "disease_fk_history_id";  //외래키, 히스토리
//        public static final String COLUMN_DISEASE_FK_ALARM_ID = "disease_fk_alarm_id";  //외래키, 알람
//        public static final String COLUMN_DISEASE_FK_PILL_ID = "disease_fk_pill_id";  //외래키, 약

        public static final String COLUMN_DISEASE_CREATEDAT = "disease_createdAt";  //NUMERIC(datetime), 컬럼 생성날짜
        public static final String COLUMN_DISEASE_UPDATEAT = "disease_updateAt";  //NUMERIC(datetime), 컬럼 업데이트 날짜

        public static final String[] PROJECTION_ALL = {_ID, COLUMN_DISEASE_NAME, COLUMN_DISEASE_IMAGE,
                COLUMN_DISEASE_LABEL_COLOR, COLUMN_DISEASE_DATE_START, COLUMN_DISEASE_DATE_END,
                COLUMN_DISEASE_DOSAGE_TYPE, COLUMN_DISEASE_DOSAGE_ONE_TIME, COLUMN_DISEASE_DOSAGE_TOTAL,
                COLUMN_DISEASE_DOSAGE_TOTAL_DAYS, COLUMN_DISEASE_ENABLED_WAKEUP, COLUMN_DISEASE_ENABLED_MORNING,
                COLUMN_DISEASE_ENABLED_LUNCH, COLUMN_DISEASE_ENABLED_EVENING, COLUMN_DISEASE_ENABLED_SLEEP,
                COLUMN_DISEASE_TIME_START_HOUR, COLUMN_DISEASE_TIME_START_MINUTE, COLUMN_DISEASE_TIME_INTERVAL,
                COLUMN_DISEASE_CREATEDAT, COLUMN_DISEASE_UPDATEAT};

        public static final String SORT_ORDER_DATE_START_DESC = COLUMN_DISEASE_DATE_START + " DESC";
    }

    //히스토리
    public static final class HistoryEntray implements BaseColumns {

        public static final String TABLE_NAME = "history";

        public static final String COLUMN_HISTORY_DATE = "history_date";  //NUMERIC(datetime), 날짜

        //매일, 2일, 3일마다
        public static final String COLUMN_HISTORY_TAKE_WAKEUP = "history_take_wakeup";  //NUMERIC(boolean), 일어나서 먹었나
        public static final String COLUMN_HISTORY_TAKE_MORNING = "history_take_morning";  //NUMERIC(boolean), 아침에 먹었나
        public static final String COLUMN_HISTORY_TAKE_LUNCH = "history_take_lunch";  //NUMERIC(boolean), 점심에 먹었나
        public static final String COLUMN_HISTORY_TAKE_EVENING = "history_take_evening";  //NUMERIC(boolean), 저녁에 먹었나
        public static final String COLUMN_HISTORY_TAKE_SLEEP = "history_take_sleep";  //NUMERIC(boolean), 잠자기전에 먹었나

        //시간마다
        public static final String COLUMN_HISTORY_TAKE_CURRENT = "history_take_current";  //INTEGER, 현재 얼마나 먹었나.

        public static final String COLUMN_HISTORY_FK_DISEASE_ID = "history_fk_disease_id";  //외래키, 병

        public static final String COLUMN_HISTORY_CREATEDAT = "history_createdAt";  //NUMERIC(datetime), 컬럼 생성날짜
        public static final String COLUMN_HISTORY_UPDATEAT = "history_updateAt";  //NUMERIC(datetime), 컬럼 업데이트 날짜

        public static final String[] PROJECTION_ALL = {_ID, COLUMN_HISTORY_DATE, COLUMN_HISTORY_TAKE_WAKEUP,
                COLUMN_HISTORY_TAKE_MORNING, COLUMN_HISTORY_TAKE_LUNCH, COLUMN_HISTORY_TAKE_EVENING,
                COLUMN_HISTORY_TAKE_SLEEP, COLUMN_HISTORY_TAKE_CURRENT, COLUMN_HISTORY_CREATEDAT,
                COLUMN_HISTORY_UPDATEAT};
    }

    //알람
    public static final class AlarmEntray implements BaseColumns {

        public static final String TABLE_NAME = "alarm";

        public static final String COLUMN_ALARM_ENABLED = "alarm_enabled";  //NUMERIC(boolean), 알람 활성 여부
        //        public static final String COLUMN_ALARM_VIBRATE = "alarm_vibrate";  //NUMERIC(boolean), 진동여부(필요없을듯)
//        public static final String COLUMN_ALARM_RINGTONE = "alarm_ringtone";  //NUMERIC(boolean), 소리여부(필요없을듯)
        public static final String COLUMN_ALARM_RINGTONE_PATH = "alarm_ringtone_path";  //TEXT, 소리 PATH
        public static final String COLUMN_ALARM_TIME = "alarm_time";  //NUMERIC(datetime), 울릴 시간
        public static final String COLUMN_ALARM_DATE = "alarm_date";  //NUMERIC(datetime), 울릴 날짜

        public static final String COLUMN_ALARM_FK_DISEASE_ID = "alarm_fk_disease_id";  //외래키, 병

        public static final String COLUMN_ALARM_CREATEDAT = "alarm_createdAt";  //NUMERIC(datetime),컬럼 생성날짜
        public static final String COLUMN_ALARM_UPDATEAT = "alarm_updateAt";  //NUMERIC(datetime),컬럼 업데이트 날짜

        public static final String[] PROJECTION_ALL = {_ID, COLUMN_ALARM_ENABLED, COLUMN_ALARM_RINGTONE_PATH,
                COLUMN_ALARM_TIME, COLUMN_ALARM_DATE, COLUMN_ALARM_CREATEDAT, COLUMN_ALARM_UPDATEAT};

    }

    //약
    public static final class PillEntray implements BaseColumns {

        public static final String TABLE_NAME = "pill";

        public static final String COLUMN_PILL_NAME_KOREA = "pill_name_korea";  //TEXT, 이름(한)
        public static final String COLUMN_PILL_NAME_ENGLISH = "pill_name_english";  //TEXT, 이름(영)
        public static final String COLUMN_PILL_IMAGE_URL = "pill_image_url";  //TEXT, 이미지URL
        public static final String COLUMN_PILL_INGREDIENT = "pill_ingredient";  //TEXT, 성분명
        public static final String COLUMN_PILL_ASSORTMENT = "pill_assortment";  //TEXT, 전문/일반 //구분
        public static final String COLUMN_PILL_UNITARINESS_OR_COMPLEXNESS = "pill_unitariness_or_complexness";  //TEXT, 단일/복합
        public static final String COLUMN_PILL_MANUFACTURE_ASSORTMENT = "pill_manufacture_assortment";  //TEXT, 제조/수입사
        public static final String COLUMN_PILL_SELLER = "pill_seller";  //TEXT, 판매사
        public static final String COLUMN_PILL_FORMULATION = "pill_formulation";  //TEXT, 제형
        public static final String COLUMN_PILL_TAKING_ROUTE = "pill_taking_route";  //TEXT, 투여경로
        public static final String COLUMN_PILL_KOREA_FOOD_AND_DRUG_ADMINISTRATION_CATEGORY = "pill_korea_food_and_drug_administration_category";  //TEXT, 식약처 분류
        public static final String COLUMN_PILL_INSURANCE_CODE = "pill_insurance_code";  //TEXT, 보험코드

        //의약품 안전성 정보(DUR)
        public static final String COLUMN_PILL_TABOO_COMBINATION = "pill_taboo_combination";  //TEXT, 병용금기
        public static final String COLUMN_PILL_TABOO_AGE = "pill_taboo_age";  //TEXT, 연령금기
        public static final String COLUMN_PILL_TABOO_PREGNANT = "pill_taboo_pregnant";  //TEXT, 임부금기
        public static final String COLUMN_PILL_CAUTION_OLD_MAN = "pill_caution_old_man";  //TEXT, 노인주의
        public static final String COLUMN_PILL_CAUTION_VOLUME_AND_TREATMENT_PERIOD = "pill_caution_volume_and_treatment_period";  //TEXT, 용량/투여기간주의
        public static final String COLUMN_PILL_CAUTION_DIVISION = "pill_caution_division";  //TEXT, 분할주의
        public static final String COLUMN_PILL_PROHIBITION_BLOOD_DONATION = "pill_prohibition_blood_donation";  //TEXT, 헌혈금지
        public static final String COLUMN_PILL_SHAPE_INFO = "pill_shape_info";  //TEXT, 성상
        public static final String COLUMN_PILL_PACKING_UNIT = "pill_packing_unit";  //TEXT, 포장단위
        public static final String COLUMN_PILL_STORAGINT_METHOD = "pill_storagint_method";  //TEXT, 저장방법
        public static final String COLUMN_PILL_EFFICACY = "pill_efficacy";  //TEXT, 효능효과
        public static final String COLUMN_PILL_DOSAGE = "pill_dosage";  //TEXT, 용법용량
        public static final String COLUMN_PILL_PRECAUTION = "pill_precaution";  //TEXT, 사용상 주의사항
        public static final String COLUMN_PILL_MEDICATION_GUIDE = "pill_medication_guide";  //TEXT, 복약지도

        public static final String COLUMN_PILL_FK_DISEASE_ID = "pill_fk_disease_id";  //외래키, 병

        public static final String COLUMN_Pill_CREATEDAT = "pill_createdAt";  //NUMERIC(datetime), 컬럼 생성날짜
        public static final String COLUMN_Pill_UPDATEAT = "pill_updateAt";  //NUMERIC(datetime), 컬럼 업데이트 날짜

        public static final String[] PROJECTION_ALL = {_ID, COLUMN_PILL_NAME_KOREA, COLUMN_PILL_NAME_ENGLISH,
                COLUMN_PILL_IMAGE_URL, COLUMN_PILL_INGREDIENT, COLUMN_PILL_ASSORTMENT,
                COLUMN_PILL_UNITARINESS_OR_COMPLEXNESS, COLUMN_PILL_MANUFACTURE_ASSORTMENT, COLUMN_PILL_SELLER,
                COLUMN_PILL_FORMULATION, COLUMN_PILL_TAKING_ROUTE, COLUMN_PILL_KOREA_FOOD_AND_DRUG_ADMINISTRATION_CATEGORY,
                COLUMN_PILL_INSURANCE_CODE, COLUMN_PILL_TABOO_COMBINATION, COLUMN_PILL_TABOO_AGE,
                COLUMN_PILL_TABOO_PREGNANT, COLUMN_PILL_CAUTION_OLD_MAN, COLUMN_PILL_CAUTION_VOLUME_AND_TREATMENT_PERIOD,
                COLUMN_PILL_CAUTION_DIVISION, COLUMN_PILL_PROHIBITION_BLOOD_DONATION, COLUMN_PILL_SHAPE_INFO,
                COLUMN_PILL_PACKING_UNIT, COLUMN_PILL_STORAGINT_METHOD, COLUMN_PILL_EFFICACY,
                COLUMN_PILL_DOSAGE, COLUMN_PILL_PRECAUTION, COLUMN_PILL_MEDICATION_GUIDE,
                COLUMN_Pill_CREATEDAT, COLUMN_Pill_UPDATEAT};
    }

    //Disease와 Pill의 관계를 나타내는 테이블
    public static final class PrescriptionEntray implements BaseColumns {

        public static final String TABLE_NAME = "prescription";

        public static final String COLUMN_PRESCRIPTION_DISEASE_ID = "prescription_disease_id";  //INTEGER, disease ID
        public static final String COLUMN_PRESCRIPTION_PILL_ID = "prescription_pill_id";  //INTEGER, pill ID

        public static final String COLUMN_PRESCRIPTION_CREATEDAT = "prescription_createdAt";  //NUMERIC(datetime), 컬럼 생성날짜
        public static final String COLUMN_PRESCRIPTION_UPDATEAT = "prescription_updateAt";  //NUMERIC(datetime), 컬럼 업데이트 날짜

        public static final String[] PROJECTION_ALL = {_ID, COLUMN_PRESCRIPTION_DISEASE_ID,
                COLUMN_PRESCRIPTION_PILL_ID, COLUMN_PRESCRIPTION_CREATEDAT, COLUMN_PRESCRIPTION_UPDATEAT};
    }
}
