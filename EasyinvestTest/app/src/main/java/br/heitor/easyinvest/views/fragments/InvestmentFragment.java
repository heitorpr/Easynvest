package br.heitor.easyinvest.views.fragments;

import android.annotation.SuppressLint;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import br.heitor.easyinvest.R;
import br.heitor.easyinvest.model.Info;
import br.heitor.easyinvest.model.InvestmentScreen;
import br.heitor.easyinvest.model.MoreInfoObject;
import br.heitor.easyinvest.utils.FontManager;
import br.heitor.easyinvest.views.widgets.FontButtonView;
import br.heitor.easyinvest.views.widgets.FontTextView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class InvestmentFragment extends BaseFragment {
    @BindView(R.id.txt_title)
    FontTextView txtTitle;
    @BindView(R.id.txt_label_title)
    FontTextView txtLabelTitle;
    @BindView(R.id.txt_title_investment_fund)
    FontTextView txtTitleInvestmentFund;
    @BindView(R.id.txt_what_is)
    FontTextView txtWhatIs;
    @BindView(R.id.txt_what_is_explanation)
    FontTextView txtWhatIsExplanation;
    @BindView(R.id.txt_title_risk)
    FontTextView txtTitleRisk;
    @BindView(R.id.txt_title_more_information)
    FontTextView txtTitleMoreInformation;
    @BindView(R.id.linear_info)
    LinearLayout linearInfo;
    @BindView(R.id.btn_invest)
    FontButtonView btnInvest;
    @BindView(R.id.linear_risk)
    LinearLayout linearRisk;
    @BindView(R.id.linear_more_information)
    LinearLayout linearMoreInformation;
    @BindView(R.id.chart)
    LineChart chart;

    private InvestmentScreen investmentModel;

    public InvestmentFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_investment, container, false);
        unbinder = ButterKnife.bind(this, view);
        initVariables();
        return view;
    }

    @Override
    protected void initVariables() {
        super.initVariables();

        if (investmentModel == null) investmentModel = InvestmentScreen.create(getActivity());
        setViews();
    }

    private void setViews() {
        txtLabelTitle.setText(investmentModel.getTitle());
        txtTitleInvestmentFund.setText(investmentModel.getFundName());
        txtWhatIs.setText(investmentModel.getWhatIs());
        txtWhatIsExplanation.setText(investmentModel.getDefinition());
        txtTitleRisk.setText(investmentModel.getRiskTitle());
        txtTitleMoreInformation.setText(investmentModel.getInfoTitle());
        updateRisk();
        updateMoreInformation();
        updateChart();
    }

    private void updateChart() {
        chart.setNoDataTextColor(ContextCompat.getColor(ctx, R.color.color_font));
        chart.setNoDataText(getString(R.string.nodata_chart));
        chart.setNoDataTextTypeface(FontManager.getInstance().get("DinPro", FontManager.FontStyleEnum.REGULAR));
        chart.setDrawGridBackground(false);

        Paint mInfoPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mInfoPaint.setColor(ContextCompat.getColor(ctx, R.color.color_font)); // orange
        mInfoPaint.setTextAlign(Paint.Align.CENTER);
        mInfoPaint.setTextSize(getResources().getDimension(R.dimen.font_size_body));
        mInfoPaint.setTypeface(FontManager.getInstance().get("DinPro", FontManager.FontStyleEnum.REGULAR));

        chart.setPaint(mInfoPaint, LineChart.PAINT_INFO);

        List<Entry> entriesFund = new ArrayList<>();
        List<Entry> entriesCDI = new ArrayList<>();
        List<Float> fundArray = investmentModel.getGraphFund();
        List<Float> CDIArray = investmentModel.getGraphCDI();

        for (float data : fundArray) {
            entriesFund.add(new Entry(data, data));
        }

        for (float data : CDIArray) {
            entriesCDI.add(new Entry(data, data));
        }

        LineDataSet dataSetFund = new LineDataSet(entriesFund, investmentModel.getFundName());
        dataSetFund.setAxisDependency(YAxis.AxisDependency.LEFT);
        dataSetFund.setLineWidth(2f);
        dataSetFund.setFillColor(ContextCompat.getColor(ctx, R.color.blue));
        dataSetFund.setColor(ContextCompat.getColor(ctx, R.color.blue));
        dataSetFund.setDrawValues(false);
        dataSetFund.setDrawCircles(false);
        dataSetFund.setHighlightEnabled(false);

        LineDataSet dataSetCDI = new LineDataSet(entriesCDI, "CDI");
        dataSetCDI.setAxisDependency(YAxis.AxisDependency.RIGHT);
        dataSetCDI.setLineWidth(2f);
        dataSetCDI.setFillColor(ContextCompat.getColor(ctx, R.color.green));
        dataSetCDI.setColor(ContextCompat.getColor(ctx, R.color.green));
        dataSetCDI.setDrawValues(false);
        dataSetCDI.setDrawCircles(false);
        dataSetCDI.setHighlightEnabled(false);

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setTextColor(ContextCompat.getColor(ctx, R.color.grey_3));
        xAxis.setTypeface(FontManager.getInstance().get("DinPro", FontManager.FontStyleEnum.REGULAR));

        YAxis yAxis = chart.getAxisLeft();
        yAxis.setDrawAxisLine(false);
        yAxis.enableGridDashedLine(5, 10, 0);
        yAxis.setTextColor(ContextCompat.getColor(ctx, R.color.grey_3));
        yAxis.setTypeface(FontManager.getInstance().get("DinPro", FontManager.FontStyleEnum.REGULAR));

        YAxis yAxisRight = chart.getAxisRight();
        yAxisRight.setDrawAxisLine(false);
        yAxisRight.setDrawLabels(false);
        yAxisRight.setDrawGridLines(false);
        yAxisRight.setTextColor(ContextCompat.getColor(ctx, R.color.grey_3));
        yAxisRight.setTypeface(FontManager.getInstance().get("DinPro", FontManager.FontStyleEnum.REGULAR));

        LineData lineData = new LineData(dataSetCDI, dataSetFund);

        chart.setData(lineData);
        chart.getLegend().setTextColor(ContextCompat.getColor(ctx, R.color.grey_3));
        chart.getLegend().setEnabled(true);
        chart.getDescription().setEnabled(false);
        chart.setScaleYEnabled(false);
        chart.setHighlightPerTapEnabled(false);
        chart.setDoubleTapToZoomEnabled(false);
        chart.setVisibleXRangeMinimum(5);
    }

    private void updateRisk() {
        int viewIndex = investmentModel.getRisk();
        LinearLayout linearArrows = (LinearLayout) linearRisk.getChildAt(0);
        LinearLayout linearBars = (LinearLayout) linearRisk.getChildAt(1);

        viewIndex = viewIndex > 5 ? 5 : viewIndex;
        viewIndex = viewIndex - 1; //Due to view starts with zero index

        linearArrows.getChildAt(viewIndex).setVisibility(View.VISIBLE);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) linearBars.getChildAt(viewIndex).getLayoutParams();
        params.height = (int) getResources().getDimension(R.dimen.selected_bar_height);

        linearBars.getChildAt(viewIndex).setLayoutParams(params);
    }

    @SuppressLint("SetTextI18n")
    private void updateMoreInformation() {
        LinearLayout linearOnMonth = (LinearLayout) linearMoreInformation.getChildAt(1);
        LinearLayout linearOnYear = (LinearLayout) linearMoreInformation.getChildAt(2);
        LinearLayout linearAllMonths = (LinearLayout) linearMoreInformation.getChildAt(3);

        MoreInfoObject onMonth = investmentModel.getMoreInfoOnMonth();
        MoreInfoObject onYear = investmentModel.getMoreInfoOnYear();
        MoreInfoObject allMonths = investmentModel.getMoreInfoAllMonths();

        ((FontTextView) linearOnMonth.getChildAt(1)).setText(NumberFormat.getNumberInstance().format(onMonth.getFund()) + "%");
        ((FontTextView) linearOnMonth.getChildAt(2)).setText(NumberFormat.getNumberInstance().format(onMonth.getCDI()) + "%");
        ((FontTextView) linearOnYear.getChildAt(1)).setText(NumberFormat.getNumberInstance().format(onYear.getFund()) + "%");
        ((FontTextView) linearOnYear.getChildAt(2)).setText(NumberFormat.getNumberInstance().format(onYear.getCDI()) + "%");
        ((FontTextView) linearAllMonths.getChildAt(1)).setText(NumberFormat.getNumberInstance().format(allMonths.getFund()) + "%");
        ((FontTextView) linearAllMonths.getChildAt(2)).setText(NumberFormat.getNumberInstance().format(allMonths.getCDI()) + "%");

        LayoutInflater inflater = LayoutInflater.from(ctx);
        linearInfo.removeAllViews();

        List<Info> infoArray = investmentModel.getInfo();
        addInfoView(inflater, infoArray, R.layout.layout_more_info_row);

        List<Info> downInfoArray = investmentModel.getDownInfo();
        addInfoView(inflater, downInfoArray, R.layout.layout_more_info_row_download);
    }

    private void addInfoView(LayoutInflater inflater, List<Info> downInfoArray, int layout_res) {
        for (Info info : downInfoArray) {
            LinearLayout view = (LinearLayout) inflater.inflate(layout_res, linearInfo, false);

            ((FontTextView) view.getChildAt(0)).setText(info.getName());

            if (info.getData() != null)
                ((FontTextView) view.getChildAt(1)).setText(info.getData());

            linearInfo.addView(view);
        }
    }
}
