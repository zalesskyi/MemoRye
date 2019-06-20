package com.zalesskyi.android.memorye.base

import android.content.Context
import android.os.Bundle
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zalesskyi.android.memorye.R
import com.zalesskyi.android.memorye.base.adapter.BaseRecyclerViewAdapter
import com.zalesskyi.android.memorye.extensions.bindInterfaceOrThrow
import com.zalesskyi.android.memorye.extensions.hideKeyboard

/**
 *  Base fragment that implements work with the ViewModel and the life cycle.
 */
abstract class BaseLifecycleFragment<T : BaseLifecycleVM> : Fragment(),
    BaseView,
    BackPressable {

    /**
     * Set the version name for version control.
     */
    protected abstract var versionName: String

    /**
     * Set the server endpoint.
     */
    protected abstract var endpoint: String

    /**
     * Set the Java-class ViewModel.
     */
    abstract val viewModelClass: Class<T>

    private val textWatchers: Map<EditText?, TextWatcher> = mutableMapOf()

    protected open val viewModel: T by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProviders.of(this).get(viewModelClass)
    }

    /**
     * Set id of layout.
     */
    protected abstract val layoutId: Int

    protected var toolbar: Toolbar? = null

    private var baseView: BaseView? = null

    private var backPressedCallback: BackPressedCallback? = null

    private var bottomNavigationCallback: BottomNavigationCallback? = null

    protected open val progressObserver = Observer<Boolean> {
        if (it == true) showProgress() else hideProgress()
    }

    protected open val errorObserver = Observer<Any> { error ->
        error?.let { processError(it) }
    }

    /**
     * In the method need to subscribe to the LiveData from the [viewModel].
     */
    abstract fun observeLiveData(viewModel: T)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        baseView = bindInterfaceOrThrow<BaseView>(parentFragment, context)
        backPressedCallback = bindInterfaceOrThrow<BackPressedCallback>(parentFragment, context)
        bottomNavigationCallback = bindInterfaceOrThrow<BottomNavigationCallback>(parentFragment, context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeAllLiveData()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(layoutId, container, false)
        hideKeyboard(view)
        invalidateVersionsInfo(view)
        return view
    }

    override fun onResume() {
        super.onResume()
        initToolbar()
        view?.let { hideKeyboard(it) }
        bottomNavigationCallback?.hasBottomNavigation(hasBottomNavigation())
    }

    override fun onDetach() {
        baseView = null
        backPressedCallback = null
        bottomNavigationCallback = null
        super.onDetach()
    }

    /**
     * Extension for adding [TextWatcher] to [EditText] which will be cleared when fragment is destroyed.
     */
    fun EditText.addTextWatcher(watcher: TextWatcher) = apply {
        textWatchers.plus(this to watcher)
        addTextChangedListener(watcher)
    }

    /**
     * Pop the top state off the back stack.
     */
    fun backPressed() {
        backPressedCallback?.backPressed()
    }

    override fun onDestroyView() {
        textWatchers.forEach { (key, value) -> key?.removeTextChangedListener(value) }
        super.onDestroyView()
    }

    /**
     * Called when need to show progress view.
     */
    override fun showProgress() {
        baseView?.showProgress()
    }

    /**
     * Called when need to hide progress view.
     */
    override fun hideProgress() {
        baseView?.hideProgress()
    }

    /**
     * Show SnackBar with [StringRes]
     *
     * @param res Id of StringRes text to show
     */
    override fun showSnackBar(@StringRes res: Int) {
        baseView?.showSnackBar(view, res)
    }

    /**
     * Show SnackBar with [String]
     *
     * @param text String text to show
     */
    override fun showSnackBar(text: String?) {
        baseView?.showSnackBar(view, text)
    }

    /**
     * Show SnackBar with [StringRes]
     *
     * @param res Id of [StringRes] to show
     * @param rootView Instance of [View]
     */
    override fun showSnackBar(rootView: View?, @StringRes res: Int) {
        baseView?.showSnackBar(rootView, getString(res))
    }

    /**
     * Show SnackBar with [String]
     *
     * @param text [String] text to show
     * @param rootView Instance of [View]
     */
    override fun showSnackBar(rootView: View?, text: String?) {
        baseView?.showSnackBar(rootView, text)
    }

    /**
     * Show SnackBar with [StringRes] and function for execution
     *
     * @param actionFun Function for execution
     * @param text      Id of StringRes text to show
     */
    override fun showSnackBar(@StringRes text: Int, @StringRes buttonText: Int, actionFun: () -> Unit, duration: Int) {
        baseView?.showSnackBar(text, buttonText, actionFun)
    }

    /**
     * Error handler
     *
     * @param error object [Any] which describe error
     */
    override fun onError(error: Any) {
        baseView?.onError(error)
    }

    protected open fun hasBottomNavigation() = false

    protected open fun processError(error: Any) = onError(error)

    open fun showAlert(message: String,
                       title: String? = null,
                       cancelable: Boolean = true,
                       positiveRes: Int = R.string.ok,
                       positiveFun: () -> Unit = {},
                       negativeRes: Int? = R.string.no,
                       negativeFun: () -> Unit = {}) {
        context?.let { ctx ->
            AlertDialog.Builder(ctx).apply {
                setMessage(message)
                setCancelable(cancelable)
                title?.let { setTitle(it) }
                setPositiveButton(positiveRes) { _, _ -> positiveFun() }
                negativeRes?.let { setNegativeButton(it) { _, _ -> negativeFun() } }
                show()
            }
        }
    }

    open fun showAlert(message: Int,
                       title: Int? = null,
                       cancelable: Boolean = true,
                       positiveRes: Int = R.string.ok,
                       positiveFun: () -> Unit = {},
                       negativeRes: Int? = null,
                       negativeFun: () -> Unit = {}) {
        context?.let { ctx ->
            AlertDialog.Builder(ctx).apply {
                setMessage(message)
                setCancelable(cancelable)
                title?.let { setTitle(it) }
                setPositiveButton(positiveRes) { _, _ -> positiveFun() }
                negativeRes?.let { setNegativeButton(it) { _, _ -> negativeFun() } }
                show()
            }
        }
    }

    /**
     * Set if fragment has version control.
     */
    protected open fun hasVersions() = false

    /**
     * Set versions layout id
     *
     * @return Id of versions layout
     */
    @IdRes
    protected abstract fun getVersionsLayoutId(): Int

    /**
     * Set versions [TextView] id
     *
     * @return Id of versions TextView
     */
    @IdRes
    protected abstract fun getVersionsTextViewId(): Int

    /**
     * Set debug mode
     *
     * @return Application in debug mode or not
     */
    protected abstract fun isDebug(): Boolean

    /**
     * Set EndPoint [TextView] id
     *
     * @return Id of EndPoint TextView
     */
    @IdRes
    protected abstract fun getEndPointTextViewId(): Int

    /**
     * If in debug build and fragment has versionLayoutId
     * Display versions info
     *
     * @param view Of versions screen
     */
    protected fun invalidateVersionsInfo(view: View) {
        if (hasVersions() && getVersionsLayoutId() != -1) {
            val versionsLayout = view.findViewById<View>(getVersionsLayoutId())
            if (isDebug()) {
                versionsLayout.visibility = View.VISIBLE
                (view.findViewById<TextView>(getVersionsTextViewId())).text = String.format("version: %s", versionName)
                (view.findViewById<TextView>(getEndPointTextViewId())).text = String.format("Endpoint: %s", endpoint)
            } else {
                versionsLayout.visibility = View.GONE
            }
        }
    }

    /**
     * Set id of screen title
     *
     * @return Id of screen title
     */
    @StringRes
    protected abstract fun getScreenTitle(): Int

    /**
     * Set if fragment has toolbar
     *
     * @return True if fragment has toolbar
     * False if fragment without toolbar
     */
    protected abstract fun hasToolbar(): Boolean

    /**
     * Set id of toolbar
     *
     * @return Toolbar id
     */
    @IdRes
    protected abstract fun getToolbarId(): Int

    /**
     * Initialize toolbar
     */
    protected fun initToolbar() {
        view?.apply {
            if (hasToolbar() && getToolbarId() != NO_TOOLBAR) {
                toolbar = findViewById(getToolbarId())
                with(activity as AppCompatActivity) {
                    setSupportActionBar(toolbar)
                    supportActionBar?.let {
                        setupActionBar(it)
                        backNavigationIcon()?.let { toolbar?.setNavigationIcon(it) }
                        if (needToShowBackNav()) {
                            toolbar?.setNavigationOnClickListener { handleNavigation() }
                        }
                    }
                }
            }
        }
    }

    protected open fun handleNavigation() {
        backPressedCallback?.backPressed()
    }

    protected open fun blockBackAction(): Boolean = false

    protected abstract fun showBlockBackAlert()

    override fun onBackPressed(): Boolean =
            when (blockBackAction()) {
                true -> {
                    showBlockBackAlert()
                    blockBackAction()
                }
                else -> blockBackAction()
            }

    protected open fun backNavigationIcon(): Int? = null

    /**
     * Setup action bar
     *
     * @param actionBar Modified action bar
     */
    protected fun setupActionBar(actionBar: ActionBar) {
        actionBar.apply {
            title = getStringScreenTitle()
            setDisplayHomeAsUpEnabled(needToShowBackNav())
        }
    }

    /**
     * Set if need to show back navigation in toolbar
     *
     * @return True if toolbar has back navigation
     * False if toolbar without back navigation
     */
    protected open fun needToShowBackNav() = true

    /**
     * Set [String] screen title
     *
     * @return Screen title
     */
    protected open fun getStringScreenTitle() =
            if (getScreenTitle() != NO_TITLE) getString(getScreenTitle()) else ""

    private fun observeAllLiveData() {
        observeLiveData(viewModel)
        with(viewModel) {
            isLoadingLD.observe(this@BaseLifecycleFragment, progressObserver)
            errorLD.observe(this@BaseLifecycleFragment, errorObserver)
        }
    }

    protected fun <T : BaseRecyclerViewAdapter<*, *>>
            RecyclerView.initRecyclerView(adapter: T?,
                                          @RecyclerView.Orientation orientation: Int = RecyclerView.VERTICAL,
                                          @DrawableRes dividerDrawable: Int? = null, isShowDivider: Boolean = false) {
        layoutManager = LinearLayoutManager(context, orientation, false)
        if (isShowDivider) {
            ContextCompat.getDrawable(context, dividerDrawable ?: R.drawable.divider_item)?.let {
                val dividerItemDecoration = DividerItemDecorator(it)
                addItemDecoration(dividerItemDecoration)
            }
        }
        this.adapter = adapter
    }

    protected inline fun <reified T> FragmentManager.invokeInterfaceIfExist(block: (item: T) -> Unit) {
        fragments.forEach { if (it is T) block(it) }
    }
}