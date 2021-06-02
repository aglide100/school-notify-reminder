package com.example.myapplication.View.Fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

//  ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 여기 리사이클러뷰
   public class MyActivity extends Activity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<MyData> myDataset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_planlist);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        myDataset = new ArrayList<>();
        mAdapter = new MyAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);

        myDataset.add((new MyData("테스트용", R.mipmap.ic_launcher)));
         }

    }
//  viewHolder는 사용된 뷰 객체를 기억하는  객체?
//  어댑터는 여러 아이템을 리사이클러 뷰에 바인딩 시켜주는 작업?
    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private ArrayList<MyData> mDataset;

        public class ViewHolder extends RecyclerView.ViewHolder {
            public ImageView mImageView;
            public TextView mTextView;

            public ViewHolder(View view) {
                super(view);
                mImageView = (ImageView)view.findViewById(R.id.image);
                mTextView = (TextView)view.findViewById(R.id.textView1);
            }
        }

        public MyAdapter(ArrayList<MyData> myDataset) {
            myDataset = myDataset;
        }

       @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_planlist_cardview, parent, false);

            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int postion) {
            holder.mTextView.setText(mDataset.get(postion).text);
            holder.mImageView.setImageResource(mDataset.get(postion).img);
        }

        @Override
        public int getItemCount() {
            return mDataset.size();
        }
    }

    class MyData{
        public String text;
        public int img;
        public MyData(String text, int img){
            this.text = text;
            this.img = img;
        }
    }

    }


