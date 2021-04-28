package com.jaydip.mvvmdemo.ui.orderlist

import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jaydip.mvvmdemo.R
import com.jaydip.mvvmdemo.api.response.OrderModel
import com.jaydip.mvvmdemo.databinding.OrderListFragmentBinding
import com.jaydip.mvvmdemo.ui.base.BaseFragment
import com.jaydip.mvvmdemo.util.AppConstant
import com.jaydip.mvvmdemo.util.Status
import com.jaydip.mvvmdemo.util.isNetworkAvailable
import com.jaydip.mvvmdemo.view.NpaLinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home_order_list.*
import org.koin.android.viewmodel.ext.android.viewModel

class OrderListFragment(private val isCompletedOrder: Boolean) :
    BaseFragment<OrderListFragmentBinding,
            OrderListViewModel>(R.layout.fragment_home_order_list),
    OrderListAdapter.OrderItemClickListener {

    private lateinit var mOrderListAdapter: OrderListAdapter
    private var orderList: MutableList<OrderModel> = mutableListOf()

    /*Pagination*/
    private var currentPage = 0
    private var isLastPage = false
    private val perPageCount = 10
    private var totalVisibleItemCount = 2
    private var isLoading = false
    private var isApiCalling = false

    private val navController: NavController by lazy {
        actMainNavHostContainer.findNavController()
    }

    //    private val sharedViewModel: SharedViewModel by activityViewModels()
    override fun initializeViews() {
        super.initializeViews()
        lifecycle.addObserver(viewModel)
        resetPagination()
        initPullToRefreshList()
        initOrderList()
        setupObserver()
        isApiCalling = true
        getOrderListData(true)
        binding.frgOrderRvOrder.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager: LinearLayoutManager =
                    recyclerView.layoutManager as LinearLayoutManager
                val firstVisiblePosition =
                    layoutManager.findFirstCompletelyVisibleItemPosition()
                val lastVisiblePosition = layoutManager.findLastCompletelyVisibleItemPosition()
                if (lastVisiblePosition == (currentPage) * perPageCount - 1) {
                    totalVisibleItemCount = lastVisiblePosition - firstVisiblePosition
                    if (!isLoading && !isLastPage && !isApiCalling) {
                        frgHomeLoadMoreProgressBar.visibility = View.VISIBLE
                        isApiCalling = true
                        getOrderListData(false)
                    }
                }
            }
        })

    }

    private fun resetPagination() {
//        orderList.clear()
//        if (this::mOrderListAdapter.isInitialized) {
//            frgOrderRvOrder.recycledViewPool.clear()
//            mOrderListAdapter.notifyDataSetChanged()
//        }
        currentPage = 0
        isLastPage = false
        totalVisibleItemCount = 2
        isLoading = false
        isApiCalling = false
    }

    private fun initPullToRefreshList() {
        context?.let {
            ContextCompat.getColor(
                it, R.color.colorWhite
            )
        }?.let { frgOrderSwipeToRefresh.setProgressBackgroundColorSchemeColor(it) }
        frgOrderSwipeToRefresh.setColorSchemeColors(
            ContextCompat.getColor(
                frgOrderSwipeToRefresh.context, R.color.colorPrimary
            )
        )

        frgOrderSwipeToRefresh.setOnRefreshListener {
//            frgSlPagingProgressView.visibility = View.GONE
            if (isNetworkAvailable(requireContext())) {
                if (!isApiCalling) {
                    resetPagination()
                    frgOrderSwipeToRefresh.isRefreshing = true
                    isApiCalling = true
                    getOrderListData(false)
                }
            } else {
                toast(getString(R.string.no_internet_connection), Toast.LENGTH_SHORT)
                frgOrderSwipeToRefresh.isRefreshing = false
            }
        }

    }

    private fun getOrderListData(booleanIsProgressRequired: Boolean) {
        if (isNetworkAvailable(requireContext())) {
            viewModel.getOrderListAPI(
                currentPage,
                perPageCount,
                isCompletedOrder,
                booleanIsProgressRequired
            )
        } else {
            frgHomeLoadMoreProgressBar.visibility = View.GONE
            frgOrderSwipeToRefresh.isRefreshing = false
            toast(getString(R.string.no_internet_connection), Toast.LENGTH_SHORT)
        }
    }

    private fun initOrderList() {
        val layoutManager = NpaLinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL, false
        )
        mOrderListAdapter =
            OrderListAdapter(this, orderList, isCompletedOrder)
        frgOrderRvOrder.layoutManager = layoutManager
        frgOrderRvOrder.adapter = mOrderListAdapter
    }

    private fun setupObserver() {
        if (viewModel.orderList.hasActiveObservers()) {
            viewModel.orderList.removeObservers(this)
        }
        viewModel.orderList.observe(this, Observer {
            when (it.status) {
                Status.LOADING -> {
                    if (isLoading) {
                        frgHomeLoadMoreProgressBar.visibility = View.VISIBLE
                    } else {
                        frgHomeOrderListClProgressView.visibility = View.VISIBLE
                    }
                }
                Status.ERROR -> {
                    isApiCalling = false
                    frgHomeOrderListClProgressView.visibility = View.GONE
                    frgHomeLoadMoreProgressBar.visibility = View.GONE
                    frgOrderSwipeToRefresh.isRefreshing = false
                    checkAdapterIsEmpty()
                }
                Status.SUCCESS -> {
                    frgHomeOrderListClProgressView.visibility = View.GONE
                    frgHomeLoadMoreProgressBar.visibility = View.GONE
                    frgOrderSwipeToRefresh.isRefreshing = false
                    it.data?.let { orderData ->
                        if (orderData.status == AppConstant.RESPONSE_200) {
                            isApiCalling = false
                            it.data.data.orderList.let { it1 ->

                                if (it1.isNotEmpty()) {
                                    if (currentPage == 0) {
                                        orderList.clear()
                                        frgOrderRvOrder.recycledViewPool.clear()
                                        mOrderListAdapter.notifyDataSetChanged()
                                    }
                                    currentPage += 1
                                    isLoading = false
                                    val previousListSize: Int = orderList.size
                                    orderList.addAll(it1)
                                    println("orderList=" + orderList.size)
                                    frgOrderRvOrder.recycledViewPool.clear()
                                    mOrderListAdapter.notifyDataSetChanged()
                                    frgHomeOrderListClProgressView.visibility = View.GONE
                                    if (currentPage != 0) {
                                        binding.frgOrderRvOrder.scrollToPosition(
                                            previousListSize - totalVisibleItemCount
                                        )
                                    }
                                } else {
                                    isLoading = false
                                    isLastPage = true
                                }
                            }
                        }
                        checkAdapterIsEmpty()
                    }!!
                }
            }
        })
    }

    private fun checkAdapterIsEmpty() {
        if (orderList.size > 0) {
            frgOrderRvOrder.visibility = View.VISIBLE
            frgHomeOrderListTvNoOrder.visibility = View.GONE
        } else {
            frgOrderRvOrder.visibility = View.GONE
            frgHomeOrderListTvNoOrder.visibility = View.VISIBLE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.onCleared()
    }

    override val viewModel: OrderListViewModel by viewModel()

    override fun onItemClick(orderModel: OrderModel, position: Int) {
//        val bundleOutlet = Bundle()
//        bundleOutlet.putString("arg_order_id", orderModel.ord_id.toString())
//        bundleOutlet.putBoolean("arg_is_completed_order", isCompletedOrder)
//        requireView().findNavController()
//            .navigate(
//                R.id.action_nav_home_to_orderDetailFragment,
//                bundleOutlet
//            )
    }
}