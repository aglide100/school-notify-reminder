package com.example.myapplication.CalendarAPI;

import android.Manifest;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.example.myapplication.MyApplication;
import com.example.myapplication.CalendarAPI.Exceptions.CalendarCantNotUseException;
import com.example.myapplication.CalendarAPI.Exceptions.CalendarNeedUpdateGoogleServiceException;
import com.example.myapplication.CalendarAPI.Exceptions.CalendarNetworkException;
import com.example.myapplication.CalendarAPI.Exceptions.CalendarNotYetFinishBringDataException;
import com.example.myapplication.CalendarAPI.Interfaces.CalenderResultInterface;
import com.example.myapplication.CalendarAPI.Models.CalendarActivityRequestCode;
import com.example.myapplication.CalendarAPI.Models.CalendarInputEvent;
import com.example.myapplication.CalendarAPI.Models.CalendarRequestCode;
import com.example.myapplication.CalendarAPI.Utils.CalendarDataUtil;
import com.example.myapplication.CalendarAPI.Utils.CalendarNetworkUtil;

import com.example.myapplication.R;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.util.ExponentialBackOff;
import com.google.api.services.calendar.CalendarScopes;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@SuppressWarnings("unused")
public class CalendarAPI {
    @SuppressLint("StaticFieldLeak")
    private static CalendarAPI instance;
    public static CalendarAPI getInstance(){
        if (instance == null) {
            instance = new CalendarAPI();
        }
        return instance;
    }

    private CalendarRequestTask task;
    private CalenderResultInterface saveResultInterface;
    private CalendarRequestCode saveRequestCode;
    private Activity saveActivity;
    private Object[] saveParams;

    public void setInterface(CalendarRequestCode requestCode, Activity activity, CalenderResultInterface calenderResultInterface, Object[] objects) {
        this.saveRequestCode = requestCode;
        this.saveActivity = activity;
        this.saveResultInterface = calenderResultInterface;
        this.saveParams = objects;
    }

    public void setEmptyInterface() {
        setInterface(null, null, null, null);
    }

    public boolean isRequestCode(int code) {
        return CalendarActivityRequestCode.getRequestCode(code) != null;
    }

    public void progressRequest(int code, int result, Intent intent) {
        if (isRequestCode(code)) {
            CalendarActivityRequestCode requestCode = CalendarActivityRequestCode.getRequestCode(code);
            switch (Objects.requireNonNull(requestCode)) {
                case REQUEST_ACCOUNT_PICKER:
                    if (result == Activity.RESULT_OK && intent != null && intent.getExtras() != null) {
                        String accountName = intent.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
                        if (accountName != null) {
                            CalendarDataUtil.setSaveAccountName(accountName);

                            GoogleAccountCredential credential = getAccountInfo();
                            credential.setSelectedAccountName(accountName);

                            if (saveResultInterface != null && saveActivity != null) {
                                try {
                                    requestCalendar(saveActivity, saveRequestCode, credential, saveResultInterface, saveParams);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                    break;
                case REQUEST_AUTHORIZATION:
                    if (result == Activity.RESULT_OK) {
                        try {
                            requestCalendar(saveActivity, saveRequestCode, getAccountInfo(), saveResultInterface, saveParams);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        saveResultInterface.failedWithActivityResult(requestCode);
                    }

                    break;
            }
        }
    }

    public GoogleAccountCredential getAccountInfo() {
        Context ctx = MyApplication.ApplicationContext();
        return GoogleAccountCredential.usingOAuth2(ctx,Collections.singletonList(CalendarScopes.CALENDAR)).setBackOff(new ExponentialBackOff());
    }

    public void chooseAccount() {
        TedPermission.with(saveActivity)
                .setPermissions(Manifest.permission.GET_ACCOUNTS)
                .setPermissionListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                        GoogleAccountCredential credential = getAccountInfo();
                        if (credential.getSelectedAccount() == null) {
                            saveActivity.startActivityForResult(credential.newChooseAccountIntent(), CalendarActivityRequestCode.REQUEST_ACCOUNT_PICKER.getCode());
                        } else {
                            try {
                                requestCalendar(saveActivity, saveRequestCode, credential, saveResultInterface);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onPermissionDenied(List<String> deniedPermissions) {
                        saveResultInterface.permissionRevoked();
                    }
                }).check();
    }

    private void requestCalendar(Activity activity, CalendarRequestCode requestCode, CalenderResultInterface resultInterface, Object... params) throws CalendarNeedUpdateGoogleServiceException, CalendarCantNotUseException, CalendarNetworkException, CalendarNotYetFinishBringDataException {
        requestCalendar(activity, requestCode, getAccountInfo(), resultInterface, params);
    }

    private void requestCalendar(Activity activity, CalendarRequestCode requestCode, GoogleAccountCredential mCredential, CalenderResultInterface resultInterface, Object... params) throws CalendarNeedUpdateGoogleServiceException, CalendarCantNotUseException, CalendarNetworkException, CalendarNotYetFinishBringDataException {
        requestCalendar(activity, requestCode.getCode(), mCredential, resultInterface, params);
    }

    private void requestCalendar(Activity activity, String requestCode, GoogleAccountCredential mCredential, CalenderResultInterface resultInterface, Object... params) throws CalendarNeedUpdateGoogleServiceException, CalendarCantNotUseException, CalendarNetworkException, CalendarNotYetFinishBringDataException {
        CalendarRequestCode calendarRequestCode = CalendarRequestCode.getRequestCode(requestCode);
        if (calendarRequestCode == null) {
            calendarRequestCode = CalendarRequestCode.REQUEST_GET_CALENDAR_ID;
        }
        if (!CalendarNetworkUtil.isNetworkAvailable()) {
            // 네트워크 처리 안될때
            throw new CalendarNetworkException();
        } else if (!CalendarNetworkUtil.isGooglePlayServicesAvailable()) {
            if (CalendarNetworkUtil.acquireGooglePlayServices()) {
                // 구글 서비스 업데이트 해야될때
                throw new CalendarNeedUpdateGoogleServiceException();
            } else {
                // 구글 서비스를 아예 못쓸때
                throw new CalendarCantNotUseException();
            }
        } else if (mCredential == null) {
            throw new CalendarNetworkException();
        } else if (mCredential.getSelectedAccount() == null) {
            String savedName = CalendarDataUtil.getSaveAccountName();
            if (savedName != null) {
                mCredential.setSelectedAccountName(savedName);
            }

            if (mCredential.getSelectedAccountName() == null) {
                // 구글 로그인 아직도 안되어 있을때
                CalendarDataUtil.setSaveAccountName(null);
                setInterface(calendarRequestCode, activity, resultInterface, params);
                chooseAccount();
                return;
            }
        } else if (task != null && task.getStatus() != AsyncTask.Status.FINISHED) {
            // 앞서 실행한 작업이 아직 안끊난경우
            throw new CalendarNotYetFinishBringDataException();
        }

        task = new CalendarRequestTask(mCredential, activity, resultInterface);

        ArrayList<Object> paramsList = new ArrayList<>(Arrays.asList(params));
        paramsList.removeAll(Collections.singleton(null));
        task.execute(calendarRequestCode, paramsList);
    }

    public void createCalendar(Activity activity, CalenderResultInterface resultInterface, String calendarName) throws CalendarNetworkException, CalendarNotYetFinishBringDataException, CalendarCantNotUseException, CalendarNeedUpdateGoogleServiceException {
        requestCalendar(activity, CalendarRequestCode.REQUEST_CREATE_CALENDAR.getCode(), getAccountInfo(), resultInterface, calendarName);
    }

    public void createCalendar(Activity activity, CalenderResultInterface resultInterface) throws CalendarNetworkException, CalendarNotYetFinishBringDataException, CalendarCantNotUseException, CalendarNeedUpdateGoogleServiceException {
        requestCalendar(activity, CalendarRequestCode.REQUEST_CREATE_CALENDAR.getCode(), getAccountInfo(), resultInterface, activity.getString(R.string.app_name));
    }

    public void getEvents(Activity activity, CalenderResultInterface resultInterface, String calendarName) throws CalendarNetworkException, CalendarNotYetFinishBringDataException, CalendarCantNotUseException, CalendarNeedUpdateGoogleServiceException {
        requestCalendar(activity, CalendarRequestCode.REQUEST_GET_EVENTS.getCode(), getAccountInfo(), resultInterface, calendarName);
    }

    public void getEvents(Activity activity, CalenderResultInterface resultInterface) throws CalendarNetworkException, CalendarNotYetFinishBringDataException, CalendarCantNotUseException, CalendarNeedUpdateGoogleServiceException {
        requestCalendar(activity, CalendarRequestCode.REQUEST_GET_EVENTS.getCode(), getAccountInfo(), resultInterface, activity.getString(R.string.app_name));
    }

    public void addEvent(Activity activity, CalenderResultInterface resultInterface, String calendarName, CalendarInputEvent... events) throws CalendarNetworkException, CalendarNotYetFinishBringDataException, CalendarCantNotUseException, CalendarNeedUpdateGoogleServiceException {
        requestCalendar(activity, CalendarRequestCode.REQUEST_ADD_EVENT.getCode(), getAccountInfo(), resultInterface, calendarName, events);
    }

    public void addEvent(Activity activity, CalenderResultInterface resultInterface, CalendarInputEvent... events) throws CalendarNetworkException, CalendarNotYetFinishBringDataException, CalendarCantNotUseException, CalendarNeedUpdateGoogleServiceException {
        requestCalendar(activity, CalendarRequestCode.REQUEST_ADD_EVENT.getCode(), getAccountInfo(), resultInterface, activity.getString(R.string.app_name), events);
    }
}
