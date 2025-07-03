package com.masonlian.thejournal.service.serviceImpl;

import com.masonlian.thejournal.dao.CalendarDao;
import com.masonlian.thejournal.dto.QueryPara;
import com.masonlian.thejournal.dto.request.CalendarEventRequest;
import com.masonlian.thejournal.model.CalendarEvent;
import com.masonlian.thejournal.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.List;


@Component
public class CalendarServiceImpl implements CalendarService {

    @Autowired
    CalendarDao calendarDao;

    @Override
    public Integer createCalendarEvent(CalendarEventRequest calendarEventRequest){
        return calendarDao.createCalendarEvent(calendarEventRequest);

    }

    @Override
    public void updateCalendarEvent(Integer eventId, CalendarEventRequest calendarEventRequest){
        calendarDao.updateCalendarEvent(eventId, calendarEventRequest);
    }

    @Override
    public void deleteCalendarEventById(Integer eventId){
        calendarDao.deleteCalendarEventById(eventId);
    }

    @Override
    public CalendarEvent getCalendarEventById(Integer eventId){

        return calendarDao.getCalendarEventById(eventId);
    }

    @Override
    public List<CalendarEvent> getCalendarEventsByDate(QueryPara calendarQueryPara){

        return calendarDao.getCalendarEventsByDate(calendarQueryPara);


    }


}
