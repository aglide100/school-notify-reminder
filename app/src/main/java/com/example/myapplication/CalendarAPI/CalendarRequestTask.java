package com.example.myapplication.CalendarAPI;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.AsyncTask;

import com.example.myapplication.CalendarAPI.Interfaces.CalenderResultInterface;
import com.example.myapplication.CalendarAPI.Models.CalendarActivityRequestCode;
import com.example.myapplication.CalendarAPI.Models.CalendarInputEvent;
import com.example.myapplication.CalendarAPI.Models.CalendarRequestCode;
import com.example.myapplication.CalendarAPI.Models.CalendarResponseData;
import com.example.myapplication.CalendarAPI.Models.CalendarTaskResponse;
import com.example.myapplication.R;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.googleapis.extensions.android.gms.auth.UserRecoverableAuthIOException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.CalendarList;
import com.google.api.services.calendar.model.CalendarListEntry;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.Events;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

@SuppressWarnings("deprecation")
@SuppressLint("StaticFieldLeak")
public class CalendarRequestTask extends AsyncTask<Object, Void, CalendarResponseData> {
    private final Calendar calendarService;
    private final Activity activity;
    private final CalenderResultInterface resultInterface;

    public CalendarRequestTask(GoogleAccountCredential credential, Activity activity, CalenderResultInterface resultInterface) {
        this.activity = activity;
        this.resultInterface = resultInterface;

        HttpTransport transport = AndroidHttp.newCompatibleTransport();
        JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();

        calendarService = new Calendar
                .Builder(transport, jsonFactory, credential)
                .setApplicationName("Google Calendar API Android")
                .build();

        CalendarAPI.getInstance().setEmptyInterface();
    }

    private String getCalendarID(String calendarTitle) throws UserRecoverableAuthIOException {
        String id = null;

        String pageToken = null;
        do {
            CalendarList calendarList = null;
            try {
                calendarList = calendarService.calendarList().list().setPageToken(pageToken).execute();
            } catch (UserRecoverableAuthIOException e) {
                throw e;
            } catch (IOException e) {
                e.printStackTrace();
            }

            assert calendarList != null;
            List<CalendarListEntry> items = calendarList.getItems();

            for (CalendarListEntry calendarListEntry : items) {
                if ( calendarListEntry.getSummary().equals(calendarTitle)) {
                    id = calendarListEntry.getId();
                }
            }
            pageToken = calendarList.getNextPageToken();
        } while (pageToken != null);

        return id;
    }

    private String createCalendar(String calendarTitle) {
        String result = null;
        try {
            com.google.api.services.calendar.model.Calendar calendar = new com.google.api.services.calendar.model.Calendar();
            // 캘린더의 제목 설정
            calendar.setSummary(calendarTitle);
            // 캘린더의 시간대 설정
            calendar.setTimeZone("Asia/Seoul");

            // 구글 캘린더에 새로 만든 캘린더를 추가
            com.google.api.services.calendar.model.Calendar createdCalendar = calendarService.calendars().insert(calendar).execute();

            result = createdCalendar.getId();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private ArrayList<Event> getEvents(String calendarName) {
        ArrayList<Event> result = new ArrayList<>();
        try {
            String calendarID = getCalendarID(calendarName);
            if (calendarID == null) {
                createCalendar(calendarName);
                return result;
            } else {
                Events events = calendarService.events().list(calendarID)
                        .setMaxResults(10)
                        .setOrderBy("startTime")
                        .setSingleEvents(true)
                        .execute();

                List<Event> items = events.getItems();
                result = new ArrayList<>(items);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private Event addEvents(String calendarName, CalendarInputEvent inputEvent) {
        Event event = null;
        try {
            String calendarID = getCalendarID(calendarName);
            if (calendarID == null) {
                calendarID = createCalendar(calendarName);
            }

            event = new Event().setSummary(inputEvent.getTitle());
            if (inputEvent.getLocation() != null) {
                event.setLocation(inputEvent.getLocation());
            }
            if (inputEvent.getDescription() != null) {
                event.setDescription(inputEvent.getDescription());
            }

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss+09:00", Locale.KOREA);
            if (inputEvent.getEndTime() != null) {
                String formattedDate = formatter.format(inputEvent.getEndTime());
                DateTime dateTime = new DateTime(formattedDate);
                EventDateTime eventDateTime = new EventDateTime();
                eventDateTime.setDateTime(dateTime)
                        .setTimeZone("Asia/Seoul");

                event.setEnd(eventDateTime);
            }
            if (inputEvent.getStartTime() != null) {
                String formattedDate = formatter.format(inputEvent.getStartTime());
                DateTime dateTime = new DateTime(formattedDate);
                EventDateTime eventDateTime = new EventDateTime();
                eventDateTime.setDateTime(dateTime)
                        .setTimeZone("Asia/Seoul");

                event.setStart(eventDateTime);
            }
            event = calendarService.events().insert(calendarID, event).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return event;
    }

    @SuppressWarnings({"unchecked"})
    @Override
    protected CalendarResponseData doInBackground(Object... params) {
        CalendarResponseData responseData = new CalendarResponseData();
        CalendarRequestCode requestCode = (CalendarRequestCode) params[0];
        responseData.setRequestCode(requestCode);

        ArrayList<Object> paramsList = (ArrayList<Object>) params[1];
        paramsList.removeAll(Collections.singleton(null));

        try {
            String calendarName = null;
            if (paramsList.size() > 0) {
                calendarName = (String) paramsList.get(0);
            }
            if (calendarName == null) {
                calendarName = activity.getString(R.string.app_name);
            }
            String calendarID = getCalendarID(calendarName);
            switch (requestCode) {
                case REQUEST_GET_CALENDAR_ID:
                    if (calendarID == null) {
                        responseData.setResponse(CalendarTaskResponse.RESULT_FAILED);
                    } else {
                        responseData.setData(calendarID);
                        responseData.setResponse(CalendarTaskResponse.RESULT_OK);
                    }
                    break;
                case REQUEST_CREATE_CALENDAR:
                    if (calendarID == null) {
                        calendarID = createCalendar(calendarName);
                        responseData.setResponse(CalendarTaskResponse.RESULT_OK);
                    } else {
                        responseData.setResponse(CalendarTaskResponse.RESULT_ALREADY_EXIST);
                    }
                    responseData.setData(calendarID);
                    break;
                case REQUEST_GET_EVENTS:
                    responseData.setResponse(CalendarTaskResponse.RESULT_OK);
                    responseData.setData(getEvents(calendarName));
                    break;
                case REQUEST_ADD_EVENT:
                    ArrayList<Event> result = new ArrayList<>();
                    if (paramsList.size() > 1) {
                        Object[] items = (Object[]) paramsList.get(1);
                        ArrayList<Object> inputList = new ArrayList<>(Arrays.asList(items));
                        inputList.removeAll(Collections.singleton(null));
                        for (int index = 0; index < inputList.size(); index++) {
                            CalendarInputEvent inputEvent = (CalendarInputEvent) inputList.get(index);
                            Event event = addEvents(calendarName, inputEvent);
                            if (event != null) {
                                result.add(event);
                            }
                        }
                    }
                    responseData.setResponse(result.size() > 0 ? CalendarTaskResponse.RESULT_OK : CalendarTaskResponse.RESULT_FAILED);
                    responseData.setData(result);
                    break;
            }
        } catch (UserRecoverableAuthIOException e) {
            CalendarAPI.getInstance().setInterface(requestCode, activity, resultInterface, paramsList.toArray());
            activity.startActivityForResult(e.getIntent(), CalendarActivityRequestCode.REQUEST_AUTHORIZATION.getCode());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return responseData;
    }

    @Override
    protected void onPostExecute(CalendarResponseData responseData) {
        super.onPostExecute(responseData);

        if (responseData.getActivityRequestCode() != null) {
            resultInterface.failedWithActivityResult(responseData.getActivityRequestCode());
        } else {
            resultInterface.getResult(responseData);
        }
    }
}