package com.example.myapplication.View.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.myapplication.Model.MainModel;
import com.example.myapplication.Model.Plan;
import com.example.myapplication.Presenter.Contract;
import com.example.myapplication.Presenter.MainPresenter;
import com.example.myapplication.R;
import com.example.myapplication.View.Basic.BasicFragment;

import java.util.ArrayList;

// 현재 가지고 있는 플랜 리스트를 보여주는 프래그먼트
// 플랜을 선택시 아이템 리스트 액티비티를 불러옴

//  Fragment를 상속하며 Contract.View를 구현하는 BasicFragment를 상속
public class PlanListFragment extends BasicFragment {

    private Context mContext;
    private Contract.Presenter presenter;
    private ArrayList<Plan> planList;
    private MainModel mainModel;

    private TextView textView;

    //    프래그먼트가 아직 첨부되기 전이라 액티비티를 받아오기 위해서 onAttach를 오버라이딩 해야됨
    //    프래그먼트가 onAttch되는 과정에서 context를 받아옴
    //    context를 안전하게 사용하기 위한 방법?
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        return inflater.inflate(R.layout.fragment_planlist, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//      presenter 객체 생성달
        presenter = new MainPresenter(this);
//      planList을 가져오기 위해 MainModel을 생성
        mainModel = new MainModel(presenter);

//      planList를 가져옴. plan은 이름, ID를 가지고 있음
        planList = mainModel.getPlans();

        textView = view.findViewById(R.id.planName);
        textView.setText(planList.get(1).getPlanName());

//       플랜 리스트중 특정 플랜 선택시 아이템 리스트 액티비티 호출!
//        인탠트로 Plan객체를 itemListActivity로 전
    }
}
