package com.example.chloechoi.cctvparkingcontrolproject.main.Fragment.history;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chloechoi.cctvparkingcontrolproject.list.HistoryAdapter;
import com.example.chloechoi.cctvparkingcontrolproject.item.History;
import com.example.chloechoi.cctvparkingcontrolproject.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by chloechoi on 28/11/2018.
 */

public class HistoryFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private HistoryAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<History> mHistoryData;
    private MaterialCalendarView materialCalendarView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history,container, false);

        ArrayList<String> arrayList;
        //ArrayList<DayData> Day_data;

        mRecyclerView = (RecyclerView) view.findViewById(R.id.history_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.scrollToPosition(0);

        materialCalendarView = (MaterialCalendarView) view.findViewById(R.id.history_calendarView);
        materialCalendarView.state().edit()
                .setFirstDayOfWeek(Calendar.SUNDAY)
                .setMinimumDate(CalendarDay.from(2017, 0, 1))
                .setMaximumDate(CalendarDay.from(2030, 11, 31))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();//View);

        materialCalendarView.setOnDateChangedListener
                (new OnDateSelectedListener(){
                    @Override
                    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                        /*TODO send request to server and get the datas*/
                        CalendarDay selectedDay = null;
                        String DATE;
                        int year;
                        int month;
                        int day;

                        selectedDay = date;
                        DATE = selectedDay.toString();
                        String[] parsedDATA = DATE.split("[{]");
                        parsedDATA = parsedDATA[1].split("[}]");
                        parsedDATA = parsedDATA[0].split("-");
                        year = Integer.parseInt(parsedDATA[0]);
                        month = Integer.parseInt(parsedDATA[1])+1;
                        day = Integer.parseInt(parsedDATA[2]);

//                        arrayList = new ArrayList<String>();
//                        for(int i=0; i<Day_data.size(); i++){
//                            if(Day_data.get(i).getDay() == day){
//                                arrayList.add(Day_data.get(i).getText_schedule());
//                            }
//                        }
                        //updateScheduleList();

                        /*TODO
                        * get data from server by using above variables
                        * and push that into mHistoryData ArrayList*/

                        mAdapter = new HistoryAdapter(mHistoryData, getActivity());
                        mRecyclerView.setAdapter(mAdapter);
                        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

                        mRecyclerView.addOnItemTouchListener(
                                new HistoryRecyclerItemClickListener(getActivity(), mRecyclerView, new HistoryRecyclerItemClickListener.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(View view, int position) {
                                        /*TODO
                                        * 주차기록 디테일 뷰로 이동
                                        * 다은이랑 어떻게 띄울지 서버 고려해서 결정해야함*/
                                    }

                                    @Override
                                    public void onLongItemClick(View view, int position) {

                                    }
                                })
                        );
                    }
                }
        );
        /*TODO reference
        * http://dpdpwl.tistory.com/3#recentComments
        * https://hashcode.co.kr/questions/4935/android-material-calendar%EB%A5%BC-%EC%9D%B4%EC%9A%A9%ED%95%9C-listview-%EC%9D%BC%EC%A0%95-%EC%B6%94%EA%B0%80*/

        return view;
    }

//    public void updateScheduleList(){
//        adapter= new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,arrayList);
//        schedule_List.setAdapter(adapter);
//    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
